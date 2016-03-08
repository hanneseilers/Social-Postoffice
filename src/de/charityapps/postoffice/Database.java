package de.charityapps.postoffice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;


/**
 * Class to handle database requests
 * @author eilers
 *
 */
public class Database {
	
	private static Database INSTANCE = null;
	private static Logger logger = LogManager.getLogger( Database.class );
	
	private Connection mConnection;
	
	private Database() {		
		logger.info( "Starting Database Connection" );		
		try {
			
			Class.forName("org.sqlite.JDBC");
			mConnection = DriverManager.getConnection( "jdbc:sqlite:postoffice.db" );
			initDatabase();
			
		} catch (ClassNotFoundException e) {
			logger.error( e.getMessage() );
			e.printStackTrace();
			System.exit(0);
		} catch (SQLException e) {
			logger.error( e.getMessage() );
			e.printStackTrace();
			System.exit(0);
		}		
	}
	
	private void initDatabase(){
		logger.debug( "initiating database tables" );
		String vSql = "CREATE TABLE IF NOT EXISTS users "
				+"(id INTEGER PRIMARY KEY ASC,"
				+ "name VARCHAR(45) NOT NULL DEFAULT '',"
				+ "house VARCHAR(45) NULL,"
				+ "floor VARCHAR(45) NULL,"
				+ "room VARCHAR(45) NULL,"
				+ "income INT(11) NOT NULL DEFAULT 0,"
				+ "outgo INT(11) NOT NULL DEFAULT 0,"
				+ "manualAdded TINYINT(1) NOT NULL DEFAULT 0,"
				+ "deleted TINYINT(1) NOT NULL DEFAULT 0);";
		execUpdate( vSql );
	}
	
	public ResultSet execQuery(String aSql){		
		try {
			
			logger.debug( "Executed sql query: " + aSql );
			Statement vStatement = mConnection.createStatement();
			ResultSet vResult = vStatement.executeQuery( aSql );
			return vResult;
			
		} catch (SQLException e) {
			logger.error( e.getMessage() );
			e.printStackTrace();
		}
		
		return null;		
	}
	
	public boolean execUpdate(String aSql){		
		try {
			
			logger.debug( "Executed sql update: " + aSql );
			Statement vStatement = mConnection.createStatement();
			int vResult = vStatement.executeUpdate( aSql );
			vStatement.close();
			return (vResult != 0);
			
		} catch (SQLException e) {
			logger.error( e.getMessage() );
			e.printStackTrace();
		}	
		
		return false;
	}
	
	public void closeDatabaseConnection(){
		try{
			if( mConnection != null )
				mConnection.close();
		} catch (SQLException e) {
			logger.error( e.getMessage() );
			e.printStackTrace();
		}	
	}
	
	public void backup(){
		// TODO
	}
	
	public static Database getInstance(){
		if( INSTANCE == null )
			INSTANCE = new Database();
		
		return INSTANCE;
	}

}
