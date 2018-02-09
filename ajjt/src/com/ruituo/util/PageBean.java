package com.ruituo.util;

public class PageBean {
	private int nowPage;//当前页面
	private int previusPage;//上一页
	private int nextPage;//下一页
	private int firstPage;//首页
	private int lastPage;//尾页
	private int allPages;//总页数
	private int allCount;//总记录数
	private int everyPageCount;//每页显示的记录条数
	public int getNowPage() {
		return nowPage;
	}
	public void setNowPage(int nowPage) {
		this.nowPage = nowPage;
	}
	public int getPreviusPage() {
		return previusPage;
	}
	public void setPreviusPage(int previusPage) {
		this.previusPage = previusPage;
	}
	public int getNextPage() {
		return nextPage;
	}
	public void setNextPage(int nextPage) {
		this.nextPage = nextPage;
	}
	public int getFirstPage() {
		return firstPage;
	}
	public void setFirstPage(int firstPage) {
		this.firstPage = firstPage;
	}
	public int getLastPage() {
		return lastPage;
	}
	public void setLastPage(int lastPage) {
		this.lastPage = lastPage;
	}
	public int getAllPages() {
		return allPages;
	}
	public void setAllPages(int allPages) {
		this.allPages = allPages;
	}
	public int getAllCount() {
		return allCount;
	}
	public void setAllCount(int allCount) {
		this.allCount = allCount;
	}
	public int getEveryPageCount() {
		return everyPageCount;
	}
	public void setEveryPageCount(int everyPageCount) {
		this.everyPageCount = everyPageCount;
	}
	@Override
	public String toString() {
		return "PageBean [nowPage=" + nowPage + ", previusPage=" + previusPage
				+ ", nextPage=" + nextPage + ", firstPage=" + firstPage
				+ ", lastPage=" + lastPage + ", allPages=" + allPages
				+ ", allCount=" + allCount + ", everyPageCount="
				+ everyPageCount + "]";
	}
	public PageBean(int nowPage, int previusPage, int nextPage, int firstPage,
			int lastPage, int allPages, int allCount, int everyPageCount) {
		super();
		this.nowPage = nowPage;
		this.previusPage = previusPage;
		this.nextPage = nextPage;
		this.firstPage = firstPage;
		this.lastPage = lastPage;
		this.allPages = allPages;
		this.allCount = allCount;
		this.everyPageCount = everyPageCount;
	}
	public PageBean() {
		super();
	}
	
}
