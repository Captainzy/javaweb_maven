package designMode.singleton;


/**
 * @author zouyang
 * @time 2017年2月7日 下午4:51:35
 * @description 枚举写法，单例类的够着函数使用protected修饰，最为安全的实现单例的方法是通过内部静态enum的方法来实现，
 *              因为JVM会保证enum不能被反射并且构造器方法只执行一次。 因此，枚举写法是单例模式推荐写法。 
 *使用：直接 Singleton_05.SINGLETON.getSingletonInstance();
 */
public enum Singleton_05 {
	/**
	 * 通过enum关键字来实现枚举，在枚举中需要注意的有：
	 *  1. 枚举中的属性必须放在最前面，一般使用大写字母表示 
	 *  2. 枚举中可以和java类一样定义方法 
	 *  3. 枚举中的构造方法必须是私有的
	 */
	SINGLETON;
	private Singleton singleton;

	private Singleton_05() {
		singleton = new Singleton();
	}

	public Singleton getSingletonInstance() {
		return singleton;
	}
}