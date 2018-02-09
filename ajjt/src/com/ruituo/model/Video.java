package com.ruituo.model;

import java.util.List;

import com.jfinal.plugin.activerecord.Model;

@SuppressWarnings("serial")
public class Video extends Model<Video> {

	public static final Video dao = new Video();
	private String sql;
	
	/**
	 * 视频列表总数
	 */
	public int countVideoListIndex(){
		sql = "select count(id) 'count' from t_video where 1=1 ";
		Object obj = Video.dao.findFirst(sql).get("count");
		Integer count = 0;
		if(obj != null){
			count = Integer.parseInt(obj.toString());
		}
		return count;
	}
	/**
	 * 获得新闻列表总数
	 */
	public int countVideoList(Integer columnId){
		sql = "select count(id) 'count' from t_video where 1=1 ";
		if(columnId != null && columnId != 0){
			sql += " and columnid = '" + columnId + "'";
		}
		Object obj = Video.dao.findFirst(sql).get("count");
		Integer count = 0;
		if(obj != null){
			count = Integer.parseInt(obj.toString());
		}
		return count;
	}
	/**
	 * 视频列表
	 */
	public List<Video> getVideoListIndex(int strPage,int pageSize){
		sql = "select t.* from t_video t where 1=1 order by t.addtime desc limit ?,?";
		return Video.dao.find(sql,new Object[]{strPage,pageSize});
	}
	/**
	 * 获得新闻列表
	 */
	public List<Video> getVideoListByColumnId(Integer columnId,int strPage,int pageSize){
		sql = "select t.* from t_column c,t_video t where c.id = t.columnId and c.id = ? order by t.addtime desc limit ?,?";
		return Video.dao.find(sql,new Object[]{columnId,strPage,pageSize});
	}
	/**
	 * 视频
	 */
	public Video getVideoIndex(int id){
		sql = "select * from t_video where id=?";
		return Video.dao.findFirst(sql,new Object[]{id});
	}
	//视频
	public List<Video> getVideoList(int strPage,int pageSize){
		sql = "select * from t_video order by addtime desc limit ?,?";
		return Video.dao.find(sql,new Object[]{strPage,pageSize});
	}	
	
	public int countVideoList(){
		sql = "select count(id) 'count' from t_video where 1=1 ";
		
		Object obj = Video.dao.findFirst(sql).get("count");
		Integer count = 0;
		
			count = Integer.parseInt(obj.toString());
		
		return count;
	}	
	
}
