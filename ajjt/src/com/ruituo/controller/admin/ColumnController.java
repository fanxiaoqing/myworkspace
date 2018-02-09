package com.ruituo.controller.admin;

import java.util.Date;
import java.util.List;

import com.jfinal.aop.Before;
import com.ruituo.common.BaseController;
import com.ruituo.common.Constant;
import com.ruituo.interceptor.LoginInterceptor;
import com.ruituo.model.Column;
import com.ruituo.util.PageBean;
import com.ruituo.util.PageBeanUtil;

@Before(LoginInterceptor.class)
public class ColumnController extends BaseController {

	/**
	 * 一级栏目列表
	 */
	public void getOneColumnList(){
		getData();
		int nowPage = 1;	//当前页数
		if(getPara("pageNum")!=null){
			nowPage=Integer.parseInt(getPara("pageNum"));
		}
		int pageSize = Constant.EACH_PAGE_PAGESIZE;
		
		int allCount = Column.dao.countColumnList();
		String pageName = "/column/getOneColumnList";
		
		String parameters = "";
		if(getPara("parameters")!= null){
			parameters = getPara("parameters");
		}
		List<Column> oneList = Column.dao.getColumnList(PageBeanUtil.getStartRecord(nowPage,pageSize),pageSize);
		PageBean pageBean=PageBeanUtil.wrapperToPageBean(nowPage,pageSize,allCount);
		
		setAttr("parameters", parameters);
		setAttr("pageName", pageName);
		setAttr("pageBean", pageBean);
		setAttr("oneList", oneList);
		render("/admin/html/column/onecolumn.html");
	}
	
	/**
	 * 二级栏目列表
	 */
	public void getTwoColumnList(){
		getData();
		int nowPage = 1;	//当前页数
		if(getPara("pageNum")!=null){
			nowPage=Integer.parseInt(getPara("pageNum"));
		}
		int pageSize = Constant.EACH_PAGE_PAGESIZE;
		String uppid = getPara("uppid");
		if(getParaToInt("uppid") != null){
			uppid = getPara("uppid");
		}
		
		int allCount = Column.dao.countTwoColumnList(uppid);
		String pageName = "/column/getTwoColumnList";
		
		String parameters = "&uppid="+uppid;
		if(getPara("parameters")!= null){
			parameters = getPara("parameters");
		}
		List<Column> twoList = Column.dao.getTwoColumnList(uppid,PageBeanUtil.getStartRecord(nowPage,pageSize),pageSize);
        PageBean pageBean=PageBeanUtil.wrapperToPageBean(nowPage,pageSize,allCount);
		
		setAttr("parameters", parameters);
		setAttr("pageName", pageName);
		setAttr("pageBean", pageBean);
		setAttr("twoList", twoList);
		setAttr("uppid", uppid);
		render("/admin/html/column/twocolumn.html");
	}
	
	/**
	 * goto add onecolumn
	 */
	public void addOneCol(){
		getData();
		render("/admin/html/column/addonecol.html");
	}
	
	/**
	 * goto add twocolumn
	 */
	public void addTwoCol(){
		getData();
		Integer uppid = getParaToInt("uppid");
		Column column = Column.dao.getColumnById(uppid);
		setAttr("column", column);
		render("/admin/html/column/addtwocol.html");
	}
	
	/**
	 * save onecolumn
	 */
	public void saveOneCol(){
		String columnName = getPara("columnName");
		String columnType = getPara("typeRadio");
		String columnNum = getPara("columnNum");
		String columnNice = getPara("columnNice");
		String columnurl = getPara("columnurl");
		String columnImg = getPara("columnImg");
		Column column = new Column();
		column.set("column_name", columnName);
		column.set("column_type", columnType);
		column.set("column_num", columnNum);
		column.set("column_nice", columnNice);
		column.set("colimn_url", columnurl);
		column.set("coilumn_img", columnImg);
		column.set("addtime", new Date());
		column.set("column_annotation", "1");
		if(column.save()){
			renderJson(1);
		}else{
			renderJson(2);
		}
	}
	
	/**
	 * save twocolumn
	 */
	public void saveTwoCol(){
		String columnName = getPara("columnName");
		String coltype = getPara("coltype");
		String columnUrl = getPara("columnUrl");
		String colid = getPara("colid");
		String columnImg = getPara("columnImg");
		Column column = new Column();
		column.set("colnumtwo_name", columnName);
		column.set("column_type", coltype);
		column.set("colimn_url", columnUrl);
		column.set("addtime", new Date());
		column.set("uppid", colid);
		column.set("coilumn_img", columnImg);
		column.set("column_annotation", "2");
		if(column.save()){
			renderJson(1);
		}else{
			renderJson(2);
		}
	}
	
	/**
	 * pre update onecolumn
	 */
	public void preUpOneCol(){
		getData();
		Integer uppid = getParaToInt("id");
		Column column = Column.dao.getColumnById(uppid);
		setAttr("column", column);
		render("/admin/html/column/uponecol.html");
	}
	
	/**
	 * save onecolumn
	 */
	public void saveUpOneCol(){
		String columnName = getPara("columnName");
		Integer columnId = getParaToInt("columnId");
		String columnNum = getPara("columnNum");
		String columnImg = getPara("columnImg");
		String columnNice = getPara("columnNice");
		String columnurl = getPara("columnurl");
		Column column = Column.dao.getColumnById(columnId);
		column.set("column_name", columnName);
		column.set("column_num", columnNum);
		column.set("coilumn_img", columnImg);
		column.set("column_nice", columnNice);
		column.set("colimn_url", columnurl);
		column.set("addtime", new Date());
		if(column.update()){
			renderJson(1);
		}else{
			renderJson(2);
		}
	}
	
	/**
	 * delete onecolumn
	 */
	public void delOneCol(){
		Integer columnId = getParaToInt("columnId");
		Column column = Column.dao.getColumnById(columnId);
		if(column.delete()){
			renderJson(1);
		}else{
			renderJson(2);
		}
	}
	
	/**
	 * pre update onecolumn
	 */
	public void preUpTwoCol(){
		getData();
		Integer uppid = getParaToInt("id");
		Column column = Column.dao.getColumnById(uppid);
		setAttr("column", column);
		render("/admin/html/column/uptwocol.html");
	}
	
	/**
	 * save onecolumn
	 */
	public void saveUpTwoCol(){
		String columnName = getPara("columnName");
		Integer columnId = getParaToInt("columnId");
		String columnUrl = getPara("columnUrl");
		String columnImg = getPara("columnImg");
		Column column = Column.dao.getColumnById(columnId);
		column.set("colnumtwo_name", columnName);
		column.set("colimn_url", columnUrl);
		column.set("coilumn_img", columnImg);
		if(column.update()){
			renderJson(1);
		}else{
			renderJson(2);
		}
	}
	
	/**
	 * delete twocolumn
	 */
	public void delTwoCol(){
		Integer columnId = getParaToInt("columnId");
		Column column = Column.dao.getColumnById(columnId);
		if(column.delete()){
			renderJson(1);
		}else{
			renderJson(2);
		}
	}
	
}
