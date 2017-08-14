package springFramework.upload;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.support.DefaultMultipartHttpServletRequest;

import common.utils.UploadUtil;

@Controller
@RequestMapping("/upload")
public class UploadController {

	@RequestMapping("/index")
	public String uploadView(){
		return "upload";
	}
	@RequestMapping(value="/uploadByStream",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody String uploadByStream(MultipartHttpServletRequest request){
		
		Map<String, MultipartFile> map = request.getFileMap();
		List<MultipartFile> files = new ArrayList<MultipartFile>();
		for(Entry<String, MultipartFile> entry:map.entrySet()){
			MultipartFile file = entry.getValue();
			files.add(file);
		}
		System.out.println("需要上传："+files.size()+"个文件");
		String pathname = "D:\\test\\";
		String result = UploadUtil.uploadByStream(files, pathname);
		System.out.println(result);
		return "";
	}
	
	@RequestMapping(value="/uploadByMultipartFile",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody String uploadByMultipartFile(MultipartHttpServletRequest request){
		Map<String, MultipartFile> map = request.getFileMap();
		List<MultipartFile> files = new ArrayList<MultipartFile>();
		for(Entry<String, MultipartFile> entry:map.entrySet()){
			MultipartFile file = entry.getValue();
			files.add(file);
		}
		System.out.println("需要上传："+files.size()+"个文件");
		String pathname = "D:\\test\\";
		String result = UploadUtil.uploadMultipartFile(files, pathname);
		System.out.println(result);
		return "";
	}
}
