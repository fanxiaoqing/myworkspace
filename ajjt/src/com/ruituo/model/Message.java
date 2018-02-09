package com.ruituo.model;

import java.util.List;

import com.jfinal.plugin.activerecord.Model;

@SuppressWarnings("serial")
public class Message extends Model<Message> {

	public static final Message dao = new Message();
	private String sql;
	
	
	/**
	 * 获得留言列表
	 */
	public List<Message> getMessageList(int strPage,int pageSize){
		sql = "select * from t_message order by addtime desc limit ?,?";
		return Message.dao.find(sql,new Object[]{strPage,pageSize});
	}
	
	/**
	 * 获得留言列表 
	 */
	public int countMessageList(){
		sql = "select count(id) 'count' from t_message order by addtime desc";
		Object obj = Message.dao.findFirst(sql).get("count");
		int count = 0;
		if(obj != null){
			count = Integer.parseInt(obj.toString());
		}
		return count;
	}
	
}
