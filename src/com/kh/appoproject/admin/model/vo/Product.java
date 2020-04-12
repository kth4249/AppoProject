package com.kh.appoproject.admin.model.vo;

import java.sql.Date;

public class Product {
	private int productNo;
	private Date productRegDate;
	private String productTitle;
	private String productComment;
	private	char productUsed;
	private char productForm;
	private char productState;
	private int productCount;
	private int productDeclareCount;
	private int memberReg;
	private int itemCode;
	private String memberId;
	private String deviceName;
	
	public Product() {
		// TODO Auto-generated constructor stub
	}

	
	// 관리자 메인 페이지 신고 게시판 조회용 생성자
	public Product(int productNo, String productTitle, String memberId) {
		super();
		this.productNo = productNo;
		this.productTitle = productTitle;
		this.memberId = memberId;
	}
	
	// Board Manage에서 사용
	public Product(int productNo, String productTitle, String deviceName, String memberId) {
		super();
		this.productNo = productNo;
		this.productTitle = productTitle;
		this.deviceName = deviceName;
		this.memberId = memberId;
	}

	public Product(int productNo, String productTitle, String deviceName, String memberId, int productCount) {
		super();
		this.productNo = productNo;
		this.productTitle = productTitle;
		this.productCount = productCount;
		this.memberId = memberId;
		this.deviceName = deviceName;
	}


	// 상품게시글 조회용 생성자
	public Product(int productNo, Date productRegDate, String productTitle, String productComment, char productUsed,
			char productForm, char productState, int productCount, int productDeclareCount, int memberReg,
			int itemCode) {
		super();
		this.productNo = productNo;
		this.productRegDate = productRegDate;
		this.productTitle = productTitle;
		this.productComment = productComment;
		this.productUsed = productUsed;
		this.productForm = productForm;
		this.productState = productState;
		this.productCount = productCount;
		this.productDeclareCount = productDeclareCount;
		this.memberReg = memberReg;
		this.itemCode = itemCode;
	}


	public Product(int productNo, Date productRegDate, String productTitle, String productComment, char productUsed,
			char productForm, char productState, int productCount, int productDeclareCount, int memberReg, int itemCode,
			String memberId) {
		super();
		this.productNo = productNo;
		this.productRegDate = productRegDate;
		this.productTitle = productTitle;
		this.productComment = productComment;
		this.productUsed = productUsed;
		this.productForm = productForm;
		this.productState = productState;
		this.productCount = productCount;
		this.productDeclareCount = productDeclareCount;
		this.memberReg = memberReg;
		this.itemCode = itemCode;
		this.memberId = memberId;
	}




	public int getProductNo() {
		return productNo;
	}

	public void setProductNo(int productNo) {
		this.productNo = productNo;
	}

	public Date getProductRegDate() {
		return productRegDate;
	}

	public void setProductRegDate(Date productRegDate) {
		this.productRegDate = productRegDate;
	}

	public String getProductTitle() {
		return productTitle;
	}

	public void setProductTitle(String productTitle) {
		this.productTitle = productTitle;
	}

	public String getProductComment() {
		return productComment;
	}

	public void setProductComment(String productComment) {
		this.productComment = productComment;
	}

	public char getProductUsed() {
		return productUsed;
	}

	public void setProductUsed(char productUsed) {
		this.productUsed = productUsed;
	}

	public char getProductForm() {
		return productForm;
	}

	public void setProductForm(char productForm) {
		this.productForm = productForm;
	}

	public char getProductState() {
		return productState;
	}

	public void setProductState(char productState) {
		this.productState = productState;
	}

	public int getProductCount() {
		return productCount;
	}

	public void setProductCount(int productCount) {
		this.productCount = productCount;
	}

	public int getProductDeclareCount() {
		return productDeclareCount;
	}

	public void setProductDeclareCount(int productDeclareCount) {
		this.productDeclareCount = productDeclareCount;
	}

	public int getMemberReg() {
		return memberReg;
	}

	public void setMemberReg(int memberReg) {
		this.memberReg = memberReg;
	}

	public int getItemCode() {
		return itemCode;
	}

	public void setItemCode(int itemCode) {
		this.itemCode = itemCode;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	

	public String getDeviceName() {
		return deviceName;
	}


	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}


	@Override
	public String toString() {
		return "Product [productNo=" + productNo + ", productRegDate=" + productRegDate + ", productTitle="
				+ productTitle + ", productComment=" + productComment + ", productUsed=" + productUsed
				+ ", productForm=" + productForm + ", productState=" + productState + ", productCount=" + productCount
				+ ", productDeclareCount=" + productDeclareCount + ", memberReg=" + memberReg + ", itemCode=" + itemCode
				+ ", memberId=" + memberId + "]";
	}

	
	
}
