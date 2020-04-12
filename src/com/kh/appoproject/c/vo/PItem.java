package com.kh.appoproject.c.vo;

public class PItem {

	private int itemCode;
	private String itemName;
	private String itemInfo;
	private int marketPrice;
	private int deviceCode;
	
	public PItem() {	}
	
	

	public PItem(String itemName, String itemInfo, int marketPrice) {
		super();
		this.itemName = itemName;
		this.itemInfo = itemInfo;
		this.marketPrice = marketPrice;
	}



	public PItem(int itemCode, String itemName, String itemInfo, int marketPrice, int deviceCode) {
		super();
		this.itemCode = itemCode;
		this.itemName = itemName;
		this.itemInfo = itemInfo;
		this.marketPrice = marketPrice;
		this.deviceCode = deviceCode;
	}

	public int getItemCode() {
		return itemCode;
	}

	public void setItemCode(int itemCode) {
		this.itemCode = itemCode;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getItemInfo() {
		return itemInfo;
	}

	public void setItemInfo(String itemInfo) {
		this.itemInfo = itemInfo;
	}

	public int getMarketPrice() {
		return marketPrice;
	}

	public void setMarketPrice(int marketPrice) {
		this.marketPrice = marketPrice;
	}

	public int getDeviceCode() {
		return deviceCode;
	}

	public void setDeviceCode(int deviceCode) {
		this.deviceCode = deviceCode;
	}

	@Override
	public String toString() {
		return "PItem [itemCode=" + itemCode + ", itemName=" + itemName + ", itemInfo=" + itemInfo + ", marketPrice="
				+ marketPrice + ", deviceCode=" + deviceCode + "]";
	}
	
	
	
}
