package threadPractice.customerAndProducter;

/**
 * @author zouyang
 * @time 2017年1月9日 下午4:56:57
 * @description 生产者
 */
public class Producer implements Runnable {
	private ProductWarehouse pwh;
	private String name;
	private int count;
	Producer() {
	}

	Producer(String name,int count,ProductWarehouse pwh) {
		this.pwh = pwh;
		this.name = name;
		this.count = count;
	}

	@Override
	public void run() {
		pwh.produce(this.count);
	}
}
