package com.ruituo.common;

import com.jfinal.core.Controller;
import com.ruituo.model.Web;

public class BaseController extends Controller {

	/**
	 * 获得基础数据
	 */
	public void getData(){
		String a = getSession().getServletContext().getContextPath();
		setAttr("path", a);
		Web web = Web.dao.getWeb();
		setAttr("web", web);
	}
	
}
