package threadPractice.customerAndProducter;

public class TestMain {
	public static void main(String[] args) {
		ProductWarehouse pwh = new ProductWarehouse();
		Customer c1 = new Customer("C1", 10, pwh);
		Customer c2 = new Customer("C2", 20, pwh);
		Customer c3 = new Customer("C3", 30, pwh);
		Customer c4 = new Customer("C4", 15, pwh);
		Customer c5 = new Customer("C5", 14, pwh);
		Thread t1 = new Thread(new Producer("p1", 20, pwh));
		Thread t2 = new Thread(new Producer("p2", 15, pwh));

		t1.setPriority(1);
		t2.setPriority(1);

		c1.start();
		c2.start();
		c3.start();
		c4.start();
		c5.start();

		t1.start();
		t2.start();
		
	}
}
