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

	private JTextArea component = null;
	private MessageFormat header = null;
	private MessageFormat footer = null;
	private int fontSize = 9;
	private String fontFamily = "Monospaced";
	
	/**
	 * Constructor using a header and a footer text
	 * @param content
	 * @param header
	 * @param footer
	 */
	public Printer(String content, String header, String footer) {
		component = new JTextArea(content);
		
		if( header != null )
			this.header = new MessageFormat(header);
		if( footer != null )
			this.footer = new MessageFormat(footer);
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
	 * @param fontSize	{@link Integer} font size in pt.
	 */
	public void setFontSize(int aFontSize){
		fontSize = aFontSize;
	}
	
	/**
	 * Sets font family like, Monospace, Serif, Sans-Serif, ...
	 * @param aFontFamily	{@link String} of font family.
	 */
	public void setFontFamily(String aFontFamily){
		fontFamily = aFontFamily;
	}
	
	/**
	 * Prints data
	 * @return True if data send to printer
	 */
	public boolean print(){
		try{
			
			// set font
			component.setFont( new Font(fontFamily, Font.PLAIN, fontSize) );
			logger.debug( "Printing " + fontFamily + " " + fontSize + "pt. font" );
			
			// print
			if( header == null && footer == null )
				return component.print();
			else if( header == null && footer != null )
				header = new MessageFormat("");
			else if( footer == null && header != null )
				footer = new MessageFormat("");
			
			return component.print(header, footer);
			
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
			vOut.print( component.getText() );
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
