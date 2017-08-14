package springFramework.typeConversion;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class IntegerToString implements Converter<Integer, String>{

	@Override
	public String convert(Integer n) {
		String str = "str:"+n;
		return str;
	}

}
