package threadPractice.customerAndProducter;
/**
 * @author zouyang
 * @time 2017年1月9日 下午4:56:46
 * @description 消费者
 */
public class Customer extends Thread{
	private ProductWarehouse pwh;
	private String name;
	private int count;
	Customer(){}
	Customer(String name,int count,ProductWarehouse pwh){
		this.pwh = pwh;
		this.name = name;
		this.count = count;
	}
	@Override
	public void run() {
		pwh.sale(this.count);
	}

}
