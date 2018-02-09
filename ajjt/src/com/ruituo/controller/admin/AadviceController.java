package com.ruituo.controller.admin;

import java.util.List;
import com.ruituo.common.BaseController;
import com.ruituo.common.Constant;
import com.ruituo.model.Aadvice;
import com.ruituo.util.PageBean;
import com.ruituo.util.PageBeanUtil;

public class AadviceController extends BaseController {
	
	/**
	 * goto 用户建议
	 */
	public void gotoAadviceList(){
		getData();
		int nowPage = 1;	//当前页数
		if(getPara("pageNum")!=null){
			nowPage=Integer.parseInt(getPara("pageNum"));
		}
		int pageSize = Constant.EACH_PAGE_PAGESIZE;
		
		int allCount = Aadvice.dao.countAadviceList();
		String pageName = "/adv/gotoAadviceList";
		
		String parameters = "";
		if(getPara("parameters")!= null){
			parameters = getPara("parameters");
		}
		List<Aadvice> aadvlist = Aadvice.dao.getAadviceList(PageBeanUtil.getStartRecord(nowPage,pageSize),pageSize);
		PageBean pageBean=PageBeanUtil.wrapperToPageBean(nowPage,pageSize,allCount);
		
		setAttr("parameters", parameters);
		setAttr("pageName", pageName);
		setAttr("pageBean", pageBean);
		setAttr("aadvlist", aadvlist);
		render("/admin/html/aadvice/aadvice.html");
	}
	
}
