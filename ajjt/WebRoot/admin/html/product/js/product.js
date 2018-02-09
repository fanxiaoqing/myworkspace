var pro = "";

function getPath(path){
	this.pro = path;
}

/***
 * 查询栏目下主题
 */
function changeProduct(){
	var columnId = $.trim($("#columnId").val());
	location.href = pro+"/pro/getProductList?columnId="+columnId;
}

/**
 * 验证产品标题为空
 */
function checkTitle(){
	var title = $.trim($("#themeTile").val());
	var falg = true;
	if(title == null || title == ""){
		layer.tips("请输入产品标题", "#themeTile", {tips: [2, "#E34127"],time: 1000});
		falg = false;
	}
	return falg;
}

/**
 * 验证产品栏目为空
 */
function checkType(){
	debugger;
	var title = $.trim($("#columnIds").val());
	var falg = true;
	if(title == null || title == "0"){
		layer.tips("请选择产品栏目", "#columnIds", {tips: [2, "#E34127"],time: 1000});
		falg = false;
	} else{
		$.ajax({
			type : "post",
			url : pro+"/pro/select",
			async : false,
			data : {
				columnId : title,
			},
			dataType:"json",
			success : function(data){
				var items = '<option>请选择三级栏目</option>';
				$.each(data,function(i,o){
					items += '<option value=\''+o.id+'\'>'+o.colnumtwo_name+'</option>';
				});
				document.getElementById("columnId").innerHTML = items;
			}
		});
	}
	return falg;
}
function checkTypes(){
	var title = $.trim($("#columnId").val());
	var falg = true;
	if(title == null || title == "0"){
		layer.tips("请选择产品栏目", "#columnId", {tips: [2, "#E34127"],time: 1000});
		falg = false;
	}
	return falg;
}
/**
 * 验证产品图片为空
 */
function checkImg(){
	var img = $.trim($("#testimg").val());
	var falg = true;
	if(img == null || img == ""){
		layer.alert("请上传商品图片");
		falg = false;
	}
	return falg;
}

/**
 * 验证产品栏目为空
 */
function checkContext(){
	var context = ue.getContent();
	var falg = true;
	if(context == null || context == ""){
		layer.alert("您没有编辑产品内容");
		falg = false;
	}
	return falg;
}

/**
 * 保存产品信息
 */
function saveProduct(){
	if(checkTitle()&&checkType()){
		var themeTile = $.trim($("#themeTile").val());
		var columnId = $.trim($("#columnId").val());
		var columnIds = $.trim($("#columnIds").val());
		var testimg = $.trim($("#testimg").val());
		//var intro = $.trim($("#intro").val());
		//var introInfo = ueIn.getContent();
		var context = ue.getContent();
		$.ajax({
			type : "post",
			url : pro+"/pro/saveProduct",
			async : false,
			data : {
				themeTile : themeTile,
				columnId : columnId,
				columnIds : columnIds,
				testimg : testimg,
				context : context,
				//intro : intro,				
				//introInfo : introInfo,
			},
			dataType:"json",
			success : function(data){
				if(data == "1"){
					layer.alert("添加成功", {
						skin: "layui-layer-molv",
						closeBtn: 0
					}, function(){
						   location.href = pro+"/pro/getProductList?columnId="+columnId;
					});
				}else{
					layer.alert("添加失败",{skin: "layui-layer-molv"});
				}
			}
		});
	}
}

/**
 * 
 */
function saveUpProduct(){
	if(checkTitle()){
		var themeTile = $.trim($("#themeTile").val());
		var productId = $.trim($("#productId").val());
		var columnId = $.trim($("#columnId").val());
		
		var testimg = $.trim($("#testimg").val());
		var context = ue.getContent();
		$.ajax({
			type : "post",
			url : pro+"/pro/saveUpProduct",
			async : false,
			data : {
				productId : productId,
				themeTile : themeTile,
				columnId : columnId,
				testimg : testimg,
				context : context,
			},
			dataType:"json",
			success : function(data){
				if(data == "1"){
					layer.alert("修改成功", {
						skin: "layui-layer-molv",
						closeBtn: 0
					}, function(){
						   location.href = pro+"/pro/getProductList?columnId="+columnId;
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
function delProduct(id){
	var columnId = $.trim($("#columnId").val());
	$.ajax({
		type : "post",
		url : pro+"/pro/delProduct",
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
					   location.href = pro+"/pro/getProductList?columnId="+columnId;
				});
			}else{
				layer.alert("删除失败",{skin: "layui-layer-molv"});
			}
		}
	});
}

/**
 * 修改产品
 */
function upProduct(id){
	location.href = pro+"/pro/upProduct?id="+id;
}
/**
 * 查看产品图片列表
 * @param id
 */
function imgProduct(id){
	
	location.href = pro+"/pro/ProductImgList?id="+id;
}