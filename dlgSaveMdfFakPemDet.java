
//Title:        Your Product Name
//Version:
//Copyright:    Copyright (c) 1999
//Author:       Your Name
//Company:      Your Company
//Description:  Your description

package pembelian;

import java.awt.*;
import java.awt.event.*;

import com.borland.dx.sql.dataset.*;
import com.borland.jbcl.control.*;

public class dlgSaveMdfFakPemDet extends Dialog {
  Panel panel1 = new Panel();

  Label label1 = new Label();
  Label label2 = new Label();
  TransparentImage transparentImage1 = new TransparentImage();
  Button btnOk = new Button();
  Button btnCancel = new Button();

  Frame frmMdfFakPemDet;
  Frame frmFakPem;

  TextArea passedTextArea = new TextArea(1000,4);

  Double pDbl =  new Double(0);

  static double passedInsFakPembTerblg = 0; //IMPORTANT FOR CALCULATING LOSS AND PROFIT OR FOR PAYMENT
  static float passedCalJlhBrg = 0; //IMPORTANT FOR UPDATING STOCK///////

  static String passedInsFakPembK = " ";
  static String passedInsFakPembDetKBrg = " ";
  static double passedInsFakPembDetHrg = 0;
  static float passedInsFakPembDetBnyk = 0; //Use update to update into tblbarang /////
  static double passedInsFakPembDetPotHrg = 0;
  static float passedInsFakPembDetDisc = 0;

  static double passedInsFakPembDetJlh = 0;

  public dlgSaveMdfFakPemDet(Frame frame, String title, boolean modal) {
    super(frame, title, modal);
    frmMdfFakPemDet = frame;
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

  public dlgSaveMdfFakPemDet(Frame frame) {
    this(frame, "", false);
  }

  public dlgSaveMdfFakPemDet(Frame frame, boolean modal) {
    this(frame, "", modal);
  }

  public dlgSaveMdfFakPemDet(Frame frame, String title) {
    this(frame, title, false);
  }

  public void passMdfFakPembDetValue(//////////PASSED AS tblInvPemDet table VALUE////////////
                                    Frame frm,
                                    String noFak, /*double terblg, */float jlhBrg,
                                    String kBrg, double hrg, float bnyk,
                                    float disc, double ptHrg, double jlh, TextArea areaDet){

      frmFakPem = frm;

      passedInsFakPembK= noFak;
//      passedInsFakPembTerblg = terblg;////////////Use update instead of insert///
      passedCalJlhBrg = jlhBrg;  ////////////IMPORTANT FOR UPDATING STOCK/////

      passedInsFakPembDetKBrg = kBrg;
      passedInsFakPembDetHrg = hrg;
      passedInsFakPembDetBnyk = bnyk; //Use update to update into tblbarang /////
      passedInsFakPembDetDisc = disc;
      passedInsFakPembDetPotHrg = ptHrg;
      passedInsFakPembDetJlh = jlh;

      passedTextArea = areaDet;

      System.out.println(passedInsFakPembDetKBrg +", "+
                         passedInsFakPembDetBnyk +", "+
                         passedInsFakPembDetDisc + ", "+
                          passedInsFakPembDetJlh);
  }


  void jbInit() throws Exception {
    panel1.setSize(new Dimension(400,127));
    label1.setBounds(new Rectangle(63, 13, 326, 23));
    label1.setFont(new java.awt.Font("Serif", 1, 14));
    label1.setAlignment(1);
    label1.setText("Simpan Data Perubahan Faktur Pembelian ...!!!!");
    panel1.setLayout(null);
    label2.setBounds(new Rectangle(76, 39, 276, 23));
    label2.setAlignment(1);
    label2.setText("Click \'Ok\' untuk Process, \'Cancel\' untuk Batal...!!!");
    transparentImage1.setDrawEdge(false);
    transparentImage1.setImageName("C:\\My Documents\\excla.JPG");
    transparentImage1.setBounds(new Rectangle(17, 16, 43, 35));
    btnOk.setBounds(new Rectangle(116, 74, 55, 23));
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
    btnCancel.setBounds(new Rectangle(199, 73, 55, 23));
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
    panel1.setBackground(Color.lightGray);
    panel1.add(btnOk, null);
    panel1.add(label1, null);
    panel1.add(transparentImage1, null);
    panel1.add(btnCancel, null);
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
      selectCurrentJlhBrg();
  }



/* String getQuery(String fakNo){
      String sqlQuery = "select "+
       "tblInvPemDet.banyak, tblBarang.nama_barang, tblBarang.kemasan, \n"+
       "tblInvPemDet.disc, tblInvPemDet.harga, tblInvPemDet.jumlah \n"+
       "from tblInvPemDet\n"+
       "inner join tblBarang on tblInvPemDet.kode_barang=tblBarang.kode_barang\n"+
       "where tblInvPemDet.no_faktur ='" + fakNo+ "'\n" +
       "order by tblInvPemDet.kode_barang";
      return sqlQuery;
  }
*/
  void MdfTblInvPemDetValue(){
    //boolean retInsFakPemDet;
    selectData save = new selectData();
    String sqlText = "update tblInvPemDet set \n"+
              "harga ="+ passedInsFakPembDetHrg + ","+
              "banyak ="+ passedInsFakPembDetBnyk + ","+
              "disc ="+ passedInsFakPembDetDisc + "," +
              "potongan_hrg="+passedInsFakPembDetPotHrg +","+
              "jumlah ="+ passedInsFakPembDetJlh +"\n"+
              "where tblInvPemDet.no_faktur ='"+ passedInsFakPembK.toLowerCase().trim()+"' and \n"+
              "tblInvPemDet.kode_barang='"+passedInsFakPembDetKBrg.toLowerCase().trim()+"'";
      int ok = save.insertData(sqlText);
      if(ok==1){
            System.out.println("SUCCESS on UPDATING TBL INVPEMDETVAL");
             UpdTblInvPemValue();
      } else{
            System.out.println("REFRESH VALUE FAIL...!!!!");
      }
  }

  double getSumPemDet(){
      selectData sel = new selectData();
      String sqlText = "select sum(jumlah) as jlh from tblInvPemDet where tblInvPemDet.no_faktur='"+passedInsFakPembK+"'";
      String col ="jlh";
      double terblg = sel.selectSingleDataDouble(sqlText, col);
      return terblg;

  }

  void UpdTblInvPemValue(){
    //boolean retUpdInvPem;
    double terblg = getSumPemDet();
    selectData save = new selectData();
    String sqlText = "update tblInvPem set tblInvPem.terbilang = "+  terblg+
                     "\nwhere tblInvPem.no_faktur = '"+ passedInsFakPembK + "'";
      int ok = save.insertData(sqlText);
      if(ok==1){
            this.dispose();
            dispInsertSuccess("Data Updated....");
      }else {
            System.out.println("UPDATE TBLINVPEMBELIAN VALUE FAIL.....");
      }
  }

  void dispInsertSuccess(String msg){
    dlgSuccess succ= new dlgSuccess(frmMdfFakPemDet, "",true);
    succ.setLabel(msg);
    centerDialog cDlg = new centerDialog();
    cDlg.centerDialog(succ, 290, 145);
  }

  void selectCurrentJlhBrg(){
    selectData sel = new selectData();
    String sqlText1 = "select tblBarang.jlh_brg as jlh from tblBarang where tblBarang.kode_barang='"+passedInsFakPembDetKBrg+"'";
    String col1 ="jlh";
    float currJlhBrg = sel.selectSingleDataFloat(sqlText1,col1);
    String sqlText2 = "select tblInvPemDet.banyak as bnyk from tblInvPemDet\n"+
                      "where tblInvPemDet.no_faktur ='"+ passedInsFakPembK.toLowerCase().trim()+"' and \n"+
                      "tblInvPemDet.kode_barang='"+passedInsFakPembDetKBrg.toLowerCase().trim()+"'";
    String col2 = "bnyk";
    float currBnykBrg = sel.selectSingleDataInt(sqlText2,col2);
    float newJlhBrg = currJlhBrg-currBnykBrg+passedInsFakPembDetBnyk;
    UpdTblBrgValue(newJlhBrg);
  }

  void UpdTblBrgValue(float newJlhBrg){
    //boolean retUpd;
    selectData save = new selectData();
    String sqlText = "update tblBarang set tblBarang.jlh_brg = "+ newJlhBrg +
                     "\nwhere tblBarang.kode_barang = '"+ passedInsFakPembDetKBrg + "'";
      int ok = save.insertData(sqlText);
      if(ok==1){
              System.out.println("SUCCESS on U P D A T I N G TBL B A R A N G");
              MdfTblInvPemDetValue();
      }else{
              System.out.println("UPDATE TBLBARANG VLALUE FAIL...........");
        }
  }

  void btnCancel_actionPerformed(ActionEvent e) {
    this.dispose();
  }

  void btnOk_keyPressed(KeyEvent e) {
    if(e.getKeyCode()==KeyEvent.VK_ENTER){
        //Need to add one more frame to conform proceeed insertion
        this.dispose();
        saveAction();

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