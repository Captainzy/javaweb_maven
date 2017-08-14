package threadPractice.lockCondition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Customer implements Runnable {
	private static ReentrantLock lock;
	private static Condition c;
	private static BusinessWindow bw;
	private boolean isVip;
	private String name;
	public Customer() {
	}

	public Customer(String name,ReentrantLock lock, Condition c, BusinessWindow bw, boolean isVip) {
		this.name = name;
		this.lock = lock;
		this.c = c;
		this.bw = bw;
		this.isVip = isVip;
	}

	@Override
	public void run() {
		lock.lock();
		try {
			System.out.println("等待办理业务");
			c.await();
			System.out.println("客户开始办理");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		if (isVip) {
			System.out.println("VIP客户办理业务。。。。");
			bw.handleBusiness();
		} else {
			System.out.println("普通客户办理业务。。。。办理结束提醒窗口办理下一个客户的业务");
			bw.handleBusiness();
		}
		System.out.println(name+"办理结束提醒窗口办理下一个客户的业务");
		bw.getBwc().signal();
		lock.unlock();
	}

}
