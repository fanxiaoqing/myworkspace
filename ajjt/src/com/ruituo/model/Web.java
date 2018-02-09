package com.ruituo.model;

import com.jfinal.plugin.activerecord.Model;

@SuppressWarnings("serial")
public class Web extends Model<Web>{

	public static final Web dao = new Web();
	private String sql = "";
	
	/**
	 * 获得网站信息
	 */
	public Web getWeb(){
		sql = "select * from t_web";
		return Web.dao.findFirst(sql);
	}
	
}
