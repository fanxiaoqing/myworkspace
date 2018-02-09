
var _menus = {"menus":[
			{"menuid":"1","icon":"icon-sys","menuname":"网站设置",
				"menus":[
						{"menuid":"12","menuname":"网站信息","icon":"icon-nav","url":"/web/gotoWebDetail"},
						{"menuid":"13","menuname":"用户留言","icon":"icon-nav","url":"/mes/gotoMessList"},
						{"menuid":"14","menuname":"用户建议","icon":"icon-nav","url":"/adv/gotoAadviceList"},
						{"menuid":"15","menuname":"简历列表","icon":"icon-nav","url":"/resume/getResumeList"}
					]
			},{"menuid":"9","icon":"icon-sys","menuname":"栏目管理",
				"menus":[{"menuid":"21","menuname":"栏目管理","icon":"icon-nav","url":"/column/getOneColumnList"}
						]
			},{"menuid":"8","icon":"icon-sys","menuname":"产品管理",
				"menus":[{"menuid":"22","menuname":"产品列表","icon":"icon-nav","url":"/pro/getProductList"},
				         {"menuid":"23","menuname":"产品添加","icon":"icon-nav","url":"/pro/addProduct"},
				         {"menuid":"24","menuname":"产品批添加","icon":"icon-nav","url":"/pro/addMachProduct"}
					]
			},{"menuid":"56","icon":"icon-sys","menuname":"新闻管理",
				"menus":[{"menuid":"31","menuname":"新闻列表","icon":"icon-nav","url":"/news/getNewsList"},
						{"menuid":"32","menuname":"新闻添加","icon":"icon-nav","url":"/news/addNews"},
						{"menuid":"33","menuname":"新闻批添加","icon":"icon-nav","url":"/news/addPachNews"}
					]
			},{"menuid":"28","icon":"icon-sys","menuname":"主题管理",
				"menus":[{"menuid":"41","menuname":"主题列表","icon":"icon-nav","url":"/theme/getThemeList"},
						{"menuid":"42","menuname":"主题添加","icon":"icon-nav","url":"/theme/addTheme"}
					]
			},{"menuid":"40","icon":"icon-sys","menuname":"视频中心",
				"menus":[{"menuid":"51","menuname":"视频列表","icon":"icon-nav","url":"/video/getVideoList"}]
			},{"menuid":"2","icon":"icon-sys","menuname":"文件管理",
				"menus":[{"menuid":"16","menuname":"文件上传","icon":"icon-nav","url":"/file/addFile"},
						{"menuid":"17","menuname":"文件列表","icon":"icon-nav","url":"/file/getFileList"}
					]
			}
			/*},{"menuid":"39","icon":"icon-sys","menuname":"链接管理",
				"menus":[{"menuid":"51","menuname":"客服QQ","icon":"icon-nav","url":"/qq/getQQList"},
						{"menuid":"52","menuname":"客服电话","icon":"icon-nav","url":"/link/getLinkList"}
					]
			}*/
	]};
        //设置登录窗口
        function openPwd() {
            $('#w').window({
                title: '修改密码',
                width: 300,
                modal: true,
                shadow: true,
                closed: true,
                height: 160,
                resizable:false
            });
        }
        //关闭登录窗口
        function closePwd() {
            $('#w').window('close');
        }

        

        //修改密码

        $(function() {

            openPwd();

            $('#editpass').click(function() {
                $('#w').window('open');
            });

            $('#btnEp').click(function() {
                serverLogin();
            })

			$('#btnCancel').click(function(){closePwd();})

           
        });
		

        
        
function checkPassWord(){
	var pwd = $.trim($("txtNewPass").val());
	var falg = true;
	if(pwd == null || pwd == ""){
		layer.tips("请输入新密码", "#txtNewPass", {tips: [2, "#E34127"],time: 500});
		falg = false;
	}
	return falg;
} 

function checkPassWordTwo(){
	if(checkPassWord()){
		var pwdtwo = $.trim($("txtRePass").val());
		var pwd = $.trim($("txtNewPass").val());
		var falg = true;
		if(pwdtwo == null || pwdtwo == ""){
			layer.tips("请输入重复密码", "#txtRePass", {tips: [2, "#E34127"],time: 500});
			falg = false;
		}else if(pwd != pwdtwo){
			layer.tips("两次密码输入不同", "#txtRePass", {tips: [2, "#E34127"],time: 500});
			falg = false;
		}
		return falg;
	}
} 

function updatePassWord(userId){
	if(checkPassWordTwo()){
		var pwd = $.trim($("txtNewPass").val());
		$.ajax({
			type : "post",
			url : "/wel/updatePassWord",
			async : false,
			data : {
				userId : userId,
				pwd : pwd
			},
			dataType:"json",
			success : function(data){
				if(data == "1"){
					layer.msg("修改成功", {icon: 6});
				}else{
					layer.msg("修改失败", {icon: 5});
				}
			}
		});
	}
}
        
