package threadPractice.lock;

import java.util.concurrent.locks.ReentrantLock;

public class Woman implements Runnable {
	private static ReentrantLock lock;
	private Money m;
	private String name;
	public Woman() {
	}

	public Woman(ReentrantLock lock, Money m,String name) {
		this.lock = lock;
		this.m = m;
		this.name = name;
	}

	@Override
	public void run() {
		
		lock.lock();
		for(int i = 0;i<10;i++){
			m.decreaseMoney(name,5);
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("用钱结束");
		lock.unlock();

	}

}
