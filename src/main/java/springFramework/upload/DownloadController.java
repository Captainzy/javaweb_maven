package springFramework.upload;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/downloadFile")
public class DownloadController {
	@RequestMapping("/download")
	public String downPage(){
		return "download";
	}
	/**
	 * @author zouyang
	 * @time 2017年2月24日 上午11:35:38
	 * @description 采用spring的封装对象ResponseEntity<T>实现下载,这种方式下载的文件不宜太大，
	 * 				并且通过HttpClient编写客户端调用下载时，这种方式可能会造成文件损坏的问题（经过查询可能是内部转换的时候造成的问题）。
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/downloadFileByMethod_01")
	public ResponseEntity<byte[]> downloadFileByMethod_01() throws Exception{
		File file = new File("C:\\Users\\TZ\\Desktop\\公司微信密码.txt");
		//处理显示中文文件名的问题
        String fileName=new String(file.getName().getBytes("utf-8"),"ISO-8859-1");
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		//浏览器支持的文件类型，一般会默认使用浏览器打开，比如txt、jpg等，会直接在浏览器中显示，如果需要提示用户保存，
		//就要利用Content-Disposition进行一下处理，关键在于一定要加上attachment
		//格式：headers.add("Content-Disposition", "attachment;filename=name");
		headers.add("Content-Disposition", "attachment;filename="+fileName);
		//当代码里面使用Content-Disposition来确保浏览器弹出下载对话框的时候。一定要确保没有做过关于禁止浏览器缓存的操作
		headers.add("Pragma", "No-cache");
		headers.add("Cache-Control", "No-cache");
		headers.add("Expires", "0");
		
		ResponseEntity<byte[]> rsEntity = new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),headers,HttpStatus.OK);
		return rsEntity;
	}
	
	/**
	 * @author zouyang
	 * @time 2017年2月24日 上午11:51:47
	 * @description 用传统的httpservlet方式,流来实现下载
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	@RequestMapping(value="/downloadFileByMethod_03")
	public void downloadFileByMethod_03(HttpServletRequest request,HttpServletResponse response) throws IOException{
		File file = new File("C:\\Users\\TZ\\Desktop\\图片.png");
		response.setHeader("Pragma", "no-cache");  
        response.setHeader("Cache-Control", "no-cache");  
        response.setDateHeader("Expires", 0);  
        response.setContentType("image/jpeg");  
        //浏览器支持的文件类型，一般会默认使用浏览器打开，比如txt、jpg等，会直接在浏览器中显示，如果需要提示用户保存，
  		//就要利用Content-Disposition进行一下处理，关键在于一定要加上attachment
  		//格式：headers.add("Content-Disposition", "attachment;filename=name");
        //response.addHeader("Content-Disposition", "attachment;filename="+file.getName());
        BufferedOutputStream bout = new BufferedOutputStream(response.getOutputStream());
        bout.write(FileUtils.readFileToByteArray(file));
        bout.flush();
        bout.close();
	}
}
