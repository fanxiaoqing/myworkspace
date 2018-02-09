var pro = "";

function getPath(path){
	this.pro = path;
}

/**
 * 查看留言
 */
function upMessage(id){
	layer.open({
		type: 2,
		title: false,
		area: ["800px", "500px"],
		shade: 0.8,
		closeBtn: 1,
		shadeClose: true,
		content: pro+"/mes/preMessage?id="+id
	});
}

/**
 * 删除留言
 */
function delMessage(id){
	layer.confirm("你确定要删除该留言信息?", {
		  btn: ["确定","取消"] //按钮
	}, function(){
		$.ajax({
			type : "post",
			url : pro+"/mes/delMessage",
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