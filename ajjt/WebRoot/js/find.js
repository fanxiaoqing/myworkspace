
var pro = "";

function getPath(path){
	this.pro = path;
}

function inQuery(){
	
	var pa = $.trim($("#username").val());
	if(pa == ''){
		return;
	}
	window.location.href = pro + '/findproduct?pa='+pa;
	/*$.ajax({
		type : "post",
		url : pro+"/findCar",
		async : false,
		data : {
			pa : pa	
		},
		dataType:"json",
		success : function(data){
			 window.location.href = 'web/chanpin.html?list=data.list';
		}
			
	});*/
}