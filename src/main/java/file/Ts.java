package file;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Ts {
	public static void main(String[] args) throws IOException {
		String path = "C:/Users/TZ/Desktop/问题.txt";
		File f = new File(path);
		for (int i = 0; i < 2; i++) {
			FileInputStream fis = new FileInputStream(f);
			BufferedInputStream bis = new BufferedInputStream(fis);
			String str;
			String s = "";
			StringBuffer sb = new StringBuffer();
			byte[] b = new byte[2048];
			while ((bis.read(b)) != -1) {
				sb.append(new String(b, "GBK"));
			}
			fis.close();
			bis.close();
			File f2 = new File("C:/Users/TZ/Desktop/问题2.txt");
			if (!f2.exists()) {
				f2.createNewFile();
			}
			FileOutputStream fout = new FileOutputStream(f2);
			BufferedOutputStream bw = new BufferedOutputStream(fout);
			bw.write(sb.toString().getBytes());
			bw.flush();
			fout.close();
			bw.close();
		}
	}
}
