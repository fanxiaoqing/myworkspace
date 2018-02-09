var pro = "";

function getPath(path){
	this.pro = path;
}

/**
 * 添加链接
 */
function addLink(){
	layer.open({
		type: 2,
		title: false,
		area: ["450px", "300px"],
		shade: 0.8,
		closeBtn: 1,
		shadeClose: true,
		content: pro+"/link/addLink"
	});
}

/**
 * 修改链接
 */
function upLink(id){
	layer.open({
		type: 2,
		title: false,
		area: ["450px", "300px"],
		shade: 0.8,
		closeBtn: 1,
		shadeClose: true,
		content: pro+"/link/upLink?id="+ id
	});
}

/**
 * 验证友情链接
 */
function checkLinkName(){
	var linkName = $.trim($("#linkName").val());
	var falg = true;
	if(linkName == null || linkName == ""){
		layer.tips("请输入客服名称", "#linkName", {tips: [4, "#E34127"],time: 1000});
		falg = false;
	}
	return falg;
}

/**
 * 验证友情链接
 */
function checkLinkUrl(){
	var linkUrl = $.trim($("#linkUrl").val());
	var falg = true;
	if(linkUrl == null || linkUrl == ""){
		layer.tips("请输入客服电话", "#linkUrl", {tips: [4, "#E34127"],time: 1000});
		falg = false;
	}
	return falg;
}

/**
 * 保存链接
 */
function saveLink(){
	if(checkLinkName()&&checkLinkUrl()){
		var linkName = $.trim($("#linkName").val());
		var linkUrl = $.trim($("#linkUrl").val());
		$.ajax({
			type : "post",
			url : pro+"/link/saveLink",
			async : false,
			data : {
				linkName : linkName,
				linkUrl : linkUrl
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
 * 保存链接
 */
function saveUpLink(){
	if(checkLinkName()&&checkLinkUrl()){
		var linkName = $.trim($("#linkName").val());
		var linkUrl = $.trim($("#linkUrl").val());
		var linkId = $.trim($("#linkId").val());
		$.ajax({
			type : "post",
			url : pro+"/link/saveUpLink",
			async : false,
			data : {
				linkName : linkName,
				linkUrl : linkUrl,
				linkId : linkId
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
 * 删除链接
 */
function delLink(id){
	layer.confirm("你确定要删除该客服信息?", {
		  btn: ["确定","取消"] //按钮
	}, function(){
		$.ajax({
			type : "post",
			url : pro+"/link/delLink",
			async : false,
			data : {
				linkId : id
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