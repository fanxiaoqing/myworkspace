package com.ruituo.model;

import java.util.List;

import com.jfinal.plugin.activerecord.Model;

@SuppressWarnings("serial")
public class Aadvice extends Model<Aadvice> {

	public static final Aadvice dao = new Aadvice();
	private String sql;
	
	/**
	 * 获得建议列表
	 */
	public List<Aadvice> getAadviceList(int strPage,int pageSize){
		sql = "select * from t_advice order by addtime desc limit ?,?";
		return Aadvice.dao.find(sql,new Object[]{strPage,pageSize});
	}
	
	/**
	 * 获得建议列表
	 */
	public int countAadviceList(){
		sql = "select count(id) 'count' from t_advice order by addtime desc";
		Object obj = Aadvice.dao.findFirst(sql).get("count");
		int count = 0;
		if(obj != null){
			count = Integer.parseInt(obj.toString());
		}
		return count;
	}
	
}
