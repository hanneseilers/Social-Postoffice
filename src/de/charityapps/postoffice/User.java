package de.charityapps.postoffice;

public class User {

	private int id;
	private String name = "";
	private String house = "";
	private String floor = "";
	private String room = "";
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
		if( vName.length() > 20 )
			vName = vName.substring( 0, 18 ) + ".";
		return String.format( "%1$-20s", vName)
				+ "\tHaus: " + String.format( "%1$4s", house )
				+ "\tEtage: " + String.format( "%1$6s", floor )
				+ "\tRaum: " + String.format( "%1$4s", floor )
				+ "\tBriefe: " + (income-outgo);
	}
	
}
