
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
//import com.borland.dx.sql.dataset.*;

public class dlgSaveFakPenj extends Dialog {
  Panel panel1 = new Panel();
  Label label1 = new Label();
  Label label2 = new Label();
  Button btnOk = new Button();
  Button btnCancel = new Button();
  TransparentImage transparentImage1 = new TransparentImage();

  static String  passedInsFakPenjK = "";
  static String  passedInsFakPenjT =  "";
  static String  passedInsFakPenjS = "";
  static double  passedInsFakPenjTerblg = 0;
  static int  passedInsFakPenjH = 0;
  static String  passedInsFakPenjL = "";
  static String  passedInsFakPenjC = "";

  Frame frmFakPenj;
  static String user="";
  static String passwd="";

  /////////Temporary value to hold data terbilang and to be passed again//////////
  TextField txtTerblg;

  public dlgSaveFakPenj(Frame frame, String title, boolean modal) {
    super(frame, title, modal);
    frmFakPenj = frame;
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

  public dlgSaveFakPenj(Frame frame) {
    this(frame, "", false);
  }

  public dlgSaveFakPenj(Frame frame, boolean modal) {
    this(frame, "", modal);
  }

  public dlgSaveFakPenj(Frame frame, String title) {
    this(frame, title, false);
  }

  public void passInsFakPenjValue(String usr, String pass, String noFak, String tgl, String suppK,
                                   int hari, String lns, String cat/*, TextField terblg*/){

      user = usr;
      passwd = pass;
      passedInsFakPenjK= noFak;
      passedInsFakPenjT = tgl;
      passedInsFakPenjS = suppK;
      passedInsFakPenjH = hari;
      passedInsFakPenjL = lns;
      passedInsFakPenjC = cat;

           ///////temporary passed , is to be passed again to save pembelian details////
//      txtTerblg = terblg;

      System.out.println(passedInsFakPenjK + ", "+ passedInsFakPenjT + ", "+
                         passedInsFakPenjS + ", "+
                         passedInsFakPenjH + ", "+ passedInsFakPenjL + ", "+ passedInsFakPenjC);

  }

  void jbInit() throws Exception {
    panel1.setBackground(Color.lightGray);
    panel1.setSize(new Dimension(400,127));
    label1.setBackground(Color.lightGray);
    label1.setBounds(new Rectangle(76, 11, 287, 30));
    label1.setFont(new java.awt.Font("Serif", 1, 14));
    label1.setAlignment(1);
    label1.setText("Simpan Data Faktur Penjualan...!!! ");
    panel1.setLayout(null);
    label2.setBackground(Color.lightGray);
    label2.setBounds(new Rectangle(51, 42, 316, 23));
    label2.setAlignment(1);
    label2.setText("Click \'Ok\' untuk Process, \'Cancel untuk Batal...");
    btnOk.setBounds(new Rectangle(130, 74, 55, 23));
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
    btnCancel.setBounds(new Rectangle(210, 74, 55, 23));
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
    panel1.add(btnOk, null);
    panel1.add(transparentImage1, null);
    panel1.add(btnCancel, null);
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

 public void InsTblInvPenjValue(){
    selectData save = new selectData();
    String sqlText = "insert into tblInvPenj values ( '"+
              passedInsFakPenjK.toLowerCase().trim() + "','"+
              passedInsFakPenjT.toLowerCase().trim() + "','"+
              passedInsFakPenjS.toLowerCase().trim() + "', "+
              passedInsFakPenjH + ",'"  +
              passedInsFakPenjL.toLowerCase().trim() + "', " +
              0 + ", '" + //terbilang is 0
              ""+ "', "+//tgl_pbyr
              0  + "," +  //total pembayaran is 0
              0  + ",'"+/////CN
              ""+ "', '"+ //tg
              ""+ "', '"+ //tg Keterangan
              passedInsFakPenjC.trim() + "')";
      int ok = save.insertData(sqlText);
      if(ok==1){
          this.dispose();
           dispPenjDet();
       //   succMessage("DATA TELAH TERSIMPAN..!!!");
          System.out.println("Data telah tersimpan");
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

  void btnOk_actionPerformed(ActionEvent e) {
     InsTblInvPenjValue();
     this.dispose();
  }

  void dispPenjDet(){
    dlgInsFakPenjDet insFakPenjDet = new dlgInsFakPenjDet(frmFakPenj, " ", true);
    insFakPenjDet.passedFakPenj(passedInsFakPenjK, user, passwd);
    centerDialog cDlg = new centerDialog();
    cDlg.centerDialog(insFakPenjDet, 790, 345);

    //insFakPenjDet.setVisible(true);

  }

  void btnOk_keyReleased(KeyEvent e) {
     if(e.getKeyCode()==KeyEvent.VK_ENTER){
          this.dispose();
          InsTblInvPenjValue();
     }else if(e.getKeyCode()==KeyEvent.VK_RIGHT){
          btnCancel.requestFocus();
     }
  }

  void btnCancel_actionPerformed(ActionEvent e) {
        this.dispose();
  }

  void btnCancel_keyPressed(KeyEvent e) {
      if(e.getKeyCode()==KeyEvent.VK_ENTER){
          frmInsFakPenj rst = new frmInsFakPenj(user, passwd);
          boolean retRst = rst.reset();
          if(retRst==true) System.out.println("RESET CALLED AND EXECUTED...");
          this.dispose();
     }else if(e.getKeyCode()==KeyEvent.VK_LEFT){
          btnOk.requestFocus();
     }
  }

}
