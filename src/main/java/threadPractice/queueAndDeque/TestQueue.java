package threadPractice.queueAndDeque;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class TestQueue {

	public static void main(String[] args) {
		/**
		 * 阻塞队列的概念是，一个指定长度的队列，如果队列满了，添加新元素的操作会被阻塞等待，直到有空位为止。
		 * 同样，当队列为空时候，请求队列元素的操作同样会阻塞等待，直到有可用元素为止。
		 * 阻塞队列实现类：ArrayBlockingQueue, DelayQueue, LinkedBlockingQueue, PriorityBlockingQueue, SynchronousQueue 
		 */
		BlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(3);
		for(int i = 0;i<10;i++){
			try {
				System.out.println("放置数字："+i+"  进入队列");
				queue.put(i);
				System.out.println("放置结束");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
