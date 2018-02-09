var project = "";

function init(projectName){
	this.project = projectName;
} 	

/**
 * 文件自动上传
 */
function upload(){
	document.getElementById("upload").submit();
}

/**
 * 文件上传回调
 */
function callback(path){     	     
    var doc;
    if(document.all){
     	doc = document.frames["hidden_frame"].document;
    }
    else{
     	doc = document.getElementById("hidden_frame").contentDocument;
    }
    layer.alert("文件上传成功");
    $("#file").val(doc.getElementById("path").innerHTML); 
}