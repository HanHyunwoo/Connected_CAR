package com.vo;

public class Analyzed {
	String CarId; //아이디
	String DateStart;//발생시간
	String DateEnd; //발생종료시간
	int Burst; //급가속
	int Deceleration; //급감속
	int QuickStart; //급출발
	int SuddenStop; //급정지
	int SafetyDis; //안전거리
	int Snooze; //졸음
	int Battery; //배터리
	
	public String getCarId() {
		return CarId;
	}
	public void setCarId(String carId) {
		CarId = carId;
	}
	public String getDateStart() {
		return DateStart;
	}
	public void setDateStart(String dateStart) {
		DateStart = dateStart;
	}
	public String getDateEnd() {
		return DateEnd;
	}
	public void setDateEnd(String dateEnd) {
		DateEnd = dateEnd;
	}
	public int getBurst() {
		return Burst;
	}
	public void setBurst(int burst) {
		Burst = burst;
	}
	public int getDeceleration() {
		return Deceleration;
	}
	public void setDeceleration(int deceleration) {
		Deceleration = deceleration;
	}
	public int getQuickStart() {
		return QuickStart;
	}
	public void setQuickStart(int quickStart) {
		QuickStart = quickStart;
	}
	public int getSuddenStop() {
		return SuddenStop;
	}
	public void setSuddenStop(int suddenStop) {
		SuddenStop = suddenStop;
	}
	public int getSafetyDis() {
		return SafetyDis;
	}
	public void setSafetyDis(int safetyDis) {
		SafetyDis = safetyDis;
	}
	public int getSnooze() {
		return Snooze;
	}
	public void setSnooze(int snooze) {
		Snooze = snooze;
	}
	public int getBattery() {
		return Battery;
	}
	public void setBattery(int battery) {
		Battery = battery;
	}
	@Override
	public String toString() {
		return "Analyzed [CarId=" + CarId + ", DateStart=" + DateStart + ", DateEnd=" + DateEnd + ", Burst=" + Burst
				+ ", Deceleration=" + Deceleration + ", QuickStart=" + QuickStart + ", SuddenStop=" + SuddenStop
				+ ", SafetyDis=" + SafetyDis + ", Snooze=" + Snooze + ", Battery=" + Battery + "]";
	}
	
	

}
