package threadPractice.semaphore;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class TestSemaphore {
	public static void main(String[] args){
		/**
		 * 信号量可以决定在同一时刻可以有多少的线程执行，没有获取到信号量的线程都会被阻塞
		 * 一个信号量有且仅有3种操作，且它们全部是原子的：初始化、增加和减少 ；
		 * 增加可以为一个进程解除阻塞； 
		 * 减少可以让一个进程进入阻塞。
		 */
		//初始化信号量，设置该信号量一共可以授权多少个许可，下面设置了2个许可，那么同一时刻使用信号量的线程最多只能有两个
		final Semaphore s = new Semaphore(2, true);
		ExecutorService es = Executors.newFixedThreadPool(10);
		for(int i = 0;i<10;i++){
			final int x = i;
			es.submit(new Runnable(){

				@Override
				public void run() {
					try {
						s.acquire(1);
						System.out.println(new Date()+"第"+x+"线程开始执行");
						Thread.sleep(1000);
						s.release();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			});
		}
		es.shutdown();
	}
}
