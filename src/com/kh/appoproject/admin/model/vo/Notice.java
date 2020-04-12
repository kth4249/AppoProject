package com.kh.appoproject.admin.model.vo;

import java.sql.Date;

public class Notice {
	private int noticeNo;
	private String noticeTitle;
	private String noticeContent;
	private Date noticeCreateDate;
	private String noticeClass;
	
	public Notice() {
		// TODO Auto-generated constructor stub
	}
	

	public Notice(int noticeNo, String noticeTitle, String noticeContent, String noticeClass) {
		super();
		this.noticeNo = noticeNo;
		this.noticeTitle = noticeTitle;
		this.noticeContent = noticeContent;
		this.noticeClass = noticeClass;
	}

	

	public Notice(int noticeNo, String noticeTitle, Date noticeCreateDate) {
		super();
		this.noticeNo = noticeNo;
		this.noticeTitle = noticeTitle;
		this.noticeCreateDate = noticeCreateDate;
	}
	
	
	

	public Notice(int noticeNo, String noticeTitle, Date noticeCreateDate, String noticeClass) {
		super();
		this.noticeNo = noticeNo;
		this.noticeTitle = noticeTitle;
		this.noticeCreateDate = noticeCreateDate;
		this.noticeClass = noticeClass;
	}



	public Notice(String noticeTitle, String noticeContent, String noticeClass) {
		super();
		this.noticeTitle = noticeTitle;
		this.noticeContent = noticeContent;
		this.noticeClass = noticeClass;
	}



	public Notice(int noticeNo, String noticeTitle, String noticeContent, Date noticeCreateDate) {
		super();
		this.noticeNo = noticeNo;
		this.noticeTitle = noticeTitle;
		this.noticeContent = noticeContent;
		this.noticeCreateDate = noticeCreateDate;
	}
	
	

	public Notice(int noticeNo, String noticeTitle, String noticeContent, Date noticeCreateDate, String noticeClass) {
		super();
		this.noticeNo = noticeNo;
		this.noticeTitle = noticeTitle;
		this.noticeContent = noticeContent;
		this.noticeCreateDate = noticeCreateDate;
		this.noticeClass = noticeClass;
	}



	public int getNoticeNo() {
		return noticeNo;
	}

	public void setNoticeNo(int noticeNo) {
		this.noticeNo = noticeNo;
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
	
	
	

	public String getNoticeClass() {
		return noticeClass;
	}



	public void setNoticeClass(String noticeClass) {
		this.noticeClass = noticeClass;
	}



	@Override
	public String toString() {
		return "Notice [noticeNo=" + noticeNo + ", noticeTitle=" + noticeTitle + ", noticeContent=" + noticeContent
				+ ", noticeCreateDate=" + noticeCreateDate + ", noticeClass=" + noticeClass + "]";
	}

	
}
