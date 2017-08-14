package collectionTransform;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author zouyang
 * @time 2016年12月1日 上午9:49:41
 * @description Array,List,Set,Map之间的相互转化
 */
public class CollectionTransformUtil {

	/**
	 * 数组转List 工具类 Arrays.asList()把数组转换成集合时，不能使用其修改集合相关的方 法，它的
	 * add/remove/clear方法会抛出 UnsupportedOperationException 异常。 asList 的返回对象是一个
	 * Arrays 内部类，并没有实现集合的修改方法。Arrays.asList体现的是适配器模式，只是转换接口，后台的数据仍是数组
	 */
	public <T> List<T> arrayToList(T[] array) {
		return Arrays.asList(array);
	}

	/**
	 * 数组转Set
	 */
	public <T> Set<T> arrayToSet(T[] array) {
		Set<T> set = new HashSet<T>();
		for (T obj : array) {
			set.add(obj);
		}
		return set;
	}

	/**
	 * List转数组
	 * 使用集合转数组的方法，必须使用集合的 toArray(T[] array)，传入的是类型完全一样的数组，大小就是list.size()。 
	 * 直接使用 toArray 无参方法存在问题，此方法返回值只能是 Object[]类，若强转其它 类型数组将出现 ClassCastException 错误。
	 */
	public <T> T[] listToArray(List<T> list, T[] array) {
		list.toArray(array);
		return array;
	}

	/**
	 * List转Set
	 */
	public <T> Set<T> listToSet(List<T> list) {
		Set<T> set = new HashSet<T>();
		set.addAll(list);
		return set;
	}

	/**
	 * Set转数组
	 */
	public <T> T[] setToArray(Set<T> set, T[] array) {
		set.toArray(array);
		return array;
	}

	/**
	 * Set转List
	 */
	public <T> List<T> setToList(Set<T> set) {
		List<T> list = new ArrayList<T>();
		list.addAll(set);
		return list;
	}

	/**
	 * Map转数组
	 */
	public <E, T> T[] mapToArray(Map<E, T> map, T[] array, boolean flag) {
		// 根据flag判断将Map的key转化为数组还是讲value转化为数组
		if (flag) {
			// flag为true将key转化为数组
			map.keySet().toArray(array);
			return array;
		} else {
			// flag为false将value转化为数组
			map.values().toArray(array);
			return array;
		}
	}

}
