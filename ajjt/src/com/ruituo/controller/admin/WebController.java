package com.ruituo.controller.admin;

import java.util.Date;

import com.jfinal.aop.Before;
import com.ruituo.common.BaseController;
import com.ruituo.interceptor.LoginInterceptor;
import com.ruituo.model.Web;

@Before(LoginInterceptor.class)
public class WebController extends BaseController {

	/**
	 * goto 网站信息
	 */
	public void gotoWebDetail(){
		getData();
		if(getPara("alt") != null && getPara("alt").equals("1")){
			setAttr("msg", "修改成功");
		}
		Web web = Web.dao.getWeb();
		setAttr("web", web);
		
		render("/admin/html/web/web.html");
	}
	
	/**
	 * 网站更新
	 */
	public void updateWeb(){
		String web_title = getPara("web_title");
		String web_head = getPara("web_head");
		String head_phone = getPara("head_phone");
		String head_qq = getPara("head_qq");
		String head_wx = getPara("head_wx");
		String web_keywords = getPara("web_keywords");
		String web_description = getPara("web_description");
		String web_copyright = getPara("web_copyright");
		String head_em = getPara("head_em");
		String head_ads = getPara("head_ads");
		String head_fax = getPara("head_fax");
		String web_count = getPara("web_count");
		Web web = Web.dao.getWeb();
		web.set("web_title", web_title);
		web.set("web_head", web_head);
		web.set("head_phone", head_phone);
		web.set("head_qq", head_qq);
		web.set("head_wx", head_wx);
		web.set("web_keywords", web_keywords);
		web.set("head_email", head_em);
		web.set("head_address", head_ads);
		web.set("web_description", web_description);
		web.set("web_copyright", web_copyright);
		web.set("head_fax", head_fax);
		web.set("updatetime", new Date());
		web.set("web_count", web_count);
		if(web.update()){
			redirect("/web/gotoWebDetail?alt=1");
		}else{
			redirect("/web/gotoWebDetail");
		}
		
	}
	
}
