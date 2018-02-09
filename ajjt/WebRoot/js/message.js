var pro = "";

function getPath(path){
	this.pro = path;
}

/**
 * 验证信息
 */
function checkUsername(){
	var userName = $.trim($("#username").val());
	var reg = /^[\u4e00-\u9fa5]{2,10}$/;
	var falg = true;
	if(userName == null || userName == ""){
		layer.tips("请输入姓名", "#username", {tips: [2, "#E34127"],time: 1000});
		falg = false;
	}else if(!reg.test(userName)){
		layer.tips("您输入的姓名非汉字格式", "#username", {tips: [2, "#E34127"],time: 1000});
		falg = false;
	}
	return falg;
}

function checkQQ(){
	var qq = $.trim($("#qq").val());
	var reg = /^[1-9]([0-9]{5,11})$/;
	var falg = true;
	if(qq != null && qq != ""){
		if(!reg.test(qq)){
			layer.tips("您输入的QQ格式错误", "#qq", {tips: [2, "#E34127"],time: 1000});
			falg = false;
		}
	}
	return falg;
}
function checkNum(){
	var num = $.trim($("#num").val());
//	var reg = /^[1-9]([0-9]{5,11})$/;
	var falg = true;
	if(num == null || num == ""){
//		if(!reg.test(num)){
			layer.tips("请输入锅炉编号", "#num", {tips: [2, "#E34127"],time: 1000});
			falg = false;
//		}
	}
	return falg;
}
function checkTitle(){
	var title = $.trim($("#title").val());
	var falg = true;
	if(title == null || title == ""){
		layer.tips("请输入锅炉编号", "#title", {tips: [2, "#E34127"],time: 1000});
		falg = false;
	}
	return falg;
}

function checkPhone(){
	var phone = $.trim($("#phone").val());
	var reg = /^([0-9]{3,4}-)?[0-9]{7,8}|1[3|4|5|7|8]\d{9}$/;
	var falg = true;
	if(phone == null || phone == ""){
		layer.tips("请输入的联系方式", "#phone", {tips: [2, "#E34127"],time: 1000});
		falg = false;
	}else if(!reg.test(phone)){
		layer.tips("您输入的联系方式格式错误", "#phone", {tips: [2, "#E34127"],time: 1000});
		falg = false;
	}
	return falg;
}

function checkEmail(){
	var email = $.trim($("#email").val());
	var reg = /^\w[-\w.+]*@([A-Za-z0-9][-A-Za-z0-9]+\.)+[A-Za-z]{2,14}$/;
	var falg = true;
	if(email != null && email != ""){
		if(!reg.test(email)){
			layer.tips("您输入的邮箱格式错误", "#email", {tips: [2, "#E34127"],time: 1000});
			falg = false;
		}
	}
	return falg;
}


function checkAll(){
	if(checkUsername()&&checkPhone()){
		return true;
	}else{
		return false;
	}
}

/**
 * 表单提交
 */
function submitFrom(){
	if(checkAll()){
		var userName = $.trim($("#username").val());
		//var qq = $.trim($("#qq").val());
		var phone = $.trim($("#phone").val());
		var email = $.trim($("#email").val());
		//var address = $.trim($("#address").val());
		var context = $.trim($("#context").val());
		$.ajax({
			type : "post",
			url : pro+"/saveMessage",
			async : false,
			data : {
				userName : userName,
				//qq : qq,
				phone : phone,
				email : email,
				//address : address,
				context : context
			},
			dataType:"json",
			success : function(data){
				if(data == "1"){
					layer.alert("留言成功", {
						skin: "layui-layer-molv",
						closeBtn: 0
					}, function(){
						parent.location.reload();   
					});
				}else{
					layer.alert("留言失败", {
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
function submitFromHelp(){
	if(checkAll()&checkTitle()&&checkNum()){
		var userName = $.trim($("#username").val());
		var qq = $.trim($("#qq").val());
		var num = $.trim($("#num").val());
		var title = $.trim($("#title").val());
		var phone = $.trim($("#phone").val());
		var email = $.trim($("#email").val());
		var address = $.trim($("#address").val());
		var context = $.trim($("#context").val());
		$.ajax({
			type : "post",
			url : pro+"/saveOnlineHelp",
			async : false,
			data : {
				userName : userName,
				num : num,
				title : title,
				qq : qq,
				phone : phone,
				email : email,
				address : address,
				context : context
			},
			dataType:"json",
			success : function(data){
				if(data == "1"){
					layer.alert("在线报修成功", {
						skin: "layui-layer-molv",
						closeBtn: 0
					}, function(){
						parent.location.reload();   
					});
				}else{
					layer.alert("在线报修失败", {
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
*/