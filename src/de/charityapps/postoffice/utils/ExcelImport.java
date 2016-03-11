package de.charityapps.postoffice.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import de.charityapps.postoffice.User;

public class ExcelImport {
	
	private File mExcelFile;
	private HSSFWorkbook mWorkbook;

	/**
	 * Constructor
	 * @param aFile
	 * @throws FileNotFoundException
	 */
	public ExcelImport(File aFile) throws FileNotFoundException {
		if( aFile.isFile() && aFile.exists()
				&& (aFile.getPath().endsWith(".xls") || aFile.getPath().endsWith(".xlsx")) )
		mExcelFile = aFile;
		readWorkbook();
	}
	
	/**
	 * Constructor
	 * @param aFileName
	 * @throws FileNotFoundException
	 */
	public ExcelImport(String aFileName) throws FileNotFoundException {
		this( new File(aFileName) );
	}
	
	public List<User> getUsers(){
		List<User> vUsers = new ArrayList<User>();
		if( mWorkbook != null ){
			
			// go through every sheet as house
			
		}
		
		return vUsers;
	}
	
	
	private void readWorkbook() throws FileNotFoundException{
		try {
			
			if( mExcelFile != null ){
				POIFSFileSystem vFileSystem = new POIFSFileSystem(mExcelFile);
				mWorkbook = new HSSFWorkbook( vFileSystem );				
			}
			
		} catch (IOException e) {
			e.printStackTrace();
			throw new FileNotFoundException("Excel file not found");
		}
	}
	
	
}
