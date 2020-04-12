package com.kh.appoproject.admin.model.vo;

public class Image {
	private int imageNo;
	private String imagePath;
	private char imageState;
	private int imageLevel;
	private int productNo;
	
	public Image() {
		// TODO Auto-generated constructor stub
	}
	
	// 상품 이미지 조회용 생성자	
	public Image(String imagePath, int productNo) {
		super();
		this.imagePath = imagePath;
		this.productNo = productNo;
	}



	public Image(int imageNo, String imagePath, char imageState, int imageLevel, int productNo) {
		super();
		this.imageNo = imageNo;
		this.imagePath = imagePath;
		this.imageState = imageState;
		this.imageLevel = imageLevel;
		this.productNo = productNo;
	}

	public int getImageNo() {
		return imageNo;
	}

	public void setImageNo(int imageNo) {
		this.imageNo = imageNo;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public char getImageState() {
		return imageState;
	}

	public void setImageState(char imageState) {
		this.imageState = imageState;
	}

	public int getImageLevel() {
		return imageLevel;
	}

	public void setImageLevel(int imageLevel) {
		this.imageLevel = imageLevel;
	}

	public int getProductNo() {
		return productNo;
	}

	public void setProductNo(int productNo) {
		this.productNo = productNo;
	}

	@Override
	public String toString() {
		return "Image [imageNo=" + imageNo + ", imagePath=" + imagePath + ", imageState=" + imageState + ", imageLevel="
				+ imageLevel + ", productNo=" + productNo + "]";
	}
	
	
}
