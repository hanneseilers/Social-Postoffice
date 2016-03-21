package de.charityapps.postoffice.ui.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.trolltech.qt.gui.QMainWindow;

import de.charityapps.postoffice.Statistics;
import de.charityapps.postoffice.ui.Ui_StatisticsDialog;
import de.charityapps.postoffice.utils.Printer;

public class StatisticsDialog {

	private long mLettersTotal;
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
		mLettersTotal = Statistics.getLettersTotal();
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
					+ "Briefe vorhanden: " + Long.toString(mLettersTotal-mLettersOutgo) + "\n"
					+ "Briefe ausgegeben: " + Long.toString(mLettersOutgo) + "\n"
					+ "Kunden gesamt: " +  Long.toString(mUsersTotal) + "\n"
					+ "Kunden manuell hinzugefügt: " + Long.toString(mUsersManual) + "\n"
					+ "Kunden gelöscht: " +  Long.toString(mUsersDeleted);
		
		// print
		Printer vPrinter = new Printer(vContent, vHeader, vFooter);
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
		mDialog.lblLettersTotal.setText( Long.toString(mLettersTotal-mLettersOutgo) );
		mDialog.lblLettersOutgo.setText( Long.toString(mLettersOutgo) );
		mDialog.lblUsersTotal.setText( Long.toString(mUsersTotal) );
		mDialog.lblUsersManual.setText( Long.toString(mUsersManual) );
		mDialog.lblUserDeleted.setText( Long.toString(mUsersDeleted) );
	}
	
}
