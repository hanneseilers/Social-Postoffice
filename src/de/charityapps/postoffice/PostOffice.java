package de.charityapps.postoffice;

import java.io.FileNotFoundException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import com.trolltech.qt.core.QModelIndex;
import com.trolltech.qt.gui.QApplication;
import com.trolltech.qt.gui.QFileDialog;
import com.trolltech.qt.gui.QMainWindow;
import com.trolltech.qt.gui.QStringListModel;

import de.charityapps.postoffice.ui.Ui_MainWindow;
import de.charityapps.postoffice.ui.utils.PrintDialog;
import de.charityapps.postoffice.ui.utils.StatisticsDialog;
import de.charityapps.postoffice.ui.utils.UserDialog;
import de.charityapps.postoffice.utils.BackupThread;
import de.charityapps.postoffice.utils.ExcelImport;
import de.charityapps.postoffice.utils.StatusUpdater;
import de.charityapps.postoffice.utils.UserListUtils;

/**
 * Main class
 * @author eilers
 *
 */
public class PostOffice implements StatusUpdater {
	
	public static final String TAG_VERSION = "";
	public static final String APP_NAME = "Social-Postoffice";
	
	private static PostOffice INSTANCE = null;
	private static Logger logger = LogManager.getLogger( PostOffice.class );
	
	private QApplication mQApplication;
	private QMainWindow mQMainWindow;
	private Ui_MainWindow mUi;
	
	private List<User> mUsers = new ArrayList<User>();
	private String mLastUserSearch = null;
	
	/**
	 * Constructor
	 */
	private PostOffice() {		
		// create database
		logger.debug( "creating database" );
		Database.getInstance();
		
		// create gui
		logger.debug( "starting user interface" );
		mQApplication = new QApplication( new String[]{} );
		mUi = new Ui_MainWindow();
		mQMainWindow = new QMainWindow();
		mUi.setupUi(mQMainWindow);
		mQMainWindow.show();
		
		// connect signals
		logger.debug( "connecting ui singlas" );
		mUi.actionPrint.triggered.connect( this, "printList()" );
		mUi.actionExport.triggered.connect( this, "exportUserData()" );
		mUi.actionImport.triggered.connect( this, "importUserData()" );
		mUi.actionSettings.triggered.connect( this, "editSettings()" );
		mUi.actionStatistic.triggered.connect( this, "showStatistics()" );
		mUi.btnIncome.clicked.connect( this, "addIncome()" );
		mUi.btnOutgo.clicked.connect( this, "addOutgo()" );
		mUi.btnUsrAdd.clicked.connect( this, "addUser()" );
		mUi.btnUsrDelete.clicked.connect( this, "deleteUser()" );
		mUi.btnUsrEdit.clicked.connect( this, "editUser()" );
		mUi.txtSearch.textChanged.connect( this, "searchUser(String)" );
		
		searchUser( null );
	}
	
	/**
	 * @return 	{@link QApplication} of user interface
	 */
	public QApplication getApplication(){
		return mQApplication;
	}
	
	/**
	 * Function to edit user data (shows user dialog)
	 */
	public void editUser(){
		User vUser = getSelectedUser();
		if( vUser != null )
			new UserDialog( vUser ).show();
	}
	
	/**
	 * Function to delete selected user
	 */
	public void deleteUser(){
		User vUser = getSelectedUser();
		if( vUser != null )
			vUser.delete();
		searchUser(mLastUserSearch);
	}
	
	/**
	 * Function to add new user (shows user dialog)
	 */
	public void addUser(){
		new UserDialog( mUi.txtSearch.text().trim() ).show();
	}
	
	/**
	 * Function to search for a user.
	 * If a user found containing the mentioned name,
	 * it will be selected in user interface.
	 * @param aName	{@link String} name of user to find.
	 */
	public void searchUser(String aName){
		if( aName != null )
			aName = aName.trim();
		
		mUsers = getUsers( aName );
		mLastUserSearch = aName;
		
		// convert to string list
		List<String> vUsersList = new ArrayList<String>();
		for( User vUser : mUsers ){
			vUsersList.add( vUser.toString() );
		}		
		
		final QStringListModel vModel = new QStringListModel( vUsersList );		
		vModel.setStringList(vUsersList);
		
		QApplication.invokeLater( new Runnable() {			
			@Override
			public void run() {
				mUi.lstUsr.setModel( vModel );	
			}
		} );		
	}
	
	/**
	 * Updates search by researching with last search parameter.
	 */
	public void updateSearch(){
		searchUser( mLastUserSearch );
	}
	
	/**
	 * Adds a letter to user.
	 */
	public void addIncome(){
		User vUser = getSelectedUser();
		if( vUser != null ){
			String vSql = "UPDATE users SET income=" + (vUser.getIncome()+1)
					+ " WHERE rowid=" + vUser.getId();
			Database.getInstance().execUpdate( vSql );
		}
		searchUser(mLastUserSearch);
	}
	
	/**
	 * Removes letter form user.
	 * Only works if user has pending letters
	 */
	public void addOutgo(){
		User vUser = getSelectedUser();
		if( vUser != null && vUser.getIncome() > vUser.getOutgo() ){
			String vSql = "UPDATE users SET outgo=" + (vUser.getOutgo()+1)
					+ " WHERE rowid=" + vUser.getId();
			Database.getInstance().execUpdate( vSql );
		}
		searchUser(mLastUserSearch);
	}
	
	/**
	 * Shows statistics dialog
	 */
	public void showStatistics(){
		(new StatisticsDialog()).show();
	}
	
	/**
	 * Creates content of users with letters for printing.
	 * Opens the systems print dialog.
	 */
	public void printList(){
		List<User> vUsers = getUsers( null );
		(new PrintDialog(vUsers)).show();
	}
	
	/**
	 * Opens file dialog to choose excel file to import user data.
	 * Imports and updates user data.
	 */
	public void importUserData(){
		final String vFileName = QFileDialog.getOpenFileName( null, "Kundendatei auswählen", "Excel Dateien (*.xls, *.xlsx)" );
		if( vFileName != null
				&& (vFileName.contains(".xls") || vFileName.contains(".xlsx") || vFileName.contains(".csv")) ){
				
				// import user data from file
				logger.info( "Selected file for import: " + vFileName );
				new Thread( new Runnable() {					
					@Override
					public void run() {
						try{
							
							setUserListEnable( false );
							showStatus( "Importing user data ..." );
							logger.info( "Import user data from " + vFileName );
							
							ExcelImport vImport = new ExcelImport( vFileName );
							List<User> vImportUsers = vImport.getUsers();
							List<User> vMergesUsers = ExcelImport.merge(getUsers(), vImportUsers);
							
							// update database
							showStatus( "Updating database ..." );
							logger.info( "Updating database." );
							for( User vUser : vMergesUsers ){
								if( vUser.getName() == null ){
									vUser.delete();
									showStatus( "Kunde löschen: #ID-" + vUser.getId() );
								} else if( vUser.getId() < 0 ){
									vUser.add();
									showStatus( "Kunde hinzufügen: " + vUser.getName() + " (" + vUser.getBirthdate() + ")" );
								} else {
									showStatus( "Kunde aktualisieren: " + vUser.getName() + " (" + vUser.getBirthdate() + ")" );
									vUser.update();
								}
							}
							
							// update search
							searchUser( null );
						
						} catch (FileNotFoundException e) {
							logger.error( "Cannot find file " + vFileName );
							e.printStackTrace();
						}	
						
						setUserListEnable( true );
						showStatus( "Database updated." );
					}
				} ).start();
			
		}
	}
	
	public void exportUserData(){
		// TODO
	}
	
	/**
	 * Opens settings dialog.
	 */
	public void editSettings(){
		// TODO
	}
	
	/**
	 * @return	User selected in user interface.
	 */
	private User getSelectedUser(){
		List<QModelIndex> vSelectedUsers = mUi.lstUsr.selectionModel().selectedRows();
		
		if( vSelectedUsers.size() > 0 && mUsers.size() >= vSelectedUsers.size() ){
			return mUsers.get( vSelectedUsers.get(0).row() );
		}
		
		return null;
	}
	
	/**
	 * @return	{@link List} of all {@link User}s
	 */
	private List<User> getUsers(){
		return getUsers(null);
	}
	
	/**
	 * Gets all users containing a specific name.
	 * @param aName	{@link String} of name for user selection.
	 * @return		{@link List} of {@link User}s that names containing
	 * 				passed as function argument.
	 */
	private List<User> getUsers(String aName){
		List<User> vUsers = new ArrayList<User>();	
		
		try {
			
			String vSql = "SELECT * FROM users WHERE deleted=0 ORDER BY house ASC, floor ASC, name ASC, room ASC;";
			ResultSet vResult = Database.getInstance().execQuery( vSql );
			
			while( vResult.next() ){
				User vUser = new User( vResult.getInt("id") );
				vUser.setName( vResult.getString("name") );
				vUser.setHouse( vResult.getString("house") );
				vUser.setFloor( vResult.getString("floor") );
				vUser.setRoom( vResult.getString("room") );
				vUser.setBirthdate( vResult.getString("birthdate") );
				vUser.setIncome( vResult.getInt("income") );
				vUser.setOutgo( vResult.getInt("outgo") );
				vUser.setManualAdded( vResult.getBoolean("manualAdded") );
				
				vUsers.add( vUser );
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		
		return UserListUtils.selectByName( vUsers, aName );
	}
	
	public void showStatus(final String aMessage){
		QApplication.invokeLater( new Runnable() {			
			@Override
			public void run() {
				mUi.statusbar.showMessage( aMessage );
			}
		} );
	}
	
	public void setUserListEnable(final boolean aEnable){
		QApplication.invokeLater( new Runnable() {			
			@Override
			public void run() {
				mUi.lstUsr.setEnabled( aEnable );
			}
		} );
	}
	
	/**
	 * @return Instance of {@link PostOffice}
	 */
	public static PostOffice getInstance(){
		if( INSTANCE == null )
			INSTANCE = new PostOffice();
		
		return INSTANCE;
	}

	/**
	 * Main function.
	 */
	public static void main(String[] args) {
		Logger vLogger = LogManager.getLogger( PostOffice.class );
		vLogger.info( "checking for updates using jGithubLoader" );
		vLogger.info( "application name: " + APP_NAME );
		vLogger.info( "current version: " + TAG_VERSION );
		
		// load updates
//		GithubLoader vLoader = new GithubLoader( "hanneseilers", "Social-Postoffice", true );
//		vLoader.setAppName( APP_NAME );
//		vLoader.update( TAG_VERSION );
		
		// start database backup thread
		BackupThread vBackupThread = new BackupThread();
		vBackupThread.start();
		
		// start post office
		logger.debug( "starting application" );
		PostOffice.getInstance().getApplication().exec();
		
		// stop database backup thread
		vBackupThread.setStop();		
		
		// create database backup
		logger.debug( "creating database backup" );
		Database.getInstance().backup();
		Database.getInstance().closeDatabaseConnection();
		logger.debug( "application stopped" );		
	}

}
