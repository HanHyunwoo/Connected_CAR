package test;

import java.util.Timer;
import java.util.TimerTask;
import java.util.Calendar;
import java.util.Date;

class DayScheduler2 extends TimerTask{
	public DayScheduler2() {
		Timer timer = new Timer(false);
		Calendar cal = Calendar.getInstance();
		// Ư���ð����� �����ϰ��� �� ��� �Ʒ��Ͱ��� .set���� ����, ���ص� ������
		cal.set(2018, 4, 23, 20, 59, 0); // ��,��(0~11,�� 5���� 4),��,��,��,�� ����
		timer.schedule(new DayScheduler2(), new Date(cal.getTimeInMillis()), 3000); // 24 * 60 * 60 * 1000
	}

	public static void main(String[] args) {
		DayScheduler2 k = new DayScheduler2();
		k.run();
	}

	@Override
	public void run() {
		System.out.println("timer task");
		
	}
}

/*class MyTask extends TimerTask {
	public void run() {
		System.out.println("timer task");
	}
}*/