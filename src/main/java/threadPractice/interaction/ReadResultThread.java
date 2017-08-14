package threadPractice.interaction;

public class ReadResultThread  extends Thread{
	private Calculator c;
	ReadResultThread(Calculator c){
		this.c = c;
	}
	@Override
	public void run() {
		synchronized(c){
			try {
				System.out.println(Thread.currentThread()+"计算结果。。。。。。。");
				c.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
		System.out.println(Thread.currentThread()+"结果是："+c.getTotal());
		
	}
	
}
