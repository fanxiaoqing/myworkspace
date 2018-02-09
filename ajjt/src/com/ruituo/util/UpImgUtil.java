package com.ruituo.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.log4j.Logger;
import com.jfinal.core.Controller;
import com.jfinal.upload.UploadFile;

public class UpImgUtil extends Controller{

	private static final Logger log = Logger.getLogger(UpImgUtil.class);
	
	/**
	 * 上传图片
	 */
	public void uploadImg(){
		String project = getSession().getServletContext().getContextPath();
		UploadFile file = getFile("file", project + "/");
        File source = file.getFile();
        String fileName = file.getFileName(); //图片名称
        String extension = fileName.substring(fileName.lastIndexOf(".")); //图片类型
        long sourceLong = source.length(); //图片大小
        String outSpan = "";
        
        String allowExt = ".jpg .jpeg .gif .bmp .png";
        
		if(allowExt != "" && allowExt.indexOf(extension.toLowerCase()) == -1){
			renderHtml("<script>parent.layer.alert('您选择的文件类型不正确！');</script>");
			return;
		}
		else{
			if(sourceLong/1024<2048){	//最大允许上传1M图片
				Date dateNow=new Date();  
				SimpleDateFormat  dateFormat=new SimpleDateFormat ("yyyyMMddHHmmss");  
				String dateNowStr=dateFormat.format(dateNow);  
				int randomNum = (int)((Math.random()*9+1)*100000);
				String path = ("upload/"+dateNowStr+randomNum+extension).trim();
				String fileName2 = dateNowStr+randomNum+extension;
				try {
		            FileInputStream fis = new FileInputStream(source);
		            File targetDir = new File(getSession().getServletContext().getRealPath("/upload"));
		            if (!targetDir.exists()) {
		                targetDir.mkdirs();
		            }
		            File target = new File(targetDir, fileName2);
		            if (!target.exists()) {
		                target.createNewFile();
		            }
		            FileOutputStream fos = new FileOutputStream(target);
		            byte[] bts = new byte[300];
		            while (fis.read(bts, 0, 300) != -1) {
		                fos.write(bts, 0, 300);
		            }
		            fos.close();
		            fis.close();
		            source.delete();
		        } catch (FileNotFoundException e) {
		        	log.warn(e.toString());
		        } catch (IOException e) {
		        	log.warn(e.toString());
		        }
				outSpan = "<span id='path'>"+path+"</span>";
			}
			else{
				renderHtml("<script>parent.layer.alert('图片大小不能超过2M');</script>");
				return;
			}
		}
		renderHtml(outSpan+"<script>parent.callback('" + project + "')</script>");
	}
	
}
