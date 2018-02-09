package com.ruituo.controller.login;

import com.ruituo.common.BaseController;
import com.ruituo.model.User;
import com.ruituo.util.StringUtils;

public class LoginController extends BaseController {

	/**
	 * goto登陆
	 */
	public void index(){
		getData();
		render("login.html");
	}
	
	/**
	 * 登陆界面
	 */
	public void login(){
		String userName = getPara("userName");
		String passWord = getPara("passWord");
		User user = User.dao.getUser(userName, StringUtils.encrypt(passWord));
		if(user != null){
			setSessionAttr("user", user);
			renderJson(1);
		}else{
			renderJson(2);
		}
	}
	
}
