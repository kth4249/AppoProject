package com.kh.appoproject.admin.model.vo;

public class Destination {
	private int destinationNo;
	private String destinationAddr;
	private String destinationContact;
	private String destinationNote;
	private String destinationName;
	private int memberCode;
	
	public Destination() {
		// TODO Auto-generated constructor stub
	}

	public Destination(int destinationNo, String destinationAddr, String destinationContact, String destinationNote,
			String destinationName, int memberCode) {
		super();
		this.destinationNo = destinationNo;
		this.destinationAddr = destinationAddr;
		this.destinationContact = destinationContact;
		this.destinationNote = destinationNote;
		this.destinationName = destinationName;
		this.memberCode = memberCode;
	}

	public int getDestinationNo() {
		return destinationNo;
	}

	public void setDestinationNo(int destinationNo) {
		this.destinationNo = destinationNo;
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

	public int getMemberCode() {
		return memberCode;
	}

	public void setMemberCode(int memberCode) {
		this.memberCode = memberCode;
	}

	@Override
	public String toString() {
		return "Destination [destinationNo=" + destinationNo + ", destinationAddr=" + destinationAddr
				+ ", destinationContact=" + destinationContact + ", destinationNote=" + destinationNote
				+ ", destinationName=" + destinationName + ", memberCode=" + memberCode + "]";
	}
	
	
}
