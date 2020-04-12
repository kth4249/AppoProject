package com.kh.appoproject.advice.qna.model.vo;

public class Qna {
	private int qnaNo;
	private String qnaTitle;
	private String qnaContent;
	private String qnaStatus;
	private int memberNo;
	private String answerContent;
	private String answerYn;
	
	public Qna() {
		// TODO Auto-generated constructor stub
	}
	
	public Qna(int qnaNo, String qnaTitle, String qnaContent, String qnaStatus, int memberNo, String answerContent,
			String answerYn) {
		super();
		this.qnaNo = qnaNo;
		this.qnaTitle = qnaTitle;
		this.qnaContent = qnaContent;
		this.qnaStatus = qnaStatus;
		this.memberNo = memberNo;
		this.answerContent = answerContent;
		this.answerYn = answerYn;
	}

	public Qna(String qnaTitle, String qnaContent, int memberNo) {
		super();
		this.qnaTitle = qnaTitle;
		this.qnaContent = qnaContent;
		this.memberNo = memberNo;
	}
	
	

	public Qna(int qnaNo, String qnaTitle, String qnaContent, String answerContent) {
		super();
		this.qnaNo = qnaNo;
		this.qnaTitle = qnaTitle;
		this.qnaContent = qnaContent;
		this.answerContent = answerContent;
	}

	public Qna(int qnaNo, String qnaTitle, String qnaContent, String answerContent, String answerYn) {
		super();
		this.qnaNo = qnaNo;
		this.qnaTitle = qnaTitle;
		this.qnaContent = qnaContent;
		this.answerContent = answerContent;
		this.answerYn = answerYn;
	}

	public Qna(int qnaNo, String qnaTitle, String qnaContent, int memberNo) {
		super();
		this.qnaNo = qnaNo;
		this.qnaTitle = qnaTitle;
		this.qnaContent = qnaContent;
		this.memberNo = memberNo;
	}
	
	

	public Qna(String qnaTitle, String qnaContent, String answerContent) {
		super();
		this.qnaTitle = qnaTitle;
		this.qnaContent = qnaContent;
		this.answerContent = answerContent;
	}

	public Qna(String qnaTitle, String qnaContent, String answerContent, String answerYn) {
		super();
		this.qnaTitle = qnaTitle;
		this.qnaContent = qnaContent;
		this.answerContent = answerContent;
		this.answerYn = answerYn;
	}
	
	

	public Qna(int qnaNo, String qnaTitle, String qnaContent) {
		super();
		this.qnaNo = qnaNo;
		this.qnaTitle = qnaTitle;
		this.qnaContent = qnaContent;
	}

	public int getQnaNo() {
		return qnaNo;
	}

	public void setQnaNo(int qnaNo) {
		this.qnaNo = qnaNo;
	}

	public String getQnaTitle() {
		return qnaTitle;
	}

	public void setQnaTitle(String qnaTitle) {
		this.qnaTitle = qnaTitle;
	}

	public String getQnaContent() {
		return qnaContent;
	}

	public void setQnaContent(String qnaContent) {
		this.qnaContent = qnaContent;
	}

	public String getQnaStatus() {
		return qnaStatus;
	}

	public void setQnaStatus(String qnaStatus) {
		this.qnaStatus = qnaStatus;
	}

	public int getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}

	public String getAnswerContent() {
		return answerContent;
	}

	public void setAnswerContent(String answerContent) {
		this.answerContent = answerContent;
	}

	public String getAnswerYn() {
		return answerYn;
	}

	public void setAnswerYn(String answerYn) {
		this.answerYn = answerYn;
	}

	@Override
	public String toString() {
		return "Qna [qnaNo=" + qnaNo + ", qnaTitle=" + qnaTitle + ", qnaContent=" + qnaContent + ", qnaStatus="
				+ qnaStatus + ", memberNo=" + memberNo + ", answerContent=" + answerContent + ", answerYn=" + answerYn
				+ "]";
	}
	
	
	
	
	
}
