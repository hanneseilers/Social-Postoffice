package de.charityapps.postoffice.utils;


import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.print.PrinterException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.MessageFormat;

import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.ServiceUI;
import javax.print.SimpleDoc;
import javax.print.attribute.DocAttributeSet;
import javax.print.attribute.HashDocAttributeSet;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.swing.JTextArea;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * Class for printing plain text
 * @author Hannes Eilers
 *
 */
public class Printer {
	
	private static Logger logger = LogManager.getLogger( Printer.class );

	private String mContent = null;
	private String mHeader = null;
	private String mFooter = null;
	private int mFontSize = 9;
	private int mFontStyle = Font.PLAIN;
	private String mFontFamily = "Monospaced";
	
	/**
	 * Constructor using a header and a footer text
	 * @param content
	 * @param header
	 * @param footer
	 */
	public Printer(String content, String header, String footer) {
		mContent = content;
		
		if( header != null )
			mHeader = header;
		if( footer != null )
			mFooter = footer;
	}
	
	/**
	 * Constructor using header text (no footer)
	 * @param content
	 * @param header
	 */
	public Printer(String content, String header){
		this(content, header, null);
	}
	
	/**
	 * Constructor using content only (no heade ror footer)
	 * @param content
	 */
	public Printer(String content){
		this(content, null, null);
	}
	
	/**
	 * Sets font size in pt, Default is 9pt.
	 * @param mFontSize	{@link Integer} font size in pt.
	 */
	public void setFontSize(int aFontSize){
		mFontSize = aFontSize;
	}
	
	/**
	 * Sets font family like, Monospace, Serif, Sans-Serif, ...
	 * @param aFontFamily	{@link String} of font family.
	 */
	public void setFontFamily(String aFontFamily){
		mFontFamily = aFontFamily;
	}
	
	/**
	 * Sets font style.
	 * @param aFontStyle	{@link Font} style.
	 */
	public void setFontStyle(int aFontStyle){
		mFontStyle = aFontStyle;
	}
	
	/**
	 * Prints data
	 * @return True if data send to printer
	 */
	public boolean print(){
		try{

			// set components
			JTextArea component = new JTextArea(mContent);
			MessageFormat vHeader = new MessageFormat("");
			MessageFormat vFooter = new MessageFormat("");
			
			// set font
			component.setFont( new Font(mFontFamily, mFontStyle, mFontSize) );
			logger.info( "Printing " + mFontFamily + " " + mFontSize + "pt. font" );
			
			// print
			if( mHeader == null && mFooter == null )
				return component.print();
			
			if( mHeader != null)
				vHeader = new MessageFormat(mHeader);
			if( mFooter != null)
				vFooter = new MessageFormat(mFooter);
			
			return component.print(vHeader, vFooter);
			
		} catch( PrinterException e ){
			Logger.getLogger(getClass()).error("Could not print data.");
		}
		
		return false;
	}
	
	public boolean print2(){
		// TODO: Stable html webpage printing
		try{
			// create temp html file
			PrintWriter vOut = new PrintWriter( "tmp.htm" );
			vOut.print( mContent );
			vOut.close();
			
			// print html		
			File doc = new File( "tmp.htm" );
			PrintRequestAttributeSet pras = new HashPrintRequestAttributeSet();
			DocFlavor flavor = DocFlavor.INPUT_STREAM.TEXT_HTML_UTF_8;
			PrintService printService[] = PrintServiceLookup.lookupPrintServices(flavor, pras);
			PrintService defaultService = PrintServiceLookup.lookupDefaultPrintService();
			PrintService service = ServiceUI.printDialog(GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration(), 200, 200,
			                      printService, defaultService, flavor, pras);
			if (service != null) {
			    DocPrintJob job = service.createPrintJob();
			    FileInputStream fis = new FileInputStream(doc);
			    DocAttributeSet das = new HashDocAttributeSet();
			    Doc document = new SimpleDoc(fis, flavor, das);
			    job.print(document, pras);
			    
			    return true;
			}
		} catch(PrintException e){
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		return false;		
	}
	
}
