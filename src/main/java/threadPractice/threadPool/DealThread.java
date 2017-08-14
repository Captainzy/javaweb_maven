package threadPractice.threadPool;

public class DealThread implements Runnable {
	private String name;

	DealThread() {
	}

	DealThread(String name) {
		this.name = name;
	}

	@Override
	public void run() {
		System.out.println("线程：" + name + "，正在执行");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("线程：" + name + "，执行结束");
	}

}
