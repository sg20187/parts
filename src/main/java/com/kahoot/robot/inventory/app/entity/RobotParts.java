package com.kahoot.robot.inventory.app.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
public class RobotParts
{

	@Id
	@GeneratedValue
	private long serialNumber;
	private String name;
	private String manufacturer;
	private double weight;
	
/*	@ManyToMany(cascade = { CascadeType.ALL })
	@JoinTable (name = "COMPATIBLE_ROBOT_PARTS", 
		joinColumns = { @JoinColumn ( name = "SERIAL_NUMBER" ) }, 
		inverseJoinColumns = { @JoinColumn ( name = "COMPATIBLE_SERIAL_NUMBER" ) } )
	private Set < RobotParts > robotParts  = new HashSet < RobotParts > ( );			//robotParts
	
	@ManyToMany ( mappedBy = "robotParts" )
	 @JsonIgnore
	private Set < RobotParts > compatibleRobotParts = new HashSet < RobotParts > ( );   //compatibleRobotParts
*/	
	
	@ManyToMany(cascade = { CascadeType.ALL })
	@JoinTable (name = "COMPATIBLE_ROBOT_PARTS", joinColumns = { 
	@JoinColumn ( name = "SERIAL_NUMBER", referencedColumnName = "serialNumber", nullable = false ) }, inverseJoinColumns = { 
	@JoinColumn ( name = "COMPATIBLE_SERIAL_NUMBER", referencedColumnName = "serialNumber", nullable = false ) } )
	private Set < RobotParts > compatibleParts  = new HashSet < RobotParts > ( );			
	
	@ManyToMany ( mappedBy = "compatibleParts" )
	//@JsonIgnore
	private Set < RobotParts > compatibleTo = new HashSet < RobotParts > ( );   
	
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
	public Set<RobotParts> getCompatibleParts() {
		return compatibleParts;
	}
	public void setCompatibleParts(Set<RobotParts> compatibleParts) {
		this.compatibleParts = compatibleParts;
	}
	public Set<RobotParts> getCompatibleTo() {
		return compatibleTo;
	}
	public void setCompatibleTo(Set<RobotParts> compatibleTo) {
		this.compatibleTo = compatibleTo;
	}
	
	
}
