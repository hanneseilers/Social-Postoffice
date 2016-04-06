package de.charityapps.postoffice.ui;
/********************************************************************************
 ** Form generated from reading ui file 'printdialog.jui'
 **
 ** Created by: Qt User Interface Compiler version 4.8.6
 **
 ** WARNING! All changes made in this file will be lost when recompiling ui file!
 ********************************************************************************/
import com.trolltech.qt.core.*;
import com.trolltech.qt.gui.*;
import com.trolltech.qt.gui.QAbstractItemView.SelectionMode;

public class Ui_PrintDialog implements com.trolltech.qt.QUiForm<QMainWindow>
{
    public QWidget centralwidget;
    public QWidget verticalLayoutWidget;
    public QVBoxLayout verticalLayout;
    public QLabel lblHousesSelectionText;
    public QRadioButton chkPrintAllHouses;
    public QRadioButton chkPrintEmptyHouses;
    public QRadioButton chkPrintSelectedHouses;
    public QListView lstHouses;
    public QCheckBox chkEveryHouseOnOnePage;
    public QHBoxLayout horizontalLayout;
    public QPushButton btnCancel;
    public QPushButton btnPrint;

    public Ui_PrintDialog() { super(); }

    public void setupUi(QMainWindow PrintDialog)
    {
        PrintDialog.setObjectName("PrintDialog");
        PrintDialog.resize(new QSize(301, 367).expandedTo(PrintDialog.minimumSizeHint()));
        PrintDialog.setMinimumSize(new QSize(0, 0));
        centralwidget = new QWidget(PrintDialog);
        centralwidget.setObjectName("centralwidget");
        verticalLayoutWidget = new QWidget(centralwidget);
        verticalLayoutWidget.setObjectName("verticalLayoutWidget");
        verticalLayoutWidget.setGeometry(new QRect(10, 10, 281, 351));
        verticalLayout = new QVBoxLayout(verticalLayoutWidget);
        verticalLayout.setObjectName("verticalLayout");
        lblHousesSelectionText = new QLabel(verticalLayoutWidget);
        lblHousesSelectionText.setObjectName("lblHousesSelectionText");

        verticalLayout.addWidget(lblHousesSelectionText);

        chkPrintAllHouses = new QRadioButton(verticalLayoutWidget);
        chkPrintAllHouses.setObjectName("chkPrintAllHouses");
        chkPrintAllHouses.setChecked(true);

        verticalLayout.addWidget(chkPrintAllHouses);

        chkPrintEmptyHouses = new QRadioButton(verticalLayoutWidget);
        chkPrintEmptyHouses.setObjectName("chkPrintEmptyHouses");

        verticalLayout.addWidget(chkPrintEmptyHouses);

        chkPrintSelectedHouses = new QRadioButton(verticalLayoutWidget);
        chkPrintSelectedHouses.setObjectName("chkPrintSelectedHouses");

        verticalLayout.addWidget(chkPrintSelectedHouses);

        lstHouses = new QListView(verticalLayoutWidget);
        lstHouses.setObjectName("lstHouses");
        lstHouses.setEnabled(false);
        lstHouses.setSelectionMode(SelectionMode.MultiSelection);

        verticalLayout.addWidget(lstHouses);

        chkEveryHouseOnOnePage = new QCheckBox(verticalLayoutWidget);
        chkEveryHouseOnOnePage.setObjectName("chkEveryHouseOnOnePage");

        verticalLayout.addWidget(chkEveryHouseOnOnePage);

        horizontalLayout = new QHBoxLayout();
        horizontalLayout.setObjectName("horizontalLayout");
        btnCancel = new QPushButton(verticalLayoutWidget);
        btnCancel.setObjectName("btnCancel");

        horizontalLayout.addWidget(btnCancel);

        btnPrint = new QPushButton(verticalLayoutWidget);
        btnPrint.setObjectName("btnPrint");

        horizontalLayout.addWidget(btnPrint);


        verticalLayout.addLayout(horizontalLayout);

        PrintDialog.setCentralWidget(centralwidget);
        retranslateUi(PrintDialog);
        btnCancel.clicked.connect(PrintDialog, "close()");
        btnPrint.clicked.connect(PrintDialog, "close()");

        PrintDialog.connectSlotsByName();
    } // setupUi

    void retranslateUi(QMainWindow PrintDialog)
    {
        PrintDialog.setWindowTitle(com.trolltech.qt.core.QCoreApplication.translate("PrintDialog", "Durckeinstellungen", null));
        lblHousesSelectionText.setText(com.trolltech.qt.core.QCoreApplication.translate("PrintDialog", "H\u00e4user zum drucken ausw\u00e4hlen:", null));
        chkPrintAllHouses.setText(com.trolltech.qt.core.QCoreApplication.translate("PrintDialog", "Alle H\u00e4user drucken", null));
        chkPrintEmptyHouses.setText(com.trolltech.qt.core.QCoreApplication.translate("PrintDialog", "Nur Eintr\u00e4ge ohne Haus drucken", null));
        chkPrintSelectedHouses.setText(com.trolltech.qt.core.QCoreApplication.translate("PrintDialog", "Ausgew\u00e4hlte H\u00e4user drucken:", null));
        chkEveryHouseOnOnePage.setText(com.trolltech.qt.core.QCoreApplication.translate("PrintDialog", "Jedes Haus auf eine neue Seite drucken", null));
        btnCancel.setText(com.trolltech.qt.core.QCoreApplication.translate("PrintDialog", "Abbrechen", null));
        btnPrint.setText(com.trolltech.qt.core.QCoreApplication.translate("PrintDialog", "Drucken", null));
    } // retranslateUi

}

