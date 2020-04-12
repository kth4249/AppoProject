package com.kh.appoproject.product.model.vo;

import java.sql.Date;

public class Order extends Product{
	
	private int orderNo;
	private String orderState;
	private String orderDestination;
	private String orderCheck;
	private Date orderDate;
	private String orderMethod;
	private String importNo;
	private String orderConfirm;
	private int productCode;
	private int buyerCode;
	private String memberId;
	
	public Order() {}

	public Order(int orderNo, String orderState, String orderDestination, String orderCheck, Date orderDate,
			String orderMethod, String importNo, String orderConfirm, int productCode, int buyerCode) {
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
	}
	
	

	public Order(int basicPrice, String productTitle, int orderNo, String orderState, Date orderDate) {
		super(/* basicPrice, productTitle */);
		this.orderNo = orderNo;
		this.orderState = orderState;
		this.orderDate = orderDate;
	}
	
	

	public Order(int basicPrice, String productTitle, String memberId, int orderNo, String orderState, Date orderDate) {
		super(/* basicPrice, productTitle */);
		this.orderNo = orderNo;
		this.orderState = orderState;
		this.orderDate = orderDate;
		this.memberId = memberId;
	}

	public int getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}

	public String getOrderState() {
		return orderState;
	}

	public void setOrderState(String orderState) {
		this.orderState = orderState;
	}

	public String getOrderDestination() {
		return orderDestination;
	}

	public void setOrderDestination(String orderDestination) {
		this.orderDestination = orderDestination;
	}

	public String getOrderCheck() {
		return orderCheck;
	}

	public void setOrderCheck(String orderCheck) {
		this.orderCheck = orderCheck;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public String getOrderMethod() {
		return orderMethod;
	}

	public void setOrderMethod(String orderMethod) {
		this.orderMethod = orderMethod;
	}

	public String getImportNo() {
		return importNo;
	}

	public void setImportNo(String importNo) {
		this.importNo = importNo;
	}

	public String getOrderConfirm() {
		return orderConfirm;
	}

	public void setOrderConfirm(String orderConfirm) {
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

	@Override
	public String toString() {
		return "Order [orderNo=" + orderNo + ", orderState=" + orderState + ", orderDestination=" + orderDestination
				+ ", orderCheck=" + orderCheck + ", orderDate=" + orderDate + ", orderMethod=" + orderMethod
				+ ", importNo=" + importNo + ", orderConfirm=" + orderConfirm + ", productCode=" + productCode
				+ ", buyerCode=" + buyerCode + "]";
	}
	
	
}
