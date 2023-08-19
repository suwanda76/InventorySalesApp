
//Title:        Your Product Name
//Version:
//Copyright:    Copyright (c) 1999
//Author:       Your Name
//Company:      Your Company
//Description:  Your description

package pembelian;

import java.awt.*;
import java.awt.event.*;
////////SQL DATASET///////////
import com.borland.jbcl.control.*;
import com.borland.dx.sql.dataset.*;

public class dlgSaveMdfFakPem extends Dialog {
  Panel panel1 = new Panel();
  Label label1 = new Label();
  Label label2 = new Label();
  Button btnOk = new Button();
  Button btnCancel = new Button();
  TransparentImage transparentImage1 = new TransparentImage();

  static String  passedInsFakPembK = " ";
  static String  passedInsFakPembT =  " ";
  static String  passedInsFakPembS = " ";
  static double  passedInsFakPembTerblg = 0;
  static int  passedInsFakPembH = 0;
  static String  passedInsFakPembL = " ";
  static String  passedInsFakPembC = " ";

  Frame frmMdfFakPem;
  QueryDataSet fakPemQDset;
  Database fakPemDbase;

  /////////Temporary value to hold data terbilang and to be passed again//////////
  TextField txtTerblg;

  public dlgSaveMdfFakPem(Frame frame, String title, boolean modal) {
    super(frame, title, modal);
    frmMdfFakPem = frame;
    enableEvents(AWTEvent.WINDOW_EVENT_MASK);
    try  {
      jbInit();
      add(panel1);
      pack();
    }
    catch(Exception ex) {
      ex.printStackTrace();
    }
  }

  public dlgSaveMdfFakPem(Frame frame) {
    this(frame, "", false);
  }

  public dlgSaveMdfFakPem(Frame frame, boolean modal) {
    this(frame, "", modal);
  }

  public dlgSaveMdfFakPem(Frame frame, String title) {
    this(frame, title, false);
  }

  public void passMdfFakPembValue(/*QueryDataSet qDSet, Database dbase, */String noFak, String tgl, String suppK,
                                   int hari, String lns, String cat/*, TextField terblg*/){
      //fakPemQDset = qDSet;
      //fakPemDbase = dbase;

      passedInsFakPembK= noFak;
      passedInsFakPembT = tgl;
      passedInsFakPembS = suppK;
      passedInsFakPembH = hari;
      passedInsFakPembL = lns;
      passedInsFakPembC = cat;

           ///////temporary passed , is to be passed again to save pembelian details////
      //txtTerblg = terblg;

      System.out.println(passedInsFakPembK + ", "+ passedInsFakPembT + ", "+
                         passedInsFakPembS + ", "+ passedInsFakPembTerblg + ", " +
                         passedInsFakPembH + ", "+ passedInsFakPembL + ", "+ passedInsFakPembC);

  }

  void jbInit() throws Exception {
    panel1.setBackground(Color.lightGray);
    panel1.setSize(new Dimension(400,127));
    label1.setBackground(Color.lightGray);
    label1.setBounds(new Rectangle(54, 11, 343, 30));
    label1.setFont(new java.awt.Font("Serif", 1, 14));
    label1.setAlignment(1);
    label1.setText("Edit Data Faktur Pembelian...!!! ");
    panel1.setLayout(null);
    label2.setBackground(Color.lightGray);
    label2.setBounds(new Rectangle(53, 38, 328, 23));
    label2.setAlignment(1);
    label2.setText("Click \'Ok\' untuk Process, \'Cancel untuk Batal...");
    btnOk.setBounds(new Rectangle(90, 74, 55, 23));
    btnOk.setLabel("Ok");
    btnOk.addKeyListener(new java.awt.event.KeyAdapter() {

      public void keyReleased(KeyEvent e) {
        btnOk_keyReleased(e);
      }
    });
    btnOk.addActionListener(new java.awt.event.ActionListener() {

      public void actionPerformed(ActionEvent e) {
        btnOk_actionPerformed(e);
      }
    });
    btnCancel.setBounds(new Rectangle(170, 74, 55, 23));
    btnCancel.setName("Cancel");
    btnCancel.setLabel("Cancel");
    btnCancel.addKeyListener(new java.awt.event.KeyAdapter() {

      public void keyPressed(KeyEvent e) {
        btnCancel_keyPressed(e);
      }
    });
    btnCancel.addActionListener(new java.awt.event.ActionListener() {

      public void actionPerformed(ActionEvent e) {
        btnCancel_actionPerformed(e);
      }
    });
    transparentImage1.setDrawEdge(false);
    transparentImage1.setImageName("C:\\My Documents\\excla.JPG");
    transparentImage1.setBounds(new Rectangle(10, 12, 36, 33));
    panel1.add(btnCancel, null);
    panel1.add(btnOk, null);
    panel1.add(transparentImage1, null);
    panel1.add(label1, null);
    panel1.add(label2, null);
  }

  protected void processWindowEvent(WindowEvent e) {
    if(e.getID() == WindowEvent.WINDOW_CLOSING) {
      cancel();
    }
    super.processWindowEvent(e);
  }

  void cancel() {
    this.dispose();
  }
              //passedInsFakPembK.toLowerCase().trim() + "','"+
 public void MdfTblInvPemValue(){
    selectData save = new selectData();
    String sqlText = "update tblInvPem set "+
              "tanggal = '" + passedInsFakPembT.toLowerCase().trim() + "', "+
              "kode_supplier = '" + passedInsFakPembS.toLowerCase().trim() + "', "+
              "hari = " + passedInsFakPembH + ", "  +
              "lunas = '" + passedInsFakPembL.toLowerCase().trim() + "', " +
              "catatan = '" + passedInsFakPembC.trim() + "'\n" +
              "where no_faktur = '"+ passedInsFakPembK.toLowerCase().trim() + "'";
      int ok = save.insertData(sqlText);
      if(ok==1){
          this.dispose();
        dispInsertSuccess("Data Updated..");
      }else if (ok==2){
          this.dispose();
          //succMessage("SQL PENYIMPANAN ERROR No.1 ...!!!");
          System.out.println("Data penyimpanan error no.1");
      }else if (ok==3){
          this.dispose();
          //succMessage("SQL PENYIMPANAN ERROR No.2 ...!!!");
          System.out.println("Data penyimpanan error no.2");
       }
    }

  void dispInsertSuccess(String msg){
    dlgSuccess succ= new dlgSuccess( frmMdfFakPem, "",true);
    succ.setLabel(msg);
    centerDialog cDlg = new centerDialog();
    cDlg.centerDialog(succ, 290, 145);
  }

  void btnOk_actionPerformed(ActionEvent e) {
     MdfTblInvPemValue();
     this.dispose();
  }

  void dispPemDet(){
    //dlgFakPemFlag mdfFakPembDet = new dlgFakPemFlag(frmMdfFakPem, " ", true);
   // mdfFakPembDet.passedPemValue("Data telah ter-Update...!!", passedInsFakPembK, txtTerblg, fakPemQDset, fakPemDbase);
    //mdfFakPembDet.setVisible(true);
    //centerDialog cDlg = new centerDialog();
    //cDlg.centerDialog(mdfFakPembDet, 400, 242);
  }

  void btnOk_keyReleased(KeyEvent e) {
     if(e.getKeyCode()==KeyEvent.VK_ENTER){
          this.dispose();
          MdfTblInvPemValue();
     }else if(e.getKeyCode()==KeyEvent.VK_RIGHT){
          btnCancel.requestFocus();
     }
  }

  void btnCancel_actionPerformed(ActionEvent e) {
        this.dispose();
  }

  void btnCancel_keyPressed(KeyEvent e) {
      if(e.getKeyCode()==KeyEvent.VK_ENTER){
          this.dispose();
     }else if(e.getKeyCode()==KeyEvent.VK_LEFT){
          btnOk.requestFocus();
     }
  }

}
