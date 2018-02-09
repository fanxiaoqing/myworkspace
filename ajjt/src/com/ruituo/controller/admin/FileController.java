package com.ruituo.controller.admin;

import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.upload.UploadFile;
import com.ruituo.common.BaseController;
import com.ruituo.common.Constant;
import com.ruituo.config.SystemConfig;
import com.ruituo.model.File;
import com.ruituo.util.PageBean;
import com.ruituo.util.PageBeanUtil;

public class FileController extends BaseController {

	/**
	 * 获得视频列表
	 */
	public void getFileList(){
		getData();
		int nowPage = 1;	//当前页数
		if(getPara("pageNum")!=null){
			nowPage=Integer.parseInt(getPara("pageNum"));
		}
		int pageSize = Constant.EACH_PAGE_PAGESIZE;
		
		int allCount = File.dao.countFileListIndex();
		String pageName = "/file/getFileList";
		
		String parameters = "";
		if(getPara("parameters")!= null){
			parameters = getPara("parameters");
		}
		List<File> fileList = File.dao.getFileListIndex(PageBeanUtil.getStartRecord(nowPage,pageSize),pageSize);
		PageBean pageBean=PageBeanUtil.wrapperToPageBean(nowPage,pageSize,allCount);
		
		setAttr("parameters", parameters);
		setAttr("pageName", pageName);
		setAttr("pageBean", pageBean);
		setAttr("fileList", fileList);
		render("/admin/html/file/filelist.html");
	}
	
	/**
	 * 添加文件
	 */
	public void addFile(){
		getData();
		render("/admin/html/file/addfile.html");
	}
	
	/**
	 * 保存文件
	 */
	public void uploadFile(){
		UploadFile uploadFile=this.getFile();
		JSONObject jsonObject = new JSONObject();
		if(uploadFile != null){
			String fileName = uploadFile.getFileName();
			String extentionName = fileName.substring(fileName.lastIndexOf(".")); // 后缀名
            String titleName = fileName.substring(0,fileName.lastIndexOf(".")); // 后缀名
		
            String filePath = SystemConfig.absolutePath +  fileName;// 文件完整路径
            String path = SystemConfig.relativePath + fileName;
            
            String themeTile = getPara("themeTile");
            String enThemeTile = getPara("enThemeTile");
    		String context = getPara("context");
    		System.out.println("1--");
            /*
             * 上传图片
             */
            File file = new File();
    		file.set("filename", themeTile);
    		file.set("enfilename", enThemeTile);
    		file.set("file", path);
    		file.set("context", context);
    		file.set("addtime", new Date());
    		if(file.save()){
    			renderJson(1);
    		}else{
    			renderJson(2);
    		}
    		uploadFile.getFile().renameTo(new java.io.File(filePath));
    		jsonObject.put("filePath", filePath);
            jsonObject.put("status", "success");
		}else{
			  //返回任意数据即代表上传成功
            jsonObject.put("error", "未选择文件");
            jsonObject.put("status", "error");
		}
		renderJson(jsonObject.toJSONString());
	}
	
	/**
	 * 更新文件
	 */
	public void preupFile(){
		getData();
		Integer id = getParaToInt("id");
		File file = File.dao.findById(id);
		setAttr("file", file);
		render("/admin/html/file/upfile.html");
	}

	
	/**
	 * 删除文件
	 */
	public void delFile(){
		Integer id = getParaToInt("id");
		File file = File.dao.findById(id);
		if(file.delete()){
			renderJson(1);
		}else{
			renderJson(2);
		}
	}	
		
	
}
