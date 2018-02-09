var pro = "";

function getPath(path){
	this.pro = path;
}

/**
 * 添加客服
 */
function addVideo(){
	layer.open({
		type: 2,
		title: false,
		area: ["450px", "400px"],
		shade: 0.8,
		closeBtn: 1,
		shadeClose: true,
		content: pro+"/video/addVideo"
	});
}
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
 * 验证QQ客服
 */
function checkQQName(){
	var videoName = $.trim($("#videoName").val());
	var falg = true;
	if(videoName == null || videoName == ""){
		layer.tips("请输入视频名称", "#videoName", {tips: [2, "#E34127"],time: 1000});
		falg = false;
	}
	return falg;
}

/**
 * 验证QQ客服
 */
function checkQQCode(){
	var videoCode = $.trim($("#videoCode").val());
	var falg = true;
	if(videoCode == null || videoCode == ""){
		layer.tips("请输入视频地址", "#videoCode", {tips: [2, "#E34127"],time: 1000});
		falg = false;
	}
	return falg;
}

/**
 * 保存客服QQ
 */
function saveVideo(){
	if(checkQQName()&&checkQQCode()){
		debugger
		var columnId = $.trim($("#columnId").val());
		var videoName = $.trim($("#videoName").val());
		var videoCode = $.trim($("#videoCode").val());
		var testimg = $.trim($("#testimg").val());
		$.ajax({
			type : "post",
			url : pro+"/video/saveVideo",
			async : false,
			data : {
				columnId  : columnId,
				videoName : videoName,
				testimg : testimg,
				videoCode : videoCode
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
					layer.alert("添加失败", {
						skin: "layui-layer-molv",
						closeBtn: 0
					}, function(){
						parent.layer.close(index);
					});
				}
			}
		});
	}
}

/**
 * 修改视频
 */
function upVideo(id){
	layer.open({
		type: 2,
		title: false,
		area: ["450px", "400px"],
		shade: 0.8,
		closeBtn: 1,
		shadeClose: true,
		content: pro+"/video/preupVideo?id="+id
	});
}

/**
 * 保存视频
 */
function saveupVideo(){
	if(checkQQName()&&checkQQCode()){
		
		var videoName = $.trim($("#videoName").val());
		
		var videoCode = $.trim($("#videoCode").val());
		var videoId = $.trim($("#videoId").val());
		$.ajax({
			type : "post",
			url : pro+"/video/saveUpVideo",
			async : false,
			data : {
			
				videoName : videoName,
				videoCode : videoCode,
				videoId : videoId
			},
			dataType:"json",
			success : function(data){
				if(data == "1"){
					layer.alert("修改成功", {
						skin: "layui-layer-molv",
						closeBtn: 0
					}, function(){
						parent.location.reload();   
					});
				}else{
					layer.alert("修改失败", {
						skin: "layui-layer-molv",
						closeBtn: 0
					}, function(){
						parent.layer.close(index);
					});
				}
			}
		});
	}
}

/**
 * 删除视频
 */
function delVideo(id){
	layer.confirm("你确定要删除该视频信息?", {
		  btn: ["确定","取消"] //按钮
	}, function(){
		$.ajax({
			type : "post",
			url : pro+"/video/delVideo",
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


