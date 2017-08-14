package threadPractice.priority;
/*
 * 线程优先级
 * 线程让步
 */
public class ThreadPriorityTest {
	public static void main(String[] args){
		MyThread1 t1 = new MyThread1();
		Thread t2 = new Thread(new MyThread2());
		t1.setPriority(10);
		t2.setPriority(1);
		t1.start();
		t2.start();
	}
	
	static class MyThread1 extends Thread{

		@Override
		public void run() {
			for(int i = 0;i<10;i++){
				System.out.println("线程1 第"+i+"次执行！");
				Thread.yield();
			}
		}
		
	}
	
	static class MyThread2 implements Runnable{

		@Override
		public void run() {
			for(int i = 0;i<10;i++){
				System.out.println("线程2 第"+i+"次执行！");
				Thread.yield();
			}
		}
		
	}
}
