package com.yunengzhe.PVMTS.domain.vo;

public class IsReadCount {

	private int alarmCount;//报警未读总数
	private int letterCount;//站内信未读总数
	private int noticeCount;//系统未读总数
	public int getAlarmCount() {
		return alarmCount;
	}
	public void setAlarmCount(int alarmCount) {
		this.alarmCount = alarmCount;
	}
	public int getLetterCount() {
		return letterCount;
	}
	public void setLetterCount(int letterCount) {
		this.letterCount = letterCount;
	}
	public int getNoticeCount() {
		return noticeCount;
	}
	public void setNoticeCount(int noticeCount) {
		this.noticeCount = noticeCount;
	}
	public IsReadCount(int alarmCount, int letterCount, int noticeCount) {
		super();
		this.alarmCount = alarmCount;
		this.letterCount = letterCount;
		this.noticeCount = noticeCount;
	}
	public IsReadCount() {
		super();
	}
	@Override
	public String toString() {
		return "IsReadCount [alarmCount=" + alarmCount + ", letterCount="
				+ letterCount + ", noticeCount=" + noticeCount + "]";
	}
	
	
}
