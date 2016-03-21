package de.charityapps.postoffice.ui;
/********************************************************************************
 ** Form generated from reading ui file 'statistics.jui'
 **
 ** Created by: Qt User Interface Compiler version 4.8.6
 **
 ** WARNING! All changes made in this file will be lost when recompiling ui file!
 ********************************************************************************/
import com.trolltech.qt.core.*;
import com.trolltech.qt.core.Qt.AlignmentFlag;
import com.trolltech.qt.gui.*;

public class Ui_StatisticsDialog implements com.trolltech.qt.QUiForm<QMainWindow>
{
    public QWidget centralwidget;
    public QWidget gridLayoutWidget;
    public QGridLayout gridLayout;
    public QLabel label;
    public QLabel lblLettersTotal;
    public QLabel label_4;
    public QLabel label_3;
    public QLabel label_2;
    public QLabel label_5;
    public QLabel lblLettersOutgo;
    public QLabel lblUsersTotal;
    public QLabel lblUsersManual;
    public QLabel lblUserDeleted;
    public QPushButton btnPrint;
    public QPushButton btnClose;

    public Ui_StatisticsDialog() { super(); }

    public void setupUi(QMainWindow StatisticsDialog)
    {
        StatisticsDialog.setObjectName("StatisticsDialog");
        StatisticsDialog.resize(new QSize(331, 172).expandedTo(StatisticsDialog.minimumSizeHint()));
        centralwidget = new QWidget(StatisticsDialog);
        centralwidget.setObjectName("centralwidget");
        gridLayoutWidget = new QWidget(centralwidget);
        gridLayoutWidget.setObjectName("gridLayoutWidget");
        gridLayoutWidget.setGeometry(new QRect(0, 10, 331, 161));
        gridLayout = new QGridLayout(gridLayoutWidget);
        gridLayout.setObjectName("gridLayout");
        label = new QLabel(gridLayoutWidget);
        label.setObjectName("label");
        QSizePolicy sizePolicy = new QSizePolicy(com.trolltech.qt.gui.QSizePolicy.Policy.Preferred, com.trolltech.qt.gui.QSizePolicy.Policy.Fixed);
        sizePolicy.setHorizontalStretch((byte)0);
        sizePolicy.setVerticalStretch((byte)0);
        sizePolicy.setHeightForWidth(label.sizePolicy().hasHeightForWidth());
        label.setSizePolicy(sizePolicy);
        label.setAlignment(AlignmentFlag.AlignRight);

        gridLayout.addWidget(label, 0, 0, 1, 1);

        lblLettersTotal = new QLabel(gridLayoutWidget);
        lblLettersTotal.setObjectName("lblLettersTotal");
        QSizePolicy sizePolicy1 = new QSizePolicy(com.trolltech.qt.gui.QSizePolicy.Policy.Preferred, com.trolltech.qt.gui.QSizePolicy.Policy.Preferred);
        sizePolicy1.setHorizontalStretch((byte)0);
        sizePolicy1.setVerticalStretch((byte)0);
        sizePolicy1.setHeightForWidth(lblLettersTotal.sizePolicy().hasHeightForWidth());
        lblLettersTotal.setSizePolicy(sizePolicy1);

        gridLayout.addWidget(lblLettersTotal, 0, 1, 1, 1);

        label_4 = new QLabel(gridLayoutWidget);
        label_4.setObjectName("label_4");
        QSizePolicy sizePolicy2 = new QSizePolicy(com.trolltech.qt.gui.QSizePolicy.Policy.Preferred, com.trolltech.qt.gui.QSizePolicy.Policy.Fixed);
        sizePolicy2.setHorizontalStretch((byte)0);
        sizePolicy2.setVerticalStretch((byte)0);
        sizePolicy2.setHeightForWidth(label_4.sizePolicy().hasHeightForWidth());
        label_4.setSizePolicy(sizePolicy2);
        label_4.setAlignment(AlignmentFlag.AlignRight);

        gridLayout.addWidget(label_4, 2, 0, 1, 1);

        label_3 = new QLabel(gridLayoutWidget);
        label_3.setObjectName("label_3");
        QSizePolicy sizePolicy3 = new QSizePolicy(com.trolltech.qt.gui.QSizePolicy.Policy.Preferred, com.trolltech.qt.gui.QSizePolicy.Policy.Fixed);
        sizePolicy3.setHorizontalStretch((byte)0);
        sizePolicy3.setVerticalStretch((byte)0);
        sizePolicy3.setHeightForWidth(label_3.sizePolicy().hasHeightForWidth());
        label_3.setSizePolicy(sizePolicy3);
        label_3.setAlignment(AlignmentFlag.AlignRight);

        gridLayout.addWidget(label_3, 1, 0, 1, 1);

        label_2 = new QLabel(gridLayoutWidget);
        label_2.setObjectName("label_2");
        QSizePolicy sizePolicy4 = new QSizePolicy(com.trolltech.qt.gui.QSizePolicy.Policy.Preferred, com.trolltech.qt.gui.QSizePolicy.Policy.Fixed);
        sizePolicy4.setHorizontalStretch((byte)0);
        sizePolicy4.setVerticalStretch((byte)0);
        sizePolicy4.setHeightForWidth(label_2.sizePolicy().hasHeightForWidth());
        label_2.setSizePolicy(sizePolicy4);
        label_2.setAlignment(AlignmentFlag.AlignRight);

        gridLayout.addWidget(label_2, 3, 0, 1, 1);

        label_5 = new QLabel(gridLayoutWidget);
        label_5.setObjectName("label_5");
        QSizePolicy sizePolicy5 = new QSizePolicy(com.trolltech.qt.gui.QSizePolicy.Policy.Preferred, com.trolltech.qt.gui.QSizePolicy.Policy.Fixed);
        sizePolicy5.setHorizontalStretch((byte)0);
        sizePolicy5.setVerticalStretch((byte)0);
        sizePolicy5.setHeightForWidth(label_5.sizePolicy().hasHeightForWidth());
        label_5.setSizePolicy(sizePolicy5);
        label_5.setAlignment(AlignmentFlag.AlignRight);

        gridLayout.addWidget(label_5, 4, 0, 1, 1);

        lblLettersOutgo = new QLabel(gridLayoutWidget);
        lblLettersOutgo.setObjectName("lblLettersOutgo");

        gridLayout.addWidget(lblLettersOutgo, 1, 1, 1, 1);

        lblUsersTotal = new QLabel(gridLayoutWidget);
        lblUsersTotal.setObjectName("lblUsersTotal");

        gridLayout.addWidget(lblUsersTotal, 2, 1, 1, 1);

        lblUsersManual = new QLabel(gridLayoutWidget);
        lblUsersManual.setObjectName("lblUsersManual");

        gridLayout.addWidget(lblUsersManual, 3, 1, 1, 1);

        lblUserDeleted = new QLabel(gridLayoutWidget);
        lblUserDeleted.setObjectName("lblUserDeleted");

        gridLayout.addWidget(lblUserDeleted, 4, 1, 1, 1);

        btnPrint = new QPushButton(gridLayoutWidget);
        btnPrint.setObjectName("btnPrint");
        QSizePolicy sizePolicy6 = new QSizePolicy(com.trolltech.qt.gui.QSizePolicy.Policy.Preferred, com.trolltech.qt.gui.QSizePolicy.Policy.Preferred);
        sizePolicy6.setHorizontalStretch((byte)0);
        sizePolicy6.setVerticalStretch((byte)0);
        sizePolicy6.setHeightForWidth(btnPrint.sizePolicy().hasHeightForWidth());
        btnPrint.setSizePolicy(sizePolicy6);

        gridLayout.addWidget(btnPrint, 5, 0, 1, 1);

        btnClose = new QPushButton(gridLayoutWidget);
        btnClose.setObjectName("btnClose");
        QSizePolicy sizePolicy7 = new QSizePolicy(com.trolltech.qt.gui.QSizePolicy.Policy.Preferred, com.trolltech.qt.gui.QSizePolicy.Policy.Preferred);
        sizePolicy7.setHorizontalStretch((byte)0);
        sizePolicy7.setVerticalStretch((byte)0);
        sizePolicy7.setHeightForWidth(btnClose.sizePolicy().hasHeightForWidth());
        btnClose.setSizePolicy(sizePolicy7);

        gridLayout.addWidget(btnClose, 5, 1, 1, 1);

        StatisticsDialog.setCentralWidget(centralwidget);
        retranslateUi(StatisticsDialog);
        btnClose.clicked.connect(StatisticsDialog, "close()");

        StatisticsDialog.connectSlotsByName();
    } // setupUi

    void retranslateUi(QMainWindow StatisticsDialog)
    {
        StatisticsDialog.setWindowTitle(com.trolltech.qt.core.QCoreApplication.translate("StatisticsDialog", "Statistik", null));
        label.setText(com.trolltech.qt.core.QCoreApplication.translate("StatisticsDialog", "Briefe vorhanden:", null));
        lblLettersTotal.setText(com.trolltech.qt.core.QCoreApplication.translate("StatisticsDialog", "0", null));
        label_4.setText(com.trolltech.qt.core.QCoreApplication.translate("StatisticsDialog", "Kunden gesamt:", null));
        label_3.setText(com.trolltech.qt.core.QCoreApplication.translate("StatisticsDialog", "Briefe ausgegeben:", null));
        label_2.setText(com.trolltech.qt.core.QCoreApplication.translate("StatisticsDialog", "Kunden manuell erstellt:", null));
        label_5.setText(com.trolltech.qt.core.QCoreApplication.translate("StatisticsDialog", "Kunden gel\u00f6scht:", null));
        lblLettersOutgo.setText(com.trolltech.qt.core.QCoreApplication.translate("StatisticsDialog", "0", null));
        lblUsersTotal.setText(com.trolltech.qt.core.QCoreApplication.translate("StatisticsDialog", "0", null));
        lblUsersManual.setText(com.trolltech.qt.core.QCoreApplication.translate("StatisticsDialog", "0", null));
        lblUserDeleted.setText(com.trolltech.qt.core.QCoreApplication.translate("StatisticsDialog", "0", null));
        btnPrint.setText(com.trolltech.qt.core.QCoreApplication.translate("StatisticsDialog", "Statistik drucken", null));
        btnClose.setText(com.trolltech.qt.core.QCoreApplication.translate("StatisticsDialog", "Schlie\u00dfen", null));
    } // retranslateUi

}

