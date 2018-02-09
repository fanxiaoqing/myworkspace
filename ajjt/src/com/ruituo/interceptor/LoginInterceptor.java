package com.ruituo.interceptor;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.ruituo.model.User;


public class LoginInterceptor implements  Interceptor {

	@Override
	public void intercept(Invocation inv) {
		User user = inv.getController().getSessionAttr("user");
		if(user == null){
			String path = inv.getController().getRequest().getContextPath();
			inv.getController().renderHtml("<script>parent.location.href='"+ path +"/login'</script>");
		}else{
			inv.invoke();
		}
	}

}
