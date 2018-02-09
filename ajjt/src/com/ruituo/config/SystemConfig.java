package com.ruituo.config;

import org.apache.log4j.Logger;

import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.ext.handler.ContextPathHandler;
import com.jfinal.ext.handler.FakeStaticHandler;
import com.jfinal.ext.interceptor.SessionInViewInterceptor;
import com.jfinal.kit.PathKit;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.c3p0.C3p0Plugin;
import com.ruituo.controller.IndexController;
import com.ruituo.controller.admin.AadviceController;
import com.ruituo.controller.admin.ColumnController;
import com.ruituo.controller.admin.FileController;
import com.ruituo.controller.admin.LinkController;
import com.ruituo.controller.admin.MessageController;
import com.ruituo.controller.admin.NewsController;
import com.ruituo.controller.admin.ProductController;
import com.ruituo.controller.admin.QQController;
import com.ruituo.controller.admin.ResumeController;
import com.ruituo.controller.admin.ThemeController;
import com.ruituo.controller.admin.VideoController;
import com.ruituo.controller.admin.WebController;
import com.ruituo.controller.admin.WelcomeController;
import com.ruituo.controller.login.LoginController;
import com.ruituo.model.Aadvice;
import com.ruituo.model.Column;
import com.ruituo.model.File;
import com.ruituo.model.Link;
import com.ruituo.model.Message;
import com.ruituo.model.News;
import com.ruituo.model.Product;
import com.ruituo.model.QQ;
import com.ruituo.model.Resume;
import com.ruituo.model.Theme;
import com.ruituo.model.User;
import com.ruituo.model.Video;
import com.ruituo.model.Web;
import com.ruituo.util.UpImgUtil;


public class SystemConfig extends JFinalConfig {
	
	private static final Logger log = Logger.getLogger(SystemConfig.class);
	public final static String relativePath = "upload/";   //相对路径
    public final static String absolutePath = PathKit.getWebRootPath() + "/" + relativePath;
	
	/**
	 * 配置常量
	 */
	public void configConstant(Constants me) {
		PropKit.use("a_little_config.txt");
		me.setDevMode(true);   //调试模式（开发时候设置为true）
		me.setEncoding("UTF-8");
		me.setError404View("/404.html");
        me.setError500View("/500.html");
	}
	
	/**
	 * 配置路由
	 */
	public void configRoute(Routes me) {
		me.add("/",IndexController.class);
		//后台路由
		
		me.add("/img",UpImgUtil.class);
		me.add("/wel",WelcomeController.class);
		me.add("/web",WebController.class);
		me.add("/mes",MessageController.class);
		me.add("/column",ColumnController.class);
		me.add("/qq",QQController.class);
		me.add("/link",LinkController.class);
		me.add("/theme",ThemeController.class);
		me.add("/news",NewsController.class);
		me.add("/pro",ProductController.class);
		me.add("/adv",AadviceController.class);
		me.add("/resume",ResumeController.class);
		me.add("/video",VideoController.class);
		me.add("/file",FileController.class);
		//登陆路由
		me.add("/login",LoginController.class);
		
	}
	
	public static C3p0Plugin createC3p0Plugin() {
		return new C3p0Plugin(PropKit.get("jdbcUrl"), PropKit.get("user"), PropKit.get("password").trim());
	}
	
	/**
	 * 配置插件
	 */
	public void configPlugin(Plugins me) {
		C3p0Plugin C3p0Plugin = createC3p0Plugin();
		me.add(C3p0Plugin);
		
		log.warn("MySql数据库链接启动...");
		ActiveRecordPlugin arp = new ActiveRecordPlugin(C3p0Plugin);
		me.add(arp);
		arp.addMapping("t_web", Web.class);
		arp.addMapping("t_message", Message.class);
		arp.addMapping("t_column", Column.class);
		arp.addMapping("t_qq", QQ.class);
		arp.addMapping("t_link", Link.class);
		arp.addMapping("t_theme", Theme.class);
		arp.addMapping("t_news", News.class);
		arp.addMapping("t_product", Product.class);
		arp.addMapping("t_user", User.class);
		arp.addMapping("t_advice", Aadvice.class);
		arp.addMapping("t_resume", Resume.class);
		arp.addMapping("t_video", Video.class);
		arp.addMapping("t_file", File.class);
	}
	
	/**
	 * 配置全局拦截器
	 */
	public void configInterceptor(Interceptors me) {
		me.add(new SessionInViewInterceptor()); //全局Session
	}
	
	/**
	 * 配置处理器
	 */
	public void configHandler(Handlers me){
		me.add(new ContextPathHandler("path"));
	}
	
}
