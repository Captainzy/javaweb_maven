package properties;

import java.util.Map;

public class Test {
	public static void main(String[] args){
		String filepath = Test.class.getClassLoader().getResource("").getPath()+"redis.properties";
		Map<String,String> map = ReadPropertiesUtil.readByProperties(filepath);
		System.out.println("Properties-->"+map.toString());

	}
}
