package com.kh.appoproject.payment.model.vo;

public class Order {
	private String impUid;
	private String merchantUid;
	private String name;
	private int amount;
	private String buyerName;
	private String buyerEmail;
	private String buyerTel;
	private String buyerPostCode;
	private String buyerAddr;
	
	public Order() {}

	public Order(String name, int amount, String buyerName, String buyerEmail, String buyerTel, String buyerPostCode,
			String buyerAddr) {
		super();
		this.name = name;
		this.amount = amount;
		this.buyerName = buyerName;
		this.buyerEmail = buyerEmail;
		this.buyerTel = buyerTel;
		this.buyerPostCode = buyerPostCode;
		this.buyerAddr = buyerAddr;
	}

	public Order(String impUid, String merchantUid, String name, int amount, String buyerName, String buyerEmail,
			String buyerTel, String buyerPostCode, String buyerAddr) {
		super();
		this.impUid = impUid;
		this.merchantUid = merchantUid;
		this.name = name;
		this.amount = amount;
		this.buyerName = buyerName;
		this.buyerEmail = buyerEmail;
		this.buyerTel = buyerTel;
		this.buyerPostCode = buyerPostCode;
		this.buyerAddr = buyerAddr;
	}

	public String getImpUid() {
		return impUid;
	}

	public void setImpUid(String impUid) {
		this.impUid = impUid;
	}

	public String getMerchantUid() {
		return merchantUid;
	}

	public void setMerchantUid(String merchantUid) {
		this.merchantUid = merchantUid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getBuyerName() {
		return buyerName;
	}

	public void setBuyerName(String buyerName) {
		this.buyerName = buyerName;
	}

	public String getBuyerEmail() {
		return buyerEmail;
	}

	public void setBuyerEmail(String buyerEmail) {
		this.buyerEmail = buyerEmail;
	}

	public String getBuyerTel() {
		return buyerTel;
	}

	public void setBuyerTel(String buyerTel) {
		this.buyerTel = buyerTel;
	}

	public String getBuyerPostCode() {
		return buyerPostCode;
	}

	public void setBuyerPostCode(String buyerPostCode) {
		this.buyerPostCode = buyerPostCode;
	}

	public String getBuyerAddr() {
		return buyerAddr;
	}

	public void setBuyerAddr(String buyerAddr) {
		this.buyerAddr = buyerAddr;
	}

	@Override
	public String toString() {
		return "Order [impUid=" + impUid + ", merchantUid=" + merchantUid + ", name=" + name + ", amount=" + amount
				+ ", buyerName=" + buyerName + ", buyerEmail=" + buyerEmail + ", buyerTel=" + buyerTel
				+ ", buyerPostCode=" + buyerPostCode + ", buyerAddr=" + buyerAddr + "]";
	}
	
	
}
