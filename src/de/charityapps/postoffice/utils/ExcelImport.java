package de.charityapps.postoffice.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import de.charityapps.postoffice.User;

public class ExcelImport {
	
	private static Logger logger = LogManager.getLogger( ExcelImport.class );
	
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
	
	/**
	 * Reads userdata from all sheets in workbook.
	 * @return	{@link List} of {@link User}.
	 */
	public List<User> getUsers(){
		List<User> vUsers = new ArrayList<User>();
		
		if( mWorkbook != null ){
			
			// go through every sheet as house
			int vNumberOfSheets = mWorkbook.getNumberOfSheets();
			for( int i=0; i < vNumberOfSheets; i++ ){
				
				HSSFSheet vSheet = mWorkbook.getSheetAt(i);
				String vSheetName = vSheet.getSheetName();
				logger.info( "Sheet name: " + vSheetName );
				
				// try to figure out columns to use
				int[] vColumns = getUserdataColumnsFromSheet( vSheet );
				
				// get userdata from sheet
				vUsers.addAll( getUsers(vSheet, vColumns) );
			}
			
		} else {
			logger.error( "Cannot get userdata from workbook. No workbook loaded!" );
		}
		
		return vUsers;
	}
	
	/**
	 * Reads userdata from workbook sheet.
	 * @param aSheet		{@link HSSFSheet} to read userdata from.
	 * @param aColumns		Array of {@link Integer} that specify the columns
	 * 						where the userdata is stored. See {@code getUserdataColumnsFromSheet()}
	 * @return				{@link List} of {@link User}.
	 */
	private List<User> getUsers(HSSFSheet aSheet, int[] aColumns){
		List<User> vUsers = new ArrayList<User>();
		
		if( validateColumnsData(aColumns) && aColumns.length > 3 ){
			
			String vHouse = aSheet.getSheetName();			
			for( int r=1; r < aSheet.getLastRowNum(); r++ ){		
				
				HSSFRow vRow = aSheet.getRow(r);
				User vUser = getUser( vRow, aColumns, vHouse ); 
				if( vUser != null )
					vUsers.add( vUser );
				
			}
			
		} else {
			logger.warn( "Cannot find header data in sheet " + aSheet.getSheetName() );
		}
		
		return vUsers;
	}
	
	/**
	 * Gets userdata from row.
	 * @param aRow		{@link HSSFRow} to get userdata from.
	 * @param aColumns	Array of {@link Integer} that specify the userdata columns.
	 * 					See {@code getUserdataColumnsFromSheet()}.
	 * @param aHouse	{@link String} of users house name.
	 * @return			{@link User} if data found, {@code null} otherwise.
	 */
	private User getUser(HSSFRow aRow, int[] aColumns, String aHouse){		
		if( aRow != null && aColumns.length > 3 ){
			// convert cell types to string
			convertCellTypes( aRow, Arrays.copyOf(aColumns, 3) );
			
			// get cells
			HSSFCell vCellName = aRow.getCell( aColumns[0] );
			HSSFCell vCellFloor = aRow.getCell( aColumns[1] );
			HSSFCell vCellRoom = aRow.getCell( aColumns[2] );
			HSSFCell vCellBirthdate = aRow.getCell( aColumns[3] );
			
			// check cells and create user
			if( vCellName != null && vCellFloor != null && vCellRoom != null && vCellBirthdate != null ){
				String vName = vCellName.getStringCellValue().trim();
				String vFloor = vCellFloor.getStringCellValue().trim();
				String vRoom = vCellRoom.getStringCellValue().trim();
				Date vBirthdate = vCellBirthdate.getDateCellValue();
				
				if( vName.length() > 0 ){
					User vUser = new User(-1);
					vUser.setName( vName );
					vUser.setHouse( aHouse );
					vUser.setFloor( vFloor );
					vUser.setRoom( vRoom );
					if( vBirthdate != null ){
						SimpleDateFormat vDateFormat = new SimpleDateFormat( "MM.dd.yyyy" );
						String vDateString = vDateFormat.format( vBirthdate );
						vUser.setBirthdate( vDateString );
					}
					
					return vUser;
				}
			}
		}
		
		return null;
	}
	
	/**
	 * Converts cells of a row into string cell type.
	 * @param aRow			{@link HSSFRow} to convert.
	 * @param aColumnsData	Array of {@link Integer} cell numbers to convert.
	 */
	private void convertCellTypes(HSSFRow aRow, int[] aColumnsData){
		for( int i=0; i < aColumnsData.length; i++ ){
			if( aColumnsData[i] < aRow.getLastCellNum() ){
				HSSFCell vCell = aRow.getCell( aColumnsData[i] );
				if( vCell != null )
					vCell.setCellType( HSSFCell.CELL_TYPE_STRING );
			}
		}
	}
	
	/**
	 * Validates data of columns number array.
	 * @param aColumnsData	Array of {@link Integer} that represent columns in a sheet.
	 * 						Columns number has to be negative if not found.
	 * @return				{@code true} if all columns numbers are > 0, {@code false} otherwise.
	 */
	private boolean validateColumnsData(int[] aColumnsData){
		for( int i=0; i < aColumnsData.length; i++ ){
			if( aColumnsData[i] < 0 )
				return false;
		}
		
		return true;
	}
	
	/**
	 * Figures out columns of userdata inside a sheet.
	 * For that the first row will be analysed.
	 * @param aSheet	{@link HSSFSheet} to analyse
	 * @return			Array of {@link Integer} with column numbers.
	 * 					Format: [name, floor, room, birthdate]
	 */
	private int[] getUserdataColumnsFromSheet( HSSFSheet aSheet ){
		HSSFRow vRow = aSheet.getRow(0);
		int vColumnNameNum = -1;
		int vColumnFloorNum = -1;
		int vColumnRoomNum = -1;
		int vColumnBirthdateNum = -1;
		
		if( vRow != null ){					
			for( int c=0; c <= vRow.getLastCellNum(); c++ ){						
				// check every cell
				HSSFCell vCell = vRow.getCell(c);
				if( vCell != null ){
					String vCellContent = vCell.getStringCellValue().trim().toLowerCase();
					
					if( vCellContent.equals("name") ){
						vColumnNameNum = c;
					} else if( vCellContent.equals("etage") ){
						vColumnFloorNum = c;
					} else if( vCellContent.equals("zi.") ){
						vColumnRoomNum = c;
					} else if( vCellContent.equals("geburtsdatum") ){
						vColumnBirthdateNum = c;
					}
					
				}						
			}					
		}
		
		return new int[]{ vColumnNameNum, vColumnFloorNum, vColumnRoomNum, vColumnBirthdateNum };
	}
	
	
	/**
	 * Reads a excel workbook from stored file. 
	 * @throws FileNotFoundException
	 */
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
	
	/**
	 * Merges to user lists.
	 * New users will be added and old users will be updated.
	 * @param aOriginal		Original {@link List} of {@link User}.
	 * @param aChanges		New {@link List} of {@link User} to merge into original list.
	 * @return				Merges {@link List} of {@link User}.
	 */
	public static List<User> merge(List<User> aOriginal, List<User> aChanges){
		List<User> vUsers = new ArrayList<User>();
		
		// TODO: Merge
		
		return vUsers;
	}
	
	
}
