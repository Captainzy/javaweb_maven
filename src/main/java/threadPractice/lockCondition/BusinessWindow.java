package threadPractice.lockCondition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class BusinessWindow implements Runnable {
	private boolean dealVip;
	private static ReentrantLock lock;
	private static Condition bwc;
	private static Condition cvip;
	private static Condition c;
	private int num;// vip等待人数
	private int curNum;// 当前总共等待人数

	BusinessWindow() {
	}

	public BusinessWindow(boolean dealVip,ReentrantLock lock,Condition bwc, Condition cvip, Condition c, int num, int curNum) {
		this.dealVip = dealVip;
		this.lock = lock;
		this.bwc = bwc;
		this.cvip = cvip;
		this.c = c;
		this.num = num;
		this.curNum = curNum;
	}

	public boolean isDealVip() {
		return dealVip;
	}

	public void setDealVip(boolean dealVip) {
		this.dealVip = dealVip;
	}

	public int getCurNum() {
		return curNum;
	}

	public void setCurNum(int curNum) {
		this.curNum = curNum;
	}

	public void checkNum() {
		lock.lock();
			
		lock.unlock();
	}

	
	public Condition getBwc() {
		return bwc;
	}

	// 处理业务
	public void handleBusiness() {
		if (curNum > 0) {
			if (dealVip) {
				System.out.println("正在办理vip客户业务");
			} else {
				System.out.println("正在办理普通客户业务");
			}
			this.curNum--;
		}
	}

	@Override
	public void run() {
		System.out.println("----------开始办理业务");
		while (true) {
			lock.lock();
			if (curNum > 0) {
				if (num > 0) {
					dealVip = true;
					cvip.signal();
					this.num--;
				} else {
					dealVip = false;
					c.signal();
				}
			}
			try {
				bwc.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			lock.unlock();
		}
	}

}
