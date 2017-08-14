package threadPractice.lockCondition;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
/**
 * @author zouyang
 * @time 2017年1月12日 下午3:15:27
 * @description TODO
 * Lock结合Condition使用，可以实现唤醒使用指定condition的线程，下面例子就实现了，先唤醒vip用户在唤醒普通用户
 * 注意：
 * wait，notify和notifyAll只能在同步控制方法或者同步控制块里面使用
 * Condition也是一样只能在lock的同步控制块里使用,因为Condition使用的时候必须是已经拥有锁了
 */
public class TestCondition {
	public static void main(String[] args) {
		ReentrantLock lock = new ReentrantLock();
		Condition bwc = lock.newCondition();
		Condition cvip = lock.newCondition();
		Condition c = lock.newCondition();
		List<Customer> list = new ArrayList<Customer>();
		BusinessWindow bw = new BusinessWindow(true,lock,bwc,cvip, c, 2,6);

		list.add(new Customer("vip_c1",lock, cvip, bw, true));
		list.add(new Customer("c1",lock, c, bw, false));
		list.add(new Customer("c2",lock, c, bw, false));
		list.add(new Customer("vip_c2",lock, cvip, bw, true));
		list.add(new Customer("c3",lock, c, bw, false));
		list.add(new Customer("c4",lock, c, bw, false));

		ExecutorService es = Executors.newFixedThreadPool(10);
		
		for (int i = 0; i < list.size(); i++) {
			es.execute(list.get(i));
		}
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		new Thread(bw).start();
	}
}
