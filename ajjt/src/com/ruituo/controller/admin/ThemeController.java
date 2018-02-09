package com.ruituo.controller.admin;

import java.util.Date;
import java.util.List;

import com.jfinal.aop.Before;
import com.ruituo.common.BaseController;
import com.ruituo.common.Constant;
import com.ruituo.interceptor.LoginInterceptor;
import com.ruituo.model.Column;
import com.ruituo.model.Theme;
import com.ruituo.util.PageBean;
import com.ruituo.util.PageBeanUtil;

@Before(LoginInterceptor.class)
public class ThemeController extends BaseController {

	/**
	 * 获得主题列表
	 */
	public void getThemeList(){
		getData();
		int nowPage = 1;	//当前页数
		if(getPara("pageNum")!=null){
			nowPage=Integer.parseInt(getPara("pageNum"));
		}
		
		Integer columnId = 0;
		if(getParaToInt("columnId") != null){
			columnId = getParaToInt("columnId");
		}
		
		int pageSize = Constant.EACH_PAGE_PAGESIZE;
		
		int allCount = Theme.dao.countThemeList(columnId);
		String pageName = "/theme/getThemeList";
		
		String parameters = "&columnId="+columnId;
		if(getPara("parameters")!= null){
			parameters = getPara("parameters");
		}
		
		List<Theme> themeList = Theme.dao.getThemeList(columnId,PageBeanUtil.getStartRecord(nowPage,pageSize),pageSize);
		PageBean pageBean=PageBeanUtil.wrapperToPageBean(nowPage,pageSize,allCount);
		List<Column> twoThemeColList = Column.dao.getColumnByType(Constant.COLUMN_TYPE_THEME);
		
		setAttr("twoThemeColList", twoThemeColList);
		setAttr("parameters", parameters);
		setAttr("pageName", pageName);
		setAttr("pageBean", pageBean);
		setAttr("nowPage", nowPage);
		setAttr("themeList", themeList);
		setAttr("columnId", columnId);
		render("/admin/html/theme/themelist.html");
	}
	
	/**
	 * 添加主题
	 */
	public void addTheme(){
		getData();
		List<Column> twoThemeColList = Column.dao.getColumnByType(Constant.COLUMN_TYPE_THEME);
		setAttr("twoThemeColList", twoThemeColList);
		render("/admin/html/theme/addtheme.html");
	}
	
	/**
	 * 保存主题
	 */
	public void saveTheme(){
		String themeTile = getPara("themeTile");
		Integer columnId = getParaToInt("columnId");
		String testimg = getPara("testimg");
		String context = getPara("context");
		Theme theme = new Theme();
		theme.set("theme_title", themeTile);
		theme.set("columnid", columnId);
		theme.set("theme_img", testimg);
		theme.set("theme_context", context);
		theme.set("addtime", new Date());
		if(theme.save()){
			renderJson(1);
		}else{
			renderJson(2);
		}
	}
	
	/**
	 * 修改主题
	 */
	public void upTheme(){
		getData();
		Integer id = getParaToInt("id");
		Theme theme = Theme.dao.findById(id); 
		List<Column> twoThemeColList = Column.dao.getColumnByType(Constant.COLUMN_TYPE_THEME);
		setAttr("twoThemeColList", twoThemeColList);
		setAttr("theme", theme);
		render("/admin/html/theme/uptheme.html");
	}
	
	/**
	 * 保存主题
	 */
	public void saveUpTheme(){
		String themeTile = getPara("themeTile");
		Integer columnId = getParaToInt("columnId");
		Integer id = getParaToInt("themeId");
		String testimg = getPara("testimg");
		String context = getPara("context");
		Theme theme = Theme.dao.findById(id);
		theme.set("theme_title", themeTile);
		theme.set("columnid", columnId);
		theme.set("theme_img", testimg);
		theme.set("theme_context", context);
		if(theme.update()){
			renderJson(1);
		}else{
			renderJson(2);
		}
	}
	
	/**
	 * 删除主题
	 */
	public void delTheme(){
		Integer id = getParaToInt("id");
		Theme theme = Theme.dao.findById(id);
		if(theme.delete()){
			renderJson(1);
		}else{
			renderJson(2);
		}
	}
	
}
