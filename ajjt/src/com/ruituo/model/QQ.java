package com.ruituo.model;

import java.util.List;

import com.jfinal.plugin.activerecord.Model;

@SuppressWarnings("serial")
public class QQ extends Model<QQ> {

	public static final QQ dao = new QQ();
	private String sql;
	
	/**
	 * 获得QQ客服列表
	 */
	public List<QQ> getQQList(int strPage,int pageSize){
		sql = "select * from t_qq order by addtime asc limit ?,?";
		return QQ.dao.find(sql,new Object[]{strPage,pageSize});
	}
	
	/**
	 * 获得QQ客服列表
	 */
	public int countQQList(){
		sql = "select count(id) 'count' from t_qq order by addtime asc";
		Object obj = QQ.dao.findFirst(sql).get("count");
		int count = 0;
		if(obj != null){
			count = Integer.parseInt(obj.toString());
		}
		return count;
	}
	
	/**
	 * 获得客服对象
	 */
	public QQ getQQ(Integer id){
		sql = "select * from t_qq where id = ?";
		return QQ.dao.findFirst(sql,new Object[]{id});
	}
	
	/**
	 * 获得最新的qq
	 */
	public QQ getQQTop(){
		sql = "select * from t_qq order by addtime desc limit 0,1";
		return QQ.dao.findFirst(sql);
	}
	
}
