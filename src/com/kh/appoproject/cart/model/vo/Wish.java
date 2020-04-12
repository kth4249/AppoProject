package com.kh.appoproject.cart.model.vo;

import java.sql.Date;

public class Wish {

	private int auctionCartNo;
	private int auctionNo;
	private String productTitle;
	private String deviceName;
	private String itemName;
	private String memberId;
	private int auctionImmediateBid;
	private int auctionReservePrice;
	private String imagePath;
	private Date auctionDeadline;
	
	public Wish() {	}

	
	


	public Wish(int auctionNo, String productTitle, String deviceName, String itemName, String memberId,
			int auctionImmediateBid, int auctionReservePrice, String imagePath, Date auctionDeadline) {
		super();
		this.auctionNo = auctionNo;
		this.productTitle = productTitle;
		this.deviceName = deviceName;
		this.itemName = itemName;
		this.memberId = memberId;
		this.auctionImmediateBid = auctionImmediateBid;
		this.auctionReservePrice = auctionReservePrice;
		this.imagePath = imagePath;
		this.auctionDeadline = auctionDeadline;
	}



	public Wish(int auctionCartNo, int auctionNo, String productTitle, String deviceName, String itemName,
			String memberId, int auctionImmediateBid, int auctionReservePrice, String imagePath, Date auctionDeadline) {
		super();
		this.auctionCartNo = auctionCartNo;
		this.auctionNo = auctionNo;
		this.productTitle = productTitle;
		this.deviceName = deviceName;
		this.itemName = itemName;
		this.memberId = memberId;
		this.auctionImmediateBid = auctionImmediateBid;
		this.auctionReservePrice = auctionReservePrice;
		this.imagePath = imagePath;
		this.auctionDeadline = auctionDeadline;
	}



	public int getAuctionCartNo() {
		return auctionCartNo;
	}



	public void setAuctionCartNo(int auctionCartNo) {
		this.auctionCartNo = auctionCartNo;
	}



	public int getAuctionNo() {
		return auctionNo;
	}



	public void setAuctionNo(int auctionNo) {
		this.auctionNo = auctionNo;
	}



	public String getProductTitle() {
		return productTitle;
	}



	public void setProductTitle(String productTitle) {
		this.productTitle = productTitle;
	}



	public String getDeviceName() {
		return deviceName;
	}



	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}



	public String getItemName() {
		return itemName;
	}



	public void setItemName(String itemName) {
		this.itemName = itemName;
	}



	public String getMemberId() {
		return memberId;
	}



	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}



	public int getAuctionImmediateBid() {
		return auctionImmediateBid;
	}



	public void setAuctionImmediateBid(int auctionImmediateBid) {
		this.auctionImmediateBid = auctionImmediateBid;
	}



	public int getAuctionReservePrice() {
		return auctionReservePrice;
	}



	public void setAuctionReservePrice(int auctionReservePrice) {
		this.auctionReservePrice = auctionReservePrice;
	}



	public String getImagePath() {
		return imagePath;
	}



	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}



	public Date getAuctionDeadline() {
		return auctionDeadline;
	}



	public void setAuctionDeadline(Date auctionDeadline) {
		this.auctionDeadline = auctionDeadline;
	}



	@Override
	public String toString() {
		return "Wish [auctionCartNo=" + auctionCartNo + ", auctionNo=" + auctionNo + ", productTitle=" + productTitle
				+ ", deviceName=" + deviceName + ", itemName=" + itemName + ", memberId=" + memberId
				+ ", auctionImmediateBid=" + auctionImmediateBid + ", auctionReservePrice=" + auctionReservePrice
				+ ", imagePath=" + imagePath + ", auctionDeadline=" + auctionDeadline + "]";
	}
	
	
}
