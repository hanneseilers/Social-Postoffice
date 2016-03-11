package de.charityapps.postoffice;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.trolltech.qt.core.QModelIndex;
import com.trolltech.qt.gui.QApplication;
import com.trolltech.qt.gui.QMainWindow;
import com.trolltech.qt.gui.QStringListModel;

import de.charityapps.postoffice.ui.Ui_MainWindow;
import de.charityapps.postoffice.ui.utils.UserDialog;
import de.charityapps.postoffice.utils.Printer;
import de.charityapps.postoffice.utils.StringUtils;

/**
 * Main class
 * @author eilers
 *
 */
public class PostOffice {
	
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
		mUi.btnIncome.clicked.connect( this, "addIncome()" );
		mUi.btnOutgo.clicked.connect( this, "addOutgo()" );
		mUi.btnUsrAdd.clicked.connect( this, "addUser()" );
		mUi.btnUsrDelete.clicked.connect( this, "deleteUser()" );
		mUi.btnUsrEdit.clicked.connect( this, "editUser()" );
		mUi.txtSearch.textChanged.connect( this, "searchUser(String)" );
		
		searchUser( null );
	}
	
	public QApplication getApplication(){
		return mQApplication;
	}
	
	public void editUser(){
		
	}
	
	public void deleteUser(){
		User vUser = getSelectedUser();
		if( vUser != null && vUser.getIncome() <= vUser.getOutgo() ){
			String vSql = "UPDATE users SET deleted=1"
					+ " WHERE rowid=" + vUser.getId();
			Database.getInstance().execUpdate( vSql );
		}
		searchUser(mLastUserSearch);
	}
	
	public void addUser(){
		new UserDialog(true);
	}
	
	public void searchUser(String aName){
		mUsers = getUsers( aName );
		mLastUserSearch = aName;
		
		// convert to string list
		List<String> vUsersList = new ArrayList<String>();
		for( User vUser : mUsers ){
			vUsersList.add( vUser.toString() );
		}		
		
		QStringListModel vModel = new QStringListModel( vUsersList );		
		vModel.setStringList(vUsersList);		
		mUi.lstUsr.setModel( vModel );	
	}
	
	public void updateSearch(){
		searchUser( mLastUserSearch );
	}
	
	public void addIncome(){
		User vUser = getSelectedUser();
		if( vUser != null ){
			String vSql = "UPDATE users SET income=" + (vUser.getIncome()+1)
					+ " WHERE rowid=" + vUser.getId();
			Database.getInstance().execUpdate( vSql );
		}
		searchUser(mLastUserSearch);
	}
	
	public void addOutgo(){
		User vUser = getSelectedUser();
		if( vUser != null && vUser.getIncome() > vUser.getOutgo() ){
			String vSql = "UPDATE users SET outgo=" + (vUser.getOutgo()+1)
					+ " WHERE rowid=" + vUser.getId();
			Database.getInstance().execUpdate( vSql );
		}
		searchUser(mLastUserSearch);
	}
	
	public void printList(){
		List<User> vUsers = getUsers( null );
		
		// create header
		String vHeader = StringUtils.center( "NAME", 20 )
		+ "|" + StringUtils.center( "HAUS", 6 )
		+ "|" + StringUtils.center( "ETAGE", 6 )
		+ "|" + StringUtils.center( "RAUM", 6 )
		+ "|" + StringUtils.center( "BRIEFE", 6 );
		String vHline = "\n" + StringUtils.repeat("-", 48);
		String vText = vHeader + vHline;
		
		// create date string
		SimpleDateFormat vDateFormat = new SimpleDateFormat( "MM.dd.yyyy" );
		String vDateString = vDateFormat.format( new Date() );
		
		for( User vUser : vUsers ){
			// add users with letters
			if( vUser.getIncome() > vUser.getOutgo() ){

				// add user data to text
				int amount = (vUser.getIncome()-vUser.getOutgo());
				vText += "\n" + StringUtils.padRight( vUser.getName(), 20 )
						+ "|" + StringUtils.padLeft( vUser.getHouse(), 6 )
						+ "|" + StringUtils.padLeft( vUser.getFloor(), 6 )
						+ "|" + StringUtils.padLeft( vUser.getRoom(), 6 )
						+ "|" + StringUtils.padLeft( Integer.toString(amount), 6 )
						+ vHline;
			}
		}
		
		// initialize printer and print data
		Printer vPrinter = new Printer( vText,
				"Postliste - " + vDateString,
				"Öffnungszeiten: Mo.-Fr. 09:00-13:00, 16:00-18:00" );
		vPrinter.setFontSize(16);
		vPrinter.print();
		
	}
	
	public void importUserData(){
		
	}
	
	public void exportUserData(){
		
	}
	
	public void editSettings(){
		
	}
	
	private User getSelectedUser(){
		List<QModelIndex> vSelectedUsers = mUi.lstUsr.selectionModel().selectedRows();
		
		if( vSelectedUsers.size() > 0 && mUsers.size() >= vSelectedUsers.size() ){
			return mUsers.get( vSelectedUsers.get(0).row() );
		}
		
		return null;
	}
	
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
				
				if( (aName != null && vUser.getName().toLowerCase().contains(aName.toLowerCase()))
						|| aName == null ){
					vUsers.add( vUser );
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		
		return vUsers;
	}
	
	public static PostOffice getInstance(){
		if( INSTANCE == null )
			INSTANCE = new PostOffice();
		
		return INSTANCE;
	}

	public static void main(String[] args) {
		Logger vLogger = LogManager.getLogger( PostOffice.class );
		vLogger.info( "checking for updates using jGithubLoader" );
		vLogger.info( "application name: " + APP_NAME );
		vLogger.info( "current version: " + TAG_VERSION );
		
		// load updates
//		GithubLoader vLoader = new GithubLoader( "hanneseilers", "Social-Postoffice", true );
//		vLoader.setAppName( APP_NAME );
//		vLoader.update( TAG_VERSION );
		
		// start post office
		logger.debug( "starting application" );
		PostOffice.getInstance().getApplication().exec();
		
		logger.debug( "creating database backup" );
		Database.getInstance().backup();
		Database.getInstance().closeDatabaseConnection();
		logger.debug( "application stopped" );		
	}

}
