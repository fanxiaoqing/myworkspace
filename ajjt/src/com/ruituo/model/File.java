package com.ruituo.model;

import java.util.List;

import com.jfinal.plugin.activerecord.Model;

@SuppressWarnings("serial")
public class File extends Model<File> {

	public static final File dao = new File();
	private String sql;
	
	/**
	 * 文件列表总数
	 */
	public int countFileListIndex(){
		sql = "select count(id) 'count' from t_file where 1=1 ";
		Object obj = File.dao.findFirst(sql).get("count");
		Integer count = 0;
		if(obj != null){
			count = Integer.parseInt(obj.toString());
		}
		return count;
	}
	
	/**
	 * 文件列表
	 */
	public List<File> getFileListIndex(int strPage,int pageSize){
		sql = "select t.* from t_file t where 1=1 order by t.addtime desc limit ?,?";
		return File.dao.find(sql,new Object[]{strPage,pageSize});
	}
	/**
	 * 文件
	 */
	public File getFileIndex(int id){
		sql = "select * from t_file where id=?";
		return File.dao.findFirst(sql,new Object[]{id});
	}
	
	/**
	 * 获得文件列表
	 */
	public List<File> getFileList(int strPage,int pageSize){
		sql = "select * from t_file order by addtime desc limit ?,?";
		return File.dao.find(sql,new Object[]{strPage,pageSize});
	}	
	
	/**
	 * 获得文件列表
	 */
	public List<File> getFileListByColumnId(Integer columnId,int strPage,int pageSize){
		sql = "select t.* from t_column c,t_file t where c.id = t.columnid and c.id = ? order by t.addtime desc limit ?,?";
		return File.dao.find(sql,new Object[]{columnId,strPage,pageSize});
	}
	
	
	public int countFileList(){
		sql = "select count(id) 'count' from t_file where 1=1 ";
		
		Object obj = File.dao.findFirst(sql).get("count");
		Integer count = 0;
		
			count = Integer.parseInt(obj.toString());
		
		return count;
	}		
}
