package controllerTest;

import java.io.File;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import common.utils.SystemUtils;

public class TestMain {
	public static void main(String[] args){
//		String beginTime = "2016/2-28";
//		String endTime = "2016-3-16";
//		System.out.println(getDateList(beginTime,endTime).toString());
		   // 第一种：获取类加载的根路径   D:\git\daotie\daotie\target\classes
//        String s = SystemUtils.class.getClassLoader().getResource("").getPath();
//        System.out.println(s);

//		String value = SystemUtils.GetValueByKey("test.name");
//		System.out.println(value);
//		Object[] objs = null;
//		ArrayUtils au = 
//		System.out.println(objs.length);
//		for(int i = 0;i<objs.length;i++){
//			System.out.println(objs[i]);
//		}
		ArrayList<Object> list = new ArrayList<>();
		list.add("fdasfdas");
		list.add(123);
		justTest(list.toArray());
	}
	
	public static void justTest(Object... objs){
		for(Object obj:objs){
			System.out.println(obj);
		}
	}
	public static List<Map> getAllMap(List<Map> list,List<String> dateList,String xName,String yName){
		List<Map> resultList = new ArrayList<Map>();
		for(int i = 0,j = 0;i<dateList.size();i++){
			Map<String,Object> map = new HashMap<String,Object>();
			if(!dateList.get(i).equals(list.get(j))){
				map.put(xName, dateList.get(i));
				map.put(yName, "0");
			}else{
				map = list.get(j);
				j++;
			}
			resultList.add(map);
		}
		return resultList;
	}
	
	public static List<String> getDateList(String beginTime,String endTime){
		if(beginTime.indexOf("/")>0||endTime.indexOf("/")>0){
			beginTime = beginTime.replaceAll("/", "-");
			endTime = endTime.replaceAll("/", "-");
		}
		int interval = intervalOfDay(beginTime,endTime);
		
		Date beginDate = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		
		try {
			beginDate = df.parse(beginTime);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(beginDate);
		List<String> dateList = new ArrayList<String>();
		for(int i = 0;i<=interval;i++){
			dateList.add(df.format(calendar.getTime()));
			calendar.add(calendar.DAY_OF_MONTH, 1);
		}
		return dateList;
	}
	public static int intervalOfDay(String beginTime,String endTime){
		Date beginDate = new Date();
		Date endDate = new Date();
		SimpleDateFormat  df = new SimpleDateFormat("yyyy-MM-dd");
		try {
			beginDate = df.parse(beginTime);
			endDate = df.parse(endTime);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		int ss = 1000;  
        int mi = ss * 60;  
        int hh = mi * 60;  
        int dd = hh * 24;
		return (int) ((endDate.getTime()-beginDate.getTime())/dd);
	}
}
