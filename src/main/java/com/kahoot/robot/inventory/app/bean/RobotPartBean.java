package com.kahoot.robot.inventory.app.bean;

import java.util.List;

public class RobotPartBean {

	private long serialNumber;
	private String name;
	private String manufacturer;
	private double weight;
	private List<Long> compatibleParts;
	private List<Long> partsCompatibleWith;
	
	public long getSerialNumber() {
		return serialNumber;
	}
	public void setSerialNumber(long serialNumber) {
		this.serialNumber = serialNumber;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getManufacturer() {
		return manufacturer;
	}
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}
	public List<Long> getCompatibleParts() {
		return compatibleParts;
	}
	public void setCompatibleParts(List<Long> compatibleParts) {
		this.compatibleParts = compatibleParts;
	}
	public List<Long> getPartsCompatibleWith() {
		return partsCompatibleWith;
	}
	public void setPartsCompatibleWith(List<Long> partsCompatibleWith) {
		this.partsCompatibleWith = partsCompatibleWith;
	}
}
