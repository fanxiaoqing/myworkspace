package com.ruituo.model;

import java.util.List;

import com.jfinal.plugin.activerecord.Model;

@SuppressWarnings("serial")
public class Resume extends Model<Resume> {

	public static final Resume dao = new Resume();
	private String sql;

	/**
	 * 获得简历列表
	 */
	public List<Resume> getResumeList(int strPage,int pageSize){
		sql = "select * from t_resume order by addtime asc limit ?,?";
		return Resume.dao.find(sql,new Object[]{strPage,pageSize});
	}
	
	/**
	 * 获得简历总数
	 */
	public int countResumeList(){
		sql = "select count(id) 'count' from t_resume order by addtime asc";
		Object obj = Resume.dao.findFirst(sql).get("count");
		int count = 0;
		if(obj != null){
			count = Integer.parseInt(obj.toString());
		}
		return count;
	}
	
}
