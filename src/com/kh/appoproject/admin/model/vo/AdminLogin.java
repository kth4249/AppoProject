package com.kh.appoproject.admin.model.vo;

public class AdminLogin {
	private String adminId;
	private String adminPwd;
	private boolean adminCheck;
	
	public AdminLogin() {
		// TODO Auto-generated constructor stub
	}

	public AdminLogin(String adminId, String adminPwd, boolean adminCheck) {
		super();
		this.adminId = adminId;
		this.adminPwd = adminPwd;
		this.adminCheck = adminCheck;
	}

	public String getAdminId() {
		return adminId;
	}

	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}

	public String getAdminPwd() {
		return adminPwd;
	}

	public void setAdminPwd(String adminPwd) {
		this.adminPwd = adminPwd;
	}

	public boolean getAdminCheck() {
		return adminCheck;
	}

	public void setAdminCheck(boolean adminCheck) {
		this.adminCheck = adminCheck;
	}

	@Override
	public String toString() {
		return "AdminLogin [adminId=" + adminId + ", adminPwd=" + adminPwd + ", adminCheck=" + adminCheck + "]";
	}
	
	
	
}
