package com.ruituo.util;

public class PageUtil {

	public static int getTotalPage(int totalCount, int everyPage){
		return totalCount % everyPage == 0 ? totalCount/everyPage : totalCount / everyPage +1;
	}
}
 