package com.ruituo.controller.admin;

import java.util.List;
import com.ruituo.common.BaseController;
import com.ruituo.common.Constant;
import com.ruituo.model.Resume;
import com.ruituo.util.PageBean;
import com.ruituo.util.PageBeanUtil;

public class ResumeController extends BaseController {

	/**
	 * 获得客服QQ列表
	 */
	public void getResumeList(){
		getData();
		int nowPage = 1;	//当前页数
		if(getPara("pageNum")!=null){
			nowPage=Integer.parseInt(getPara("pageNum"));
		}
		int pageSize = Constant.EACH_PAGE_PAGESIZE;
		
		int allCount = Resume.dao.countResumeList();
		String pageName = "/resume/getResumeList";
		
		String parameters = "";
		if(getPara("parameters")!= null){
			parameters = getPara("parameters");
		}
		List<Resume> resumeList = Resume.dao.getResumeList(PageBeanUtil.getStartRecord(nowPage,pageSize),pageSize);
		PageBean pageBean=PageBeanUtil.wrapperToPageBean(nowPage,pageSize,allCount);
		
		setAttr("parameters", parameters);
		setAttr("pageName", pageName);
		setAttr("pageBean", pageBean);
		setAttr("resumeList", resumeList);
		render("/admin/html/resume/resumelist.html");
	}
	
	/**
	 * 查看简历
	 */
	public void preResume(){
		getData();
		Integer id = getParaToInt("id");
		Resume resume = Resume.dao.findById(id);
		setAttr("resume", resume);
		render("/admin/html/resume/resumedeatil.html");
	}
	
	/**
	 * 简历删除
	 */
	public void delResume(){
		Integer id = getParaToInt("id");
		Resume resume = Resume.dao.findById(id);
		if(resume.delete()){
			renderJson(1);
		}else{
			renderJson(2);
		}
	}
	
}
