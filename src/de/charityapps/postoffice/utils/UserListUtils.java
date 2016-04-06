package de.charityapps.postoffice.utils;

import java.util.ArrayList;
import java.util.List;

import de.charityapps.postoffice.User;

/**
 * Class with static functions for operations on {@link User} lists.
 * @author eilers
 *
 */
public class UserListUtils {
	
	/**
	 * Selects users from a user list by its indexes.
	 * @param aUserList	{@link List} of {@link User}.
	 * @param aIndexes	{@link List} of {@link Integer} indexes.
	 * @return			{@link List} of {@link User}.
	 */
	public static List<User> selectByIndexes(List<User> aUserList, List<Integer> aIndexes){
		List<User> vUsers = new ArrayList<User>();
		for( int vIndex : aIndexes ){
			if( vIndex < aUserList.size() )
				vUsers.add( aUserList.get(vIndex) );
		}
		
		return vUsers;
	}
	
	/**
	 * Selects users by its birthdate.
	 * @param aUserList		{@link List} of {@link User} to search in.
	 * @param aBirthdate	{@link String} of birthdate to search for.
	 * @return				{@link List} of {@link User} with matching birthdate. 
	 */
	public static List<User> selectByBirthdate(List<User> aUserList, String aBirthdate){
		List<User> vUsers = new ArrayList<User>();
		for( User vUser : aUserList ){
			if( vUser.getBirthdate().equals(aBirthdate) )
				vUsers.add( vUser );
		}
		
		return vUsers;
	}
	
	/**
	 * Selects unique users with completely mathcing name and birthdate.
	 * @param aUserList		{@link List} of {@link User} to search in.
	 * @param aName			{@link String} of user name.
	 * @param aBirthdate	{@link String} of user birthdate.
	 * @return				{@link List} of {@link User} exactly matching name and birthdate. 
	 */
	public static List<User> selectUnique(List<User> aUserList, String aName, String aBirthdate){
		return selectByBirthdate( selectByName(aUserList, aName, SelectionType.SELECT_EQUALS), aBirthdate );
	}
	
	/**
	 * Searches for user with name in a list an returns all positions
	 * matching.
	 * @param aUserList		{@link List} of {@link User} to search in.
	 * @param aName			{@link String} name to search for.
	 * @return				{@link List} of {@link Integer} of indexes of users with specified names.
	 */
	public static List<Integer> getUserIndexInList(List<User> aUserList, String aName){
		return getUserIndexInList(aUserList, aName, SelectionType.SELECT_EQUALS);
	}
	
	/**
	 * Searches for user with name in a list an returns all positions
	 * matching.
	 * @param aUserList		{@link List} of {@link User} to search in.
	 * @param aName			{@link String} name to search for.
	 * @param aSelectioType	{@link SelectionType} to specify type of search.
	 * @return				{@link List} of {@link Integer} of indexes of users matching parameters.
	 */
	public static List<Integer> getUserIndexInList(List<User> aUserList, String aName, SelectionType aSelectioType){
		List<Integer> vIndexes = new ArrayList<Integer>();
		
		for( int i=0; i < aUserList.size(); i++ ){
			User vUser = aUserList.get( i );
			switch (aSelectioType) {
			case SELECT_CONTAINS:
				if( vUser.getName().contains(aName) )
					vIndexes.add( i );
				break;
				
			case SELECT_ENDSWITH:
				if( vUser.getName().endsWith(aName) )
					vIndexes.add( i );
				break;
				
			case SELECT_EQUALS:
				if( vUser.getName().equals(aName) )
					vIndexes.add( i );
				break;
				
			case SELECT_STARTSWITH:
				if( vUser.getName().startsWith(aName) )
					vIndexes.add( i );
				break;
			}
		}
		
		return vIndexes;
	}

	/**
	 * Selects users from user list by its name.
	 * @param aUserList			{@link List} of {@link User} to search in.
	 * @param aName				{@link String} name to search for.
	 * @return					{@link List} of {@link User} that names are containing the name parameter.
	 */
	public static List<User> selectByName(List<User> aUserList, String aName ){
		return selectByName( aUserList, aName, SelectionType.SELECT_CONTAINS );
	}
	
	/**
	 * Selects users from user list by its name.
	 * @param aUserList			{@link List} of {@link User} to search in.
	 * @param aName				{@link String} name to search for.
	 * @param aSelectioType		{@link SelectionType} to specify type of search.
	 * @return					{@link List} of {@link User} that names are matching parameter.
	 */
	public static List<User> selectByName(List<User> aUserList, String aName, SelectionType aSelectioType ){
		List<User> vUsers = new ArrayList<User>();
		
		if( aName == null )
			return aUserList;
		
		aName = aName.toLowerCase();
		for( User vUser : aUserList ){
			
			String vUserName = vUser.getName().toLowerCase();
			
			switch (aSelectioType) {
			case SELECT_CONTAINS:
				if( vUserName.contains(aName) )
					vUsers.add(vUser);
				break;
				
			case SELECT_ENDSWITH:
				if( vUserName.endsWith(aName) )
					vUsers.add(vUser);
				break;
				
			case SELECT_EQUALS:
				if( vUserName.equals(aName) )
					vUsers.add(vUser);
				break;
				
			case SELECT_STARTSWITH:
				if( vUserName.startsWith(aName) )
					vUsers.add(vUser);
				break;
			}
			
		}
		
		return vUsers;
	}
	
	/**
	 * Selects users from list by their house.
	 * @param aUserList	{@link List} of {@link User}.
	 * @param aHouse	{@link String} of houses.
	 * @return			{@link List} of {@link User}.
	 */
	public static List<User> selectByHouse(List<User> aUserList, String aHouse){
		List<User> vUsers = new ArrayList<User>();
		
		for( User vUser : aUserList ){
			if( vUser.getHouse().equals(aHouse) )
				vUsers.add(vUser);
		}
		
		return vUsers;
	}
	
	/**
	 * Selects users from list by a list of houses.
	 * @param aUserList	{@link List} of {@link User}.
	 * @param aHouses	{@link List} of houses.
	 * @return			{@link List} of {@link User}.
	 */
	public static List<User> selectByHouses(List<User> aUserList, List<String> aHouses){
		List<User> vUsers = new ArrayList<User>();
		
		for( User vUser : aUserList ){
			if( aHouses.contains(vUser.getHouse()) )
				vUsers.add(vUser);
		}
		
		return vUsers;
	}
	
	
	public enum SelectionType{
		SELECT_CONTAINS,
		SELECT_ENDSWITH,
		SELECT_STARTSWITH,
		SELECT_EQUALS
	}
	
}
