package designMode.decorationMode;

public class Decorator_02 extends Decorator{

	public Decorator_02(Human human) {
		super(human);
	}

	public void chooseClothes(){
		System.out.println("先选一选衣服");
	}
	
	@Override
	public void wearClothes() {
		super.wearClothes();
		this.chooseClothes();
	}
	
	

}
