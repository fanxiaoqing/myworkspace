var project = "";

function getPath(path){
	this.project = path;
}

/**
 * 验证用户名不为空
 */
function checkUserName(){
	var userName = $.trim($("#username").val());
	var falg = true;
	if(userName == null || userName == "" || userName == "用户名"){
		layer.tips("请输入用户名", "#username", {tips: [2, "#E34127"],time: 500});
		falg = false;
	}
	return falg;
}

/**
 * 验证密码不为空
 */
function checkPassWord(){
	var password = $.trim($("#password").val());
	var falg = true;
	if(password == null || password == "" || password == "密码"){
		layer.tips("请输入密码", "#password", {tips: [2, "#E34127"],time: 500});
		falg = false;
	}
	return falg;
}

/**
 * 用户登录
 */
function login(){
	if(checkUserName()&&checkPassWord()){
		var userName = $.trim($("#username").val());
		var passWord = $.trim($("#password").val());
		$.ajax({
			type : "post",
			url : project + "/login/login",
			async : false,
			data : {
				userName : userName,
				passWord : passWord
			},
			dataType:"json",
			success : function(data){
				if(data == "1"){
					layer.msg("登录成功", {icon: 6}, function(){
						location.href = project + "/wel/welcome";
					});
				}else{
					layer.msg("用户名或密码错误", {icon: 5});
				}
			}
		});
	}
}
//回车触发登录
function keyLogin(e){
	  var theEvent = window.event || e;
	      var code = theEvent.keyCode || theEvent.which;
	   if (code==13) {  //回车键的键值为13
		   login();  //调用提交登录
	   }
	  }
		