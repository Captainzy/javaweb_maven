package threadPractice.lock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Woman2 implements Runnable {
	private static ReentrantReadWriteLock lock;
	private Money m;
	private String name;
	public Woman2() {
	}

	public Woman2(ReentrantReadWriteLock lock, Money m,String name) {
		this.lock = lock;
		this.m = m;
		this.name = name;
	}

	@Override
	public void run() {
		
		lock.readLock().lock();
		for(int i = 0;i<10;i++){
			m.checkMoney(name);
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println(name+"用钱结束");
		lock.readLock().unlock();

	}

}
