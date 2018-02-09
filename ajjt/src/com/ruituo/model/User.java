package com.ruituo.model;

import com.jfinal.plugin.activerecord.Model;

@SuppressWarnings("serial")
public class User extends Model<User> {

	public static final User dao = new User();
	private String sql;
	
	public User getUser(String userName,String passWord){
		sql = "select * from t_user where username = ? and password = ?";
		return User.dao.findFirst(sql,new Object[]{userName,passWord});
	}
	
}
