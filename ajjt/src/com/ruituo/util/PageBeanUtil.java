package com.ruituo.util;

public class PageBeanUtil {
	//封装PageBean
	public static PageBean wrapperToPageBean(int nowPage,int everyPageCount,int allCount){
		if(allCount == 0){
			allCount = 1;
		}
		PageBean p=new PageBean();
		p.setFirstPage(1);
		p.setNowPage(nowPage);
		p.setAllCount(allCount);
		p.setEveryPageCount(everyPageCount);
		int pageSize=p.getEveryPageCount();//获取每页显示的记录条数
		//设置总页数
		p.setAllPages(allCount%pageSize==0?(allCount/pageSize):(allCount/pageSize+1));
		//若当前页是1，则上一页的值设置为1；若当前页不是1，则上一页的值设置为当前页减1
		p.setPreviusPage(nowPage==1?1:nowPage-1);
		//若当前页小于总页数，则下一页的值是当前页加1；否则下一页的值为总页数
		p.setNextPage(nowPage<p.getAllPages()?nowPage+1:p.getAllPages());
		//设置尾页的值为总页数
		p.setLastPage(p.getAllPages());
		return p;
	}
	//获取每一页的起始记录
		public static int getStartRecord(int page,int everyPageCount){
			int getStartRecord=(page-1)*everyPageCount;
			return getStartRecord;
		}
}
