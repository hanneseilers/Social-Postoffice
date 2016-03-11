package de.charityapps.postoffice;

import de.charityapps.postoffice.utils.StringUtils;

public class User {

	private int id;
	private String name = "";
	private String house = "";
	private String floor = "";
	private String room = "";
	private String birthdate = "00.00.0000";
	private int income = 0;
	private int outgo = 0;
	private boolean manualAdded = false;
	
	public User(int aID) {
		id = aID;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getHouse() {
		return house;
	}

	public void setHouse(String house) {
		this.house = house;
	}

	public String getFloor() {
		return floor;
	}

	public void setFloor(String floor) {
		this.floor = floor;
	}

	public String getRoom() {
		return room;
	}

	public void setRoom(String room) {
		this.room = room;
	}
	
	public String getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}

	public int getIncome() {
		return income;
	}

	public void setIncome(int income) {
		this.income = income;
	}

	public int getOutgo() {
		return outgo;
	}

	public void setOutgo(int outgo) {
		this.outgo = outgo;
	}

	public boolean isManualAdded() {
		return manualAdded;
	}

	public void setManualAdded(boolean manualAdded) {
		this.manualAdded = manualAdded;
	}
	
	public String toString(){
		String vName = new String(name);
		return StringUtils.padRight(vName, 20)
				+ "(" + StringUtils.cut(birthdate, 10) + ")"
				+ "\tHaus: " + StringUtils.padLeft(house, 6)
				+ "\tEtage: " + StringUtils.padLeft(floor, 4)
				+ "\tRaum: " + StringUtils.padLeft(room, 4)
				+ "\n\t\t\t\t\tBriefe: " + StringUtils.padLeft(Integer.toString(income-outgo), 4);
	}
	
}
