package threadPractice.interaction;

public class Calculator implements Runnable{
	private int total;
	
	public int getTotal() {
		return total;
	}

	@Override
	public void run() {
		synchronized(this){
			for(int i = 0;i<101;i++){
				total += i;
			}
			notifyAll();
		}
	}
	
}
