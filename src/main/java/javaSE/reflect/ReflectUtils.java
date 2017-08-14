package javaSE.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ReflectUtils {
	/**
	 * 写代码的时候，发现从父类class通过getDeclaredMethod获取的Method可以调用子类的对象，而子类改写了这个方法，从子类class通过getDeclaredMethod也能获取到Method，这时去调用父类的对象也会报错。
	 * 在Method.invoke()方法内部存在强制类型转换，因为父类强制转化成子类的的时候就会报错类型不匹配错误了，所以如果变量的引用声明是父但实际指向的对象是子，那么这种调用也是可以的。
	 * @throws SecurityException 
	 * @throws NoSuchFieldException 
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException 
	 */
	
	//得到某个对象的属性
	public static Object getObjectProperty(Object obj,String fieldName) throws Exception{
		Class<?> objClass = obj.getClass();//得到该对象的Class
		Field field = objClass.getField(fieldName);//通过Class得到该类声明的属性
		Object o = field.get(obj);//通过对象得到该属性的实例，如果这个属性是非公有的，这里会报IllegalAccessException。
		return o;
	}
	
	//得到某个类的静态属性
	public static Object getObjectStaticProperty(String className,String fieldName) throws Exception{
		Class<?> objClass = Class.forName(className);
		Field field = objClass.getField(fieldName);
		Object o = field.get(objClass);//这里和上面有些不同，因为该属性是静态的，所以直接从类的Class里取。
		return o;
	}
	
	//执行某个对象的方法
	public static Object invokeMethod(Object obj,String methodName,Object[] args) throws Exception{
		Class<?> objClass = obj.getClass();
		Class<?>[] argsClass = new Class[args.length];
		for(int i=0;i<args.length;i++){
			argsClass[i] = args[i].getClass();
		}
		//通过methodName和参数的argsClass（方法中的参数类型集合）数组得到要执行的Method。
		Method method = objClass.getMethod(methodName, argsClass);
		return method.invoke(obj, args);
	}
	
	//执行某个对象的静态方法
	public static Object invokeStaticMethod(String className,String methodName,Object[] args) throws Exception{
		Class<?> objClass = Class.forName(className);
		Class<?>[] argsClass = new Class[args.length];
		for(int i=0;i<args.length;i++){
			argsClass[i] = args[i].getClass();
		}
		Method method = objClass.getMethod(methodName, argsClass);
		//和上面执行某个对象的方法不同点是最后一行，invoke的一个参数是null，因为这是静态方法，不需要借助实例运行。
		return method.invoke(null, args);
	}
	
	//新建一个实例
	public static Object newInstance(String className,Object[] args) throws Exception{
		Class<?> objClass = Class.forName(className);
		Class<?>[] argsClass = new Class[args.length];
		for(int i=0;i<args.length;i++){
			argsClass[i] = args[i].getClass();
		}
		Constructor cons = objClass.getConstructor(argsClass);
		return cons.newInstance(args);
	}
}
