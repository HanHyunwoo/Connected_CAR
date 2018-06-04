package test;

import java.util.Timer;
import java.util.TimerTask;
import java.util.Calendar;
import java.util.Date;

class DayScheduler_Ori {
	public DayScheduler_Ori() {
		Timer timer = new Timer(false);
		Calendar cal = Calendar.getInstance();
		// Ư���ð����� �����ϰ��� �� ��� �Ʒ��Ͱ��� .set���� ����, ���ص� ������
		cal.set(2018, 4, 23, 20, 59, 0); // ��,��(0~11,�� 5���� 4),��,��,��,�� ����
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
