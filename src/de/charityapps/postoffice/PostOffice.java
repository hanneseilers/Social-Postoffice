package de.charityapps.postoffice;

import com.trolltech.qt.gui.QApplication;
import com.trolltech.qt.gui.QMainWindow;

import de.charityapps.postoffice.ui.Ui_MainWindow;
import de.hanneseilers.jgithubloader.GithubLoader;

/**
 * Main class
 * @author eilers
 *
 */
public class PostOffice {
	
	public static final String TAG_VERSION = "";
	public static final String APP_NAME = "Social-Postoffice";
	
	private QApplication mQApplication;
	private QMainWindow mQMainWindow;
	private Ui_MainWindow mUi;
	
	/**
	 * Constructor
	 */
	public PostOffice() {
		System.out.println("new post office");
		
		// create gui
		mQApplication = new QApplication( new String[]{} );
		mUi = new Ui_MainWindow();
		mQMainWindow = new QMainWindow();
		mUi.setupUi(mQMainWindow);
		mQMainWindow.show();
		
		// create database
		
		// connect signals
		
		
		// start application
		mQApplication.exec();		
	}

	public static void main(String[] args) {
		// load updates
//		GithubLoader vLoader = new GithubLoader( "hanneseilers", "Social-Postoffice", true );
//		vLoader.setAppName( APP_NAME );
//		vLoader.update( TAG_VERSION );
		
		// start post office
		new PostOffice();
	}

}
