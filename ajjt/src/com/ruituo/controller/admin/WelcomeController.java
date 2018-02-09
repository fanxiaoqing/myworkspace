package com.ruituo.controller.admin;

import com.jfinal.aop.Before;
import com.ruituo.common.BaseController;
import com.ruituo.interceptor.LoginInterceptor;
import com.ruituo.model.User;
import com.ruituo.util.StringUtils;

@Before(LoginInterceptor.class)
public class WelcomeController extends BaseController {

	/**
	 * 后台首页
	 */
	public void welcome(){
		getData();
		render("/admin/html/index/index.html");
	}
	
	/**
	 * 修改密码
	 */
	public void updatePassWord(){
		String pwd = getPara("pwd");
		Integer userId = getParaToInt("userId");
		User user = User.dao.findById(userId);
		user.set("password", StringUtils.encrypt(pwd));
		if(user.update()){
			renderJson(1);
		}else{
			renderJson(2);
		}
	}
	
	/**
	 * 退出登陆
	 */
	public void outlogin(){
		getData();
		removeSessionAttr("user");
		render("/login/login.html");
	}
	
}
