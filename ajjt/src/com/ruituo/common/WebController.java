package com.ruituo.common;

import java.util.List;

import com.jfinal.core.Controller;
import com.ruituo.model.Column;
import com.ruituo.model.Theme;
import com.ruituo.model.Web;

public class WebController extends Controller {

	/**
	 * 获得网页的基础数据
	 */
	public void getWebData(){
		//网站信息
		Web web = Web.dao.getWeb();
		setAttr("web", web);
		//导航信息
		List<Column> oneColList = Column.dao.getColumnOneContents(Constant.COLUMN_TYPE_NEWS, Constant.COLUMN_TYPE_PRODUCT);
		List<Column> twoColList = Column.dao.getColumnTwoContents(Constant.COLUMN_TYPE_NEWS, Constant.COLUMN_TYPE_PRODUCT);
		setAttr("oneColList", oneColList);
		setAttr("twoColList", twoColList);
		//轮播图信息
		List<Theme> banner = Theme.dao.getThemeListByColumnIds(236);
		setAttr("banner", banner);
		
		
		List<Theme> bannersj = Theme.dao.getThemeListByColumnIds(222);
		setAttr("bannersj", bannersj);	
		
		
	}
	
}
