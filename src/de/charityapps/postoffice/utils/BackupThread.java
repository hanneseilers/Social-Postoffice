package de.charityapps.postoffice.utils;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import de.charityapps.postoffice.Database;

public class BackupThread extends Thread {

	private static Logger logger = LogManager.getLogger( BackupThread.class );
	private boolean mStop = false;
	
	public BackupThread() {}
	
	@Override
	public void run() {
		logger.info( "Started backup thread" );
		
		long vLastBackup = System.currentTimeMillis();
		while( !mStop ){
			if( System.currentTimeMillis()-vLastBackup >= 3600000 ){
				Database.getInstance().backup();
				vLastBackup = System.currentTimeMillis();
			}
			
			try {
				Thread.sleep( 250 );
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		logger.info( "Stopped backup thread" );
	}
	
	public void setStop(){
		mStop = true;	
		
		try {
			join(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
