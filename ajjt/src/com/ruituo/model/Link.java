package com.ruituo.model;

import java.util.List;

import com.jfinal.plugin.activerecord.Model;

@SuppressWarnings("serial")
public class Link extends Model<Link> {

	public static final Link dao = new Link();
	private String sql;
	
	/**
	 * 获得链接列表
	 */
	public List<Link> getLinkList(int strPage,int pageSize){
		sql = "select * from t_link order by addtime asc limit ?,?";
		return Link.dao.find(sql,new Object[]{strPage,pageSize});
	}
	
	/**
	 * 获得链接列表
	 */
	public int countLinkList(){
		sql = "select count(id) 'count' from t_link order by addtime asc";
		Object obj = Link.dao.findFirst(sql).get("count");
		int count = 0;
		if(obj != null){
			count = Integer.parseInt(obj.toString());
		}
		return count;
	}
	
	/**
	 * 获得链接对象
	 */
	public Link getLink(Integer id){
		sql = "select * from t_link where id = ?";
		return Link.dao.findFirst(sql,new Object[]{id});
	}
	
	/**
	 * 获得链接对象
	 */
	public Link getLinkTop(){
		sql = "select * from t_link order by addtime desc limit 0,1";
		return Link.dao.findFirst(sql);
	}
	
}
