package de.charityapps.postoffice.ui.utils;

import com.trolltech.qt.gui.QMainWindow;

import de.charityapps.postoffice.Database;
import de.charityapps.postoffice.PostOffice;
import de.charityapps.postoffice.ui.Ui_UsrDialog;

public class UserDialog {
	
	private boolean mAddUser = false;
	private int mID = -1;
	private Ui_UsrDialog mDialog;
	
	public UserDialog(int aID) {
		mID = aID;
		show();
	}
	
	public UserDialog(boolean addUser){
		this(-1);
		mAddUser = addUser;
	}
	
	public Ui_UsrDialog getUi(){
		return mDialog;
	}
	
	public void save(){
		
		String vName = mDialog.txtName.text();
		String vHouse = mDialog.txtHouse.text();
		String vFloor = mDialog.txtFloor.text();
		String vRoom = mDialog.txtRoom.text();	
		String vBirthdate = mDialog.txtBirthdate.text();
		
		if( vName.trim().length() > 0 ){
			if( mAddUser ){
				
				// add new user
				String vSql = "INSERT INTO users (name, house, floor, room, birthdate, manualAdded) VALUES ("
						+ "'" + vName + "',"
						+ "'" + vHouse + "',"
						+ "'" + vFloor + "',"
						+ "'" + vRoom + "',"
						+ "'" + vBirthdate + "',"
						+ "1);";
				Database.getInstance().execUpdate(vSql);
				
			} else {
				
				// edit user
				String vSql = "UPDATE users SET "
						+ "name='" + vName + "',"
						+ "house='" + vHouse + "',"
						+ "floor='" + vFloor + "',"
						+ "room='" + vRoom + "' "
						+ "birthdate='" + vBirthdate + "',"
						+ "manualAdded=1"
						+ " WHERE rowid=" + mID + ";";
				Database.getInstance().execUpdate(vSql);
				
			}
			
			PostOffice.getInstance().updateSearch();
		}
		
	}

	
	public void show(){
		QMainWindow vWindow = new QMainWindow();
		mDialog = new Ui_UsrDialog();
		mDialog.setupUi(vWindow);
		vWindow.show();
		
		mDialog.btnSave.clicked.connect( this, "save()" );
	}
	
}