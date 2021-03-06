package de.charityapps.postoffice.ui.utils;

import com.trolltech.qt.core.Qt.WindowType;
import com.trolltech.qt.gui.QMainWindow;

import de.charityapps.postoffice.PostOffice;
import de.charityapps.postoffice.User;
import de.charityapps.postoffice.ui.Ui_UsrDialog;

public class UserDialog {
	
	private String mUserName = null;
	private User mUser = null;
	private Ui_UsrDialog mDialog;
	
	public UserDialog(User aUser) {
		mUser = aUser;
	}
	
	public UserDialog(String aUserName){
		mUserName = aUserName;
	}
	
	public UserDialog(){}
	
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
			if( mUser == null ){
				
				// add new user
				User vUser = new User();
				vUser.setName(vName)
					.setHouse(vHouse)
					.setFloor(vFloor)
					.setRoom(vRoom)
					.setBirthdate(vBirthdate)
					.setManualAdded(true)
					.add();
				
			} else {
				
				// edit user
				mUser.setName(vName)
					.setHouse(vHouse)
					.setFloor(vFloor)
					.setRoom(vRoom)
					.setBirthdate(vBirthdate)
					.update();
				
			}
			
			PostOffice.getInstance().searchUser(vName);
		}
		
	}

	
	public void show(){
		QMainWindow vWindow = new QMainWindow();
		mDialog = new Ui_UsrDialog();
		mDialog.setupUi(vWindow);
		vWindow.setWindowFlags( WindowType.WindowStaysOnTopHint );
		vWindow.show();
		
		if( mUser != null ){
			mDialog.txtName.setText( mUser.getName() );
			mDialog.txtHouse.setText( mUser.getHouse() );
			mDialog.txtFloor.setText( mUser.getFloor() );
			mDialog.txtRoom.setText( mUser.getRoom() );
			mDialog.txtBirthdate.setText( mUser.getBirthdate() );
		} else if( mUserName != null ){
			mDialog.txtName.setText( mUserName );
		}
		
		mDialog.btnSave.clicked.connect( this, "save()" );
	}
	
}
