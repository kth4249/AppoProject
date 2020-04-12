package com.kh.appoproject.product.model.vo;

public class Image {

	private int imageNo;
	private String imagePath;
	private String imageState;
	private int imageLevel;
	private int productNo;
	
	public Image() {	}

	public Image(int imageNo, String imagePath, String imageState, int imageLevel, int productNo) {
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

	public String getImageState() {
		return imageState;
	}

	public void setImageState(String imageState) {
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
		return "Image [imageNo=" + imageNo + ", " + (imagePath != null ? "imagePath=" + imagePath + ", " : "")
				+ (imageState != null ? "imageState=" + imageState + ", " : "") + "imageLevel=" + imageLevel
				+ ", productNo=" + productNo + "]";
	}
	
	
}
