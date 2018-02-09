package com.ruituo.model;

import java.util.List;

import com.jfinal.plugin.activerecord.Model;

@SuppressWarnings("serial")
public class Column extends Model<Column> {

	public static final Column dao = new Column();
	private String sql;
	
	/**
	 * 获取主题
	 * @param start
	 * @param end
	 * @return
	 */
	public List<Column> getTheme(Integer start,Integer end){
		sql = "select * from t_column where column_annotation = '1' and column_type = '主题' order by id limit ?,?";
		return Column.dao.find(sql,new Object[]{start,end});
	}
	
	/**
	 * 获得一级栏目列表
	 */
	public List<Column> getColumnList(int strPage,int pageSize){
		sql = "select * from t_column where column_annotation = '1' order by addtime asc limit ?,?";
		return Column.dao.find(sql,new Object[]{strPage,pageSize});
	}
	
	/**
	 * 获得一级栏目列表
	 */
	public int countColumnList(){
		sql = "select count(id) 'count' from t_column where column_annotation = '1' order by addtime asc";
		Object obj = Column.dao.findFirst(sql).get("count");
		int count = 0;
		if(obj != null){
			count = Integer.parseInt(obj.toString());
		}
		return count;
	}
	
	/**
	 * 获得二级栏目列表
	 */
	public List<Column> getTwoColumnList(String uppid,int strPage,int pageSize){
		sql = "select * from t_column where uppid = ? order by addtime asc limit ?,?";
	    return Column.dao.find(sql,new Object[]{uppid,strPage,pageSize});
	}
	
	/**
	 * 获得二级栏目列表
	 */
	public int countTwoColumnList(String uppid){
		sql = "select count(id) 'count' from t_column where uppid = ? order by addtime asc";
		Object obj = Column.dao.findFirst(sql,new Object[]{uppid}).get("count");
		int count = 0;
		if(obj != null){
			count = Integer.parseInt(obj.toString());
		}
	    return count;
	}
	
	/**
	 * 获得Column对象
	 */
	public Column getColumnById(Integer id){
		sql = "select * from t_column where id = ?";
		return Column.dao.findFirst(sql,new Object[]{id});
	}
	
	/**
	 * 获得Column对象
	 */
	public List<Column> getColumnByUppid(Integer id){
		sql = "select * from t_column where uppid = ? order by addtime asc";
		return Column.dao.find(sql,new Object[]{id});
	}
	
	/**
	 * 获得Column对象
	 */
	public List<Column> getColumnByUppidTop10(Integer id){
		sql = "select * from t_column where uppid = ? order by addtime asc limit 0,10";
		return Column.dao.find(sql,new Object[]{id});
	}
	
	/**
	 * 获得二级栏目
	 */
	public List<Column> getColumnByType(String type){
		sql = "select * from t_column where column_type = ? and column_annotation = '2'";
		return Column.dao.find(sql,new Object[]{type});
	}
	
	/**
	 * 获得一级导航栏目
	 */
	public List<Column> getColumnOneContents(String typeone,String typetwo){
		sql = "select * from t_column where (column_type = ? or column_type = ? ) and column_annotation = '1' order by column_num asc,addtime desc";
		return Column.dao.find(sql,new Object[]{typeone,typetwo});
	}
	
	/**
	 * 获得二级导航栏目
	 */
	public List<Column> getColumnTwoContents(String typeone,String typetwo){
		sql = "select * from t_column where (column_type = ? or column_type = ?) and column_annotation = '2'";
		return Column.dao.find(sql,new Object[]{typeone,typetwo});
	}
	
	public List<Column> getProColumn(String typetwo){
		sql = "select * from t_column where column_type = ? and column_annotation = '2'";
		return Column.dao.find(sql,new Object[]{typetwo});
	}	
}
