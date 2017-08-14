package springFramework.formatter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.springframework.format.Formatter;

public class DateFormatter<T extends Date> implements Formatter<T>{

	@Override
	public String print(T source, Locale locale) {
		//将source格式化为String
		return null;
	}

	@Override
	public T parse(String source, Locale locale) throws ParseException {
		//将String格式化为T类型
		return null;
	}

}
