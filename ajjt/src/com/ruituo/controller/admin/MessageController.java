package com.ruituo.controller.admin;

import java.util.List;

import com.jfinal.aop.Before;
import com.ruituo.common.BaseController;
import com.ruituo.common.Constant;
import com.ruituo.interceptor.LoginInterceptor;
import com.ruituo.model.Message;
import com.ruituo.model.Resume;
import com.ruituo.util.PageBean;
import com.ruituo.util.PageBeanUtil;

@Before(LoginInterceptor.class)
public class MessageController extends BaseController {

	/**
	 * goto 用户留言
	 */
	public void gotoMessList(){
		getData();
		int nowPage = 1;	//当前页数
		if(getPara("pageNum")!=null){
			nowPage=Integer.parseInt(getPara("pageNum"));
		}
		int pageSize = Constant.EACH_PAGE_PAGESIZE;
		
		int allCount = Message.dao.countMessageList();
		String pageName = "/mes/gotoMessList";
		
		String parameters = "";
		if(getPara("parameters")!= null){
			parameters = getPara("parameters");
		}
		List<Message> messlist = Message.dao.getMessageList(PageBeanUtil.getStartRecord(nowPage,pageSize),pageSize);
		PageBean pageBean=PageBeanUtil.wrapperToPageBean(nowPage,pageSize,allCount);
		
		setAttr("parameters", parameters);
		setAttr("pageName", pageName);
		setAttr("pageBean", pageBean);
		setAttr("messlist", messlist);
		render("/admin/html/message/message.html");
	}
	
	
	/**
	 * 查看留言
	 */
	public void preMessage(){
		getData();
		Integer id = getParaToInt("id");
		Message message=Message.dao.findById(id);
		
		setAttr("message", message);
		render("/admin/html/message/messagedeatil.html");
	}
	
	/**
	 * 删除留言
	 */
	public void delMessage(){
		Integer id = getParaToInt("id");
		Message message = Message.dao.findById(id);
		if(message.delete()){
			renderJson(1);
		}else{
			renderJson(2);
		}
	}
	
}
