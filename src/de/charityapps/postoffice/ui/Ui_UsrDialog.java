package de.charityapps.postoffice.ui;
/********************************************************************************
 ** Form generated from reading ui file 'userdialog.jui'
 **
 ** Created by: Qt User Interface Compiler version 4.8.6
 **
 ** WARNING! All changes made in this file will be lost when recompiling ui file!
 ********************************************************************************/
import com.trolltech.qt.core.*;
import com.trolltech.qt.core.Qt.AlignmentFlag;
import com.trolltech.qt.gui.*;

public class Ui_UsrDialog implements com.trolltech.qt.QUiForm<QMainWindow>
{
    public QWidget centralwidget;
    public QWidget gridLayoutWidget;
    public QGridLayout gridLayout;
    public QLabel lblHouse;
    public QLabel lblFloor;
    public QLabel lblRoom;
    public QLabel lblName;
    public QLineEdit txtName;
    public QLineEdit txtHouse;
    public QLineEdit txtFloor;
    public QLineEdit txtRoom;
    public QWidget horizontalLayoutWidget;
    public QHBoxLayout horizontalLayout;
    public QPushButton btnCancel;
    public QPushButton btnSave;

    public Ui_UsrDialog() { super(); }

    public void setupUi(QMainWindow UsrDialog)
    {
        UsrDialog.setObjectName("UsrDialog");
        UsrDialog.resize(new QSize(332, 193).expandedTo(UsrDialog.minimumSizeHint()));
        centralwidget = new QWidget(UsrDialog);
        centralwidget.setObjectName("centralwidget");
        gridLayoutWidget = new QWidget(centralwidget);
        gridLayoutWidget.setObjectName("gridLayoutWidget");
        gridLayoutWidget.setGeometry(new QRect(0, 10, 331, 141));
        gridLayout = new QGridLayout(gridLayoutWidget);
        gridLayout.setObjectName("gridLayout");
        lblHouse = new QLabel(gridLayoutWidget);
        lblHouse.setObjectName("lblHouse");
        lblHouse.setAlignment(AlignmentFlag.AlignRight);

        gridLayout.addWidget(lblHouse, 1, 0, 1, 1);

        lblFloor = new QLabel(gridLayoutWidget);
        lblFloor.setObjectName("lblFloor");
        lblFloor.setAlignment(AlignmentFlag.AlignRight);

        gridLayout.addWidget(lblFloor, 2, 0, 1, 1);

        lblRoom = new QLabel(gridLayoutWidget);
        lblRoom.setObjectName("lblRoom");
        lblRoom.setAlignment(AlignmentFlag.AlignRight);

        gridLayout.addWidget(lblRoom, 3, 0, 1, 1);

        lblName = new QLabel(gridLayoutWidget);
        lblName.setObjectName("lblName");
        lblName.setAlignment(AlignmentFlag.AlignRight);

        gridLayout.addWidget(lblName, 0, 0, 1, 1);

        txtName = new QLineEdit(gridLayoutWidget);
        txtName.setObjectName("txtName");

        gridLayout.addWidget(txtName, 0, 1, 1, 1);

        txtHouse = new QLineEdit(gridLayoutWidget);
        txtHouse.setObjectName("txtHouse");

        gridLayout.addWidget(txtHouse, 1, 1, 1, 1);

        txtFloor = new QLineEdit(gridLayoutWidget);
        txtFloor.setObjectName("txtFloor");

        gridLayout.addWidget(txtFloor, 2, 1, 1, 1);

        txtRoom = new QLineEdit(gridLayoutWidget);
        txtRoom.setObjectName("txtRoom");

        gridLayout.addWidget(txtRoom, 3, 1, 1, 1);

        horizontalLayoutWidget = new QWidget(centralwidget);
        horizontalLayoutWidget.setObjectName("horizontalLayoutWidget");
        horizontalLayoutWidget.setGeometry(new QRect(0, 150, 331, 41));
        horizontalLayout = new QHBoxLayout(horizontalLayoutWidget);
        horizontalLayout.setObjectName("horizontalLayout");
        btnCancel = new QPushButton(horizontalLayoutWidget);
        btnCancel.setObjectName("btnCancel");

        horizontalLayout.addWidget(btnCancel);

        btnSave = new QPushButton(horizontalLayoutWidget);
        btnSave.setObjectName("btnSave");
        btnSave.setAutoDefault(true);
        btnSave.setDefault(true);

        horizontalLayout.addWidget(btnSave);

        UsrDialog.setCentralWidget(centralwidget);
        retranslateUi(UsrDialog);

        UsrDialog.connectSlotsByName();
    } // setupUi

    void retranslateUi(QMainWindow UsrDialog)
    {
        UsrDialog.setWindowTitle(com.trolltech.qt.core.QCoreApplication.translate("UsrDialog", "Kunde bearbeiten", null));
        lblHouse.setText(com.trolltech.qt.core.QCoreApplication.translate("UsrDialog", "Haus:", null));
        lblFloor.setText(com.trolltech.qt.core.QCoreApplication.translate("UsrDialog", "Etage:", null));
        lblRoom.setText(com.trolltech.qt.core.QCoreApplication.translate("UsrDialog", "Raum:", null));
        lblName.setText(com.trolltech.qt.core.QCoreApplication.translate("UsrDialog", "Name:", null));
        btnCancel.setText(com.trolltech.qt.core.QCoreApplication.translate("UsrDialog", "Abbrechen", null));
        btnSave.setText(com.trolltech.qt.core.QCoreApplication.translate("UsrDialog", "Speichern", null));
    } // retranslateUi

}

