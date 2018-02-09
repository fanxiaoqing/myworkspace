var pro = "";

function getPath(path){
	this.pro = path;
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
function checkFile(){
	var testimg = $.trim($("#testimg").val());
	var falg = true;
	if(testimg == null || testimg == ""){
		layer.alert("请上传文件");
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
 * 保存文件信息
 */
function saveFile(){
	if(checkTitle()){
		/*var form = document.getElementById("upload");
		form.action="<%=path%>/file/uploadFile";*/
		var themeTile = $.trim($("#themeTile").val());
		var enThemeTile = $.trim($("#enThemeTile").val());
		var file = $.trim($("#file").val());		
		var context = ue.getContent();
		$.ajax({
			type : "post",
			url : pro+"/file/saveFile",
			async : false,
			data : {
				themeTile : themeTile,
				enThemeTile : enThemeTile,
				file : file,
				context : context
			},
			dataType:"json",
			success : function(data){
				if(data == "1"){
					layer.alert("添加成功", {
						skin: "layui-layer-molv",
						closeBtn: 0
					}, function(){
						parent.location.reload();   
					});
				}else{
					layer.alert("添加失败",{skin: "layui-layer-molv"});
				}
			}
		});
		/*form.submit();*/
	}
	
}
/**
 * 修改新闻
 */
function upNews(id){
	location.href = pro+"/news/upNews?id="+id;
}
/**
 * 保存新闻信息
 */
function saveUpFile(){
	if(checkTitle()){
		var themeTile = $.trim($("#themeTile").val());
		var enThemeTile = $.trim($("#enThemeTile").val());
	/*	var columnId = $.trim($("#columnId").val());*/
		var testimg = $.trim($("#testimg").val());
		var context = ue.getContent();
		$.ajax({
			type : "post",
			url : pro+"/file/saveUpFile",
			async : false,
			data : {
			
				themeTile : themeTile,
				enThemeTile : enThemeTile,
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
						   location.href = pro+"/file/getFileList?id="+id;
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
function delFile(id){
	layer.confirm("你确定要删除该文件信息?", {
		  btn: ["确定","取消"] //按钮
	}, function(){
		$.ajax({
			type : "post",
			url : pro+"/file/delFile",
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
						location.reload();   
					});
				}else{
					layer.alert("删除失败", {
						skin: "layui-layer-molv",
						closeBtn: 0
					}, function(){
						parent.layer.close(index);
					});
				}
			}
		});
	}, function(){
		  
	});
}

