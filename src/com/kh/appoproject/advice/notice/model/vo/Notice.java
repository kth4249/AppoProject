package com.kh.appoproject.advice.notice.model.vo;

import java.sql.Date;

public class Notice {
	
	private int noticeNo;
	private String noticeClass;
	private String noticeTitle;
	private String noticeContent;
	private Date noticeCreateDate;
	private String noticeStatus;
	
	public Notice() {
		// TODO Auto-generated constructor stub
	}

	public Notice(int noticeNo, String noticeClass, String noticeTitle, String noticeContent, Date noticeCreateDate,
			String noticeStatus) {
		super();
		this.noticeNo = noticeNo;
		this.noticeClass = noticeClass;
		this.noticeTitle = noticeTitle;
		this.noticeContent = noticeContent;
		this.noticeCreateDate = noticeCreateDate;
		this.noticeStatus = noticeStatus;
	}

	public Notice(int noticeNo, String noticeClass, String noticeTitle, Date noticeCreateDate) {
		super();
		this.noticeNo = noticeNo;
		this.noticeClass = noticeClass;
		this.noticeTitle = noticeTitle;
		this.noticeCreateDate = noticeCreateDate;
	}

	
	

	public Notice(int noticeNo, String noticeTitle, String noticeContent) {
		super();
		this.noticeNo = noticeNo;
		this.noticeTitle = noticeTitle;
		this.noticeContent = noticeContent;
	}

	public int getNoticeNo() {
		return noticeNo;
	}

	public void setNoticeNo(int noticeNo) {
		this.noticeNo = noticeNo;
	}

	public String getNoticeClass() {
		return noticeClass;
	}

	public void setNoticeClass(String noticeClass) {
		this.noticeClass = noticeClass;
	}

	public String getNoticeTitle() {
		return noticeTitle;
	}

	public void setNoticeTitle(String noticeTitle) {
		this.noticeTitle = noticeTitle;
	}

	public String getNoticeContent() {
		return noticeContent;
	}

	public void setNoticeContent(String noticeContent) {
		this.noticeContent = noticeContent;
	}

	public Date getNoticeCreateDate() {
		return noticeCreateDate;
	}

	public void setNoticeCreateDate(Date noticeCreateDate) {
		this.noticeCreateDate = noticeCreateDate;
	}

	public String getNoticeStatus() {
		return noticeStatus;
	}

	public void setNoticeStatus(String noticeStatus) {
		this.noticeStatus = noticeStatus;
	}

	@Override
	public String toString() {
		return "Notice [noticeNo=" + noticeNo + ", noticeClass=" + noticeClass + ", noticeTitle=" + noticeTitle
				+ ", noticeContent=" + noticeContent + ", noticeCreateDate=" + noticeCreateDate + ", noticeStatus="
				+ noticeStatus + "]";
	}
	
	
	
}
