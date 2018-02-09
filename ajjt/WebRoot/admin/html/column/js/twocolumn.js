var pro = "";

function getPath(path){
	this.pro = path;
}

/**
 * 添加二级栏目
 */
function addTwoCol(uppid){
	layer.open({
		type: 2,
		title: false,
		area: ["450px", "350px"],
		shade: 0.8,
		closeBtn: 1,
		shadeClose: true,
		content: pro+"/column/addTwoCol?uppid="+uppid
	});
}
/**
 * 添加三级栏目
 */
function addThreeCol(uppid){
	layer.open({
		type: 2,
		title: false,
		area: ["450px", "350px"],
		shade: 0.8,
		closeBtn: 1,
		shadeClose: true,
		content: pro+"/column/addThreeCol?uppid="+uppid
	});
}
/**
 * 修改二级栏目
 */
function upTwoCol(id){
	layer.open({
		type: 2,
		title: false,
		area: ["450px", "400px"],
		shade: 0.8,
		closeBtn: 1,
		shadeClose: true,
		content: pro+"/column/preUpTwoCol?id="+id
	});
}
function upThreeCol(id){
	layer.open({
		type: 2,
		title: false,
		area: ["450px", "400px"],
		shade: 0.8,
		closeBtn: 1,
		shadeClose: true,
		content: pro+"/column/preUpThreeCol?id="+id
	});
}
/**
 * 验证栏目名称
 */
function checkColTwoName(){
	var columnName = $.trim($("#columnName").val());
	var falg = true;
	if(columnName == null || columnName == ""){
		layer.tips("请输入栏目名称", "#columnName", {tips: [2, "#E34127"],time: 1000});
		falg = false;
	}
	return falg;
}

/**
 * 验证栏目名称
 */
function checkColTwoUrl(){
	var columnUrl = $.trim($("#columnUrl").val());
	var falg = true;
	if(columnUrl == null || columnUrl == ""){
		layer.tips("请输入栏目访问路径", "#columnUrl", {tips: [2, "#E34127"],time: 1000});
		falg = false;
	}
	return falg;
}

/**
 * 添加栏目
 */
function saveTwoColumn(){
	if(checkColTwoName()&&checkColTwoUrl()){
		var columnName = $.trim($("#columnName").val());
		var coltype = $.trim($("#coltype").val());
		var colid = $.trim($("#colid").val());
		var columnUrl = $.trim($("#columnUrl").val());
		var columnImg = $.trim($("#testimg").val());
		
		$.ajax({
			type : "post",
			url :pro+ "/column/saveTwoCol",
			async : false,
			data : {
				columnName : columnName,
				coltype : coltype,
				colid : colid,
				columnUrl : columnUrl,
				columnImg : columnImg
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
function saveThreeColumn(){
	if(checkColTwoName()&&checkColTwoUrl()){
		var columnName = $.trim($("#columnName").val());
		var coltype = $.trim($("#coltype").val());
		var colid = $.trim($("#colid").val());
		var columnUrl = $.trim($("#columnUrl").val());
		$.ajax({
			type : "post",
			url :pro+ "/column/saveThreeCol",
			async : false,
			data : {
				columnName : columnName,
				coltype : coltype,
				colid : colid,
				columnUrl : columnUrl
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
 * 保存修改
 */
function saveUpTwoCol(){
	if(checkColTwoName()&&checkColTwoUrl()){
		var columnName = $.trim($("#columnName").val());
		var columnId = $.trim($("#columnId").val());
		var columnUrl = $.trim($("#columnUrl").val());
		var columnImg = $.trim($("#testimg").val());
		$.ajax({
			type : "post",
			url : pro+"/column/saveUpTwoCol",
			async : false,
			data : {
				columnName : columnName,
				columnId : columnId,
				columnImg : columnImg,
				columnUrl : columnUrl
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

/***
 * 二级栏目删除
 */
function delTwoCol(id){
	layer.confirm("你确定要删除该栏目?", {
		  btn: ["确定","取消"] //按钮
	}, function(){
		$.ajax({
			type : "post",
			url : pro+"/column/delTwoCol",
			async : false,
			data : {
				columnId : id
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
function delThreeCol(id){
	layer.confirm("你确定要删除该栏目?", {
		  btn: ["确定","取消"] //按钮
	}, function(){
		$.ajax({
			type : "post",
			url : pro+"/column/delThreeCol",
			async : false,
			data : {
				columnId : id
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