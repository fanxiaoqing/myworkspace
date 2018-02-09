var pro = "";

function getPath(path){
	this.pro = path;
}

/***
 * 查询栏目下主题
 */
function changeNews(){
	var columnId = $.trim($("#columnId").val());
	location.href = pro+"/news/getNewsList?columnId="+columnId;
}

/**
 * 验证新闻信息
 */
function checkTitle(){
	var title = $.trim($("#themeTile").val());
	var falg = true;
	if(title == null || title == ""){
		layer.tips("请输入标题", "#themeTile", {tips: [2, "#E34127"],time: 1000});
		falg = false;
	}
	return falg;
}

/**
 * 主题信息验证
 */
function checkType(){
	var columnId = $.trim($("#columnId").val());
	var falg = true;
	if(columnId == null || columnId == "0"){
		layer.tips("请选择栏目类型", "#columnId", {tips: [2, "#E34127"],time: 1000});
		falg = false;
	}
	return falg;
}

/**
 * 主题信息验证
 */
function checkImg(){
	var testimg = $.trim($("#testimg").val());
	var falg = true;
	if(testimg == null || testimg == ""){
		layer.alert("请上传图片");
		falg = false;
	}
	return falg;
}

/**
 * 主题信息验证
 */
function checkContext(){
	var context = ue.getContent();
	var falg = true;
	if(context == null || context == ""){
		layer.alert("请填写内容");
		falg = false;
	}
	return falg;
}

/**
 * 保存新闻信息
 */
function saveNews(){
	if(checkTitle()&&checkType()){
		var themeTile = $.trim($("#themeTile").val());
		var columnId = $.trim($("#columnId").val());
		var testimg = $.trim($("#testimg").val());
		var context = ue.getContent();
		$.ajax({
			type : "post",
			url : pro+"/news/saveNews",
			async : false,
			data : {
				themeTile : themeTile,
				columnId : columnId,
				testimg : testimg,
				context : context
			},
			dataType:"json",
			success : function(data){
				if(data == "1"){
					layer.alert("添加成功", {
						skin: "layui-layer-molv",
						closeBtn: 0
					}, function(){
						   location.href = pro+"/news/getNewsList?columnId="+columnId;
					});
				}else{
					layer.alert("添加失败",{skin: "layui-layer-molv"});
				}
			}
		});
	}
}

/**
 * 保存新闻信息
 */
function saveUpNews(){
	if(checkTitle()&&checkType()){
		var themeTile = $.trim($("#themeTile").val());
		var newsId = $.trim($("#newsId").val());
		var columnId = $.trim($("#columnId").val());
		var testimg = $.trim($("#testimg").val());
		var context = ue.getContent();
		$.ajax({
			type : "post",
			url : pro+"/news/saveUpNews",
			async : false,
			data : {
				newsId : newsId,
				themeTile : themeTile,
				columnId : columnId,
				testimg : testimg,
				context : context
			},
			dataType:"json",
			success : function(data){
				if(data == "1"){
					layer.alert("修改成功", {
						skin: "layui-layer-molv",
						closeBtn: 0
					}, function(){
						   location.href = pro+"/news/getNewsList?columnId="+columnId;
					});
				}else{
					layer.alert("修改失败",{skin: "layui-layer-molv"});
				}
			}
		});
	}
}

/**
 * 删除新闻信息
 */
function delNews(id){
	var columnId = $.trim($("#columnId").val());
	$.ajax({
		type : "post",
		url : pro+"/news/delNews",
		async : false,
		data : {
			id : id
		},
		dataType:"json",
		success : function(data){
			if(data == "1"){
				layer.alert("删除成功", {
					skin: "layui-layer-molv",
					closeBtn: 0
				}, function(){
					   location.href = pro+"/news/getNewsList?columnId="+columnId;
				});
			}else{
				layer.alert("删除失败",{skin: "layui-layer-molv"});
			}
		}
	});
}

/**
 * 修改新闻
 */
function upNews(id){
	location.href = pro+"/news/upNews?id="+id;
}