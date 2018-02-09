var pro = "";

function getPath(path){
	this.pro = path;
}

/**
 * 验证网站标题为空
 */
function checkWebTitle(){
	var title = $.trim($("#web_title").val());
	var falg = true;
	if(title == null || title == ""){
		layer.tips("请输入网站名称", "#web_title", {tips: [2, "#E34127"],time: 1000});
		falg = false;
	}
	return falg;
}

/**
 * 验证网站负责人为空
 */
function checkWebHead(){
	var head = $.trim($("#web_head").val());
	var reg = /^[\u4e00-\u9fa5]{2,10}$/;
	var falg = true;
	if(head == null || head == ""){
		layer.tips("请输入网站负责人名称", "#web_head", {tips: [2, "#E34127"],time: 1000});
		falg = false;
	}else if(!reg.test(head)){
		layer.tips("网站负责人姓名格式错误", "#web_head", {tips: [2, "#E34127"],time: 1000});
		falg = false;
	}
	return falg;
}

/**
 * 验证负责人电话为空
 */
function checkWebPhone(){
	var phone = $.trim($("#head_phone").val());
	var reg = /^([0-9]{3,4}-)?[0-9]{7,8}|1[3|4|5|7|8]\d{9}$/;
	var falg = true;
	if(phone == null || phone == ""){
		layer.tips("请输入负责人联系方式", "#head_phone", {tips: [2, "#E34127"],time: 1000});
		falg = false;
	}else if(!reg.test(phone)){
		layer.tips("负责人联系方式格式错误", "#head_phone", {tips: [2, "#E34127"],time: 1000});
		falg = false;
	}
	return falg;
}

/**
 * 验证负责人QQ为空
 */
function checkWebQQ(){
	var qq = $.trim($("#head_qq").val());
	var falg = true;
	if(qq == null || qq == ""){
		layer.tips("请输入负责人QQ", "#head_qq", {tips: [2, "#E34127"],time: 1000});
		falg = false;
	}
	return falg;
}

/**
 * 验证负责人WX为空
 */
function checkWebWX(){
	var wx = $.trim($("#head_wx").val());
	var falg = true;
	if(wx == null || wx == ""){
		layer.tips("请输入负责人微信", "#head_wx", {tips: [2, "#E34127"],time: 1000});
		falg = false;
	}
	return falg;
}

/**
 * 验证负责人WX为空
 */
function checkWebEM(){
	var em = $.trim($("#head_em").val());
	var reg = /^\w[-\w.+]*@([A-Za-z0-9][-A-Za-z0-9]+\.)+[A-Za-z]{2,14}$/;
	var falg = true;
	if(em == null || em == ""){
		layer.tips("请输入您的邮箱", "#head_em", {tips: [2, "#E34127"],time: 1000});
		falg = false;
	}else if(!reg.test(em)){
		layer.tips("您的邮箱格式错误", "#head_em", {tips: [2, "#E34127"],time: 1000});
		falg = false;
	}
	return falg;
}

/**
 * 验证负责人WX为空
 */
function checkWebADS(){
	var ads = $.trim($("#head_ads").val());
	var falg = true;
	if(ads == null || ads == ""){
		layer.tips("请输入公司地址", "#head_ads", {tips: [2, "#E34127"],time: 1000});
		falg = false;
	}
	return falg;
}

function checkAll(){
	if(checkWebTitle()&&checkWebHead()&&checkWebQQ()&&checkWebWX()&&checkWebEM()&&checkWebADS()){
		return true;
	}else{
		return false;
	}
}

/**
 * 
 */
