package javaSE.jdk8;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Test{
	public static void main(String[] args){
		LocalDateTime ldt = LocalDateTime.now();
		System.out.println(ldt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss")));
		
		LocalDate ld = LocalDate.now();
		System.out.println(ld.format(DateTimeFormatter.ISO_LOCAL_DATE));
		
		LocalTime lt = LocalTime.now();
		System.out.println(lt.format(DateTimeFormatter.ofPattern("hh:mm:ss")));
		System.out.println(lt.format(DateTimeFormatter.ISO_TIME));
	}
}
