package de.charityapps.postoffice;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.trolltech.qt.gui.QApplication;
import com.trolltech.qt.gui.QMainWindow;
import com.trolltech.qt.gui.QStringListModel;

import de.charityapps.postoffice.ui.Ui_MainWindow;
import de.charityapps.postoffice.ui.UserDialog;

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
		
	}
	
	public void addUser(){
		new UserDialog(true);
	}
	
	public void searchUser(String aName){
		mUsers = getUsers( aName );
		
		// convert to string list
		List<String> vUsersList = new ArrayList<String>();
		for( User vUser : mUsers ){
			vUsersList.add( vUser.toString() );
		}		
		QStringListModel vModel = new QStringListModel( vUsersList );		
		vModel.setStringList(vUsersList);
		
		mUi.lstUsr.setModel( vModel );		
	}
	
	public void addIncome(){
		
	}
	
	public void addOutgo(){
		
	}
	
	public void importUserData(){
		
	}
	
	public void exportUserData(){
		
	}
	
	public void editSettings(){
		
	}
	
	public List<User> getUsers(String aName){
		List<User> vUsers = new ArrayList<User>();	
		
		try {
			
			String vSql = "SELECT * FROM users;";
			ResultSet vResult = Database.getInstance().execQuery( vSql );
			
			while( vResult.next() ){
				User vUser = new User( vResult.getRow() );
				vUser.setName( vResult.getString("name") );
				vUser.setHouse( vResult.getString("house") );
				vUser.setFloor( vResult.getString("floor") );
				vUser.setRoom( vResult.getString("room") );
				vUser.setIncome( vResult.getInt("income") );
				vUser.setOutgo( vResult.getInt("outgo") );
				vUser.setManualAdded( vResult.getBoolean("manualAdded") );
				
				if( (aName != null && vUser.getName().contains(aName)) || aName == null ){
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
	}

}
