package properties;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.Set;

public abstract class ReadPropertiesUtil {

	public static Map<String,String> readByProperties(String filepath){
		FileInputStream in = null;
		try {
			in = new FileInputStream(new File(filepath));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		
		Properties prop = new Properties();
		try {
			prop.load(in);
			if(in != null){
				in.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		Set<Entry<Object, Object>> props = prop.entrySet();
		Map<String,String> map = new HashMap<String,String>();
		Iterator<Entry<Object, Object>> it = props.iterator();
		while(it.hasNext()){
			Entry<Object,Object> entry = it.next();
			map.put(entry.getKey().toString(), entry.getValue().toString());
		}
		return map;
	}
	
//	public static Map<String,String> readByResourceBundle(String filepath){
//		ResourceBundle rb = ResourceBundle.getBundle(filepath);
//		Set<String> keySet = rb.keySet();
//		Iterator<String> it = keySet.iterator();
//		String key = "";
//		Map<String,String> map = new HashMap<String,String>();
//		while(it.hasNext()){
//			key = it.next();
//			String value = rb.getString(key);
//			map.put(key, value);
//		}
//		return map;
//	}
}
