package com.kh.appoproject.product.model.vo;


import java.sql.Date;

import com.kh.appoproject.member.model.vo.Member;

public class Auction extends Product {
	
	private int auctionNo;
	private int auctionImmediateBid;
	private int auctionReservePrice;
	private Date auctionDeadline;
	private int biddingMember;
	
	public Auction() {}

	public Auction(int auctionNo, int auctionImmediateBid, int auctionReservePrice, Date auctionDeadline,
			int biddingMember) {
		super();
		this.auctionNo = auctionNo;
		this.auctionImmediateBid = auctionImmediateBid;
		this.auctionReservePrice = auctionReservePrice;
		this.auctionDeadline = auctionDeadline;
		this.biddingMember = biddingMember;
	}
	
	
	public Auction(int int1, Date date, String string, int int2, int int3, int int4, Date date2) {
		// TODO Auto-generated constructor stub
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

	public /*String*/ int getBiddingMember() {
		return biddingMember;
	}

	public void setBiddingMember(int biddingMember) {
		this.biddingMember = biddingMember;
	}

	@Override
	public String toString() {
		return "Auction [auctionNo=" + auctionNo + ", auctionImmediateBid=" + auctionImmediateBid
				+ ", auctionReservePrice=" + auctionReservePrice + ", auctionDeadline=" + auctionDeadline
				+ ", biddingMember=" + biddingMember + "]";
	};
	
	
	
	

}
