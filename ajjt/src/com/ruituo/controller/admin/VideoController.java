package com.ruituo.controller.admin;

import java.util.Date;
import java.util.List;

import com.ruituo.common.BaseController;
import com.ruituo.common.Constant;
import com.ruituo.model.Video;
import com.ruituo.util.PageBean;
import com.ruituo.util.PageBeanUtil;

public class VideoController extends BaseController {

	/**
	 * 获得客服QQ列表
	 */
	public void getVideoList(){
		getData();
		int nowPage = 1;	//当前页数
		if(getPara("pageNum")!=null){
			nowPage=Integer.parseInt(getPara("pageNum"));
		}
		int pageSize = Constant.EACH_PAGE_PAGESIZE;
		
		int allCount = Video.dao.countVideoListIndex();
		String pageName = "/video/getVideoList";
		
		String parameters = "";
		if(getPara("parameters")!= null){
			parameters = getPara("parameters");
		}
		List<Video> videoList = Video.dao.getVideoListIndex(PageBeanUtil.getStartRecord(nowPage,pageSize),pageSize);
		PageBean pageBean=PageBeanUtil.wrapperToPageBean(nowPage,pageSize,allCount);
		
		setAttr("parameters", parameters);
		setAttr("pageName", pageName);
		setAttr("pageBean", pageBean);
		setAttr("videoList", videoList);
		render("/admin/html/video/videolist.html");
	}
	
	/**
	 * 添加视频
	 */
	public void addVideo(){
		getData();
		render("/admin/html/video/addvideo.html");
	}
	
	/**
	 * 保存视频
	 */
	public void saveVideo(){
		String videoName = getPara("videoName");
		Integer columnId = getParaToInt("columnId");
		String videoCode = getPara("videoCode");
		String testimg = getPara("testimg");
		Video video = new Video();
		video.set("videoname", videoName);
		video.set("videourl", videoCode);
		video.set("columnId", columnId);
		video.set("addtime", new Date());
		video.set("videoimg", testimg);
		if(video.save()){
			renderJson(1);
		}else{
			renderJson(2);
		}
	}
	
	/**
	 * 更新视频
	 */
	public void preupVideo(){
		getData();
		Integer id = getParaToInt("id");
		Video video = Video.dao.findById(id);
		setAttr("video", video);
		render("/admin/html/video/upvideo.html");
	}
	
	/**
	 * 保存修改
	 */
	public void saveUpVideo(){
		String videoName = getPara("videoName");
		String videoCode = getPara("videoCode");
		Integer id = getParaToInt("videoId");
		Video video = Video.dao.findById(id);
		video.set("videoname", videoName);
		video.set("videourl", videoCode);
		if(video.update()){
			renderJson(1);
		}else{
			renderJson(2);
		}
	}
	
	/**
	 * QQ删除
	 */
	public void delVideo(){
		Integer id = getParaToInt("id");
		Video video = Video.dao.findById(id);
		if(video.delete()){
			renderJson(1);
		}else{
			renderJson(2);
		}
	}
	
	
}
