package com.ruituo.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.catalina.connector.Request;

import com.jfinal.plugin.activerecord.Db;
import com.ruituo.common.Constant;
import com.ruituo.common.WebController;
import com.ruituo.model.Aadvice;
import com.ruituo.model.Column;
import com.ruituo.model.File;
import com.ruituo.model.Message;
import com.ruituo.model.News;
import com.ruituo.model.Product;
import com.ruituo.model.Resume;
import com.ruituo.model.Theme;
import com.ruituo.model.Video;
import com.ruituo.model.Web;
import com.ruituo.util.PageBean;
import com.ruituo.util.PageBeanUtil;


public class IndexController extends WebController{

	/**
	 * 首页
	 */
	public void index(){
		Web web1 = Web.dao.getWeb();
		Integer count=web1.getInt("web_count");
		count=count+1;
		web1.set("web_count",count);
		web1.update();
		getWebData();
		
		//产品二级栏目
		List<Column> proColumn = Column.dao.getProColumn(Constant.COLUMN_TYPE_PRODUCT);
		setAttr("proColumn", proColumn);
		
		//机动车安全性能检测
		List<Product> proList01 = Product.dao.getShouYeProList(225);
		setAttr("proList01",proList01);	
		
		//机动车环保性能检测
		List<Product> proList02 = Product.dao.getShouYeProList(226);
		setAttr("proList02",proList02);
		
		//机动车综合性能检测
		List<Product> proList03 = Product.dao.getShouYeProList(227);
		setAttr("proList03",proList03);
		
		//制造厂整车质检
		List<Product> proList04 = Product.dao.getShouYeProList(228);
		setAttr("proList04",proList04);
		
		//案例
		List<Product> anli = Product.dao.getProductTypeList(237);
		setAttr("anli", anli);
		System.out.println("anli="+anli);
		//公司新闻
		List<News> news3 = News.dao.getbowuguan(224);
		setAttr("news3", news3);
		
		
		//集团发展列表
		List<News> news2 = News.dao.getShouYeProductList(227,228,229,230);
		setAttr("news2", news2);
		//公司简介
		News news1 = News.dao.getNewsByColumnId(219);
		setAttr("news1", news1);
		render("/web/index.html");
	}
	
	/**
	 * 关于我们
	 */
	public void about(){
		getWebData();
		Integer columnid = getParaToInt("columnId");
		setAttr("columnId", columnid);
		
		Column oneCol = Column.dao.getColumnById(columnid);
		setAttr("oneCol", oneCol);
		
		Column twoCol = Column.dao.getColumnById(oneCol.getInt("uppid"));
		setAttr("twoCol", twoCol);
		
		List<Column> twoColumnList = Column.dao.getColumnByUppid(oneCol.getInt("uppid"));
		setAttr("twoColumnList", twoColumnList);
		
		List<News> news = News.dao.getNewsListByColumnId(columnid);
		setAttr("news", news);
		render("/web/about.html");
	}
	/*
	 * 
	 * 下载页
	 */
	public void file(){
		getWebData();	
		
		Integer columnid = 229;
		setAttr("columnId", columnid);
		
		Column oneCol = Column.dao.getColumnById(columnid);
		setAttr("oneCol", oneCol);
		
		Column twoCol = Column.dao.getColumnById(oneCol.getInt("uppid"));
		setAttr("twoCol", twoCol);
		
		List<Column> twoColumnList = Column.dao.getColumnByUppid(oneCol.getInt("uppid"));
		setAttr("twoColumnList", twoColumnList);
		//分页开始
		int nowPage = 1;	//当前页数
		if(getPara("pageNum")!=null){
			nowPage=Integer.parseInt(getPara("pageNum"));
		}
		int pageSize = 12;
		int allCount = File.dao.countFileList();
		String pageName = "/file";
		
		String parameters = "&columnId="+columnid;
		if(getPara("parameters")!= null){
			parameters = getPara("parameters");
		}
		//对应栏目的新闻内容
		List<File> file = File.dao.getFileList(PageBeanUtil.getStartRecord(nowPage,pageSize),pageSize);
		PageBean pageBean=PageBeanUtil.wrapperToPageBean(nowPage,pageSize,allCount);						
		setAttr("parameters", parameters);	
		setAttr("pageName", pageName);	
		setAttr("pageBean", pageBean);				
		setAttr("file", file);
		render("/web/download.html");	
	}
	
	/*
	 * 
	 * 下载
	 */
	public void download() {
		Integer id = getParaToInt("id");
		File file1 = File.dao.getFileIndex(id);
		setAttr("file1", file1);
		
		String filePath= getRequest().getServletContext().getRealPath("/" + file1.getStr("file"));  
		
		java.io.File file = new java.io.File(filePath);

		if (file.isFile()) {
			renderFile(file);
			return;
		}
		renderNull();

	}
	/**
	 * 新闻中心
	 * <p>Description: </p>
	 * @date 2017年3月15日 下午2:21:38
	 * @param
	 */
	public void news(){
		getWebData();
		Integer columnid = getParaToInt("columnId");
		setAttr("columnId", columnid);
		//查询二级栏目对象
		Column oneCol = Column.dao.getColumnById(columnid);
		setAttr("oneCol", oneCol);
		//查询一级栏目对象
		Column twoCol = Column.dao.getColumnById(oneCol.getInt("uppid"));
		setAttr("twoCol", twoCol);
		//查询二级栏目集合
		List<Column> twoColumnList = Column.dao.getColumnByUppid(oneCol.getInt("uppid"));
		setAttr("twoColumnList", twoColumnList);
		
		//分页开始
		int nowPage = 1;	//当前页数
		if(getPara("pageNum")!=null){
			nowPage=Integer.parseInt(getPara("pageNum"));
		}
		int pageSize = 6;
		int allCount = News.dao.countNewsList(columnid);
		String pageName = "/news";
		
		String parameters = "&columnId="+columnid;
		if(getPara("parameters")!= null){
			parameters = getPara("parameters");
		}
		
		//对应栏目的新闻内容
		List<News> news = News.dao.getNewsListByColumnId(columnid,PageBeanUtil.getStartRecord(nowPage,pageSize),pageSize);
		PageBean pageBean=PageBeanUtil.wrapperToPageBean(nowPage,pageSize,allCount);
		
		setAttr("news", news);
		setAttr("parameters", parameters);
		setAttr("pageName", pageName);
		setAttr("pageBean", pageBean);
		render("/web/news.html");
	}
	/**
	 * 人事招聘
	 * <p>Description: </p>
	 * @date 2017年3月15日 下午2:21:38
	 * @param
	 */
	public void recruit(){
		getWebData();
		Integer columnid = getParaToInt("columnId");
		setAttr("columnId", columnid);
		//查询二级栏目对象
		Column oneCol = Column.dao.getColumnById(columnid);
		setAttr("oneCol", oneCol);
		//查询一级栏目对象
		Column twoCol = Column.dao.getColumnById(oneCol.getInt("uppid"));
		setAttr("twoCol", twoCol);
		//查询二级栏目集合
		List<Column> twoColumnList = Column.dao.getColumnByUppid(oneCol.getInt("uppid"));
		setAttr("twoColumnList", twoColumnList);
		
		//分页开始
		int nowPage = 1;	//当前页数
		if(getPara("pageNum")!=null){
			nowPage=Integer.parseInt(getPara("pageNum"));
		}
		int pageSize = 6;
		int allCount = News.dao.countNewsList(columnid);
		String pageName = "/recruit";
		
		String parameters = "&columnId="+columnid;
		if(getPara("parameters")!= null){
			parameters = getPara("parameters");
		}
		
		//对应栏目的新闻内容
		List<News> news = News.dao.getNewsListByColumnId(columnid,PageBeanUtil.getStartRecord(nowPage,pageSize),pageSize);
		PageBean pageBean=PageBeanUtil.wrapperToPageBean(nowPage,pageSize,allCount);
		
		setAttr("news", news);
		setAttr("parameters", parameters);
		setAttr("pageName", pageName);
		setAttr("pageBean", pageBean);
		render("/web/recruit.html");
	}
	
	
	/**
	 * 新闻详情页
	 */
	public void news_xq(){
		
		getWebData();
		
		
		//产品
		Integer id = getParaToInt("id");
		News news = News.dao.findFirst("SELECT * FROM t_news WHERE id = " + id);
		setAttr("news", news);
		
		Integer count=news.getInt("news_count");
		
		count=count+1;
		news.set("news_count",count);
		news.update();
		
		//上下篇
		Integer columnid = news.getInt("columnid");
		News maxnews=News.dao.findFirst("select sc.* from (select t.* from t_column c,t_news t where c.id = t.columnid and c.id ="+columnid+" order by t.id asc) sc where sc.id>"+id+" order by sc.id asc limit 0,1");
		setAttr("maxnews",maxnews);
		News minnews=News.dao.findFirst("select sc.* from (select t.* from t_column c,t_news t where c.id = t.columnid and c.id ="+columnid+" order by t.id asc) sc where sc.id<"+id+" order by sc.id desc limit 0,1");
		setAttr("minnews",minnews);
			
		//查询二级栏目对象
		Column oneCol = Column.dao.getColumnById(news.getInt("columnid"));
		setAttr("oneCol", oneCol);
		//查询一级栏目对象
		Column twoCol = Column.dao.getColumnById(oneCol.getInt("uppid"));
		setAttr("twoCol", twoCol);
		//查询二级栏目集合
		List<Column> twoColumnList = Column.dao.getColumnByUppid(oneCol.getInt("uppid"));
		setAttr("twoColumnList", twoColumnList);		
		render("/web/news_details.html");
	}
	/*
	 * 
	 * 招聘详情
	 */
	public void recruit_xq(){
		getWebData();
		
		
		//产品
		Integer id = getParaToInt("id");
		News news = News.dao.findFirst("SELECT * FROM t_news WHERE id = " + id);
		setAttr("news", news);
		
		//上下篇
		Integer columnid = news.getInt("columnid");
		News maxnews=News.dao.findFirst("select sc.* from (select t.* from t_column c,t_news t where c.id = t.columnid and c.id ="+columnid+" order by t.id asc) sc where sc.id>"+id+" order by sc.id asc limit 0,1");
		setAttr("maxnews",maxnews);
		News minnews=News.dao.findFirst("select sc.* from (select t.* from t_column c,t_news t where c.id = t.columnid and c.id ="+columnid+" order by t.id asc) sc where sc.id<"+id+" order by sc.id desc limit 0,1");
		setAttr("minnews",minnews);
			
		//查询二级栏目对象
		Column oneCol = Column.dao.getColumnById(news.getInt("columnid"));
		setAttr("oneCol", oneCol);
		//查询一级栏目对象
		Column twoCol = Column.dao.getColumnById(oneCol.getInt("uppid"));
		setAttr("twoCol", twoCol);
		//查询二级栏目集合
		List<Column> twoColumnList = Column.dao.getColumnByUppid(oneCol.getInt("uppid"));
		setAttr("twoColumnList", twoColumnList);		
		render("/web/recruit_details.html");
	}
	
	
	/**
	 * 关于我们详情
	 */
	public void about_01(){
		getWebData();
		Integer columnid = getParaToInt("columnId");
		setAttr("columnId", columnid);
		
		Column oneCol = Column.dao.getColumnById(columnid);
		setAttr("oneCol", oneCol);
		
		Column twoCol = Column.dao.getColumnById(oneCol.getInt("uppid"));
		setAttr("twoCol", twoCol);
		
		List<Column> twoColumnList = Column.dao.getColumnByUppid(oneCol.getInt("uppid"));
		setAttr("twoColumnList", twoColumnList);
		
		List<News> news = News.dao.getNewsListByColumnId(columnid);
		setAttr("news", news);
		
		render("/web/about_01.html");;
	}
	
	//荣誉
	public void about_rongyu(){
		getWebData();
		Integer columnid = getParaToInt("columnId");
		setAttr("columnId", columnid);
		//查询二级栏目对象
		Column oneCol = Column.dao.getColumnById(columnid);
		setAttr("oneCol", oneCol);
		//查询一级栏目对象
		Column twoCol = Column.dao.getColumnById(oneCol.getInt("uppid"));
		setAttr("twoCol", twoCol);
		//查询二级栏目集合
		List<Column> twoColumnList = Column.dao.getColumnByUppid(oneCol.getInt("uppid"));
		setAttr("twoColumnList", twoColumnList);
		//分页开始
		int nowPage = 1;	//当前页数
		if(getPara("pageNum")!=null){
			nowPage=Integer.parseInt(getPara("pageNum"));
		}
		int pageSize = 6;
		int allCount = Product.dao.countProductList(columnid);
		String pageName = "/product";
		
		String parameters = "&columnId="+columnid;
		if(getPara("parameters")!= null){
			parameters = getPara("parameters");
		}
		
		//对应栏目的产品内容
		List<Product> news = Product.dao.getProductListByColumnId(columnid,PageBeanUtil.getStartRecord(nowPage,pageSize),pageSize);
		PageBean pageBean=PageBeanUtil.wrapperToPageBean(nowPage,pageSize,allCount);
		setAttr("news", news);
		setAttr("parameters", parameters);
		setAttr("pageName", pageName);
		setAttr("pageBean", pageBean);
		render("/web/about-rongyu.html");
	}		
	
	/**
	 * 品牌产品
	 * <p>Description: </p>
	 * @date 2017年3月15日 下午3:00:25
	 * @param
	 */
	public void product(){
		getWebData();
		Integer columnid = getParaToInt("columnId");
		setAttr("columnId", columnid);
		//查询二级栏目对象
		Column oneCol = Column.dao.getColumnById(columnid);
		setAttr("oneCol", oneCol);
		//查询一级栏目对象
		Column twoCol = Column.dao.getColumnById(oneCol.getInt("uppid"));
		setAttr("twoCol", twoCol);
		//查询二级栏目集合
		List<Column> twoColumnList = Column.dao.getColumnByUppid(oneCol.getInt("uppid"));
		setAttr("twoColumnList", twoColumnList);
		//分页开始
		int nowPage = 1;	//当前页数
		if(getPara("pageNum")!=null){
			nowPage=Integer.parseInt(getPara("pageNum"));
		}
		int pageSize = 4;
		int allCount = Product.dao.countProductList(columnid);
		String pageName = "/product";
		
		String parameters = "&columnId="+columnid;
		if(getPara("parameters")!= null){
			parameters = getPara("parameters");
		}
		
		//对应栏目的产品内容
		List<Product> news = Product.dao.getProductListByColumnId(columnid,PageBeanUtil.getStartRecord(nowPage,pageSize),pageSize);
		PageBean pageBean=PageBeanUtil.wrapperToPageBean(nowPage,pageSize,allCount);
		setAttr("news", news);
		setAttr("parameters", parameters);
		setAttr("pageName", pageName);
		setAttr("pageBean", pageBean);
		render("/web/product.html");
	}
	/*
	 * 
	 * 荣誉页面
	 */
	public void honor(){
		getWebData();
		Integer columnid = getParaToInt("columnId");
		setAttr("columnId", columnid);
		//查询二级栏目对象
		Column oneCol = Column.dao.getColumnById(columnid);
		setAttr("oneCol", oneCol);
		//查询一级栏目对象
		Column twoCol = Column.dao.getColumnById(oneCol.getInt("uppid"));
		setAttr("twoCol", twoCol);
		//查询二级栏目集合
		List<Column> twoColumnList = Column.dao.getColumnByUppid(oneCol.getInt("uppid"));
		setAttr("twoColumnList", twoColumnList);
		//分页开始
		int nowPage = 1;	//当前页数
		if(getPara("pageNum")!=null){
			nowPage=Integer.parseInt(getPara("pageNum"));
		}
		int pageSize = 8;
		int allCount = Product.dao.countProductList(columnid);
		String pageName = "/honor";
		
		String parameters = "&columnId="+columnid;
		if(getPara("parameters")!= null){
			parameters = getPara("parameters");
		}
		
		//对应栏目的产品内容
		List<Product> news = Product.dao.getProductListByColumnId(columnid,PageBeanUtil.getStartRecord(nowPage,pageSize),pageSize);
		PageBean pageBean=PageBeanUtil.wrapperToPageBean(nowPage,pageSize,allCount);
		setAttr("news", news);
		setAttr("parameters", parameters);
		setAttr("pageName", pageName);
		setAttr("pageBean", pageBean);
		render("/web/honor.html");
	}
	/*
	 * 
	 * 案例展示
	 */
	public void anli(){
		getWebData();
		Integer columnid = getParaToInt("columnId");
		setAttr("columnId", columnid);
		//查询二级栏目对象
		Column oneCol = Column.dao.getColumnById(columnid);
		setAttr("oneCol", oneCol);
		//查询一级栏目对象
		Column twoCol = Column.dao.getColumnById(oneCol.getInt("uppid"));
		setAttr("twoCol", twoCol);
		//查询二级栏目集合
		List<Column> twoColumnList = Column.dao.getColumnByUppid(oneCol.getInt("uppid"));
		setAttr("twoColumnList", twoColumnList);
		//分页开始
		int nowPage = 1;	//当前页数
		if(getPara("pageNum")!=null){
			nowPage=Integer.parseInt(getPara("pageNum"));
		}
		int pageSize = 8;
		int allCount = Product.dao.countProductList(columnid);
		String pageName = "/anli";
		
		String parameters = "&columnId="+columnid;
		if(getPara("parameters")!= null){
			parameters = getPara("parameters");
		}
		
		//对应栏目的产品内容
		List<Product> news = Product.dao.getProductListByColumnId(columnid,PageBeanUtil.getStartRecord(nowPage,pageSize),pageSize);
		PageBean pageBean=PageBeanUtil.wrapperToPageBean(nowPage,pageSize,allCount);
		setAttr("news", news);
		setAttr("parameters", parameters);
		setAttr("pageName", pageName);
		setAttr("pageBean", pageBean);
		render("/web/case.html");
	}
	
	/**
	 * 案例详情
	 */
	public void case_xq(){
		getWebData();
		
		//产品
		Integer id = getParaToInt("id");
		Product news = Product.dao.findFirst("SELECT * FROM t_product WHERE id = " + id);
		setAttr("news", news);
		
		Integer count=news.getInt("pro_count");
		
		count=count+1;
		news.set("pro_count",count);
		news.update();
		
		//分页篇
		Integer columnid = news.getInt("columnid");
		Product maxnews=Product.dao.findFirst("select sc.* from (select t.* from t_column c,t_product t where c.id = t.columnid and c.id ="+columnid+" order by t.id asc) sc where sc.id>"+id+" order by sc.id asc limit 0,1");
		setAttr("maxnews",maxnews);
		Product minnews=Product.dao.findFirst("select sc.* from (select t.* from t_column c,t_product t where c.id = t.columnid and c.id ="+columnid+" order by t.id asc) sc where sc.id<"+id+" order by sc.id desc limit 0,1");
		setAttr("minnews",minnews);
		
		//查询二级栏目对象
		Column oneCol = Column.dao.getColumnById(news.getInt("columnid"));
		setAttr("oneCol", oneCol);
		//查询一级栏目对象
		Column twoCol = Column.dao.getColumnById(oneCol.getInt("uppid"));
		setAttr("twoCol", twoCol);
		//查询二级栏目集合
		List<Column> twoColumnList = Column.dao.getColumnByUppid(oneCol.getInt("uppid"));
		setAttr("twoColumnList", twoColumnList);
		
		render("/web/case_details.html");
	}
	
	/**
	 * 产品详情
	 */
	public void product_xq(){
		getWebData();
		
		//产品
		Integer id = getParaToInt("id");
		Product news = Product.dao.findFirst("SELECT * FROM t_product WHERE id = " + id);
		setAttr("news", news);
		
		Integer count=news.getInt("pro_count");
		
		count=count+1;
		news.set("pro_count",count);
		news.update();
		
		//上下篇
		Integer columnid = news.getInt("columnid");
		Product maxnews=Product.dao.findFirst("select sc.* from (select t.* from t_column c,t_product t where c.id = t.columnid and c.id ="+columnid+" order by t.id asc) sc where sc.id>"+id+" order by sc.id asc limit 0,1");
		setAttr("maxnews",maxnews);
		Product minnews=Product.dao.findFirst("select sc.* from (select t.* from t_column c,t_product t where c.id = t.columnid and c.id ="+columnid+" order by t.id asc) sc where sc.id<"+id+" order by sc.id desc limit 0,1");
		setAttr("minnews",minnews);
		
		//查询二级栏目对象
		Column oneCol = Column.dao.getColumnById(news.getInt("columnid"));
		setAttr("oneCol", oneCol);
		//查询一级栏目对象
		Column twoCol = Column.dao.getColumnById(oneCol.getInt("uppid"));
		setAttr("twoCol", twoCol);
		//查询二级栏目集合
		List<Column> twoColumnList = Column.dao.getColumnByUppid(oneCol.getInt("uppid"));
		setAttr("twoColumnList", twoColumnList);
		
		render("/web/product_details.html");
	}
	
	//活动专区
	public void activity(){
		getWebData();
		Integer columnid = getParaToInt("columnId");
		setAttr("columnId", columnid);
		//查询二级栏目对象
		Column oneCol = Column.dao.getColumnById(columnid);
		setAttr("oneCol", oneCol);
		//查询一级栏目对象
		Column twoCol = Column.dao.getColumnById(oneCol.getInt("uppid"));
		setAttr("twoCol", twoCol);
		//查询二级栏目集合
		List<Column> twoColumnList = Column.dao.getColumnByUppid(oneCol.getInt("uppid"));
		setAttr("twoColumnList", twoColumnList);
		
		//分页开始
		int nowPage = 1;	//当前页数
		if(getPara("pageNum")!=null){
			nowPage=Integer.parseInt(getPara("pageNum"));
		}
		int pageSize = 3;
		int allCount = News.dao.countNewsList(columnid);
		String pageName = "/activity";
		
		String parameters = "&columnId="+columnid;
		if(getPara("parameters")!= null){
			parameters = getPara("parameters");
		}
		
		//对应栏目的新闻内容
		List<News> news = News.dao.getNewsListByColumnId(columnid,PageBeanUtil.getStartRecord(nowPage,pageSize),pageSize);
		PageBean pageBean=PageBeanUtil.wrapperToPageBean(nowPage,pageSize,allCount);
		
		setAttr("news", news);
		setAttr("parameters", parameters);
		setAttr("pageName", pageName);
		setAttr("pageBean", pageBean);
		render("/web/activity.html");
	}
	
	
	
	/**
	 * 留言
	 * <p>Description: </p>
	 * @date 2017年3月15日 下午2:21:38
	 * @param
	 */
	public void contact(){
		getWebData();
		Integer columnid = getParaToInt("columnId");
		setAttr("columnId", columnid);
		//查询二级栏目对象
		Column oneCol = Column.dao.getColumnById(columnid);
		setAttr("oneCol", oneCol);
		//查询一级栏目对象
		Column twoCol = Column.dao.getColumnById(oneCol.getInt("uppid"));
		setAttr("twoCol", twoCol);
		//查询二级栏目集合
		List<Column> twoColumnList = Column.dao.getColumnByUppid(oneCol.getInt("uppid"));
		setAttr("twoColumnList", twoColumnList);
		
		//对应栏目的新闻内容
		List<News> news = News.dao.getNewsListByColumnId(columnid);
		setAttr("news", news);
		render("/web/contact.html");
	}	
	
	
	
	/**
	 * 保存留言信息
	 */
	public void saveMessage(){
		String userName = getPara("userName");
		String phone = getPara("phone");
		//String qq = getPara("qq");
		String context = getPara("context");
		String email = getPara("email");
		//String address = getPara("address");
		
		Message mess = new Message();
		mess.set("mess_name", userName);
		mess.set("mess_phone", phone);
		//mess.set("mess_qq", qq);
		mess.set("mess_context", context);
		mess.set("mess_email", email);
		//mess.set("mess_ads", address);
		mess.set("addtime", new Date());
		if(mess.save()){
			renderJson(1);
		}else{
			renderJson(2);
		}
	}
	
	
	/**
	 * 地图
	 */
	public void map(){
		getWebData();
		Integer columnid = getParaToInt("columnId");
		Column oneCol = Column.dao.getColumnById(columnid);
		setAttr("oneCol", oneCol);
		Column twoCol = Column.dao.getColumnById(oneCol.getInt("uppid"));
		setAttr("twoCol", twoCol);
		List<Column> twoColumnList = Column.dao.getColumnByUppid(oneCol.getInt("uppid"));
		setAttr("twoColumnList", twoColumnList);
		News news = News.dao.getNewsByColumnId(columnid);
		setAttr("news", news);
		render("/web/map.html");
	}
	
	
}
