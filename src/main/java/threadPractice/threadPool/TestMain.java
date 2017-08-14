package threadPractice.threadPool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class TestMain {
	public static void main(String[] args) {
		/**
		 * 线程池不允许使用 Executors 去创建，而是通过 ThreadPoolExecutor 的方式，这样
		 * 的处理方式让写的同学更加明确线程池的运行规则，规避资源耗尽的风险。 说明： Executors 返回的线程池对象的弊端如下： 1）
		 * FixedThreadPool 和 SingleThreadPool: 允许的请求队列长度为
		 * Integer.MAX_VALUE，可能会堆积大量的请求，从而导致 OOM。 2） CachedThreadPool 和
		 * ScheduledThreadPool: 允许的创建线程数量为 Integer.MAX_VALUE， 可能会创建大量的线程，从而导致 OOM(out of memory)
		 */
		
		// 1.通过Executor创建线程池
		ExecutorService es = Executors.newFixedThreadPool(2);
		List<Future<String>> list = new ArrayList<Future<String>>();
		for (int i = 1; i <= 6; i++) {
			String name = "t" + i;
			Future f = null;
			if (i > 3) {
				f = es.submit(new DealThread_02(name));
				list.add(f);
			} else {
				/**
				 * execute 和 submit的最大的区别就是，execute有返回值，submit没有
				 * execute传递的参数是实现Runnable接口的类
				 * submit传递的参数是实现了Runnable接口的类或者实现了Callable<?>接口的类，但是只有参数是实现了Callable<?>接口的类时才有返回值。
				 * Callable<?>接口的call方法就相当于实现Runnable接口的run方法
				 */
				// es.execute(new DealThread(name));
				f = es.submit(new DealThread(name));
				list.add(f);
			}
		}

		try {
			Thread.sleep(5000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}

		for (Future<String> f : list) {
			String result = "";
			try {
				result = f.get();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
			System.out.println("线程的返回值是：" + result);
		}

		// 2.自定义线程池，构造阻塞线程池，当提交任务被拒绝时，进入拒绝机制，我们实现拒绝方法，把任务重新用阻塞提交方法put提交，实现阻塞提交任务功能，防止队列过大，出现OOM
		CustomThreadPoolExecutor exec = new CustomThreadPoolExecutor();
		exec.init(0, 3, 30, TimeUnit.MINUTES, 5);

		ExecutorService pool = exec.getCustomThreadPoolExecutor();
		for (int i = 1; i < 100; i++) {
			System.out.println("提交第" + i + "个任务!");
			pool.execute(new Runnable() {
				@Override
				public void run() {
					try {
						System.out.println(">>>task is running=====");
						TimeUnit.SECONDS.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			});
		}
		// 2.销毁----此处不能销毁,因为任务没有提交执行完,如果销毁线程池,任务也就无法执行了
		// exec.destory();
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
