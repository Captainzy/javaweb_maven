package javaSE.collections;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Vector;

/**
 * @author zouyang
 * @time 2017年8月24日 下午5:10:03
 * @description TODO
 */
public class CollectionTest {
	public static void main(String[] args){
		
		//HashSet特点：排列无序，不可重复，底层使用哈希表实现，存取速度快，内部是HashMap
		Set set1 = new HashSet<>();
		//LinkedHashSet特点：采用哈希表存储，并用双向链表记录插入顺序，内不是LinkedHashMap
		Set set2 = new LinkedHashSet<>();
		//TreeSet特点：排列无序，不可重复，底层使用二叉树实现，排序存储，内部是TreeMap的SortedSet
		Set set3 = new TreeSet<>();
		
		//HashMap特点：键不可重复，值可重复，底层哈希表，线程不安全，允许key和value的值为null
		Map map1 = new HashMap<>();
		//Hashtable特点：键不可重复，值可重复，底层哈希表，线程安全，key和value都不能为null
		Map map2 = new Hashtable<>();
		//TreeMap特点：键不可重复，值可重复，底层二叉树
		Map map3 = new TreeMap<>();
		
		//ArrayList特点：排列有序，可重复，底层使用数组，读取速度快，增删慢，getter()和setter()方法快,线程不安全，当容量不够时，ArrayList是当前容量*1.5+1
		List list1 = new ArrayList<>();
		//LinkedList特点：排列有序，可重复，底层使用双向循环链表数据结构，查询速度慢，增删快，add()和remove()方法快，线程不安全
		List list2 = new LinkedList<>();
		//Vector特点：排序有序，可重复，底层使用数组，读取速度快，增删慢，线程安全，效率低，当容量不够时，Vector默认扩展一倍容量
		List list3 = new Vector<>();
		
	}
}
