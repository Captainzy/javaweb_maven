package threadPractice.lock;

import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Man2 implements Runnable{
	private Money m;
	private static ReentrantReadWriteLock lock;

	public Man2() {
	}

	public Man2(Money m, ReentrantReadWriteLock lock) {
		this.m = m;
		this.lock = lock;
	}
	
	@Override
	public void run() {
		lock.writeLock().lock();
	
		for(int i =0;i<10;i++){
			m.increaseMoney(15);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("存钱结束");
		
		lock.writeLock().unlock();
	}

}
