var pro = "";

function getPath(path){
	this.pro = path;
}

/**
 * 添加客服
 */
function addQQ(){
	layer.open({
		type: 2,
		title: false,
		area: ["450px", "300px"],
		shade: 0.8,
		closeBtn: 1,
		shadeClose: true,
		content: pro+"/qq/addQQ"
	});
}

/**
 * 修改客服
 */
function upQQ(id){
	layer.open({
		type: 2,
		title: false,
		area: ["450px", "300px"],
		shade: 0.8,
		closeBtn: 1,
		shadeClose: true,
		content: pro+"/qq/preupQQ?id="+id
	});
}

/**
 * 验证QQ客服
 */
function checkQQName(){
	var qqName = $.trim($("#qqName").val());
	var falg = true;
	if(qqName == null || qqName == ""){
		layer.tips("请输入客服名称", "#qqName", {tips: [2, "#E34127"],time: 1000});
		falg = false;
	}
	return falg;
}

/**
 * 验证QQ客服
 */
function checkQQCode(){
	var qqCode = $.trim($("#qqCode").val());
	var reg = /^[1-9]([0-9]{5,11})$/;
	var falg = true;
	if(qqCode == null || qqCode == ""){
		layer.tips("请输入客服QQ", "#qqCode", {tips: [2, "#E34127"],time: 1000});
		falg = false;
	}else if(!reg.test(qqCode)){
		layer.tips("客服QQ格式错误", "#qqCode", {tips: [2, "#E34127"],time: 1000});
		falg = false;
	}
	return falg;
}

/**
 * 保存客服QQ
 */
function saveQQ(){
	if(checkQQName()&&checkQQCode()){
		var qqName = $.trim($("#qqName").val());
		var qqCode = $.trim($("#qqCode").val());
		$.ajax({
			type : "post",
			url : pro+"/qq/saveQQ",
			async : false,
			data : {
				qqName : qqName,
				qqCode : qqCode
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
 * 保存客服QQ
 */
function saveupQQ(){
	if(checkQQName()&&checkQQCode()){
		var qqName = $.trim($("#qqName").val());
		var qqCode = $.trim($("#qqCode").val());
		var qqId = $.trim($("#qqId").val());
		$.ajax({
			type : "post",
			url : pro+"/qq/saveUpQQ",
			async : false,
			data : {
				qqName : qqName,
				qqCode : qqCode,
				qqId : qqId
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
 * 保存客服QQ
 */
function delQQ(id){
	layer.confirm("你确定要删除该客服信息?", {
		  btn: ["确定","取消"] //按钮
	}, function(){
		$.ajax({
			type : "post",
			url : pro+"/qq/delQQ",
			async : false,
			data : {
				qqId : id
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