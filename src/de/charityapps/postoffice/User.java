package de.charityapps.postoffice;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import de.charityapps.postoffice.utils.StringUtils;

public class User {
	
	private static Logger logger = LogManager.getLogger( User.class );

	private int id = -1;
	private String name = "";
	private String house = "";
	private String floor = "";
	private String room = "";
	private String birthdate = "00.00.0000";
	private int income = 0;
	private int outgo = 0;
	private boolean manualAdded = false;
	
	public User(){}
	
	public User(User aUser){
		this();
		id = aUser.getId();
		name = aUser.getName();
		house = aUser.getHouse();
		floor = aUser.getFloor();
		room = aUser.getRoom();
		birthdate = aUser.getBirthdate();
		income = aUser.getIncome();
		outgo = aUser.getOutgo();
		manualAdded = aUser.isManualAdded();
	}
	
	public User(int aID) {
		this();
		id = aID;
	}

	public int getId() {
		return id;
	}

	public User setId(int id) {
		this.id = id;
		return this;
	}

	public String getName() {
		return name;
	}

	public User setName(String name) {
		if( name != null )
			name = name.trim();
		this.name = name;
		return this;
	}

	public String getHouse() {
		return house;
	}

	public User setHouse(String house) {
		this.house = house.trim();
		return this;
	}

	public String getFloor() {
		return floor;
	}

	public User setFloor(String floor) {
		this.floor = floor.trim();
		return this;
	}

	public String getRoom() {
		return room;
	}

	public User setRoom(String room) {
		this.room = room.trim();
		return this;
	}
	
	public String getBirthdate() {
		return birthdate;
	}

	public User setBirthdate(String birthdate) {
		this.birthdate = birthdate.trim();
		return this;
	}

	public int getIncome() {
		return income;
	}

	public User setIncome(int income) {
		this.income = income;
		return this;
	}

	public int getOutgo() {
		return outgo;
	}

	public User setOutgo(int outgo) {
		this.outgo = outgo;
		return this;
	}

	public boolean isManualAdded() {
		return manualAdded;
	}

	public User setManualAdded(boolean manualAdded) {
		this.manualAdded = manualAdded;
		return this;
	}
	
	/**
	 * Deletes user from database if it has no more letters.
	 * @return	{@code true} if user deleted, {@code false} otherwise.
	 */
	public boolean delete(){
		if( getId() >= 0 && getIncome() <= getOutgo() ){
			logger.debug( "Deleting user " + this );
			String vSql = "UPDATE users SET deleted=1"
					+ " WHERE rowid=" + getId();
			return Database.getInstance().execUpdate( vSql );
		}
		
		return false;
	}

	/**
	 * Adds user to database.
	 * @return	{@code true} if user was added successfully, {@code false} otherwise.
	 */
	public boolean add(){
		if( getId() < 0 && getName().length() > 0 ){
			logger.debug( "Adding user " + this );
			String vSql = "INSERT INTO users (name, house, floor, room, birthdate, manualAdded) VALUES ("
					+ "'" + getName() + "',"
					+ "'" + getHouse() + "',"
					+ "'" + getFloor() + "',"
					+ "'" + getRoom() + "',"
					+ "'" + getBirthdate() + "',"
					+ (isManualAdded() ? "1" : "0") + ");";
			return Database.getInstance().execUpdate(vSql);
		}
		return false;
	}
	
	/**
	 * Updates user data in database.
	 * @return	{@code true} if update was successfull, {@code false} otherwise.
	 */
	public boolean update(){
		if( getId() >= 0 ){
			logger.debug( "Updating user " + this );
			String vSql = "UPDATE users SET "
					+ "name='" + getName() + "',"
					+ "house='" + getHouse() + "',"
					+ "floor='" + getFloor() + "',"
					+ "room='" + getRoom() + "',"
					+ "birthdate='" + getBirthdate() + "',"
					+ "manualAdded=" + (isManualAdded() ? "1" : "0")
					+ " WHERE rowid=" + getId() + ";";
			return Database.getInstance().execUpdate(vSql);
		}
		return false;
	}
	

	/**
	 * Updates user data in database using data from other user
	 * (without chanign ID, income and outgo).
	 * @param aUser	{@link User} data to use for update	
	 */
	public User update(User aUser){
		setName( aUser.getName() );
		setHouse( aUser.getHouse() );
		setFloor( aUser.getFloor() );
		setRoom( aUser.getRoom() );
		setBirthdate( aUser.getBirthdate() );
		setManualAdded( aUser.isManualAdded() );
		return this;
	}
	
	public String toString(){
		String vName = (name != null ? name : "#ID-" + Integer.toString(getId()));
		return StringUtils.padRight(vName, 20)
				+ "(" + StringUtils.cut(birthdate, 10) + ")"
				+ "\tHaus: " + StringUtils.padLeft(house, 6)
				+ "\tEtage: " + StringUtils.padLeft(floor, 4)
				+ "\tRaum: " + StringUtils.padLeft(room, 4)
				+ "\n\t\t\t\t\tBriefe: " + StringUtils.padLeft(Integer.toString(income-outgo), 4);
	}
	
}
