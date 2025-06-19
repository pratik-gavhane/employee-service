package com.msp.employeeService.entity;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class Address {
	
	private int id;
	
	private String lane1;
	
	private String lane2;
	
	private String pincode;
	
	private String state;
	
	private int empId;

	public Address() {
		//default constructor required by hibernate
	}
	public Address(String lane1, String lane2, String pincode, String state, int empId) {
		super();
		this.lane1 = lane1;
		this.lane2 = lane2;
		this.pincode = pincode;
		this.state = state;
		this.empId = empId;
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
	public int getEmpId() {
		return empId;
	}
	public void setEmpId(int empId) {
		this.empId = empId;
	}
	
}
