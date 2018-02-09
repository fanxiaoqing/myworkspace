var pro = "";

function getPath(path){
	this.pro = path;
}

/**
 * 管理二级类目
 */
function manageTwoCol(uppid){
	location.href = pro+"/column/getTwoColumnList?uppid=" + uppid;
}
/**
 * 管理三级类目
 */
function manageThreeCol(uppid){
	window.location.href = pro+"/column/getThreeColumnList?uppid=" + uppid;
}

/**
 * 添加一级栏目
 */
function addOneCol(){
	layer.open({
		type: 2,
		title: false,
		area: ["450px", "500px"],
		shade: 0.8,
		closeBtn: 1,
		shadeClose: true,
		content: pro+"/column/addOneCol"
	});
}

/**
 * 修改栏目
 */
function upOneCol(id){
	layer.open({
		type: 2,
		title: false,
		area: ["450px", "500px"],
		shade: 0.8,
		closeBtn: 1,
		shadeClose: true,
		content: pro+"/column/preUpOneCol?id="+id
	});
}

/**
 * 验证一级栏目为空
 */
function checkColName(){
	var columnName = $.trim($("#columnName").val());
	var falg = true;
	if(columnName == null || columnName == ""){
		layer.tips("请输入栏目名称", "#columnName", {tips: [2, "#E34127"],time: 1000});
		falg = false;
	}
	return falg;
}

/**
 * 验证一级栏目为空
 */
function checkColNice(){
	var columnNice = $.trim($("#columnNice").val());
	var falg = true;
	if(columnNice == null || columnNice == ""){
		layer.tips("请输入栏目别名", "#columnNice", {tips: [2, "#E34127"],time: 1000});
		falg = false;
	}
	return falg;
}

/**
 * 验证一级栏目为空
 */
function checkColNum(){
	var columnNum = $.trim($("#columnNum").val());
	var falg = true;
	if(columnNum == null || columnNum == ""){
		layer.tips("请输入栏目序号", "#columnNum", {tips: [2, "#E34127"],time: 1000});
		falg = false;
	}
	return falg;
}

/**
 * 添加栏目
 */
function saveOneColumn(){
	if(checkColName()&&checkColNice()&&checkColNum()){
		var columnName = $.trim($("#columnName").val());
		var typeRadio = $.trim($("[name=typeRadio]:checked").val());
		var columnNum = $.trim($("#columnNum").val());
		var columnImg = $.trim($("#testimg").val());
		var columnNice = $.trim($("#columnNice").val());
		var columnurl = $.trim($("#columnurl").val());
		$.ajax({
			type : "post",
			url : pro+"/column/saveOneCol",
			async : false,
			data : {
				columnName : columnName,
				typeRadio : typeRadio,
				columnNum : columnNum,
				columnImg : columnImg,
				columnNice : columnNice,
				columnurl : columnurl
			},
			dataType:"json",
			success : function(data){
				if(data == "1"){
					layer.alert("添加成功", {
						skin: "layui-layer-molv",
						closeBtn: 0
					}, function(){
						parent.location.reload();   
							//parent.layer.close(index);
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
function saveUpOneCol(){
	if(checkColName()&&checkColNice()){
		var columnName = $.trim($("#columnName").val());
		var columnId = $.trim($("#columnId").val());
		var columnNum = $.trim($("#columnNum").val());
		var columnImg = $.trim($("#testimg").val());
		var columnNice = $.trim($("#columnNice").val());
		var columnurl = $.trim($("#columnurl").val());
		$.ajax({
			type : "post",
			url : pro+"/column/saveUpOneCol",
			async : false,
			data : {
				columnName : columnName,
				columnId : columnId,
				columnNum : columnNum,
				columnImg : columnImg,
				columnNice : columnNice,
				columnurl : columnurl
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
 * 删除栏目
 */
function delOneCol(id){
	layer.confirm("你确定要删除该栏目?", {
		  btn: ["确定","取消"] //按钮
	}, function(){
		$.ajax({
			type : "post",
			url : pro+"/column/delOneCol",
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

