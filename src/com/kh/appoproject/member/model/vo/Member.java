package com.kh.appoproject.member.model.vo;

import java.sql.Date;

public class Member {
	
	private int member_No;
	private String member_Id;
	private String member_Pwd;
	private String member_NM;
	private String member_Phone;
	private String member_Email;
	private Date member_ErollDate;
	private String member_Account;
	private String member_Status;
	private String member_Address;
	
	public Member() {
		// TODO Auto-generated constructor stub
	}

	public Member(int member_No, String member_Id, String member_Pwd, String member_NM, String member_Phone,
			String member_Email, Date member_ErollDate, String member_Account, String member_Status, String member_Address) {
		super();
		this.member_No = member_No;
		this.member_Id = member_Id;
		this.member_Pwd = member_Pwd;
		this.member_NM = member_NM;
		this.member_Phone = member_Phone;
		this.member_Email = member_Email;
		this.member_ErollDate = member_ErollDate;
		this.member_Account = member_Account;
		this.member_Status = member_Status;
		this.member_Address = member_Address;
	}
	
	public Member(String member_Id, String member_Pwd){
		super();
		this.member_Id = member_Id;
		this.member_Pwd = member_Pwd;
	}
	

	public Member(String member_Id, String member_Pwd, String member_NM, String member_Phone, String member_Email,
			String member_Account, String member_Address) {
		super();
		this.member_Id = member_Id;
		this.member_Pwd = member_Pwd;
		this.member_NM = member_NM;
		this.member_Phone = member_Phone;
		this.member_Email = member_Email;
		this.member_Account = member_Account;
		this.member_Address = member_Address;
	}
	
	public Member(String member_Id, String member_NM, String member_Phone, String member_Email, String member_Account,
			String member_Address) { // 추가
		super();
		this.member_Id = member_Id;
		this.member_NM = member_NM;
		this.member_Phone = member_Phone;
		this.member_Email = member_Email;
		this.member_Account = member_Account;
		this.member_Address = member_Address;
	}
	

	public Member(String member_Id, String member_NM, String member_Email) {
		super();
		this.member_Id = member_Id;
		this.member_NM = member_NM;
		this.member_Email = member_Email;
	}

	public int getMember_No() {
		return member_No;
	}

	public void setMember_No(int member_No) {
		this.member_No = member_No;
	}

	public String getMember_Id() {
		return member_Id;
	}

	public void setMember_Id(String member_Id) {
		this.member_Id = member_Id;
	}

	public String getMember_Pwd() {
		return member_Pwd;
	}

	public void setMember_Pwd(String member_Pwd) {
		this.member_Pwd = member_Pwd;
	}

	public String getMember_NM() {
		return member_NM;
	}

	public void setMember_NM(String member_NM) {
		this.member_NM = member_NM;
	}

	public String getMember_Phone() {
		return member_Phone;
	}

	public void setMember_Phone(String member_Phone) {
		this.member_Phone = member_Phone;
	}

	public String getMember_Email() {
		return member_Email;
	}

	public void setMember_Email(String member_Email) {
		this.member_Email = member_Email;
	}

	public Date getMember_ErollDate() {
		return member_ErollDate;
	}

	public void setMember_ErollDate(Date member_ErollDate) {
		this.member_ErollDate = member_ErollDate;
	}

	public String getMember_Account() {
		return member_Account;
	}

	public void setMember_Account(String member_Account) {
		this.member_Account = member_Account;
	}

	public String getMember_Status() {
		return member_Status;
	}

	public void setMember_Status(String member_Status) {
		this.member_Status = member_Status;
	}

	public String getMember_Address() {
		return member_Address;
	}

	public void setMember_Address(String member_Address) {
		this.member_Address = member_Address;
	}

	@Override
	public String toString() {
		return "Member [member_No=" + member_No + ", member_Id=" + member_Id + ", member_Pwd=" + member_Pwd
				+ ", member_NM=" + member_NM + ", member_Phone=" + member_Phone + ", member_Email=" + member_Email
				+ ", member_ErollDate=" + member_ErollDate + ", member_Account=" + member_Account + ", member_Status="
				+ member_Status + ", member_Address=" + member_Address + "]";
	}

	
	
	

}
