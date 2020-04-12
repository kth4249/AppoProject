package com.kh.appoproject.product.model.vo;

import java.sql.Date;

public class Product {

	private int productNo;
	private Date productRegDate;
	private String productTitle;
	private String productComment;
	private String productUsed;
	private String productForm;
	private String productState;
	private int productCount;
	private int memberReg;
	private int itemCode;
	private int basicNo;
	private int basicPrice;
	private int auctionNo;
	private int auctionImmediateBid;
	private int auctionReservePrice;
	private Date auctionDeadline;
	private int biddingMember;
	private String itemName;
	private String itemInfo;
	private String deviceName;
	private String memberId;
	private int deviceCode;
	private String imagePath;
	private Date productEnrollDate; // 추가
	private String destinationAddr; 
	private String destinationContact;
	private String destinationNote;
	private String destinationName;
	private String destinationReceiber;

	public Product() {	}

	/**
	 * @param basicPrice
	 */
	public Product(int basicPrice) {
		super();
		this.basicPrice = basicPrice;
	}

	
	public Product(String itemName) {
		super();
		this.itemName = itemName;
	}

	public Product(int productNo, String productTitle, String productComment, int itemCode) {
		super();
		this.productNo = productNo;
		this.productTitle = productTitle;
		this.productComment = productComment;
		this.itemCode = itemCode;
	}

	/**
	 * @param productTitle
	 * @param productComment
	 * @param productUsed
	 * @param productForm
	 * @param itemCode
	 */
	public Product(String productTitle, String productComment, String productUsed, String productForm, int itemCode) {
		super();
		this.productTitle = productTitle;
		this.productComment = productComment;
		this.productUsed = productUsed;
		this.productForm = productForm;
		this.itemCode = itemCode;
	}


	/**
	 * @param auctionImmediateBid
	 * @param auctionReservePrice
	 * @param auctionDeadline
	 */
	public Product(int auctionImmediateBid, int auctionReservePrice, Date auctionDeadline) {
		super();
		this.auctionImmediateBid = auctionImmediateBid;
		this.auctionReservePrice = auctionReservePrice;
		this.auctionDeadline = auctionDeadline;
	}

	public Product(String productTitle, String productComment, String productUsed, String productForm, int itemCode,
			int basicPrice) {
		super();
		this.productTitle = productTitle;
		this.productComment = productComment;
		this.productUsed = productUsed;
		this.productForm = productForm;
		this.itemCode = itemCode;
		this.basicPrice = basicPrice;
	}


	public Product(String productTitle, String productComment, String productUsed, String productForm, int itemCode,
			int auctionImmediateBid, int auctionReservePrice, Date auctionDeadline) {
		super();
		this.productTitle = productTitle;
		this.productComment = productComment;
		this.productUsed = productUsed;
		this.productForm = productForm;
		this.itemCode = itemCode;
		this.auctionImmediateBid = auctionImmediateBid;
		this.auctionReservePrice = auctionReservePrice;
		this.auctionDeadline = auctionDeadline;
	}



	public Product(int productNo, String productTitle, String productForm, int productCount, int basicPrice,
			int auctionImmediateBid, int auctionReservePrice, Date auctionDeadline) {
		super();
		this.productNo = productNo;
		this.productTitle = productTitle;
		this.productForm = productForm;
		this.productCount = productCount;
		this.basicPrice = basicPrice;
		this.auctionImmediateBid = auctionImmediateBid;
		this.auctionReservePrice = auctionReservePrice;
		this.auctionDeadline = auctionDeadline;
	}

	/**
	 * @param productNo
	 * @param productTitle
	 * @param productComment
	 * @param productUsed
	 * @param productForm
	 * @param productCount
	 * @param memberReg
	 * @param basicPrice
	 * @param auctionImmediateBid
	 * @param auctionReservePrice
	 * @param auctionDeadline
	 * @param biddingMember
	 * @param itemName
	 * @param itemInfo
	 * @param deviceName
	 */
	public Product(int productNo, String productTitle, Date productRegDate, String productComment, String productUsed, String productForm,
			int productCount, int memberReg, int basicPrice, int auctionImmediateBid, int auctionReservePrice,
			Date auctionDeadline, int biddingMember, String itemName, String itemInfo, String deviceName, String memberId) {
		super();
		this.productNo = productNo;
		this.productTitle = productTitle;
		this.productRegDate = productRegDate;
		this.productComment = productComment;
		this.productUsed = productUsed;
		this.productForm = productForm;
		this.productCount = productCount;
		this.memberReg = memberReg;
		this.basicPrice = basicPrice;
		this.auctionImmediateBid = auctionImmediateBid;
		this.auctionReservePrice = auctionReservePrice;
		this.auctionDeadline = auctionDeadline;
		this.biddingMember = biddingMember;
		this.itemName = itemName;
		this.itemInfo = itemInfo;
		this.deviceName = deviceName;
		this.memberId = memberId;
	}

	public Product(int productNo, Date productRegDate, String productTitle, String productComment,
			String productUsed, String productForm, String productState, int productCount, int memberReg, int itemCode,
			int basicNo, int basicPrice, int auctionNo, int auctionImmediateBid, int auctionReservePrice,
			Date auctionDeadline, int biddingMember) {
		super();
		this.productNo = productNo;
		this.productRegDate = productRegDate;
		this.productTitle = productTitle;
		this.productComment = productComment;
		this.productUsed = productUsed;
		this.productForm = productForm;
		this.productState = productState;
		this.productCount = productCount;
		this.memberReg = memberReg;
		this.itemCode = itemCode;
		this.basicNo = basicNo;
		this.basicPrice = basicPrice;
		this.auctionNo = auctionNo;
		this.auctionImmediateBid = auctionImmediateBid;
		this.auctionReservePrice = auctionReservePrice;
		this.auctionDeadline = auctionDeadline;
		this.biddingMember = biddingMember;
	}
	
	public Product(int productNo, Date productEnrollDate, String productTitle, String productState, int productCount) {
		super();
		this.productNo = productNo;
		this.productEnrollDate = productEnrollDate;
		this.productTitle = productTitle;
		this.productState = productState;
		this.productCount = productCount;
	}
	
	
	



	public Product(int productNo, Date productRegDate, String productTitle, String productComment, String productUsed,
			String productForm, String productState, int productCount, int memberReg, int itemCode, int basicNo,
			int basicPrice, int auctionNo, int auctionImmediateBid, int auctionReservePrice, Date auctionDeadline,
			int biddingMember, String itemName, String itemInfo, String deviceName, String memberId, int deviceCode,
			String imagePath, Date productEnrollDate) {
		super();
		this.productNo = productNo;
		this.productRegDate = productRegDate;
		this.productTitle = productTitle;
		this.productComment = productComment;
		this.productUsed = productUsed;
		this.productForm = productForm;
		this.productState = productState;
		this.productCount = productCount;
		this.memberReg = memberReg;
		this.itemCode = itemCode;
		this.basicNo = basicNo;
		this.basicPrice = basicPrice;
		this.auctionNo = auctionNo;
		this.auctionImmediateBid = auctionImmediateBid;
		this.auctionReservePrice = auctionReservePrice;
		this.auctionDeadline = auctionDeadline;
		this.biddingMember = biddingMember;
		this.itemName = itemName;
		this.itemInfo = itemInfo;
		this.deviceName = deviceName;
		this.memberId = memberId;
		this.deviceCode = deviceCode;
		this.imagePath = imagePath;
		this.productEnrollDate = productEnrollDate;
	}
	
	

	public Product(int productNo, String productUsed, int auctionReservePrice, int biddingMember, String itemName,
			String itemInfo, String deviceName, String memberId, String imagePath, String destinationAddr,
			String destinationContact, String destinationNote, String destinationName, String destinationReceiber) {
		super();
		this.productNo = productNo;
		this.productUsed = productUsed;
		this.auctionReservePrice = auctionReservePrice;
		this.biddingMember = biddingMember;
		this.itemName = itemName;
		this.itemInfo = itemInfo;
		this.deviceName = deviceName;
		this.memberId = memberId;
		this.imagePath = imagePath;
		this.destinationAddr = destinationAddr;
		this.destinationContact = destinationContact;
		this.destinationNote = destinationNote;
		this.destinationName = destinationName;
		this.destinationReceiber = destinationReceiber;
	}

	public Date getProductEnrollDate() { // 추가
		return productEnrollDate;
	}


	public void setProductEnrollDate(Date productEnrollDate) { // 추가
		this.productEnrollDate = productEnrollDate;
	}


	public int getDeviceCode() {
		return deviceCode;
	}



	public void setDeviceCode(int deviceCode) {
		this.deviceCode = deviceCode;
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

	public String getProductUsed() {
		return productUsed;
	}

	public void setProductUsed(String productUsed) {
		this.productUsed = productUsed;
	}

	public String getProductForm() {
		return productForm;
	}

	public void setProductForm(String productForm) {
		this.productForm = productForm;
	}

	public String getProductState() {
		return productState;
	}

	public void setProductState(String productState) {
		this.productState = productState;
	}

	public int getProductCount() {
		return productCount;
	}

	public void setProductCount(int productCount) {
		this.productCount = productCount;
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

	public int getBasicNo() {
		return basicNo;
	}

	public void setBasicNo(int basicNo) {
		this.basicNo = basicNo;
	}

	public int getBasicPrice() {
		return basicPrice;
	}

	public void setBasicPrice(int basicPrice) {
		this.basicPrice = basicPrice;
	}

	public int getAuctionNo() {
		return auctionNo;
	}

	public void setAuctionNo(int auctionNo) {
		this.auctionNo = auctionNo;
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

	public Date getAuctionDeadline() {
		return auctionDeadline;
	}

	public void setAuctionDeadline(Date auctionDeadline) {
		this.auctionDeadline = auctionDeadline;
	}

	public int getBiddingMember() {
		return biddingMember;
	}

	public void setBiddingMember(int biddingMember) {
		this.biddingMember = biddingMember;
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


	public String getMemberId() {
		return memberId;
	}




	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}



	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	
	

	public String getDestinationAddr() {
		return destinationAddr;
	}

	public void setDestinationAddr(String destinationAddr) {
		this.destinationAddr = destinationAddr;
	}

	public String getDestinationContact() {
		return destinationContact;
	}

	public void setDestinationContact(String destinationContact) {
		this.destinationContact = destinationContact;
	}

	public String getDestinationNote() {
		return destinationNote;
	}

	public void setDestinationNote(String destinationNote) {
		this.destinationNote = destinationNote;
	}

	public String getDestinationName() {
		return destinationName;
	}

	public void setDestinationName(String destinationName) {
		this.destinationName = destinationName;
	}

	public String getDestinationReceiber() {
		return destinationReceiber;
	}

	public void setDestinationReceiber(String destinationReceiber) {
		this.destinationReceiber = destinationReceiber;
	}

	@Override
	public String toString() {
		return "Product [productNo=" + productNo + ", productRegDate=" + productRegDate + ", productTitle="
				+ productTitle + ", productComment=" + productComment + ", productUsed=" + productUsed
				+ ", productForm=" + productForm + ", productState=" + productState + ", productCount=" + productCount
				+ ", memberReg=" + memberReg + ", itemCode=" + itemCode + ", basicNo=" + basicNo + ", basicPrice="
				+ basicPrice + ", auctionNo=" + auctionNo + ", auctionImmediateBid=" + auctionImmediateBid
				+ ", auctionReservePrice=" + auctionReservePrice + ", auctionDeadline=" + auctionDeadline
				+ ", biddingMember=" + biddingMember + ", itemName=" + itemName + ", itemInfo=" + itemInfo
				+ ", deviceName=" + deviceName + ", memberId=" + memberId + ", deviceCode=" + deviceCode
				+ ", imagePath=" + imagePath + "]";
	}



	
	
	
}	