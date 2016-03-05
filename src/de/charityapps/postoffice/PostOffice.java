package de.charityapps.postoffice;

import com.trolltech.qt.gui.QApplication;
import com.trolltech.qt.gui.QMainWindow;
import de.charityapps.postoffice.ui.Ui_MainWindow;
import de.charityapps.postoffice.ui.Ui_UsrDialog;

/**
 * Main class
 * @author eilers
 *
 */
public class PostOffice {
	
	public static final String TAG_VERSION = "";
	public static final String APP_NAME = "Social-Postoffice";
	
	private static PostOffice INSTANCE = null;
	
	private QApplication mQApplication;
	private QMainWindow mQMainWindow;
	private Ui_MainWindow mUi;
	
	/**
	 * Constructor
	 */
	public PostOffice() {
		
		// create gui
		mQApplication = new QApplication( new String[]{} );
		mUi = new Ui_MainWindow();
		mQMainWindow = new QMainWindow();
		mUi.setupUi(mQMainWindow);
		mQMainWindow.show();
		
		// create database
		
		// connect signals
		mUi.actionExport.triggered.connect( this, "exportUserData()" );
		mUi.actionImport.triggered.connect( this, "importUserData()" );
		mUi.actionSettings.triggered.connect( this, "editSettings()" );
		mUi.btnIncome.clicked.connect( this, "addIncome()" );
		mUi.btnOutgo.clicked.connect( this, "addOutgo()" );
		mUi.btnUsrAdd.clicked.connect( this, "addUser()" );
		mUi.btnUsrDelete.clicked.connect( this, "deleteUser()" );
		mUi.btnUsrEdit.clicked.connect( this, "editUser()" );
		
		// start application
		mQApplication.exec();		
	}
	
	public void editUser(){
		
	}
	
	public void deleteUser(){
		
	}
	
	public void addUser(){
		
	}
	
	public void searchUser(){
		
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
	
	private void showUserEditDialog(){
		QMainWindow vWindow = new QMainWindow();
		Ui_UsrDialog vDialog = new Ui_UsrDialog();
		vDialog.setupUi(vWindow);
		vWindow.show();
	}
	
	public static PostOffice getInstance(){
		if( INSTANCE == null )
			INSTANCE = new PostOffice();
		
		return INSTANCE;
	}

	public static void main(String[] args) {
		// load updates
//		GithubLoader vLoader = new GithubLoader( "hanneseilers", "Social-Postoffice", true );
//		vLoader.setAppName( APP_NAME );
//		vLoader.update( TAG_VERSION );
		
		// start post office
		PostOffice.getInstance();
	}

}
