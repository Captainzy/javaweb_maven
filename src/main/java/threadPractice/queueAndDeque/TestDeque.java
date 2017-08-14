package threadPractice.queueAndDeque;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

public class TestDeque {
	public static void main(String[] args){
		/**
		 * 对于阻塞栈，与阻塞队列相似。不同点在于栈是“后入先出”的结构，每次操作的是栈顶，而队列是“先进先出”的结构，每次操作的是队列头。
		 */
		BlockingDeque bDeque = new LinkedBlockingDeque(20); 
        for (int i = 0; i < 30; i++) { 
                //将指定元素添加到此阻塞栈中，如果没有可用空间，将一直等待（如果有必要）。 
                try {
					bDeque.putFirst(i);
				} catch (InterruptedException e) {
					e.printStackTrace();
				} 
                System.out.println("向阻塞栈中添加了元素:" + i); 
        } 
        System.out.println("程序到此运行结束，即将退出----"); 
	}
}
