package com.kh.appoproject.admin.model.vo;

import java.sql.Date;

public class ProductOrder {
	private int orderNo;
	private char orderState;
	private String orderDestination;
	private char orderCheck;
	private Date orderDate;
	private char orderMethod;
	private String importNo;
	private char orderConfirm;
	private int productCode;
	private int buyerCode;
	private String buyerId;
	private String sellerId;
	private int price;
	
	public ProductOrder() {
		// TODO Auto-generated constructor stub
	}
	
	
	// 결재관리 조회용 생성자
	public ProductOrder(int orderNo, char orderState, String orderDestination, char orderCheck, Date orderDate,
			char orderMethod, String importNo, char orderConfirm, String buyerId, String sellerId, int price) {
		super();
		this.orderNo = orderNo;
		this.orderState = orderState;
		this.orderDestination = orderDestination;
		this.orderCheck = orderCheck;
		this.orderDate = orderDate;
		this.orderMethod = orderMethod;
		this.importNo = importNo;
		this.orderConfirm = orderConfirm;
		this.buyerId = buyerId;
		this.sellerId = sellerId;
		this.price = price;
	}
	
	


	// 결재관리 조회용 생성자
	public ProductOrder(int orderNo, char orderState, String orderDestination, char orderCheck, Date orderDate,
			char orderMethod, String importNo, char orderConfirm, int productCode, String buyerId, String sellerId,
			int price) {
		super();
		this.orderNo = orderNo;
		this.orderState = orderState;
		this.orderDestination = orderDestination;
		this.orderCheck = orderCheck;
		this.orderDate = orderDate;
		this.orderMethod = orderMethod;
		this.importNo = importNo;
		this.orderConfirm = orderConfirm;
		this.productCode = productCode;
		this.buyerId = buyerId;
		this.sellerId = sellerId;
		this.price = price;
	}


	public ProductOrder(int orderNo, char orderState, String orderDestination, char orderCheck, Date orderDate,
			char orderMethod, String importNo, char orderConfirm, int productCode, int buyerCode, String buyerId,
			String sellerId, int price) {
		super();
		this.orderNo = orderNo;
		this.orderState = orderState;
		this.orderDestination = orderDestination;
		this.orderCheck = orderCheck;
		this.orderDate = orderDate;
		this.orderMethod = orderMethod;
		this.importNo = importNo;
		this.orderConfirm = orderConfirm;
		this.productCode = productCode;
		this.buyerCode = buyerCode;
		this.buyerId = buyerId;
		this.sellerId = sellerId;
		this.price = price;
	}

	public int getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}

	public char getOrderState() {
		return orderState;
	}

	public void setOrderState(char orderState) {
		this.orderState = orderState;
	}

	public String getOrderDestination() {
		return orderDestination;
	}

	public void setOrderDestination(String orderDestination) {
		this.orderDestination = orderDestination;
	}

	public char getorderCheck() {
		return orderCheck;
	}

	public void setorderCheck(char orderCheck) {
		this.orderCheck = orderCheck;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public char getOrderMethod() {
		return orderMethod;
	}

	public void setOrderMethod(char orderMethod) {
		this.orderMethod = orderMethod;
	}

	public String getImportNo() {
		return importNo;
	}

	public void setImportNo(String importNo) {
		this.importNo = importNo;
	}

	public char getOrderConfirm() {
		return orderConfirm;
	}

	public void setOrderConfirm(char orderConfirm) {
		this.orderConfirm = orderConfirm;
	}

	public int getProductCode() {
		return productCode;
	}

	public void setProductCode(int productCode) {
		this.productCode = productCode;
	}

	public int getBuyerCode() {
		return buyerCode;
	}

	public void setBuyerCode(int buyerCode) {
		this.buyerCode = buyerCode;
	}

	public String getBuyerId() {
		return buyerId;
	}

	public void setBuyerId(String buyerId) {
		this.buyerId = buyerId;
	}

	public String getSellerId() {
		return sellerId;
	}

	public void setSellerId(String sellerId) {
		this.sellerId = sellerId;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "ProductOrder [orderNo=" + orderNo + ", orderState=" + orderState + ", orderDestination="
				+ orderDestination + ", orderCheck=" + orderCheck + ", orderDate=" + orderDate + ", orderMethod="
				+ orderMethod + ", importNo=" + importNo + ", orderConfirm=" + orderConfirm + ", productCode="
				+ productCode + ", buyerCode=" + buyerCode + ", buyerId=" + buyerId + ", sellerId=" + sellerId
				+ ", price=" + price + "]";
	}
	
	
	
	
	
	
	

}
