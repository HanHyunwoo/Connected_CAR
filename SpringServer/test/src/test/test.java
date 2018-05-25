package test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class test {
	

	public static void main(String[] args) {
		SimpleDateFormat formatter = new SimpleDateFormat ( "yyyy.MM.dd HH:mm:ss", Locale.KOREA ); 
		Date currentTime = new Date ( ); 
		String dTime = formatter.format ( currentTime ); 
		System.out.println (dTime);

	    Calendar cal = new GregorianCalendar();
	    cal.add(Calendar.DATE, -1);
	    System.out.println("어제 년: " +  cal.get(Calendar.YEAR));
	    System.out.println("어제 월: " + (cal.get(Calendar.MONTH) + 1));
	    System.out.println("어제 일: " +  cal.get(Calendar.DAY_OF_MONTH));
	    
	    // 24시간 전의 날짜, 시간, 시간대를
	    // Sun Dec 10 13:50:52 KST 2006 이런 식으로 한 줄로 출력
	    Date date = cal.getTime();
	    System.out.println(date.toString());
	}

}
