package threadPractice.lock;

import java.util.concurrent.locks.ReentrantLock;

public class Man implements Runnable{
	private Money m;
	private static ReentrantLock lock;

	public Man() {
	}

	public Man(Money m, ReentrantLock lock) {
		this.m = m;
		this.lock = lock;
	}
	
	@Override
	public void run() {
		lock.lock();
		
		for(int i =0;i<10;i++){
			m.increaseMoney(15);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("存钱结束");
		
		lock.unlock();
	}

}
