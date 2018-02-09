var project = "";

function init(projectName){
	this.project = projectName;
} 	

/**
 * 图片自动上传
 */
function upload(){
	document.getElementById("upload0").submit();
}

/**
 * 图片上传回调
 */
function callback(path){     	     
    var doc;
    if(document.all){
     	doc = document.frames["hidden_frame0"].document;
    }
    else{
     	doc = document.getElementById("hidden_frame0").contentDocument;
    }
    layer.alert("图片上传成功");
    $("#testimg").val(doc.getElementById("path").innerHTML); 
}