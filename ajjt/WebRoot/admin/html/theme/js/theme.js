var pro = "";

function getPath(path){
	this.pro = path;
}

/***
 * 查询栏目下主题
 */
function changeTheme(){
	var columnId = $.trim($("#columnId").val());
	location.href = pro+"/theme/getThemeList?columnId="+columnId;
}

/**
 * 主题信息验证
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
 * 保存主题信息
 */
function saveTheme(){
	if(checkTitle()&&checkType()&&checkImg()){
		var themeTile = $.trim($("#themeTile").val());
		var columnId = $.trim($("#columnId").val());
		var testimg = $.trim($("#testimg").val());
		var context = ue.getContent();
		$.ajax({
			type : "post",
			url : pro+"/theme/saveTheme",
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
						   location.href = pro+"/theme/getThemeList?columnId="+columnId;
					});
				}else{
					layer.alert("添加失败",{skin: "layui-layer-molv"});
				}
			}
		});
	}
}

/**
 * 保存主题信息
 */
function saveUpTheme(){
	if(checkTitle()&&checkType()&&checkImg()){
		var themeTile = $.trim($("#themeTile").val());
		var themeId = $.trim($("#themeId").val());
		var columnId = $.trim($("#columnId").val());
		var testimg = $.trim($("#testimg").val());
		var context = ue.getContent();
		$.ajax({
			type : "post",
			url : pro+"/theme/saveUpTheme",
			async : false,
			data : {
				themeId : themeId,
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
						   location.href = pro+"/theme/getThemeList?columnId="+columnId;
					});
				}else{
					layer.alert("修改失败",{skin: "layui-layer-molv"});
				}
			}
		});
	}
}


/**
 * 删除主题信息
 */
function delTheme(id){
	var columnId = $.trim($("#columnId").val());
	$.ajax({
		type : "post",
		url : pro+"/theme/delTheme",
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
					   location.href = pro+"/theme/getThemeList?columnId="+columnId;
				});
			}else{
				layer.alert("删除失败",{skin: "layui-layer-molv"});
			}
		}
	});
}


/**
 * 修改主题
 */
function upTheme(id){
	location.href = pro+"/theme/upTheme?id="+id;
}

