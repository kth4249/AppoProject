package com.kh.appoproject.admin.model.vo;

import java.sql.Date;

public class Member {
	private int memberNo;
	private String memberId;
	private String memberPwd;
	private String memberNM;
	private String memberPhone;
	private String memberEmail;
	private String memberAddr;
	private Date memberEnrollDate;
	private String memberAccount;
	private char memberStatus;
	
	public Member() {
		// TODO Auto-generated constructor stub
	}
	
	
	// 오늘 가입한 회원 조회용 생성자
	public Member(String memberId, String memberNM, String memberPhone, String memberEmail) {
		super();
		this.memberId = memberId;
		this.memberNM = memberNM;
		this.memberPhone = memberPhone;
		this.memberEmail = memberEmail;
	}



	// 모든 회원 정보 조회용 생성자
	public Member(int memberNo, String memberId, String memberNM, String memberPhone, String memberEmail) {
		super();
		this.memberNo = memberNo;
		this.memberId = memberId;
		this.memberNM = memberNM;
		this.memberPhone = memberPhone;
		this.memberEmail = memberEmail;
	}

	
	// 아이디로 회원상세조회 생성자
	public Member(int memberNo, String memberId, String memberNM, String memberPhone, String memberEmail,
			Date memberEnrollDate, String memberAccount) {
		super();
		this.memberNo = memberNo;
		this.memberId = memberId;
		this.memberNM = memberNM;
		this.memberPhone = memberPhone;
		this.memberEmail = memberEmail;
		this.memberEnrollDate = memberEnrollDate;
		this.memberAccount = memberAccount;
	}
	
	
	
	public Member(int memberNo, String memberId, String memberNM, String memberPhone, String memberEmail,
			String memberAddr, Date memberEnrollDate, String memberAccount) {
		super();
		this.memberNo = memberNo;
		this.memberId = memberId;
		this.memberNM = memberNM;
		this.memberPhone = memberPhone;
		this.memberEmail = memberEmail;
		this.memberAddr = memberAddr;
		this.memberEnrollDate = memberEnrollDate;
		this.memberAccount = memberAccount;
	}


	public Member(int memberNo, String memberId, String memberPwd, String memberNM, String memberPhone,
			String memberEmail, String memberAddr, Date memberEnrollDate, String memberAccount, char memberStatus) {
		super();
		this.memberNo = memberNo;
		this.memberId = memberId;
		this.memberPwd = memberPwd;
		this.memberNM = memberNM;
		this.memberPhone = memberPhone;
		this.memberEmail = memberEmail;
		this.memberAddr = memberAddr;
		this.memberEnrollDate = memberEnrollDate;
		this.memberAccount = memberAccount;
		this.memberStatus = memberStatus;
	}


	public int getMemberNo() {
		return memberNo;
	}


	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}


	public String getMemberId() {
		return memberId;
	}


	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}


	public String getMemberPwd() {
		return memberPwd;
	}


	public void setMemberPwd(String memberPwd) {
		this.memberPwd = memberPwd;
	}


	public String getMemberNM() {
		return memberNM;
	}


	public void setMemberNM(String memberNM) {
		this.memberNM = memberNM;
	}


	public String getMemberPhone() {
		return memberPhone;
	}


	public void setMemberPhone(String memberPhone) {
		this.memberPhone = memberPhone;
	}


	public String getMemberEmail() {
		return memberEmail;
	}


	public void setMemberEmail(String memberEmail) {
		this.memberEmail = memberEmail;
	}


	public String getMemberAddr() {
		return memberAddr;
	}


	public void setMemberAddr(String memberAddr) {
		this.memberAddr = memberAddr;
	}


	public Date getMemberEnrollDate() {
		return memberEnrollDate;
	}


	public void setMemberEnrollDate(Date memberEnrollDate) {
		this.memberEnrollDate = memberEnrollDate;
	}


	public String getMemberAccount() {
		return memberAccount;
	}


	public void setMemberAccount(String memberAccount) {
		this.memberAccount = memberAccount;
	}


	public char getMemberStatus() {
		return memberStatus;
	}


	public void setMemberStatus(char memberStatus) {
		this.memberStatus = memberStatus;
	}


	@Override
	public String toString() {
		return "Member [memberNo=" + memberNo + ", memberId=" + memberId + ", memberPwd=" + memberPwd + ", memberNM="
				+ memberNM + ", memberPhone=" + memberPhone + ", memberEmail=" + memberEmail + ", memberAddr="
				+ memberAddr + ", memberEnrollDate=" + memberEnrollDate + ", memberAccount=" + memberAccount
				+ ", memberStatus=" + memberStatus + "]";
	}
	
	
	
}
