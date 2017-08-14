package threadPractice.lock;

import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class LockTest {
	public static void main(String[] args){
		/**
		 * 普通锁：读读，读写，写写都是互斥的，即同一时间可能一个线程获得锁
		 */
		ReentrantLock lock = new ReentrantLock();//普通锁
		Money m = new Money();
		Man man = new Man(m, lock);
		Woman wm = new Woman(lock, m,"1号");
		Woman wm2 = new Woman(lock,m,"2号");
		Thread t1 = new Thread(man);
		Thread t2 = new Thread(wm);
		Thread t3 = new Thread(wm2);
//		t1.start();
//		t2.start();
//		t3.start();
		
		/**
		 * 读写锁：读写，写写是互斥的，而  读读 不是互斥的，所以读写锁在写线程没有获得锁的情况下，读线程可以多个线程都拥有锁。
		 */
		ReentrantReadWriteLock rwlock = new ReentrantReadWriteLock();//读写锁
		Money m2 = new Money();
		Man2 man2 = new Man2(m2, rwlock);
		Woman2 wm22 = new Woman2(rwlock, m2,"W1号");
		Woman2 wm23 = new Woman2(rwlock,m2,"W2号");
		Thread t21 = new Thread(man2);
		Thread t22 = new Thread(wm22);
		Thread t23 = new Thread(wm23);
		t22.start();
		t23.start();
		t21.start();
		
	}
}
