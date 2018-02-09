package com.ruituo.controller.admin;

import java.util.Date;
import java.util.List;

import com.jfinal.aop.Before;
import com.ruituo.common.BaseController;
import com.ruituo.common.Constant;
import com.ruituo.interceptor.LoginInterceptor;
import com.ruituo.model.Link;
import com.ruituo.util.PageBean;
import com.ruituo.util.PageBeanUtil;

@Before(LoginInterceptor.class)
public class LinkController extends BaseController {

	/**
	 * 获得链接列表
	 */
	public void getLinkList(){
		getData();
		int nowPage = 1;	//当前页数
		if(getPara("pageNum")!=null){
			nowPage=Integer.parseInt(getPara("pageNum"));
		}
		int pageSize = Constant.EACH_PAGE_PAGESIZE;
		
		int allCount = Link.dao.countLinkList();
		String pageName = "/link/getLinkList";
		
		String parameters = "";
		if(getPara("parameters")!= null){
			parameters = getPara("parameters");
		}
		List<Link> linkList = Link.dao.getLinkList(PageBeanUtil.getStartRecord(nowPage,pageSize),pageSize);
		PageBean pageBean=PageBeanUtil.wrapperToPageBean(nowPage,pageSize,allCount);
		
		setAttr("parameters", parameters);
		setAttr("pageName", pageName);
		setAttr("pageBean", pageBean);
		setAttr("linkList", linkList);
		render("/admin/html/link/linklist.html");
	}
	
	/**
	 * 添加链接
	 */
	public void addLink(){
		getData();
		render("/admin/html/link/addlink.html");
	}
	
	/**
	 * 保存链接
	 */
	public void saveLink(){
		String linkName = getPara("linkName");
		String linkUrl = getPara("linkUrl");
		Link link = new Link();
		link.set("link_name", linkName);
		link.set("link_url", linkUrl);
		link.set("addtime", new Date());
		if(link.save()){
			renderJson(1);
		}else{
			renderJson(2);
		}
	}
	
	/**
	 * 修改链接
	 */
	public void upLink(){
		getData();
		Integer id = getParaToInt("id");
		Link link = Link.dao.getLink(id);
		setAttr("link", link);
		render("/admin/html/link/uplink.html");
	}
	
	/**
	 * 保存修改链接
	 */
	public void saveUpLink(){
		String linkName = getPara("linkName");
		String linkUrl = getPara("linkUrl");
		Integer id = getParaToInt("linkId");
		Link link = Link.dao.getLink(id);
		link.set("link_name", linkName);
		link.set("link_url", linkUrl);
		if(link.update()){
			renderJson(1);
		}else{
			renderJson(2);
		}
	}
	
	/**
	 * 删除链接
	 */
	public void delLink(){
		Integer id = getParaToInt("linkId");
		Link link = Link.dao.getLink(id);
		if(link.delete()){
			renderJson(1);
		}else{
			renderJson(2);
		}
	}
	
	
}
