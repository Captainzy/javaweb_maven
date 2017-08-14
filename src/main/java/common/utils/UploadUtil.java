package common.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class UploadUtil {
	//通过字节流的形式上传
	public static String uploadByStream(List<MultipartFile> files,String pathname){
		if(files.size()<=0){
			return "没有文件";
		}else{
			File dir = new File(pathname);
			if(!dir.exists()){
				dir.mkdirs();
			}else{
				if(dir.isFile()){
					return "保存路径不是一个文件夹";
				}
			}
			long begin = new Date().getTime();
			for(int i = 0;i<files.size();i++){
				MultipartFile file = files.get(i);
				/**采用MutipartFile自带方法保存文件，这种方式有个问题就是只能使用一次，下一次使用就会报File has been moved - cannot be read again
				 * 这是由于spring的MultipartFile会将Inputstream写入到临时文件里，在使用了一次后，spring会自动删除临时文件，所以当第2次使用的时候会找不到临时文件，因此报异常
				 * 解决方法：
				 * 1.配置maxInMemorySize的大小，可以让小于maxInMemorySize的文件直接读到内存中，不存放临时文件，
				 *   这种方式不建议，因为如果maxInMemorySize设置太大，会占用大量内存，如果maxInMemorySize设置在合理的范围是推荐的
				 * 2.将MultipartFile文件拿到后，由我们自己写方法存入到硬盘中，当做临时文件，在以后使用的时候就可以直接从硬盘上读取，然后在MultipartFile生命周期结束后（方法结束）将文件删除
				 * */
				File f = new File(pathname+ new Date().getTime() + file.getOriginalFilename());
				try {
					file.transferTo(f);
				} catch (Exception e) {
					e.printStackTrace();
				}
			
				
				/*FileOutputStream out;
				try {
					byte[] b = file.getBytes();
					File f = new File(pathname+ new Date().getTime() + file.getOriginalFilename());
					if(!f.exists()){
						f.createNewFile();
					}
					out = new FileOutputStream(f);
					out.write(b);
					out.flush();
					out.close();
				} catch (Exception e1) {
					e1.printStackTrace();
					return "发生异常，上传失败";
				} */
				
			}
			long end  = new Date().getTime();
			System.out.println("上传用时："+(end-begin)+"ms");
			return "上传完成";
		}
	}
	//通过spring包装好的解析器实现上传，这种方式上传速度更快
	public static String uploadMultipartFile(List<MultipartFile> files,String pathname){
		if(files.size()<=0){
			return "没有文件";
		}else{
			File dir = new File(pathname);
			if(!dir.exists()){
				dir.mkdirs();
			}else{
				if(dir.isFile()){
					return "保存路径不是一个文件夹";
				}
			}
			long begin = new Date().getTime();
			for(int i = 0;i<files.size();i++){
				MultipartFile file = files.get(i);
				/**采用MutipartFile自带方法保存文件，这种方式有个问题就是只能使用一次，下一次使用就会报File has been moved - cannot be read again
				 * 这是由于spring的MultipartFile会将Inputstream写入到临时文件里，在使用了一次后，spring会自动删除临时文件，所以当第2次使用的时候会找不到临时文件，因此报异常
				 * 解决方法：
				 * 1.配置maxInMemorySize的大小，可以让小于maxInMemorySize的文件直接读到内存中，不存放临时文件，
				 *   这种方式不建议，因为如果maxInMemorySize设置太大，会占用大量内存，如果maxInMemorySize设置在合理的范围是推荐的
				 * 2.将MultipartFile文件拿到后，由我们自己写方法存入到硬盘中，当做临时文件，在以后使用的时候就可以直接从硬盘上读取，然后在MultipartFile生命周期结束后（方法结束）将文件删除
				 * */
				 File f = new File(pathname+ new Date().getTime() + file.getOriginalFilename());
				 try {
					file.transferTo(f);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			/*	try {
					File f = new File(pathname+ new Date().getTime() + file.getOriginalFilename());
					if(!f.exists()){
						f.createNewFile();
					}
					file.transferTo(f);
				} catch (IllegalStateException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}*/
			}
			long end = new Date().getTime();
			System.out.println("上传用时："+(end-begin)+"ms");
			return "上传成功";
		}
	}
}
