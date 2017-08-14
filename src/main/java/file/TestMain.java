package file;

import java.io.File;
import java.text.DecimalFormat;

public class TestMain {
	public static void main(String[] args){
		new Thread(new Runnable() {
			@Override
			public void run() {
				while(true){
					File file = new File("D:/");
					if(file.exists()){
						DecimalFormat df = new DecimalFormat("#.###");
						String totalSpace = df.format((double)file.getTotalSpace()/(1024*1024*1024))+" GB";
						String usedSpace = df.format((double)(file.getTotalSpace()-file.getFreeSpace())/(1024*1024*1024))+" GB";
						String freeSpace = df.format((double)file.getFreeSpace()/(1024*1024*1024))+" GB";
						String usableSpace = df.format((double)file.getUsableSpace()/(1024*1024*1024)) + " GB";
						System.out.println("磁盘的总空间："+totalSpace);
						System.out.println("磁盘的已用空间："+usedSpace);
						System.out.println("磁盘剩余空间："+freeSpace);
						System.out.println("磁盘的剩余可用空间："+usableSpace);
						System.out.println("--------------------------------------------------------");
					}
					try {
						Thread.sleep(10000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
	}
}
