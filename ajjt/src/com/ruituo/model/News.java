package com.ruituo.model;

import java.util.List;

import com.jfinal.plugin.activerecord.Model;

@SuppressWarnings("serial")
public class News extends Model<News> {

	public static final News dao = new News();
	private String sql;
	
	/**
	 * 获得新闻列表
	 */
	public List<News> getNewsList(Integer columnId,int strPage,int pageSize){
		sql = "select t.*,c.colnumtwo_name from t_news t,t_column c where 1=1 and t.columnid = c.id";
		if(columnId != null && columnId != 0){
			sql += " and t.columnid = '" + columnId + "'";
		}
		sql += " order by t.addtime desc limit ?,?";
		return News.dao.find(sql,new Object[]{strPage,pageSize});
	}
	
	/**
	 * 获得新闻列表总数
	 */
	public int countNewsList(Integer columnId){
		sql = "select count(id) 'count' from t_news where 1=1 ";
		if(columnId != null && columnId != 0){
			sql += " and columnid = '" + columnId + "'";
		}
		Object obj = News.dao.findFirst(sql).get("count");
		Integer count = 0;
		if(obj != null){
			count = Integer.parseInt(obj.toString());
		}
		return count;
	}
	
	/**
	 * 获得主题列表by uppid
	 */
	public List<News> getNewsListByColumnUppidTop5(Integer uppid){
		sql = "select t.* from t_news t where t.columnid in (select id from t_column where uppid = ?) order by t.addtime desc limit 0,5";
		return News.dao.find(sql,new Object[]{uppid});
	}
	
	/**
	 * 获得主题列表by uppid
	 */
	public List<News> getNewsListByColumnUppid(Integer uppid){
		sql = "select t.* from t_news t where t.columnid in (select id from t_column where uppid = ?) order by t.addtime desc";
		return News.dao.find(sql,new Object[]{uppid});
	}
	
	/**
	 * 获得主题
	 */
	public News getNewsByColumnId(Integer columnId){
		sql = "select t.* from t_column c,t_news t where c.id = t.columnid and c.id = ? order by t.addtime desc limit 0,1";
		return News.dao.findFirst(sql,new Object[]{columnId});
	}
	
	/**
	 * 获得新闻列表
	 */
	public List<News> getNewsListByColumnId(Integer columnId){
		sql = "select t.* from t_column c,t_news t where c.id = t.columnid and c.id = ? order by t.addtime desc";
		return News.dao.find(sql,new Object[]{columnId});
	}
	
	/**
	 * 获得新闻列表
	 */
	public List<News> getNewsListByColumnId(Integer columnId,int strPage,int pageSize){
		sql = "select t.* from t_column c,t_news t where c.id = t.columnid and c.id = ? order by t.addtime desc limit ?,?";
		return News.dao.find(sql,new Object[]{columnId,strPage,pageSize});
	}
	
	/**
	 * 获得新闻总数
	 */
	public int countNewsListByColumnId(Integer columnId){
		sql = "select count(t.id) 'count' from t_column c,t_news t where c.id = t.columnid and c.id = ? ";
		Object obj =  News.dao.findFirst(sql,new Object[]{columnId}).get("count");
		int count = 0;
		if(obj != null){
			count = Integer.parseInt(obj.toString());
		}
		return count;
	}
	/**
	 * 获得公司新闻或行业新闻列表
	 */
	public List<News> getNewsByColumnId(Integer columnId,Integer id){
		sql = "select t.* from t_column c,t_news t where c.id = t.columnid and (c.id = ? or c.id = ?) order by t.addtime desc limit 0,4";
		return News.dao.find(sql,new Object[]{columnId,id});
	}
	/**
	 * 获得首页新闻list
	 * <p>Description: </p>
	 * @date 2017年3月15日 下午6:56:40
	 * @param
	 */
	public List<News> getShouYeProductList(Integer columnid,Integer a,Integer b,Integer c){
		sql = "select * from t_news WHERE columnid = ? OR columnid = ? OR columnid = ? OR columnid = ? order by addtime DESC LIMIT 0,4";
		//sql = "select * from t_product where columnid = ? order by addtime desc";
		return News.dao.find(sql,new Object[]{columnid,a,b,c});
	}
	public List<News> getShouYeNewsList(Integer columnid,Integer a){
		sql = "select * from t_news WHERE OR columnid = ? OR columnid = ? order by addtime DESC LIMIT 0,6";
		//sql = "select * from t_product where columnid = ? order by addtime desc";
		return News.dao.find(sql,new Object[]{columnid,a});
	}
	
	public List<News> getbowuguan(Integer columnid){
		sql = "select * from t_news WHERE columnid = ? order by addtime DESC LIMIT 0,6";
		//sql = "select * from t_product where columnid = ? order by addtime desc";
		return News.dao.find(sql,new Object[]{columnid});
	}
}
