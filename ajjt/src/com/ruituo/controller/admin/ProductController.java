package com.ruituo.controller.admin;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.aop.Before;
import com.jfinal.upload.UploadFile;
import com.ruituo.common.BaseController;
import com.ruituo.common.Constant;
import com.ruituo.config.SystemConfig;
import com.ruituo.interceptor.LoginInterceptor;
import com.ruituo.model.Column;
import com.ruituo.model.Product;
import com.ruituo.util.PageBean;
import com.ruituo.util.PageBeanUtil;

@Before(LoginInterceptor.class)
public class ProductController extends BaseController {

	/**
	 * 获得产品列表
	 */
	public void getProductList(){
		getData();
		int nowPage = 1;	//当前页数
		if(getPara("pageNum")!=null){
			nowPage=Integer.parseInt(getPara("pageNum"));
		}
		
		Integer columnId = 0;
		if(getParaToInt("columnId") != null){
			columnId = getParaToInt("columnId");
		}
		
		int pageSize = Constant.EACH_PAGE_PAGESIZE;
		int allCount = Product.dao.countProductList(columnId);
		String pageName = "/pro/getProductList";
		
		String parameters = "&columnId="+columnId;
		if(getPara("parameters")!= null){
			parameters = getPara("parameters");
		}
		List<Product> proList = Product.dao.getProductList(columnId,PageBeanUtil.getStartRecord(nowPage,pageSize),pageSize);
		PageBean pageBean=PageBeanUtil.wrapperToPageBean(nowPage,pageSize,allCount);
		List<Column> twoThemeColList = Column.dao.getColumnByType(Constant.COLUMN_TYPE_PRODUCT);
		
		setAttr("twoThemeColList", twoThemeColList);
		setAttr("parameters", parameters);
		setAttr("pageName", pageName);
		setAttr("pageBean", pageBean);
		setAttr("nowPage", nowPage);
		setAttr("proList", proList);
		setAttr("columnId", columnId);
		render("/admin/html/product/productlist.html");
	}
	
	/**
	 * 新闻批量添加
	 */
	public void addMachProduct(){
		getData();
		List<Column> twoThemeColList = Column.dao.getColumnByType(Constant.COLUMN_TYPE_PRODUCT);
		setAttr("twoThemeColList", twoThemeColList);
		render("/admin/html/product/machupload.html");
	}
	
	private static int imgCount = 0;
	
	public void imageUpload(){
		 UploadFile uploadFile = getFile();
		 JSONObject jsonObject = new JSONObject();
		 if (uploadFile != null) {
	            String fileName = uploadFile.getFileName();
	            String extentionName = fileName.substring(fileName.lastIndexOf(".")); // 后缀名
	            String titleName = fileName.substring(0,fileName.lastIndexOf(".")); // 后缀名

	            if (imgCount > 300){// 300为文件上传最大数目
	                imgCount = 0;
	            }
	            Date dateNow=new Date();  
				SimpleDateFormat  dateFormat=new SimpleDateFormat ("yyyyMMddHHmmss");  
				String dateNowStr=dateFormat.format(dateNow);  
				int randomNum = (int)((Math.random()*9+1)*100000);
	            String newName = dateNowStr+randomNum+extentionName; // 新名
	            imgCount++;

	            String filePath = SystemConfig.absolutePath +  newName;// 文件完整路径
	            String path = SystemConfig.relativePath + newName;
	            
	            //保存产品
	    		Integer columnId = getParaToInt("columnId");
	    		Product product = new Product();
	    		product.set("pro_title", titleName);
	    		product.set("columnid", columnId);
	    		product.set("pro_img", path);
	    		product.set("addtime", new Date());
	    		product.save();
	            
	            uploadFile.getFile().renameTo(new File(filePath)); // 重命名并上传文件

	            //返回任意数据即代表上传成功
	            jsonObject.put("filePath", filePath);
	            jsonObject.put("status", "success");
	        }else {
	            //返回任意数据即代表上传成功
	            jsonObject.put("error", "未选择文件");
	            jsonObject.put("status", "error");
	        }

		 renderJson(jsonObject.toJSONString());
	}
	
	/**
	 * 新闻添加
	 */
	public void addProduct(){
		getData();
		List<Column> twoThemeColList = Column.dao.getColumnByType(Constant.COLUMN_TYPE_PRODUCT);
		setAttr("twoThemeColList", twoThemeColList);
		render("/admin/html/product/addproduct.html");
	}
	
	/**
	 * 产品添加
	 */
	public void saveProduct(){
		String tile = getPara("themeTile");
		Integer columnId = getParaToInt("columnId");
		String testimg = getPara("testimg");
		String context = getPara("context");
		Product product = new Product();
		product.set("pro_title", tile);
		product.set("columnid", columnId);
		product.set("pro_img", testimg);
		product.set("pro_context", context);
		product.set("addtime", new Date());
		if(product.save()){
			renderJson(1);
		}else{
			renderJson(2);
		}
	}
	
	/**
	 * 产品修改
	 */
	public void upProduct(){
		getData();
		Integer id = getParaToInt("id");
		Product product = Product.dao.findById(id);
		List<Column> twoThemeColList = Column.dao.getColumnByType(Constant.COLUMN_TYPE_PRODUCT);
		setAttr("twoThemeColList", twoThemeColList);
		setAttr("product", product);
		render("/admin/html/product/upproduct.html");
	}
	
	/**
	 * 保存产品修改信息
	 */
	public void saveUpProduct(){
		String tile = getPara("themeTile");
		Integer columnId = getParaToInt("columnId");
		Integer id = getParaToInt("productId");
		String testimg = getPara("testimg");
		String context = getPara("context");
		Product product = Product.dao.findById(id);
		product.set("pro_title", tile);
		product.set("columnid", columnId);
		product.set("pro_img", testimg);
		product.set("pro_context", context);
		if(product.update()){
			renderJson(1);
		}else{
			renderJson(2);
		}
	}
	
	/**
	 * 删除产品信息
	 */
	public void delProduct(){
		Integer id = getParaToInt("id");
		Product product = Product.dao.findById(id);
		if(product.delete()){
			renderJson(1);
		}else{
			renderJson(2);
		}
	}
	
}
