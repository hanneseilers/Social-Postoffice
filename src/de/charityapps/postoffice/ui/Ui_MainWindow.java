package de.charityapps.postoffice.ui;
/********************************************************************************
 ** Form generated from reading ui file 'postoffice.jui'
 **
 ** Created by: Qt User Interface Compiler version 4.8.6
 **
 ** WARNING! All changes made in this file will be lost when recompiling ui file!
 ********************************************************************************/
import com.trolltech.qt.core.*;
import com.trolltech.qt.gui.*;

public class Ui_MainWindow implements com.trolltech.qt.QUiForm<QMainWindow>
{
    public QAction actionClose;
    public QAction actionImport;
    public QAction actionExport;
    public QAction actionSettings;
    public QWidget centralwidget;
    public QWidget gridLayoutWidget;
    public QGridLayout gridLayout;
    public QHBoxLayout horizontalLayout;
    public QLabel lblUsrSearch;
    public QLineEdit txtSearch;
    public QListView lstUsr;
    public QVBoxLayout verticalLayout;
    public QPushButton btnUsrEdit;
    public QPushButton btnUsrDelete;
    public QPushButton btnUsrAdd;
    public QHBoxLayout horizontalLayout_2;
    public QPushButton btnIncome;
    public QPushButton btnOutgo;
    public QLabel lblUsrListInfo;
    public QMenuBar menubar;
    public QMenu menuDatei;
    public QStatusBar statusbar;

    public Ui_MainWindow() { super(); }

    public void setupUi(QMainWindow MainWindow)
    {
        MainWindow.setObjectName("MainWindow");
        MainWindow.resize(new QSize(800, 599).expandedTo(MainWindow.minimumSizeHint()));
        QSizePolicy sizePolicy = new QSizePolicy(com.trolltech.qt.gui.QSizePolicy.Policy.Expanding, com.trolltech.qt.gui.QSizePolicy.Policy.Expanding);
        sizePolicy.setHorizontalStretch((byte)0);
        sizePolicy.setVerticalStretch((byte)0);
        sizePolicy.setHeightForWidth(MainWindow.sizePolicy().hasHeightForWidth());
        MainWindow.setSizePolicy(sizePolicy);
        actionClose = new QAction(MainWindow);
        actionClose.setObjectName("actionClose");
        actionImport = new QAction(MainWindow);
        actionImport.setObjectName("actionImport");
        actionExport = new QAction(MainWindow);
        actionExport.setObjectName("actionExport");
        actionExport.setEnabled(false);
        actionSettings = new QAction(MainWindow);
        actionSettings.setObjectName("actionSettings");
        actionSettings.setEnabled(false);
        centralwidget = new QWidget(MainWindow);
        centralwidget.setObjectName("centralwidget");
        centralwidget.setEnabled(true);
        QSizePolicy sizePolicy1 = new QSizePolicy(com.trolltech.qt.gui.QSizePolicy.Policy.Expanding, com.trolltech.qt.gui.QSizePolicy.Policy.Expanding);
        sizePolicy1.setHorizontalStretch((byte)0);
        sizePolicy1.setVerticalStretch((byte)0);
        sizePolicy1.setHeightForWidth(centralwidget.sizePolicy().hasHeightForWidth());
        centralwidget.setSizePolicy(sizePolicy1);
        gridLayoutWidget = new QWidget(centralwidget);
        gridLayoutWidget.setObjectName("gridLayoutWidget");
        gridLayoutWidget.setGeometry(new QRect(10, 10, 781, 531));
        gridLayout = new QGridLayout(gridLayoutWidget);
        gridLayout.setObjectName("gridLayout");
        horizontalLayout = new QHBoxLayout();
        horizontalLayout.setObjectName("horizontalLayout");
        lblUsrSearch = new QLabel(gridLayoutWidget);
        lblUsrSearch.setObjectName("lblUsrSearch");

        horizontalLayout.addWidget(lblUsrSearch);

        txtSearch = new QLineEdit(gridLayoutWidget);
        txtSearch.setObjectName("txtSearch");
        QFont font = new QFont();
        font.setPointSize(14);
        txtSearch.setFont(font);

        horizontalLayout.addWidget(txtSearch);


        gridLayout.addLayout(horizontalLayout, 2, 0, 1, 2);

        lstUsr = new QListView(gridLayoutWidget);
        lstUsr.setObjectName("lstUsr");
        QSizePolicy sizePolicy2 = new QSizePolicy(com.trolltech.qt.gui.QSizePolicy.Policy.Expanding, com.trolltech.qt.gui.QSizePolicy.Policy.Expanding);
        sizePolicy2.setHorizontalStretch((byte)0);
        sizePolicy2.setVerticalStretch((byte)0);
        sizePolicy2.setHeightForWidth(lstUsr.sizePolicy().hasHeightForWidth());
        lstUsr.setSizePolicy(sizePolicy2);
        QFont font1 = new QFont();
        font1.setFamily("Droid Sans Mono");
        font1.setPointSize(14);
        lstUsr.setFont(font1);

        gridLayout.addWidget(lstUsr, 1, 0, 1, 1);

        verticalLayout = new QVBoxLayout();
        verticalLayout.setObjectName("verticalLayout");
        btnUsrEdit = new QPushButton(gridLayoutWidget);
        btnUsrEdit.setObjectName("btnUsrEdit");
        QSizePolicy sizePolicy3 = new QSizePolicy(com.trolltech.qt.gui.QSizePolicy.Policy.Minimum, com.trolltech.qt.gui.QSizePolicy.Policy.Expanding);
        sizePolicy3.setHorizontalStretch((byte)0);
        sizePolicy3.setVerticalStretch((byte)0);
        sizePolicy3.setHeightForWidth(btnUsrEdit.sizePolicy().hasHeightForWidth());
        btnUsrEdit.setSizePolicy(sizePolicy3);

        verticalLayout.addWidget(btnUsrEdit);

        btnUsrDelete = new QPushButton(gridLayoutWidget);
        btnUsrDelete.setObjectName("btnUsrDelete");
        QSizePolicy sizePolicy4 = new QSizePolicy(com.trolltech.qt.gui.QSizePolicy.Policy.Minimum, com.trolltech.qt.gui.QSizePolicy.Policy.Expanding);
        sizePolicy4.setHorizontalStretch((byte)0);
        sizePolicy4.setVerticalStretch((byte)0);
        sizePolicy4.setHeightForWidth(btnUsrDelete.sizePolicy().hasHeightForWidth());
        btnUsrDelete.setSizePolicy(sizePolicy4);

        verticalLayout.addWidget(btnUsrDelete);

        btnUsrAdd = new QPushButton(gridLayoutWidget);
        btnUsrAdd.setObjectName("btnUsrAdd");
        QSizePolicy sizePolicy5 = new QSizePolicy(com.trolltech.qt.gui.QSizePolicy.Policy.Minimum, com.trolltech.qt.gui.QSizePolicy.Policy.Expanding);
        sizePolicy5.setHorizontalStretch((byte)0);
        sizePolicy5.setVerticalStretch((byte)0);
        sizePolicy5.setHeightForWidth(btnUsrAdd.sizePolicy().hasHeightForWidth());
        btnUsrAdd.setSizePolicy(sizePolicy5);

        verticalLayout.addWidget(btnUsrAdd);


        gridLayout.addLayout(verticalLayout, 1, 1, 1, 1);

        horizontalLayout_2 = new QHBoxLayout();
        horizontalLayout_2.setObjectName("horizontalLayout_2");
        btnIncome = new QPushButton(gridLayoutWidget);
        btnIncome.setObjectName("btnIncome");
        QSizePolicy sizePolicy6 = new QSizePolicy(com.trolltech.qt.gui.QSizePolicy.Policy.Minimum, com.trolltech.qt.gui.QSizePolicy.Policy.Minimum);
        sizePolicy6.setHorizontalStretch((byte)0);
        sizePolicy6.setVerticalStretch((byte)0);
        sizePolicy6.setHeightForWidth(btnIncome.sizePolicy().hasHeightForWidth());
        btnIncome.setSizePolicy(sizePolicy6);
        btnIncome.setMinimumSize(new QSize(0, 150));
        QFont font2 = new QFont();
        font2.setPointSize(18);
        font2.setBold(true);
        font2.setWeight(75);
        btnIncome.setFont(font2);
        btnIncome.setCursor(new QCursor(Qt.CursorShape.ArrowCursor));

        horizontalLayout_2.addWidget(btnIncome);

        btnOutgo = new QPushButton(gridLayoutWidget);
        btnOutgo.setObjectName("btnOutgo");
        QSizePolicy sizePolicy7 = new QSizePolicy(com.trolltech.qt.gui.QSizePolicy.Policy.Minimum, com.trolltech.qt.gui.QSizePolicy.Policy.Minimum);
        sizePolicy7.setHorizontalStretch((byte)0);
        sizePolicy7.setVerticalStretch((byte)0);
        sizePolicy7.setHeightForWidth(btnOutgo.sizePolicy().hasHeightForWidth());
        btnOutgo.setSizePolicy(sizePolicy7);
        btnOutgo.setMinimumSize(new QSize(0, 150));
        QFont font3 = new QFont();
        font3.setPointSize(18);
        font3.setBold(true);
        font3.setWeight(75);
        btnOutgo.setFont(font3);

        horizontalLayout_2.addWidget(btnOutgo);


        gridLayout.addLayout(horizontalLayout_2, 3, 0, 1, 2);

        lblUsrListInfo = new QLabel(gridLayoutWidget);
        lblUsrListInfo.setObjectName("lblUsrListInfo");
        QSizePolicy sizePolicy8 = new QSizePolicy(com.trolltech.qt.gui.QSizePolicy.Policy.Expanding, com.trolltech.qt.gui.QSizePolicy.Policy.Fixed);
        sizePolicy8.setHorizontalStretch((byte)0);
        sizePolicy8.setVerticalStretch((byte)0);
        sizePolicy8.setHeightForWidth(lblUsrListInfo.sizePolicy().hasHeightForWidth());
        lblUsrListInfo.setSizePolicy(sizePolicy8);
        QFont font4 = new QFont();
        font4.setBold(true);
        font4.setWeight(75);
        lblUsrListInfo.setFont(font4);

        gridLayout.addWidget(lblUsrListInfo, 0, 0, 1, 2);

        MainWindow.setCentralWidget(centralwidget);
        menubar = new QMenuBar(MainWindow);
        menubar.setObjectName("menubar");
        menubar.setGeometry(new QRect(0, 0, 800, 27));
        menuDatei = new QMenu(menubar);
        menuDatei.setObjectName("menuDatei");
        MainWindow.setMenuBar(menubar);
        statusbar = new QStatusBar(MainWindow);
        statusbar.setObjectName("statusbar");
        MainWindow.setStatusBar(statusbar);

        menubar.addAction(menuDatei.menuAction());
        menuDatei.addAction(actionImport);
        menuDatei.addAction(actionExport);
        menuDatei.addAction(actionSettings);
        menuDatei.addSeparator();
        menuDatei.addAction(actionClose);
        retranslateUi(MainWindow);
        actionClose.triggered.connect(MainWindow, "close()");

        MainWindow.connectSlotsByName();
    } // setupUi

    void retranslateUi(QMainWindow MainWindow)
    {
        MainWindow.setWindowTitle(com.trolltech.qt.core.QCoreApplication.translate("MainWindow", "Social Postoffice", null));
        actionClose.setText(com.trolltech.qt.core.QCoreApplication.translate("MainWindow", "Beenden", null));
        actionImport.setText(com.trolltech.qt.core.QCoreApplication.translate("MainWindow", "Kundendaten importieren", null));
        actionExport.setText(com.trolltech.qt.core.QCoreApplication.translate("MainWindow", "Kundendaten exportieren", null));
        actionSettings.setText(com.trolltech.qt.core.QCoreApplication.translate("MainWindow", "Einstellungen", null));
        lblUsrSearch.setText(com.trolltech.qt.core.QCoreApplication.translate("MainWindow", "Suche:", null));
        btnUsrEdit.setText(com.trolltech.qt.core.QCoreApplication.translate("MainWindow", "Kunde bearbeiten", null));
        btnUsrDelete.setText(com.trolltech.qt.core.QCoreApplication.translate("MainWindow", "Kunde l\u00f6schen", null));
        btnUsrAdd.setText(com.trolltech.qt.core.QCoreApplication.translate("MainWindow", "Kunde hinzuf\u00fcgen", null));
        btnIncome.setText(com.trolltech.qt.core.QCoreApplication.translate("MainWindow", "Eingang", null));
        btnOutgo.setText(com.trolltech.qt.core.QCoreApplication.translate("MainWindow", "Ausgang", null));
        lblUsrListInfo.setText(com.trolltech.qt.core.QCoreApplication.translate("MainWindow", "Ausgew\u00e4hlte Kunden:", null));
        menuDatei.setTitle(com.trolltech.qt.core.QCoreApplication.translate("MainWindow", "Datei", null));
    } // retranslateUi

}

