package com.kh.appoproject.advice.faq.model.vo;

import java.sql.Date;

public class Faq {
	private int faqNo;
	private String faqClass;
	private String faqTitle;
	private String faqContent;
	private Date faqCreateDate;
	
	public Faq() {
		// TODO Auto-generated constructor stub
	}

	public Faq(int faqNo, String faqClass, String faqTitle, String faqContent, Date faqCreateDate) {
		super();
		this.faqNo = faqNo;
		this.faqClass = faqClass;
		this.faqTitle = faqTitle;
		this.faqContent = faqContent;
		this.faqCreateDate = faqCreateDate;
	}

	public Faq(String faqClass, String faqTitle, String faqContent) {
		super();
		this.faqClass = faqClass;
		this.faqTitle = faqTitle;
		this.faqContent = faqContent;
	}

	public Faq(int faqNo, String faqClass, String faqTitle, String faqContent) {
		super();
		this.faqNo = faqNo;
		this.faqClass = faqClass;
		this.faqTitle = faqTitle;
		this.faqContent = faqContent;
	}

	public int getFaqNo() {
		return faqNo;
	}

	public void setFaqNo(int faqNo) {
		this.faqNo = faqNo;
	}

	public String getFaqClass() {
		return faqClass;
	}

	public void setFaqClass(String faqClass) {
		this.faqClass = faqClass;
	}

	public String getFaqTitle() {
		return faqTitle;
	}

	public void setFaqTitle(String faqTitle) {
		this.faqTitle = faqTitle;
	}

	public String getFaqContent() {
		return faqContent;
	}

	public void setFaqContent(String faqContent) {
		this.faqContent = faqContent;
	}

	public Date getFaqCreateDate() {
		return faqCreateDate;
	}

	public void setFaqCreateDate(Date faqCreateDate) {
		this.faqCreateDate = faqCreateDate;
	}

	@Override
	public String toString() {
		return "Faq [faqNo=" + faqNo + ", faqClass=" + faqClass + ", faqTitle=" + faqTitle + ", faqContent="
				+ faqContent + ", faqCreateDate=" + faqCreateDate + "]";
	}
	
	
	
}
