package com.vo;

public class HexaDate {
	String CarId; // 아이디
	String DayDate; // 발생시간
	int score; // 점수
	int efficiency; // 배터리 효율
	int c_Burst; // 급가속
	int c_Deceleration; // 급감속
	int c_QuickStart; // 급출발
	int c_SuddenStop; // 급정지
	int c_SafetyDis; // 안전거리
	int c_Snooze; // 졸음
	String Date;
	int max_efficiency;
	int min_efficiency;

	
	public int getMax_efficiency() {
		return max_efficiency;
	}

	public void setMax_efficiency(int max_efficiency) {
		this.max_efficiency = max_efficiency;
	}

	public int getMin_efficiency() {
		return min_efficiency;
	}

	public void setMin_efficiency(int min_efficiency) {
		this.min_efficiency = min_efficiency;
	}

	public String getDate() {
		return Date;
	}

	public void setDate(String date) {
		this.Date = date;
	}

	public String getCarId() {
		return CarId;
	}

	public void setCarId(String carId) {
		CarId = carId;
	}

	public String getDayDate() {
		return DayDate;
	}

	public void setDayDate(String dayDate) {
		DayDate = dayDate;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getEfficiency() {
		return efficiency;
	}

	public void setEfficiency(int efficiency) {
		this.efficiency = efficiency;
	}

	public int getC_Burst() {
		return c_Burst;
	}

	public void setC_Burst(int c_Burst) {
		this.c_Burst = c_Burst;
	}

	public int getC_Deceleration() {
		return c_Deceleration;
	}

	public void setC_Deceleration(int c_Deceleration) {
		this.c_Deceleration = c_Deceleration;
	}

	public int getC_QuickStart() {
		return c_QuickStart;
	}

	public void setC_QuickStart(int c_QuickStart) {
		this.c_QuickStart = c_QuickStart;
	}

	public int getC_SuddenStop() {
		return c_SuddenStop;
	}

	public void setC_SuddenStop(int c_SuddenStop) {
		this.c_SuddenStop = c_SuddenStop;
	}

	public int getC_SafetyDis() {
		return c_SafetyDis;
	}

	public void setC_SafetyDis(int c_SafetyDis) {
		this.c_SafetyDis = c_SafetyDis;
	}

	public int getC_Snooze() {
		return c_Snooze;
	}

	public void setC_Snooze(int c_Snooze) {
		this.c_Snooze = c_Snooze;
	}

}