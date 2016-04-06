package de.charityapps.postoffice.ui.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.trolltech.qt.gui.QMainWindow;

import de.charityapps.postoffice.Statistics;
import de.charityapps.postoffice.ui.Ui_StatisticsDialog;
import de.hanneseilers.easyprinter.EasyPrinter;

public class StatisticsDialog {

	private long mLettersIncome;
	private long mLettersOutgo;
	private long mUsersTotal;
	private long mUsersManual;
	private long mUsersDeleted;
	private Ui_StatisticsDialog mDialog;
	
	public StatisticsDialog(){
		fetchStatistics();
	}
	
	public Ui_StatisticsDialog getUi(){
		return mDialog;
	}
	
	private void fetchStatistics(){
		mLettersIncome = Statistics.getLettersIncome();
		mLettersOutgo = Statistics.getLettersOutgo();
		mUsersTotal = Statistics.getUsersTotal();
		mUsersManual = Statistics.getUsersManual();
		mUsersDeleted = Statistics.getUsersDeleted();
	}
	
	public void print(){
		// create date string and header
		SimpleDateFormat vDateFormat = new SimpleDateFormat( "dd.MM.yyyy" );
		String vDateString = vDateFormat.format( new Date() );
		String vHeader = "Social-Postoffice Statistik";
		String vFooter = "Social-Postoffice by charity-apps.net";
		String vContent = "\nDatum: " + vDateString + "\n\n"
					+ "Briefe vorhanden: " + Long.toString(mLettersIncome-mLettersOutgo) + "\n"
					+ "Briefe ausgegeben: " + Long.toString(mLettersOutgo) + "\n"
					+ "Kunden gesamt: " +  Long.toString(mUsersTotal) + "\n"
					+ "Kunden manuell hinzugefügt: " + Long.toString(mUsersManual) + "\n"
					+ "Kunden gelöscht: " +  Long.toString(mUsersDeleted);
		
		// print
		EasyPrinter vPrinter = new EasyPrinter(vContent, vHeader, vFooter);
		vPrinter.setFontSize( 14 );
		vPrinter.print();
	}

	
	public void show(){
		QMainWindow vWindow = new QMainWindow();
		mDialog = new Ui_StatisticsDialog();
		mDialog.setupUi(vWindow);
		vWindow.show();		
		
		mDialog.btnPrint.clicked.connect( this, "print()" );
		
		// set statistic data
		mDialog.lblLettersTotal.setText( Long.toString(mLettersIncome-mLettersOutgo) );
		mDialog.lblLettersOutgo.setText( Long.toString(mLettersOutgo) );
		mDialog.lblUsersTotal.setText( Long.toString(mUsersTotal) );
		mDialog.lblUsersManual.setText( Long.toString(mUsersManual) );
		mDialog.lblUserDeleted.setText( Long.toString(mUsersDeleted) );
	}
	
}
