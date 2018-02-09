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
import com.ruituo.model.News;
import com.ruituo.util.PageBean;
import com.ruituo.util.PageBeanUtil;

@Before(LoginInterceptor.class)
public class NewsController extends BaseController {

	/**
	 * 获得新闻列表
	 */
	public void getNewsList(){
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
		int allCount = News.dao.countNewsList(columnId);
		String pageName = "/news/getNewsList";
		
		String parameters = "&columnId="+columnId;
		if(getPara("parameters")!= null){
			parameters = getPara("parameters");
		}
		
		List<News> newsList = News.dao.getNewsList(columnId,PageBeanUtil.getStartRecord(nowPage,pageSize),pageSize);
		PageBean pageBean=PageBeanUtil.wrapperToPageBean(nowPage,pageSize,allCount);
		List<Column> twoThemeColList = Column.dao.getColumnByType(Constant.COLUMN_TYPE_NEWS);
		
		setAttr("twoThemeColList", twoThemeColList);
		setAttr("parameters", parameters);
		setAttr("pageName", pageName);
		setAttr("pageBean", pageBean);
		setAttr("nowPage", nowPage);
		setAttr("newsList", newsList);
		setAttr("columnId", columnId);
		render("/admin/html/news/newslist.html");
		
	}
	
	/**
	 * 新闻批量添加
	 */
	public void addPachNews(){
		getData();
		List<Column> twoThemeColList = Column.dao.getColumnByType(Constant.COLUMN_TYPE_NEWS);
		setAttr("twoThemeColList", twoThemeColList);
		render("/admin/html/news/machupload.html");
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
	            
	            /**
	             * 保存新闻
	             */
	            Integer columnId = getParaToInt("columnId");
	            News news = new News();
	    		news.set("news_title", titleName);
	    		news.set("columnid", columnId);
	    		news.set("news_img", path);
	    		news.set("addtime", new Date());
	    		news.save();
	            
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
	public void addNews(){
		getData();
		List<Column> twoThemeColList = Column.dao.getColumnByType(Constant.COLUMN_TYPE_NEWS);
		setAttr("twoThemeColList", twoThemeColList);
		render("/admin/html/news/addnews.html");
	}
	
	/**
	 * 保存新闻信息
	 */
	public void saveNews(){
		String themeTile = getPara("themeTile");
		Integer columnId = getParaToInt("columnId");
		String testimg = getPara("testimg");
		String context = getPara("context");
		News news = new News();
		news.set("news_title", themeTile);
		news.set("columnid", columnId);
		news.set("news_img", testimg);
		news.set("news_context", context);
		news.set("addtime", new Date());
		if(news.save()){
			renderJson(1);
		}else{
			renderJson(2);
		}
	}
	
	/**
	 * 修改新闻
	 */
	public void upNews(){
		getData();
		Integer id = getParaToInt("id");
		News news = News.dao.findById(id); 
		List<Column> twoThemeColList = Column.dao.getColumnByType(Constant.COLUMN_TYPE_NEWS);
		setAttr("twoThemeColList", twoThemeColList);
		setAttr("news", news);
		render("/admin/html/news/upnews.html");
	}
	
	/**
	 * 保存修改新闻信息
	 */
	public void saveUpNews(){
		String themeTile = getPara("themeTile");
		Integer columnId = getParaToInt("columnId");
		Integer id = getParaToInt("newsId");
		String testimg = getPara("testimg");
		String context = getPara("context");
		News news = News.dao.findById(id);
		news.set("news_title", themeTile);
		news.set("columnid", columnId);
		news.set("news_img", testimg);
		news.set("news_context", context);
		if(news.update()){
			renderJson(1);
		}else{
			renderJson(2);
		}
	}
	
	/**
	 * 删除新闻
	 */
	public void delNews(){
		Integer id = getParaToInt("id");
		News news = News.dao.findById(id);
		if(news.delete()){
			renderJson(1);
		}else{
			renderJson(2);
		}
	}
	
}
