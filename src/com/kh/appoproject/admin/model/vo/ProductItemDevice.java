package com.kh.appoproject.admin.model.vo;

public class ProductItemDevice {
	private String itemName;
	private String itemInfo;
	private String deviceName;
	
	public ProductItemDevice() {
		// TODO Auto-generated constructor stub
	}

	public ProductItemDevice(String itemName, String itemInfo, String deviceName) {
		super();
		this.itemName = itemName;
		this.itemInfo = itemInfo;
		this.deviceName = deviceName;
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

	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	@Override
	public String toString() {
		return "ProductItemDevice [itemName=" + itemName + ", itemInfo=" + itemInfo + ", deviceName=" + deviceName
				+ "]";
	}
	
	
}
