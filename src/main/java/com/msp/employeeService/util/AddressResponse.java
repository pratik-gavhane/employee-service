package com.msp.employeeService.util;

public class AddressResponse {
	private int id;
	
	private String lane1;
	
	private String lane2;
	
	private String pincode;
	
	private String state;
	
	public AddressResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AddressResponse(String lane1, String lane2, String pincode, String state) {
		super();
		this.lane1 = lane1;
		this.lane2 = lane2;
		this.pincode = pincode;
		this.state = state;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLane1() {
		return lane1;
	}

	public void setLane1(String lane1) {
		this.lane1 = lane1;
	}

	public String getLane2() {
		return lane2;
	}

	public void setLane2(String lane2) {
		this.lane2 = lane2;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Override
	public String toString() {
		return "AddressResponse [id=" + id + ", lane1=" + lane1 + ", lane2=" + lane2 + ", pincode=" + pincode
				+ ", state=" + state + "]";
	}
	
}
