package com.kh.appoproject.product.model.vo;

public class Basic {
	
	private int basicNo;
	private int basicPrice;
	
	public Basic() {}

	public Basic(int basicNo, int basicPrice) {
		super();
		this.basicNo = basicNo;
		this.basicPrice = basicPrice;
	}
	
	

	public Basic(int basicPrice) {
		super();
		this.basicPrice = basicPrice;
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

	@Override
	public String toString() {
		return "Basic [basicNo=" + basicNo + ", basicPrice=" + basicPrice + "]";
	}
	
	
	
}
