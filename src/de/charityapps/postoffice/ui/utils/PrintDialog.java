package de.charityapps.postoffice.ui.utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.pdfbox.pdmodel.font.PDType1Font;

import com.trolltech.qt.core.QModelIndex;
import com.trolltech.qt.core.Qt.WindowType;
import com.trolltech.qt.gui.QApplication;
import com.trolltech.qt.gui.QMainWindow;
import com.trolltech.qt.gui.QStringListModel;

import de.charityapps.postoffice.User;
import de.charityapps.postoffice.ui.Ui_PrintDialog;
import de.charityapps.postoffice.utils.StringUtils;
import de.charityapps.postoffice.utils.UserListUtils;
import de.hanneseilers.easyprinter.EasyPrinter;

public class PrintDialog {

	private List<User> mUsersList;
	private Ui_PrintDialog mDialog;
	
	public PrintDialog(List<User> aUsersList) {
		mUsersList = aUsersList;
	}
	
	/**
	 * Prints selected user data
	 */
	public void print(){
		List<User> vUsers = mUsersList;
		
		// get only users of the selected houses
		if( mDialog.chkPrintEmptyHouses.isChecked() ){
			vUsers = UserListUtils.selectByHouse(mUsersList, "");
		} else if( mDialog.chkPrintSelectedHouses.isChecked() ){
			List<QModelIndex> vSelectedHouses = mDialog.lstHouses.selectionModel().selectedRows();
			List<String> vHouses = new ArrayList<String>();
			for( QModelIndex vIndex : vSelectedHouses ){
				vHouses.add( vIndex.data().toString() );
			}
			
			vUsers = UserListUtils.selectByHouses(mUsersList, vHouses);
		}
		
		// create date string
		SimpleDateFormat vDateFormat = new SimpleDateFormat( "dd.MM.yyyy" );
		String vDateString = vDateFormat.format( new Date() );
		
		// set up printer
		EasyPrinter vPrinter = new EasyPrinter( null,
				"Postliste" + " - " + vDateString,
				"Öffnungszeiten: Mo.-Fr. 09:00-13:00, 16:00-18:00\n"
				+ "Bitte bringen Sie Ihren Ausweis mit.\n"
				+ "Die Post wird max. 7 Tage aufbewahrt.\n"
				+ "Schauen Sie jeden Tag nach neuer Post." );
		vPrinter.setFontSize( 16 );
		vPrinter.setFont( PDType1Font.COURIER_BOLD );
		vPrinter.setBorders(10);
		
		// get maximum lines
		final int vMaxLines = vPrinter.getMaxLines();
		int vLinesLeft = vMaxLines;
		
		// create table header and other text components
		String vHeader = StringUtils.center( "NAME", 34 )
		+ "|" + StringUtils.center( "HAUS", 6 )
		+ "|" + StringUtils.center( "RAUM", 6 )
		+ "|" + StringUtils.center( "BRIEFE", 6 );
		String vHline = "\n" + StringUtils.repeat("-", 55);
		String vText = "";		
		String vHouse = "";
		
		// add user data
		for( User vUser : vUsers ){
			
			// add only users with letters
			if( vUser.getIncome() > vUser.getOutgo() ){
				
				// check for page table header and house change
				if( vLinesLeft == vMaxLines || !vHouse.equals(vUser.getHouse()) ){
					
					// check if table header is last entry on page
					if( vLinesLeft <= 4
							|| (!vHouse.equals(vUser.getHouse()) && mDialog.chkEveryHouseOnOnePage.isChecked()) ){
						// not enough space left, padd to page end
						vText += StringUtils.repeat("\n", vLinesLeft);
						vLinesLeft = vMaxLines;
					}
					
					// add table header
					if( vText.length() > 0 )
						vText += "\n";
					vText += vHeader + vHline;
					vLinesLeft -= 2;
				}				
				
				// add user data to text
				int amount = (vUser.getIncome()-vUser.getOutgo());
				vText += "\n" + StringUtils.padRight( vUser.getName(), 34 )
						+ "|" + StringUtils.padLeft( vUser.getHouse(), 6 )
						+ "|" + StringUtils.padLeft( vUser.getRoom(), 6 )
						+ "|" + StringUtils.padLeft( Integer.toString(amount), 6 )
						+ vHline;
				vLinesLeft -= 2;
				
				// set house
				vHouse = vUser.getHouse();
				
				// check for page padding
				if( vLinesLeft < 2 ){
					vText += StringUtils.repeat("\n", vLinesLeft);					
					vLinesLeft = vMaxLines;
				}
				
			}
			
		}
		
		// initialize printer and print data
		vPrinter.setContent(vText);
		vPrinter.print();
	}
	
	public void enableHouseList(final boolean aChecked){
		System.err.println("selected " + aChecked);
		QApplication.invokeLater( new Runnable() {			
			@Override
			public void run() {
				mDialog.lstHouses.setEnabled(aChecked);
			}
		} );
	}
	
	/**
	 * @return {@link List} of available houses.
	 */
	private List<String> getHouses(){
    	List<String> vHouses = new ArrayList<String>();
    	for( User vUser : mUsersList ){
    		String vHouse = vUser.getHouse();
    		if( vHouse.length() > 0 && !vHouses.contains(vHouse) )
    			vHouses.add(vHouse);
    	}
    	
    	return vHouses;
    }
	
	/**
	 * Shows dialog.
	 */
	public void show(){
		QMainWindow vWindow = new QMainWindow();
		mDialog = new Ui_PrintDialog();
		mDialog.setupUi(vWindow);
		vWindow.setWindowFlags( WindowType.WindowStaysOnTopHint );
		vWindow.show();		
		
		mDialog.btnPrint.clicked.connect( this, "print()" );
		mDialog.chkPrintSelectedHouses.toggled.connect( this, "enableHouseList(boolean)" );
		
		List<String> vHouses = getHouses();
		final QStringListModel vModel = new QStringListModel( vHouses );
		
		QApplication.invokeLater( new Runnable() {			
			@Override
			public void run() {
				mDialog.lstHouses.setModel(vModel);
			}
		} );
	}
	
}
