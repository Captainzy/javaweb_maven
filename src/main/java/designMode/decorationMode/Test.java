package designMode.decorationMode;

public class Test {

	public static void main(String[] args) {
		// 测试单纯的穿衣服
		Person p = new Person();
		p.wearClothes();
		System.out.println("--------------------------------------------------------------");
		// 测试单重修饰的穿衣服
		Decorator d = new Decorator_01(p);
		d.wearClothes();
		System.out.println("--------------------------------------------------------------");
		// 测试多重修饰的穿衣服
		Decorator d2 = new Decorator_02(new Decorator_01(p));
		d2.wearClothes();
	}

}
