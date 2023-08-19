
//Title:        Your Product Name
//Version:
//Copyright:    Copyright (c) 1999
//Author:       Your Name
//Company:      Your Company
//Description:  Your description

package pembelian;

import java.awt.*;
import java.awt.event.*;

//import com.borland.dx.sql.dataset.*;
import com.borland.jbcl.control.*;

public class dlgSaveFakPenjDet extends Dialog {
  Panel panel1 = new Panel();

  Label label1 = new Label();
  Label label2 = new Label();
  TransparentImage transparentImage1 = new TransparentImage();
  Button btnOk = new Button();
  Button btnCancel = new Button();

  Frame frmFakPenjDet;
  Frame frmFakPenj;
  //QueryDataSet fakPenjQDset;
  //Database fakPenjDbase;
  TextField txtTerblg;

  TextField txtUrt, txtBnyk, txtRetur, txtDisc, txtJlh;
  Choice chcBrg;

  Double pDbl =  new Double(0);

  static double passedInsFakPenjTerblg = 0; //IMPORTANT FOR CALCULATING LOSS AND PROFIT OR FOR PAYMENT
  static float passedCalJlhBrg = 0; //IMPORTANT FOR UPDATING STOCK///////

  static String passedInsFakPenjK = " ";
//  static int passedInsFakPenjDetUrt = 0;
  static String passedInsFakPenjDetKBrg = " ";
  static double passedInsFakPenjDetHrg = 0;
  static float passedInsFakPenjDetBnyk = 0; //Use update to update into tblbarang /////
  static float passedInsFakPenjDetDisc = 0;
  static double passedInsFakPembDetPotHrg = 0;
  static double passedInsFakPenjDetJlh = 0;

  public dlgSaveFakPenjDet(Frame frame, String title, boolean modal) {
    super(frame, title, modal);
    frmFakPenjDet = frame;
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

  public dlgSaveFakPenjDet(Frame frame) {
    this(frame, "", false);
  }

  public dlgSaveFakPenjDet(Frame frame, boolean modal) {
    this(frame, "", modal);
  }

  public dlgSaveFakPenjDet(Frame frame, String title) {
    this(frame, title, false);
  }

  public void passInsFakPenjDetValue(//////////PASSED AS tblInvPemDet table VALUE////////////
                                    Frame frm,
                                    String noFak, double terblg, float jlhBrg,
                                    String kBrg, double hrg, float bnyk,
                                    float disc, double ptHrg, double jlh){

      frmFakPenj = frm;

      passedInsFakPenjK= noFak;
      passedInsFakPenjTerblg = terblg;////////////Use update instead of insert///
      passedCalJlhBrg = jlhBrg;  ////////////IMPORTANT FOR UPDATING STOCK/////

      passedInsFakPenjDetKBrg = kBrg;
      passedInsFakPenjDetHrg = hrg;
      passedInsFakPenjDetBnyk = bnyk; //Use update to update into tblbarang /////
      passedInsFakPenjDetDisc = disc;
      passedInsFakPembDetPotHrg = ptHrg;
      passedInsFakPenjDetJlh = jlh;

      System.out.println(passedInsFakPenjDetKBrg +", "+ passedInsFakPenjDetBnyk +", "+ passedInsFakPenjDetDisc + ", "+ passedInsFakPenjDetJlh);
  }


  void jbInit() throws Exception {
    panel1.setBackground(Color.lightGray);
    panel1.setSize(new Dimension(400,127));
    label1.setBackground(Color.lightGray);
    label1.setBounds(new Rectangle(71, 13, 286, 23));
    label1.setFont(new java.awt.Font("Serif", 1, 14));
    label1.setAlignment(1);
    label1.setText("Simpan Data Penjualan Barang...!!!!");
    panel1.setLayout(null);
    label2.setBackground(Color.lightGray);
    label2.setBounds(new Rectangle(68, 39, 305, 23));
    label2.setAlignment(1);
    label2.setText("Click \'Ok\' untuk Process, \'Cancel\' untuk Batal...!!!");
    transparentImage1.setDrawEdge(false);
    transparentImage1.setImageName("C:\\My Documents\\excla.JPG");
    transparentImage1.setBounds(new Rectangle(17, 16, 43, 35));
    btnOk.setBounds(new Rectangle(135, 74, 55, 23));
    btnOk.setLabel("Ok");
    btnOk.addKeyListener(new java.awt.event.KeyAdapter() {

      public void keyPressed(KeyEvent e) {
        btnOk_keyPressed(e);
      }
    });
    btnOk.addActionListener(new java.awt.event.ActionListener() {

      public void actionPerformed(ActionEvent e) {
        btnOk_actionPerformed(e);
      }
    });
    btnCancel.setBounds(new Rectangle(218, 73, 55, 23));
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

  void btnOk_actionPerformed(ActionEvent e) {
    saveAction();
  }

  void saveAction(){
      InsTblInvPenjDetValue();
  }

/*  void refreshCallFinalMenu(){
      selectData save = new selectData();
      String sqlQuery = getQuery(passedInsFakPenjK);
      int refQDSet= save.refresh(fakPenjQDset,fakPenjDbase,sqlQuery);
      if(refQDSet==1){
          this.dispose();
          //dlgFinalFakPemDet fnl = new frmFinalSavePemDet(frmFakPem, frmFakPemDet, txtUrt, chcBrg, txtBnyk, txtRetur, txtDisc, txtJlh);
         // centerScreen cScr = new centerScreen();
          //cScr.centerFrame(fnl,353,137);
      }
  }

  void setTerbilang(double terblg){
      txtTerblg.setText(pDbl.toString(terblg));
  }

 String getQuery(String fakNo){
      String sqlQuery = "select \n"+
       "tblInvPenjDet.banyak, tblBarang.nama_barang, tblBarang.kemasan, \n"+
       "tblInvPenjDet.disc, tblInvPenjDet.jumlah \n"+
       "from tblInvPenjDet\n"+
       "inner join tblBarang on tblInvPenjDet.kode_barang=tblBarang.kode_barang\n"+
       "where tblInvPenjDet.no_faktur ='" + fakNo+ "'\n" +
       "order by tblBarang.nama_barang";
      return sqlQuery;
  }
*/
  void InsTblInvPenjDetValue(){
    //boolean retInsFakPemDet;
    selectData save = new selectData();
    String sqlText = "insert into tblInvPenjDet values ( '"+
              passedInsFakPenjK.toLowerCase().trim() + "','"+
              passedInsFakPenjDetKBrg.toLowerCase().trim() + "',"+
              passedInsFakPenjDetHrg + ","+
              passedInsFakPenjDetBnyk + ","+
              passedInsFakPenjDetDisc + "," +
              passedInsFakPembDetPotHrg +","+
              passedInsFakPenjDetJlh + ")";
      int ok = save.insertData(sqlText);
      if(ok==1){
            System.out.println("SUCCESS on INSERTITNG TBL INVPEMDETVAL");
            UpdTblInvPenjValue();
      }
      else{
            System.out.println("INSERT PENJUALAN DETAILS VALUE FAIL...!!!!");
      }
  }

  double getSumTerbilang(){
     double jlh=0;
     selectData sel = new selectData();
     String sqlText = "select sum(tblInvPenjDet.jumlah) as jlh \n"+
                       "from tblInvPenjDet \n"+
                       "where no_faktur='"+passedInsFakPenjK+"'";
     String col = "jlh";
     jlh = sel.selectSingleDataDouble(sqlText,col);
     return jlh;

  }

  void UpdTblInvPenjValue(){
    //boolean retUpdInvPem;
    double jlh = getSumTerbilang();
    selectData save = new selectData();
    String sqlText = "update tblInvPenj set tblInvPenj.terbilang ="+jlh+
                     "\nwhere tblInvPenj.no_faktur ='"+ passedInsFakPenjK + "'";
      int ok = save.insertData(sqlText);
      if(ok==1){
           System.out.println("SUCCESS on U P D A T I N G TBL INVPEMDETVAL");
           UpdTblBrgValue();
      }else {
            System.out.println("UPDATE TBLINVPEMBELIAN VALUE FAIL.....");
       }
  }

  void UpdTblBrgValue(){
    selectData save = new selectData();
    String sqlText = "update tblBarang set tblBarang.jlh_brg = "+ passedCalJlhBrg   +
                     "\nwhere tblBarang.kode_barang = '"+ passedInsFakPenjDetKBrg + "'";
      int ok = save.insertData(sqlText);
      if(ok==1){
              System.out.println("SUCCESS on U P D A T I N G TBL B A R A N G");
                this.dispose();
                dispInsertSuccess("Insertion Successfully....");
      }else{
              System.out.println("UPDATE TBLBARANG VLALUE FAIL...........");
        }
  }

  void dispInsertSuccess(String msg){
    dlgSuccess succ= new dlgSuccess(frmFakPenj, "",true);
    succ.setLabel(msg);
    centerDialog cDlg = new centerDialog();
    cDlg.centerDialog(succ, 290, 145);
  }

  void btnCancel_actionPerformed(ActionEvent e) {
    this.dispose();
  }

  void btnOk_keyPressed(KeyEvent e) {
    if(e.getKeyCode()==KeyEvent.VK_ENTER){
        this.dispose();
        saveAction();
        //dlgInsFakPenjDet rst = new dlgInsFakPenjDet(frmFakPenj);
        //rst.reset();
    }else if(e.getKeyCode()==KeyEvent.VK_RIGHT){
        btnCancel.requestFocus();
    }
  }

  void btnCancel_keyPressed(KeyEvent e) {
     if(e.getKeyCode()==KeyEvent.VK_ENTER){
       this.dispose();
     }else if(e.getKeyCode()==KeyEvent.VK_LEFT){
        btnOk.requestFocus();
    }
  }

}