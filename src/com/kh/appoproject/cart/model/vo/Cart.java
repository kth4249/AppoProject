package com.kh.appoproject.cart.model.vo;

public class Cart {

	private int cartNo;
	private int basicNo;
	private String productTitle;
	private String deviceName;
	private String itemName;
	private String memberId;
	private int basicPrice;
	private String imagePath;
	
	public Cart() {
	}
	
	

	public Cart(int basicNo, String productTitle, String deviceName, String itemName, String memberId, int basicPrice) {
		super();
		this.basicNo = basicNo;
		this.productTitle = productTitle;
		this.deviceName = deviceName;
		this.itemName = itemName;
		this.memberId = memberId;
		this.basicPrice = basicPrice;
	}



	public Cart(int cartNo, int basicNo, String productTitle, String deviceName, String itemName, String memberId,
			int basicPrice) {
		super();
		this.cartNo = cartNo;
		this.basicNo = basicNo;
		this.productTitle = productTitle;
		this.deviceName = deviceName;
		this.itemName = itemName;
		this.memberId = memberId;
		this.basicPrice = basicPrice;
	}
	
	

	public Cart(int basicNo, String productTitle, String deviceName, String itemName, String memberId, int basicPrice,
			String imagePath) {
		super();
		this.basicNo = basicNo;
		this.productTitle = productTitle;
		this.deviceName = deviceName;
		this.itemName = itemName;
		this.memberId = memberId;
		this.basicPrice = basicPrice;
		this.imagePath = imagePath;
	}



	public String getImagePath() {
		return imagePath;
	}



	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}



	public int getCartNo() {
		return cartNo;
	}

	public void setCartNo(int cartNo) {
		this.cartNo = cartNo;
	}

	public int getBasicNo() {
		return basicNo;
	}

	public void setBasicNo(int basicNo) {
		this.basicNo = basicNo;
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

	public int getBasicPrice() {
		return basicPrice;
	}

	public void setBasicPrice(int basicPrice) {
		this.basicPrice = basicPrice;
	}



	@Override
	public String toString() {
		return "Cart [cartNo=" + cartNo + ", basicNo=" + basicNo + ", productTitle=" + productTitle + ", deviceName="
				+ deviceName + ", itemName=" + itemName + ", memberId=" + memberId + ", basicPrice=" + basicPrice
				+ ", imagePath=" + imagePath + "]";
	}

	
}
