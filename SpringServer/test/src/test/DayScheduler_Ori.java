package test;

import java.util.Timer;
import java.util.TimerTask;
import java.util.Calendar;
import java.util.Date;

class DayScheduler_Ori {
	public DayScheduler_Ori() {
		Timer timer = new Timer(false);
		Calendar cal = Calendar.getInstance();
		// 특정시간부터 시작하고자 할 경우 아래와같이 .set으로 설정, 안해도 무방함
		cal.set(2018, 4, 23, 20, 59, 0); // 년,월(0~11,즉 5월은 4),일,시,분,초 지정
		timer.schedule(new MyTask(), new Date(cal.getTimeInMillis()), 3000); // 24 * 60 * 60 * 1000
	}

	public static void main(String[] args) {
		new DayScheduler_Ori();
	}
}

class MyTask extends TimerTask {
	public void run() {
		System.out.println("timer task");
	}
}
