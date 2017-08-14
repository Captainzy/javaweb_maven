package threadPractice.threadPool;

import java.util.concurrent.Callable;

public class DealThread_02 implements Callable<String> {

	private String name;

	DealThread_02() {
	}

	DealThread_02(String name) {
		this.name = name;
	}

	@Override
	public String call() throws Exception {
		System.out.println("线程：" + name + "，正在执行");
		Thread.sleep(1000);
		System.out.println("线程：" + name + "，执行完了");
		return "该线程的名字是：" + name;
	}

}
