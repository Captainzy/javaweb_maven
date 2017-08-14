package designMode.decorationMode;

public class Decorator_01 extends Decorator{

	public Decorator_01(Human human) {
		super(human);
	}

	public void eatFood(){
		System.out.println("先吃饭");
	}
	
	@Override
	public void wearClothes() {
		super.wearClothes();
		this.eatFood();
	}
	
}
