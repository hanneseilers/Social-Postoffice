package de.charityapps.postoffice;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class Statistics {
	
	private static Logger logger = LogManager.getLogger( PostOffice.class );

	public static long getLettersTotal(){
		long vRet = 0;
		try {
			
			String vSql = "SELECT SUM(income-outgo) AS amount FROM users";
			ResultSet vResult = Database.getInstance().execQuery( vSql );		
			return vResult.getLong("amount");
			
		} catch (SQLException e) {
			logger.warn( "Cannot get SQL data: " + e.getMessage() );
			e.printStackTrace();
		}
		
		return vRet;
	}
	
	public static long getLettersOutgo(){
		long vRet = 0;
		try {
			
			String vSql = "SELECT SUM(outgo) AS amount FROM users";
			ResultSet vResult = Database.getInstance().execQuery( vSql );		
			return vResult.getLong("amount");
			
		} catch (SQLException e) {
			logger.warn( "Cannot get SQL data: " + e.getMessage() );
			e.printStackTrace();
		}
		
		return vRet;
	}
	
	public static long getUsersTotal(){
		long vRet = 0;
		try {
			
			String vSql = "SELECT COUNT(name) AS amount FROM users WHERE deleted=0";
			ResultSet vResult = Database.getInstance().execQuery( vSql );		
			return vResult.getLong("amount");
			
		} catch (SQLException e) {
			logger.warn( "Cannot get SQL data: " + e.getMessage() );
			e.printStackTrace();
		}
		
		return vRet;
	}
	
	public static long getUsersManual(){
		long vRet = 0;
		try {
			
			String vSql = "SELECT COUNT(name) AS amount FROM users WHERE manualAdded=1";
			ResultSet vResult = Database.getInstance().execQuery( vSql );		
			return vResult.getLong("amount");
			
		} catch (SQLException e) {
			logger.warn( "Cannot get SQL data: " + e.getMessage() );
			e.printStackTrace();
		}
		
		return vRet;
	}
	
	public static long getUsersDeleted(){
		long vRet = 0;
		try {
			
			String vSql = "SELECT COUNT(name) AS amount FROM users WHERE deleted=1";
			ResultSet vResult = Database.getInstance().execQuery( vSql );		
			return vResult.getLong("amount");
			
		} catch (SQLException e) {
			logger.warn( "Cannot get SQL data: " + e.getMessage() );
			e.printStackTrace();
		}
		
		return vRet;
	}
	
	
}
