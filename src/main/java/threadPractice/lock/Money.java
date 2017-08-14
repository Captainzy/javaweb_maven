package threadPractice.lock;

public class Money {
	private int amount;

	Money() {
	}

	Money(int amount) {
		this.amount = amount;
	}

	public int getAmount() {
		return this.amount;
	}

	public void decreaseMoney(String name,int count) {
		System.out.println(name+",用钱了");
		this.amount = this.amount - count;
		
	}
	
	public void increaseMoney(int count){
		System.out.println("存钱了");
		this.amount = this.amount + count;
	}
	
	public void checkMoney(String name){
		System.out.println(name+"，查账了（不会对信息产生任何写操作）");
	}
}
