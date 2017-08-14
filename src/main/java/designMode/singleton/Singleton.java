package designMode.singleton;

/**
 * 单例模式的各种写法，由于都是内部类所以都采用static修饰class关键字，但是在实际开发中不是内部类则不用static修饰class
 */
public class Singleton {
	
	//修饰符默认是default(friendly)，构造函数用默认修饰符修饰可以保证只有同包和本类能通过new关键字构造实例对象，这样就方便利用枚举的方式实现单例模式
	Singleton(){}
	
	/**
	 * @author zouyang
	 * @time 2017年2月7日 下午4:00:24
	 * @description 懒汉式写法（线程不安全）,具备很好的lazy loading，这种写法在多线程中不能正常工作
	 */
	public static class Singleton_01 {
		private static Singleton_01 singleton;
		
		private Singleton_01() {}

		public Singleton_01 getInstance() {
			if (singleton == null) {
				singleton = new Singleton_01();
			}
			return singleton;
		}

	}

	/**
	 * @author zouyang
	 * @time 2017年2月7日 下午4:07:51
	 * @description 懒汉式写法（线程安全），具备很好的lazy loading，这种写法在多线程中也能正常工作
	 *              但是效率很低，而且在绝大多数情况下不需要同步
	 */
	public static class Singleton_02 {
		private static Singleton_02 singleton;

		private Singleton_02() {
		}

		public synchronized Singleton_02 getInstance() {
			if (singleton == null) {
				singleton = new Singleton_02();
			}
			return singleton;
		}
	}

	/**
	 * @author zouyang
	 * @time 2017年2月7日 下午4:12:49
	 * @description 饿汉式写法，instance在类装载时就实例化，虽然导致类装载的原因有很多种，在单例模式中大多数都是调用getInstance方法，
	 *              但是也不能确定有其他的方式（或者其他的静态方法）导致类装载，这时候初始化instance显然没有达到lazy
	 *              loading的效果。
	 */
	public static class Singleton_03 {
		private static Singleton_03 singleton = new Singleton_03();

		/**
		 * 上面一行也可以换成 private static Singleton_03 singleton = null; static{
		 * singleton = new Singleton_03(); }
		 */
		private Singleton_03() {
		}

		public Singleton_03 getInstance() {
			return singleton;
		}
	}

	/**
	 * @author zouyang
	 * @time 2017年2月7日 下午4:22:47
	 * @description 静态内部类写法，这种方式是Singleton类被装载了，instance不一定被初始化。因为SingletonHolder类没有被主动使用，
	 *              只有显示通过调用getInstance方法时，才会显示装载SingletonHolder类，从而实例化instance
	 */
	public static class Singleton_04 {
		private static class SingletonHolder {
			private static final Singleton_04 singleton = new Singleton_04();
		}

		private Singleton_04() {
		}

		private Singleton_04 getInstance() {
			return SingletonHolder.singleton;
		}
	}

}
