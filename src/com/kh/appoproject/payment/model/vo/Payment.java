package com.kh.appoproject.payment.model.vo;

public class Payment {
	
	private int paymentNo; // 결제(주문)번호
	private int basicNo; // 상품번호
	private String DestinationAddr; // 배송지
	private String DestinationContact; // 연락처
	private String DestinationNote; // 배송메모
	private String DestinationName; // 수령인
	private String productTitle; // 상품이름 ex)일반 , 경매? 
	private String deviceName; // ex)mac, ipad
	private String itemName; // ex) mini, pro
	private String memberId; // 판매자명
	private int basicPrice; // 가격
	private String imagePath; // 이미지
	private String impUid;
	private String merchantUid;
	private String orderMethod;
	
	public Payment() {}

	public Payment(int paymentNo, int basicNo, String destinationAddr, String destinationContact,
			String destinationNote, String destinationName, String productTitle, String deviceName, String itemName,
			String memberId, int basicPrice, String imagePath, String impUid, String merchantUid) {
		super();
		this.paymentNo = paymentNo;
		this.basicNo = basicNo;
		DestinationAddr = destinationAddr;
		DestinationContact = destinationContact;
		DestinationNote = destinationNote;
		DestinationName = destinationName;
		this.productTitle = productTitle;
		this.deviceName = deviceName;
		this.itemName = itemName;
		this.memberId = memberId;
		this.basicPrice = basicPrice;
		this.imagePath = imagePath;
		this.impUid = impUid;
		this.merchantUid = merchantUid;
	}
	
	
	
	public Payment(String destinationAddr, String orderMethod) {
		super();
		DestinationAddr = destinationAddr;
		this.orderMethod = orderMethod;
	}

	public Payment(int basicNo, String destinationAddr, int basicPrice, String orderMethod) {
		super();
		this.basicNo = basicNo;
		DestinationAddr = destinationAddr;
		this.basicPrice = basicPrice;
		this.orderMethod = orderMethod;
	}

	public Payment(int basicNo, String destinationAddr, int basicPrice) {
		super();
		this.basicNo = basicNo;
		DestinationAddr = destinationAddr;
		this.basicPrice = basicPrice;
	}

	public int getPaymentNo() {
		return paymentNo;
	}

	public void setPaymentNo(int paymentNo) {
		this.paymentNo = paymentNo;
	}

	public int getBasicNo() {
		return basicNo;
	}

	public void setBasicNo(int basicNo) {
		this.basicNo = basicNo;
	}

	public String getDestinationAddr() {
		return DestinationAddr;
	}

	public void setDestinationAddr(String destinationAddr) {
		DestinationAddr = destinationAddr;
	}

	public String getDestinationContact() {
		return DestinationContact;
	}

	public void setDestinationContact(String destinationContact) {
		DestinationContact = destinationContact;
	}

	public String getDestinationNote() {
		return DestinationNote;
	}

	public void setDestinationNote(String destinationNote) {
		DestinationNote = destinationNote;
	}

	public String getDestinationName() {
		return DestinationName;
	}

	public void setDestinationName(String destinationName) {
		DestinationName = destinationName;
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

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
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
	
	

	public String getOrderMethod() {
		return orderMethod;
	}

	public void setOrderMethod(String orderMethod) {
		this.orderMethod = orderMethod;
	}

	@Override
	public String toString() {
		return "Payment [paymentNo=" + paymentNo + ", basicNo=" + basicNo + ", DestinationAddr=" + DestinationAddr
				+ ", DestinationContact=" + DestinationContact + ", DestinationNote=" + DestinationNote
				+ ", DestinationName=" + DestinationName + ", productTitle=" + productTitle + ", deviceName="
				+ deviceName + ", itemName=" + itemName + ", memberId=" + memberId + ", basicPrice=" + basicPrice
				+ ", imagePath=" + imagePath + ", impUid=" + impUid + ", merchantUid=" + merchantUid + ", orderMethod="
				+ orderMethod + "]";
	}

	
}

