package com.ruituo.model;

import java.util.List;

import com.jfinal.plugin.activerecord.Model;

@SuppressWarnings("serial")
public class Theme extends Model<Theme> {

	public static final Theme dao = new Theme();
	private String sql;
	
	/**
	 * 获得主题列表
	 */
	public List<Theme> getThemeList(Integer columnId,int strPage,int pageSize){
		sql = "select t.*,c.colnumtwo_name from t_theme t,t_column c where 1=1 and t.columnid = c.id";
		if(columnId != null && columnId != 0){
			sql += " and t.columnid = '" + columnId + "'";
		}
		sql += " order by t.addtime asc limit ?,?";
		return Theme.dao.find(sql,new Object[]{strPage,pageSize});
	}
	
	/**
	 * 统计分类主题
	 */
	public int countThemeList(Integer columnId){
		sql = "select count(id) 'count' from t_theme where 1=1 ";
		if(columnId != null && columnId != 0){
			sql += " and columnid = '" + columnId + "'";
		}
		Object obj = Theme.dao.findFirst(sql).get("count");
		Integer count = 0;
		if(obj != null){
			count = Integer.parseInt(obj.toString());
		}
		return count;
	}
	
	
	/**
	 * 获得主题
	 */
	public Theme getThemeByColumnId(Integer columnId){
		sql = "select t.* from t_column c,t_theme t where c.id = t.columnid and c.id = ? order by t.addtime desc limit 0,1";
		return Theme.dao.findFirst(sql,new Object[]{columnId});
	}
	
	/**
	 * 获得主题列表
	 */
	public List<Theme> getThemeListByColumnId(Integer columnId){
		sql = "select t.* from t_column c,t_theme t where c.id = t.columnid and c.id = ? order by t.addtime desc limit 0,4";
		return Theme.dao.find(sql,new Object[]{columnId});
	}

	/**
	 * 获得主题列表正序
	 */
	public List<Theme> getThemeListByColumnIds(Integer columnId){
		sql = "select t.* from t_column c,t_theme t where c.id = t.columnid and c.id = ? order by t.id";
		return Theme.dao.find(sql,new Object[]{columnId});
	}
	
	
	
	
	
	
	
	
	/**
	 * 获取轮播图信息
	 * @author wyl
	 * @param columnId
	 * @return
	 */
	public List<Theme> getLunBoListByColumnIds(Integer columnId){
		sql = "select t.* from t_column c,t_theme t where c.id = t.columnid and c.id = ? order by t.id desc limit 0,4";
		return Theme.dao.find(sql,new Object[]{columnId});
	}
	
	
}
