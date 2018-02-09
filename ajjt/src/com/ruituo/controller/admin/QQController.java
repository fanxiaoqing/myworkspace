package com.ruituo.controller.admin;

import java.util.Date;
import java.util.List;

import com.jfinal.aop.Before;
import com.ruituo.common.BaseController;
import com.ruituo.common.Constant;
import com.ruituo.interceptor.LoginInterceptor;
import com.ruituo.model.QQ;
import com.ruituo.util.PageBean;
import com.ruituo.util.PageBeanUtil;

@Before(LoginInterceptor.class)
public class QQController extends BaseController {

	/**
	 * 获得客服QQ列表
	 */
	public void getQQList(){
		getData();
		int nowPage = 1;	//当前页数
		if(getPara("pageNum")!=null){
			nowPage=Integer.parseInt(getPara("pageNum"));
		}
		int pageSize = Constant.EACH_PAGE_PAGESIZE;
		
		int allCount = QQ.dao.countQQList();
		String pageName = "/qq/getQQList";
		
		String parameters = "";
		if(getPara("parameters")!= null){
			parameters = getPara("parameters");
		}
		List<QQ> qqList = QQ.dao.getQQList(PageBeanUtil.getStartRecord(nowPage,pageSize),pageSize);
		PageBean pageBean=PageBeanUtil.wrapperToPageBean(nowPage,pageSize,allCount);
		
		setAttr("parameters", parameters);
		setAttr("pageName", pageName);
		setAttr("pageBean", pageBean);
		setAttr("qqList", qqList);
		render("/admin/html/qq/qqlist.html");
	}
	
	/**
	 * 添加QQ
	 */
	public void addQQ(){
		getData();
		render("/admin/html/qq/addqq.html");
	}
	
	/**
	 * 保存客服
	 */
	public void saveQQ(){
		String qqName = getPara("qqName");
		String qqCode = getPara("qqCode");
		QQ qq = new QQ();
		qq.set("qq_name", qqName);
		qq.set("qq_code", qqCode);
		qq.set("addtime", new Date());
		if(qq.save()){
			renderJson(1);
		}else{
			renderJson(2);
		}
	}
	
	/**
	 * 更新QQ
	 */
	public void preupQQ(){
		getData();
		Integer id = getParaToInt("id");
		QQ qq = QQ.dao.getQQ(id);
		setAttr("qq", qq);
		render("/admin/html/qq/upqq.html");
	}
	
	/**
	 * 保存修改
	 */
	public void saveUpQQ(){
		String qqName = getPara("qqName");
		String qqCode = getPara("qqCode");
		Integer qqId = getParaToInt("qqId");
		QQ qq = QQ.dao.getQQ(qqId);
		qq.set("qq_name", qqName);
		qq.set("qq_code", qqCode);
		if(qq.update()){
			renderJson(1);
		}else{
			renderJson(2);
		}
	}
	
	/**
	 * QQ删除
	 */
	public void delQQ(){
		Integer qqId = getParaToInt("qqId");
		QQ qq = QQ.dao.getQQ(qqId);
		if(qq.delete()){
			renderJson(1);
		}else{
			renderJson(2);
		}
	}
	
}
