package com.kh.appoproject.destination.model.vo;

public class Destination {
	private int destinationNo;
	private String destinationAddr;
	private int memberCode;
	private String destinationContact;
	private String destinationNote;
	private String destinationName;
	private String destinationReceiver;
	
	public Destination() {
		// TODO Auto-generated constructor stub
	}

	public Destination(int destinationNo, String destinationAddr, int memberCode, String destinationContact,
			String destinationNote, String destinationName) {
		super();
		this.destinationNo = destinationNo;
		this.destinationAddr = destinationAddr;
		this.memberCode = memberCode;
		this.destinationContact = destinationContact;
		this.destinationNote = destinationNote;
		this.destinationName = destinationName;
	}
	

	public Destination(String destinationAddr, int memberCode, String destinationContact, String destinationNote,
			String destinationName, String destinationReceiver) {
		super();
		this.destinationAddr = destinationAddr;
		this.memberCode = memberCode;
		this.destinationContact = destinationContact;
		this.destinationNote = destinationNote;
		this.destinationName = destinationName;
		this.destinationReceiver = destinationReceiver;
	}

	public Destination(int destinationNo, String destinationAddr, int memberCode, String destinationContact,
			String destinationNote, String destinationName, String destinationReceiver) {
		super();
		this.destinationNo = destinationNo;
		this.destinationAddr = destinationAddr;
		this.memberCode = memberCode;
		this.destinationContact = destinationContact;
		this.destinationNote = destinationNote;
		this.destinationName = destinationName;
		this.destinationReceiver = destinationReceiver;
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

	public int getMemberCode() {
		return memberCode;
	}

	public void setMemberCode(int memberCode) {
		this.memberCode = memberCode;
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

	
	public String getDestinationReceiver() {
		return destinationReceiver;
	}

	public void setDestinationReceiver(String destinationReceiver) {
		this.destinationReceiver = destinationReceiver;
	}

	@Override
	public String toString() {
		return "Destination [destinationNo=" + destinationNo + ", destinationAddr=" + destinationAddr + ", memberCode="
				+ memberCode + ", destinationContact=" + destinationContact + ", destinationNote=" + destinationNote
				+ ", destinationName=" + destinationName + ", destinationReceiver=" + destinationReceiver + "]";
	}

	
	
	
}
