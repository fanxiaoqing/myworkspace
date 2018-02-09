package com.ruituo.model;

import java.util.List;

import com.jfinal.plugin.activerecord.Model;

@SuppressWarnings("serial")
public class Product extends Model<Product> {

	public static final Product dao = new Product();
	private String sql;
	
	/**
	 * 获得产品列表
	 */
	public List<Product> getProductList(Integer columnId,int strPage,int pageSize){
		sql = "select t.*,c.colnumtwo_name from t_product t,t_column c where 1=1 and t.columnid = c.id";
		if(columnId != null && columnId != 0){
			sql += " and t.columnid = '" + columnId + "'";
		}
		sql += " order by t.addtime asc limit ?,?";
		return Product.dao.find(sql,new Object[]{strPage,pageSize});
	}
	
	/**
	 * 获得产品列表总数
	 */
	public int countProductList(Integer columnId){
		sql = "select count(id) 'count' from t_product where 1=1 ";
		if(columnId != null && columnId != 0){
			sql += " and columnid = '" + columnId + "'"; 
		}
		Object obj = Product.dao.findFirst(sql).get("count");
		Integer count = 0;
		if(obj != null){
			count = Integer.parseInt(obj.toString());
		}
		return count;
	}
	
	/**
	 * 获得主题列表by uppid
	 */
	public List<Product> getProductListByColumnUppid(Integer uppid){
		sql = "select t.*,c.colnumtwo_name from t_product t,t_column c where c.id = t.columnid and t.columnid in (select id from t_column where uppid = ?) order by t.addtime desc";
		return Product.dao.find(sql,new Object[]{uppid});
	}
	
	/**
	 * 获得产品列表
	 */
	public List<Product> getProductListByColumnId(Integer columnId,int strPage,int pageSize){
		sql = "select t.* from t_column c,t_product t where c.id = t.columnid and c.id = ? order by t.addtime desc limit ?,?";
		return Product.dao.find(sql,new Object[]{columnId,strPage,pageSize});
	}
	
	/**
	 * 获得新闻总数
	 */
	public int countProductListByColumnId(Integer columnId){
		sql = "select count(t.id) 'count' from t_column c,t_product t where c.id = t.columnid and c.id = ? ";
		Object obj =  Product.dao.findFirst(sql,new Object[]{columnId}).get("count");
		int count = 0;
		if(obj != null){
			count = Integer.parseInt(obj.toString());
		}
		return count;
	}
	
	/**
	 * 获得要查询的产品
	 */
	public List<Product> getProductList(String searchname){
		sql = "select * from t_product where pro_title like '%" + searchname + "%' order by addtime desc limit 0,8";
		return Product.dao.find(sql);
	}
	
	/**
	 * 获得要查询的产品
	 */
	public List<Product> getProductTypeList(Integer columnid){
		sql = "select * from t_product where columnid = ? order by addtime desc";
		return Product.dao.find(sql,new Object[]{columnid});
	}

	public List<Product> getProductListBy(String pa) {
		
		return null;
	}
	/*
	 * 首页产品列表
	 */
	public List<Product> getShouYeProductList(Integer columnid,Integer a,Integer b,Integer c,Integer d){
		sql = "select * from t_product WHERE columnid = ? OR columnid = ? OR columnid = ? OR columnid = ?  OR columnid = ? order by addtime DESC LIMIT 0,6";
		//sql = "select * from t_product where columnid = ? order by addtime desc";
		return Product.dao.find(sql,new Object[]{columnid,a,b,c,d});
	}	
	/*
	 * 首页产品列表
	 */
	public List<Product> getShouYeProList(Integer columnid){
		sql = "select * from t_product WHERE columnid = ? order by addtime DESC LIMIT 0,3";
		//sql = "select * from t_product where columnid = ? order by addtime desc";
		return Product.dao.find(sql,new Object[]{columnid});
	}	
	/*
	 * 
	 * 测试试试
	 */
	public List<Product> getProList(Integer columnid){
		sql = "select * from t_product where columnid = ? order by addtime desc";		
		return Product.dao.find(sql,new Object[]{columnid});
	}
	
}
