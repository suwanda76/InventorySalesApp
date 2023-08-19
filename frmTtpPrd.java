
//Title:        Your Product Name
//Version:
//Copyright:    Copyright (c) 1999
//Author:       Your Name
//Company:      Your Company
//Description:  Your description

package pembelian;

import java.awt.*;
import java.net.URL;
import java.sql.*;
import java.awt.event.*;
import java.text.DateFormat;
import java.text.*;
import java.util.Date;
import java.io.*;
//import javax.swing.*;

public class frmTtpPrd extends Frame {
  File fl;
  final String sqlDTblRetPem= "delete * from tblReturPem";
  final String sqlDTblRetPenj = "delete * from tblReturPenj";
  final String sqlDTblInvPemDet = " delete * from tblInvPemDet";
  final String sqlDTblInvPem = "delete * from tblInvPem";
  final String sqlDTblInvPenjDet = " delete * from tblInvPenjDet";
  final String sqlDTblInvPenj = "delete * from tblInvPenj";
  final String sqlDTblSupp = "delete * from tblSupp";
  final String sqlDTblOutlet = "delete * from tblOutlet";
  final String sqlDTblBarang = "delete * from tblBarang";


  final String sqlSelTblInvPemDetNotLns = "select * from tblInvPemDet \n"+
                                          "inner join tblInvPem on tblInvPemDet.no_faktur=tblInvPem.no_faktur\n"+
                                          "where tblInvPem.lunas='nnn'";
  final String sqlSelTblInvPenjDetNotLns = "select * from tblInvPenjDet \n"+
                                          "inner join tblInvPenj on tblInvPenjDet.no_faktur=tblInvPenj.no_faktur\n"+
                                          "where tblInvPenj.lunas='nnn'";
  final String sqlSelTblInvPemNotLns = "select * from tblInvPem \n"+
                                       "where lunas='nnn'";
  final String sqlSelTblInvPenjNotLns = "select * from tblInvPenj \n"+
                                        "where lunas='nnn'";
  Date dDate = new Date();
  SimpleDateFormat formatterYear = new SimpleDateFormat ("yyyy");
  String yr = formatterYear.format(dDate);

  Label label1 = new Label();
  Label label2 = new Label();
  Label label3 = new Label();
  TextField txtThn = new TextField(yr,4);
  Button btnSave = new Button();
  Button btnCancel = new Button();
  Button btnReset = new Button();
  public static ResultSet results;
  private Statement stmt;
  public Connection con;
  private int ResultCode;
  final String errMsg = "User sudah Sudah Ada..!!";
  static String user="";
  static String passwd="";
  Choice chcPrd = new Choice();
  Label label4 = new Label();
  static int currProgressVlue=0;
  Label progressLabel = new Label("");
  Integer pInt = new Integer(0);
  String month="";
  String year="";
  public frmTtpPrd(String usr, String pass, String mnth, String yr) {
  user=usr;
  passwd=pass;
  month = mnth;
  year = yr;
    try  {
      jbInit();
    }
    catch(Exception e) {
      e.printStackTrace();
    }
  }

  private void jbInit() throws SQLException {
  this.setBackground(Color.lightGray);
    this.setSize(new Dimension(382, 208));
    this.setEnabled(true);
    label1.setBounds(new Rectangle(3, 21, 374, 23));
    label1.setFont(new java.awt.Font("Serif", 1, 14));
    label1.setAlignment(1);
    label1.setText("TUTUP PERIODE");
    this.setResizable(false);
    this.addWindowListener(new java.awt.event.WindowAdapter() {

      public void windowClosing(WindowEvent e) {
        this_windowClosing(e);
      }
    });
    this.setTitle("Transaksi Tutup Periode");
    this.setLayout(null);
    label2.setBounds(new Rectangle(63, 59, 70, 23));
    label2.setFont(new java.awt.Font("Dialog", 0, 14));
    label2.setText("BULAN :");
    label3.setBounds(new Rectangle(64, 87, 74, 23));
    label3.setFont(new java.awt.Font("Dialog", 0, 14));
    label3.setText("TAHUN :");
    txtThn.setBackground(Color.white);
    txtThn.setBounds(new Rectangle(144, 84, 80, 26));
    txtThn.setFont(new java.awt.Font("Serif", 1, 14));
    txtThn.addKeyListener(new java.awt.event.KeyAdapter() {

      public void keyPressed(KeyEvent e) {
        txtThn_keyPressed(e);
      }
    });
    btnSave.setBounds(new Rectangle(80, 126, 55, 23));
    btnSave.setLabel("Ok");
    btnSave.addKeyListener(new java.awt.event.KeyAdapter() {

      public void keyPressed(KeyEvent e) {
        btnSave_keyPressed(e);
      }
    });
    btnSave.addActionListener(new java.awt.event.ActionListener() {

      public void actionPerformed(ActionEvent e) {
        btnSave_actionPerformed(e);
      }
    });
    btnCancel.setBounds(new Rectangle(155, 126, 55, 23));
    btnCancel.setLabel("Quit");
    btnCancel.addActionListener(new java.awt.event.ActionListener() {

      public void actionPerformed(ActionEvent e) {
        btnCancel_actionPerformed(e);
      }
    });
    btnCancel.addKeyListener(new java.awt.event.KeyAdapter() {

      public void keyPressed(KeyEvent e) {
        btnCancel_keyPressed(e);
      }
    });
    btnReset.setBounds(new Rectangle(231, 126, 55, 23));
    btnReset.setLabel("Reset");
    btnReset.addKeyListener(new java.awt.event.KeyAdapter() {

      public void keyPressed(KeyEvent e) {
        btnReset_keyPressed(e);
      }
    });
    btnReset.addActionListener(new java.awt.event.ActionListener() {

      public void actionPerformed(ActionEvent e) {
        btnReset_actionPerformed(e);
      }
    });
    chcPrd.setBounds(new Rectangle(143, 54, 157, 30));
    chcPrd.setFont(new java.awt.Font("Serif", 1, 14));
    chcPrd.addKeyListener(new java.awt.event.KeyAdapter() {

      public void keyPressed(KeyEvent e) {
        chcPrd_keyPressed(e);
      }
    });
    chcPrd.addItemListener(new java.awt.event.ItemListener() {

      public void itemStateChanged(ItemEvent e) {
        chcPrd_itemStateChanged(e);
      }
    });
    progressLabel.setBounds(new Rectangle(1, 171, 381, 23));
    progressLabel.setFont(new java.awt.Font("Serif", 0, 12));
    progressLabel.setAlignment(1);
    chcPrd.add("JANUARI");
    chcPrd.add("PEBUARI");
    chcPrd.add("MARET");
    chcPrd.add("APRIL");
    chcPrd.add("MEI");
    chcPrd.add("JUNI");
    chcPrd.add("JULI");
    chcPrd.add("AGUSTUS");
    chcPrd.add("SEPTEMBER");
    chcPrd.add("OKTOBER");
    chcPrd.add("NOPEMBER");
    chcPrd.add("DESEMBER");
    chcPrd.select(month.toUpperCase());
    txtThn.setText(year);

    label4.setBounds(new Rectangle(2, 31, 377, 23));
    label4.setAlignment(1);
    label4.setText("__________________________________________________");
    this.add(chcPrd, null);
    this.add(label1, null);
    this.add(label4, null);
    this.add(btnSave, null);
    this.add(btnCancel, null);
    this.add(txtThn, null);
    this.add(label3, null);
    this.add(label2, null);
    this.add(btnReset, null);
    this.add(progressLabel, null);
  }

  void btnSave_actionPerformed(ActionEvent e)
  {
     saveAction();
  }

  void saveAction(){
    returnVal retVal = new returnVal();
    frmSetPeriode search = new frmSetPeriode();
    String mnth = chcPrd.getSelectedItem().toUpperCase();
    boolean yrB = retVal.getIntBoolean(txtThn);
    if(yrB==true){
       int year = retVal.getIntVal(txtThn);
       String yearStr = pInt.toString(year);
       if(yearStr.length()>4 || yearStr.length()<4){
              dispErrorMsg("Please Enter 4 digits year..!!");
       }else{
            if(search.searchDatabaseFile(mnth,year)){
                ////validate database read only...../////////////
                if(search.searchDatabaseFileCanWrite(mnth,year)){
                    executeTtpPrd(mnth, year);
                    System.out.println("Database found and can be written "+ mnth +" "+ year);
                }else{
                   dispErrorMsg("Sudah Tutup Periode..!!");
                }
            }else{
                dispErrorMsg("Database NOT Found..!!!");
            }
       }
    }else{
        dispErrorMsg("Invalid year...!!!");
    }

  }


  void executeTtpPrd(String mnth, int year){
    ////////set to next periode if success///////
    selectData setNxtPrd = new selectData();
    ////////////////////////////////////////////
    tools tl = new tools();
    selectData sel = new selectData();
    String dbase = sel.DSN;
    progressLabel.setText("Select Database");
    String url = "jdbc:odbc:"+tl.setNxtPrdNew(mnth, year);
                progressLabel.setText("Delete tblInvPenjDet Bln "+ tl.getNextMonthPeriod(mnth));
                int dTblPenjDet=sel.executeUpdateNextPrd(user, passwd, url, this.sqlDTblInvPenjDet);
                if(dTblPenjDet==1){
                  progressLabel.setText("Copying tblInvPenjDet ke Bln "+ tl.getNextMonthPeriod(mnth));
                  int invPenjDetOk = selTblInvPenjPemDet(dbase, mnth, year, this.sqlSelTblInvPenjDetNotLns, "tblInvPenjDet");
                  if(invPenjDetOk==1){
                     progressLabel.setText("Delete tblInvPemDet Bln "+ tl.getNextMonthPeriod(mnth));
                     int dTblPemDet=sel.executeUpdateNextPrd(user, passwd, url, this.sqlDTblInvPemDet);
                     if(dTblPemDet==1){
                        progressLabel.setText("Copying tblInvPemDet ke Bln "+ tl.getNextMonthPeriod(mnth));
                        int invPemDetOk = selTblInvPenjPemDet(dbase, mnth, year, this.sqlSelTblInvPemDetNotLns, "tblInvPemDet");
                        if(invPemDetOk==1){
                           progressLabel.setText("Delete tblInvPem Bln "+ tl.getNextMonthPeriod(mnth));
                           int dTblPem=sel.executeUpdateNextPrd(user, passwd, url, this.sqlDTblInvPem);
                           if(dTblPem==1){
                              progressLabel.setText("Copying tblInvPem ke Bln "+ tl.getNextMonthPeriod(mnth));
                              int invPemOk = selTblInvPenjPem(dbase, mnth, year, this.sqlSelTblInvPemNotLns, "tblInvPem");
                              if(invPemOk==1){
                                 progressLabel.setText("Delete tblInvPenj Bln "+ tl.getNextMonthPeriod(mnth));
                                 int dTblPenj=sel.executeUpdateNextPrd(user, passwd, url, this.sqlDTblInvPenj);
                                 if(dTblPenj==1){
                                    progressLabel.setText("Copying tblInvPenj ke Bln "+ tl.getNextMonthPeriod(mnth));
                                    int invPenjOk = selTblInvPenjPem(dbase, mnth, year, this.sqlSelTblInvPenjNotLns, "tblInvPenj");
                                    if(invPenjOk==1){
                                       progressLabel.setText("Delete tblSupp Bln "+ tl.getNextMonthPeriod(mnth));
                                       int dTblSupp=sel.executeUpdateNextPrd(user, passwd, url, this.sqlDTblSupp);
                                       if(dTblSupp==1){
                                          progressLabel.setText("Copying tblSupp ke Bln "+ tl.getNextMonthPeriod(mnth));
                                          int cpTblSuppOk = selTblSupp(dbase, mnth, year, "select * from tblSupp", "tblSupp");
                                          if(cpTblSuppOk==1){
                                              progressLabel.setText("Delete tblOutlet Bln "+ tl.getNextMonthPeriod(mnth));
                                              int dTblOutlet=sel.executeUpdateNextPrd(user, passwd, url, this.sqlDTblOutlet);
                                              if(dTblOutlet==1){
                                                 progressLabel.setText("Copying tblOutlet ke Bln "+ tl.getNextMonthPeriod(mnth));
                                                 int cpTblOutOk = selTblOutlet(dbase, mnth, year, "select * from tblOutlet", "tblOutlet");                                                 if(cpTblOutOk==1){
                                                    progressLabel.setText("Delete tblBarang Bln "+ tl.getNextMonthPeriod(mnth));
                                                    int dTblBarang=sel.executeUpdateNextPrd(user, passwd, url, this.sqlDTblBarang);
                                                    if(dTblBarang==1){
                                                       progressLabel.setText("Copying tblBarang ke Bln "+ tl.getNextMonthPeriod(mnth));
                                                       int cpTblBrgOk=0;
                                                       if(cpTblBrgOk==1){
                                                              System.out.println(mnth.toUpperCase());
                                                              String currFile = tl.setCurrPrdNew(mnth.toUpperCase(), year);
                                                              System.out.println(currFile);
                                                              fl = new File("D:\\backup", currFile.toLowerCase()+".mdb");
                                                              //fl = new File("c:\\data", currFile+".mdb");
                                                              boolean rd = fl.setReadOnly();
                                                              if(rd){
                                                                System.out.println("Database "+ currFile + " has been set to read only");
                                                                int delPrdOk = setNxtPrd.setDatabase(user, passwd, "delete * from tblSetPeriode");
                                                                if(delPrdOk==1){
                                                                 String sqlText="insert into tblSetPeriode values ('"+
                                                                                  tl.getNextMonthPeriod(mnth).toLowerCase()+ "','"+
                                                                                  pInt.toString(year) + "','"+
                                                                                  tl.setNxtPrdNew(mnth, year)+"')";
                                                                      int nxtPrdOk = setNxtPrd.setDatabase(user, passwd, sqlText);
                                                                      if(nxtPrdOk==1){
                                                                            progressLabel.setText("ACTIVE PERIODE SET TO "+ tl.getNextMonthPeriod(mnth));
                                                                      }
                                                                }
                                                             }//end of set read only
                                                       }//copy tblBarang
                                                    }//delete tblBarang
                                                 }//copy tblOutlet
                                              }//delete tblOutlet
                                          }//copy tblSupp
                                       }//delete tblSupp
                                    }//copy invPenj
                                 }//delete invPenj
                             }//copy invPem
                          }//delete invPem
                        }//copy invPemDet
                     }//delete invPemDet
                  }//copy invPenjDet
               }//delete invPenjDet
//            }//copy retur pembelian
//         }//delete ret pembelian
//       }//copy retur penjaualn
//    }//delete ret penjualan
  }

  /*void ttpPrdSuccess(String msg){
      insSuccess succ = new insSuccess();
      centerScreen cFrm = new centerScreen();
      cFrm.
  } */


  public int selTblRetPenjPem(String url, String mnth, int year, String sqlSel, String tblRetur){
    int ok;
    try
    {
      String dbase   =  url;
      System.out.println("URL : " + dbase);


      Class.forName ("sun.jdbc.odbc.JdbcOdbcDriver");
      con = DriverManager.getConnection(dbase, user, "" );
      // Create a Statement object so we can submit
      // SQL statements to the driver
      stmt = con.createStatement ();
      String SQLText = sqlSel;
      //System.out.println(SQLText1);
      results = stmt.executeQuery(SQLText);
      boolean more = results.next();
      while(more){
          insertToNextPeriodTblRetur(mnth, year, tblRetur, results.getString(1),
                                   results.getString(2),
                                   results.getString(3),
                                   results.getString(4),
                                   results.getFloat("banyak"),
                                   results.getDouble("jlh_harga"),
                                   results.getString(7));

          more = results.next();
      }
      con.close();
      stmt.close();
      ok= 1;
    }
    catch(SQLException ex){
       while(ex!=null){
          dispErrorMsg(ex.getMessage());
          //System.out.println(ex.getMessage());
          ex = ex.getNextException();
       }
       ok=2;
    }catch (java.lang.Exception ex) {
      // Print description of the exception.
      //insert = false;
      ex.printStackTrace ();
      dispErrorMsg(ex.getMessage());
      ok=3;

    }
    return ok;
  }

  public void insertToNextPeriodTblRetur(String mnth, int year, String tblRetur, String kout, String nofak, String tgl,
                                 String kbrg, float bnyk,
                                 double jlhHrg, String cat){
    String nextPrd = getNextPeriod(mnth, year);
    System.out.println(nextPrd);
    try
    {
      String dbase   =  "jdbc:odbc:"+nextPrd;
      System.out.println("URL : " + dbase);


      Class.forName ("sun.jdbc.odbc.JdbcOdbcDriver");
      con = DriverManager.getConnection(dbase, user, "" );
      // Create a Statement object so we can submit
      // SQL statements to the driver
      stmt = con.createStatement ();
      String sqlText = "insert into "+ tblRetur +" values ('"+
                               kout  + "','"+
                               nofak + "','"+
                               tgl   + "','"+
                               kbrg  + "',"+
                               bnyk  + "," +
                               jlhHrg +",'" +
                               cat    + "')";
      System.out.println(sqlText);
      int ResultCode =stmt.executeUpdate(sqlText);
      con.close();
      stmt.close();
    }
    catch(SQLException ex){
       while(ex!=null){
          System.out.println(ex.getMessage());
          ex = ex.getNextException();
       }
    }catch (java.lang.Exception ex) {
      // Print description of the exception.
      //insert = false;
      ex.printStackTrace ();
    }
  }

    public int selTblInvPenjPemDet(String url, String mnth, int year, String sqlSel, String tblInvDet){
    int ok;
    try
    {
      String dbase   =  url;
      System.out.println("URL : " + dbase);


      Class.forName ("sun.jdbc.odbc.JdbcOdbcDriver");
      con = DriverManager.getConnection(dbase, user, "" );
      // Create a Statement object so we can submit
      // SQL statements to the driver
      stmt = con.createStatement ();
      String SQLText = sqlSel;
      //System.out.println(SQLText1);
      results = stmt.executeQuery(SQLText);
      boolean more = results.next();
      while(more){
          insertToNextPeriodTblInvDet(mnth, year, tblInvDet,
                                   results.getString(1),
                                   results.getString(2),
                                   results.getDouble("harga"),
                                   results.getFloat("banyak"),
                                   results.getFloat("disc"),
                                   results.getDouble("potongan_hrg"),
                                   results.getDouble("jumlah") );

          more = results.next();
      }
      con.close();
      stmt.close();
      ok= 1;
    }
    catch(SQLException ex){
       while(ex!=null){
          System.out.println(ex.getMessage());
          ex = ex.getNextException();
       }
       ok=2;
    }catch (java.lang.Exception ex) {
      // Print description of the exception.
      //insert = false;
      ex.printStackTrace ();
      ok=3;

    }
    return ok;
  }

  public void insertToNextPeriodTblInvDet(String mnth, int year, String tblInvDet, String nofak,
                                 String kbrg, double hrg, float bnyk, float disc,
                                 double potHrg, double jlh){
    String nextPrd = getNextPeriod(mnth, year);
    try
    {
      String dbase   =  "jdbc:odbc:"+nextPrd;
      System.out.println("URL : " + dbase);


      Class.forName ("sun.jdbc.odbc.JdbcOdbcDriver");
      con = DriverManager.getConnection(dbase, user, "" );
      // Create a Statement object so we can submit
      // SQL statements to the driver
      stmt = con.createStatement ();
      String sqlText = "insert into "+ tblInvDet +" values ('"+
                               nofak  + "','"+
                               kbrg + "', "+
                               hrg + ", "+
                               bnyk + ", "+
                               disc  + ", "+
                               potHrg  + ", " +
                               jlh    + ")";
      System.out.println(sqlText);
      int ResultCode =stmt.executeUpdate(sqlText);
      con.close();
      stmt.close();
    }
    catch(SQLException ex){
       while(ex!=null){
          System.out.println(ex.getMessage());
          ex = ex.getNextException();
       }
    }catch (java.lang.Exception ex) {
      // Print description of the exception.
      //insert = false;
      ex.printStackTrace ();
    }
  }

  public int selTblInvPenjPem(String url, String mnth, int year, String sqlSel, String tblInv){
    int ok;
    try
    {
      String dbase   =  url;
      System.out.println("URL : " + dbase);


      Class.forName ("sun.jdbc.odbc.JdbcOdbcDriver");
      con = DriverManager.getConnection(dbase, user, "" );
      // Create a Statement object so we can submit
      // SQL statements to the driver
      stmt = con.createStatement ();
      String SQLText = sqlSel;
      //System.out.println(SQLText1);
      results = stmt.executeQuery(SQLText);
      boolean more = results.next();
      while(more){
          insertToNextPeriodTblInvPemPenj(mnth, year, tblInv,
                                   results.getString(1),
                                   results.getString(2),
                                   results.getString(3),
                                   results.getInt("hari"),
                                   results.getString(5),
                                   results.getDouble("terbilang"),
                                   results.getString(7),
                                   results.getDouble("total"),
                                   results.getDouble("cn"),
                                   results.getString(10),
                                   results.getString(11),
                                   results.getString(12));

          more = results.next();
      }
      con.close();
      stmt.close();
      ok= 1;
    }
    catch(SQLException ex){
       while(ex!=null){
          System.out.println(ex.getMessage());
          ex = ex.getNextException();
       }
       ok=2;
    }catch (java.lang.Exception ex) {
      // Print description of the exception.
      //insert = false;
      ex.printStackTrace ();
      ok=3;

    }
    return ok;
  }

  public void insertToNextPeriodTblInvPemPenj(String mnth, int year, String tblInv,
                                 String nofak, String tgl, String kSuppOut,
                                 int hari, String lunas, double terblg,
                                 String tglByr, double ttl, double cn,
                                 String tg, String tg_ket, String cat){
    String nextPrd = getNextPeriod(mnth, year);
    try
    {
      String dbase   =  "jdbc:odbc:"+nextPrd;
      System.out.println("URL : " + dbase);


      Class.forName ("sun.jdbc.odbc.JdbcOdbcDriver");
      con = DriverManager.getConnection(dbase, user, "" );
      // Create a Statement object so we can submit
      // SQL statements to the driver
      stmt = con.createStatement ();
      String sqlText = "insert into "+ tblInv +" values ('"+
                               nofak  + "','"+
                               tgl + "', '"+
                               kSuppOut + "', "+
                               hari   +  ", '"+
                               lunas  + "', "+
                               terblg + ", '"+
                               tglByr + "', "+
                               ttl + ", "+
                               cn + ", '"+
                               tg + "', '"+
                               tg_ket + "', '"+
                               cat + "')";
      System.out.println(sqlText);
      int ResultCode =stmt.executeUpdate(sqlText);
      con.close();
      stmt.close();
    }
    catch(SQLException ex){
       while(ex!=null){
          System.out.println(ex.getMessage());
          ex = ex.getNextException();
       }
    }catch (java.lang.Exception ex) {
      // Print description of the exception.
      //insert = false;
      ex.printStackTrace ();
    }
  }

  public int selTblSupp(String url, String mnth, int year, String sqlSel, String tblSupp){
    int ok;
    try
    {
      String dbase   =  url;
      System.out.println("URL : " + dbase);


      Class.forName ("sun.jdbc.odbc.JdbcOdbcDriver");
      con = DriverManager.getConnection(dbase, user, "" );
      // Create a Statement object so we can submit
      // SQL statements to the driver
      stmt = con.createStatement ();
      String SQLText = sqlSel;
      //System.out.println(SQLText1);
      results = stmt.executeQuery(SQLText);
      boolean more = results.next();
      while(more){
          insertToNextPeriodTblSupp(mnth, year, tblSupp,
                                   results.getString(1).trim(),
                                   results.getString(2).trim(),
                                   results.getString(3).trim(),
                                   results.getString(4).trim(),
                                   results.getString(5).trim(),
                                   results.getString(6).trim());
          more = results.next();
      }
      con.close();
      stmt.close();
      ok= 1;
    }
    catch(SQLException ex){
       while(ex!=null){
          System.out.println(ex.getMessage());
          ex = ex.getNextException();
       }
       ok=2;
    }catch (java.lang.Exception ex) {
      // Print description of the exception.
      //insert = false;
      ex.printStackTrace ();
      ok=3;

    }
    return ok;
  }

  public void insertToNextPeriodTblSupp(String mnth, int year, String tblSupp,
                                 String tgl, String ksupp, String supp, String almt,
                                 String telp, String fax){
    String nextPrd = getNextPeriod(mnth, year);
    try
    {
      String dbase   =  "jdbc:odbc:"+nextPrd;
      System.out.println("URL : " + dbase);


      Class.forName ("sun.jdbc.odbc.JdbcOdbcDriver");
      con = DriverManager.getConnection(dbase, user, "" );
      // Create a Statement object so we can submit
      // SQL statements to the driver
      stmt = con.createStatement ();
      String sqlText = "insert into "+ tblSupp +" values ('"+
                               tgl + "','"+
                               ksupp  + "','"+
                               supp + "','"+
                               almt + "','"+
                               telp + "','"+
                               fax +"')";
      System.out.println(sqlText);
      int ResultCode =stmt.executeUpdate(sqlText);
      con.close();
      stmt.close();
    }
    catch(SQLException ex){
       while(ex!=null){
          System.out.println(ex.getMessage());
          ex = ex.getNextException();
       }
    }catch (java.lang.Exception ex) {
      // Print description of the exception.
      //insert = false;
      ex.printStackTrace ();
    }
  }

  public int selTblOutlet(String url, String mnth, int year, String sqlSel, String tblOutlet){
    int ok;
    try
    {
      String dbase   =  url;
      System.out.println("URL : " + dbase);


      Class.forName ("sun.jdbc.odbc.JdbcOdbcDriver");
      con = DriverManager.getConnection(dbase, user, "" );
      // Create a Statement object so we can submit
      // SQL statements to the driver
      stmt = con.createStatement ();
      String SQLText = sqlSel;
      //System.out.println(SQLText1);
      results = stmt.executeQuery(SQLText);
      boolean more = results.next();
      while(more){
          insertToNextPeriodTblOutlet(mnth, year, tblOutlet,
                                   results.getString(1),
                                   results.getString(2),
                                   results.getString(3),
                                   results.getString(4),
                                   results.getString(5),
                                   results.getString(6));
          more = results.next();
      }
      con.close();
      stmt.close();
      ok= 1;
    }
    catch(SQLException ex){
       while(ex!=null){
          System.out.println(ex.getMessage());
          ex = ex.getNextException();
       }
       ok=2;
    }catch (java.lang.Exception ex) {
      // Print description of the exception.
      //insert = false;
      //  ex.printStackTrace ();
      ok=3;

    }
    return ok;
  }

  public void insertToNextPeriodTblOutlet(String mnth, int year, String tblOutlet,
                                 String tgl, String kout, String out,
                                 String almt, String telp, String fax){
    String nextPrd = getNextPeriod(mnth, year);
    try
    {
      String dbase   =  "jdbc:odbc:"+nextPrd;
      System.out.println("URL : " + dbase);


      Class.forName ("sun.jdbc.odbc.JdbcOdbcDriver");
      con = DriverManager.getConnection(dbase, user, "" );
      // Create a Statement object so we can submit
      // SQL statements to the driver
      stmt = con.createStatement ();
      String sqlText = "insert into "+ tblOutlet +" values ('"+
                               tgl  + "','"+
                               kout  + "','"+
                               out  + "','"+
                               almt  + "','"+
                               telp  + "','"+
                               fax + "')";
      System.out.println(sqlText);
      int ResultCode =stmt.executeUpdate(sqlText);
      con.close();
      stmt.close();
    }
    catch(SQLException ex){
       while(ex!=null){
          System.out.println(ex.getMessage());
          ex = ex.getNextException();
       }
    }catch (java.lang.Exception ex) {
      // Print description of the exception.
      //insert = false;
      ex.printStackTrace ();
    }
  }

  public int selTblBarang(String url, String mnth, int year, String sqlSel, String tblBarang){
    int ok;
    try
    {
      String dbase   =  url;
      System.out.println("URL : " + dbase);


      Class.forName ("sun.jdbc.odbc.JdbcOdbcDriver");
      con = DriverManager.getConnection(dbase, user, "" );
      // Create a Statement object so we can submit
      // SQL statements to the driver
      stmt = con.createStatement ();
      String SQLText = sqlSel;
      //System.out.println(SQLText1);
      results = stmt.executeQuery(SQLText);
      boolean more = results.next();
      while(more){
          insertToNextPeriodTblBarang(mnth, year, tblBarang,
                                   results.getString(1),
                                   results.getString(2),
                                   results.getString(3),
                                   results.getString(4),
                                   results.getDouble("harga_beli"),
                                   results.getDouble("harga_jual"),
                                   results.getString(7),
                                   results.getFloat("jlh_brg") );
          more = results.next();
      }
      con.close();
      stmt.close();
      ok= 1;
    }
    catch(SQLException ex){
       while(ex!=null){
          System.out.println(ex.getMessage());
          ex = ex.getNextException();
       }
       ok=2;
    }catch (java.lang.Exception ex) {
      // Print description of the exception.
      //insert = false;
      ex.printStackTrace ();
      ok=3;

    }
    return ok;
  }

  public void insertToNextPeriodTblBarang(String mnth,int year, String tblBarang,
                                 String tgl, String kbrg, String ksupp,
                                 String nmBrg, double hrgBl, double hrgJl,
                                 String kms, float jlhBrg){
    String nextPrd = getNextPeriod(mnth, year);
    try
    {
      String dbase   =  "jdbc:odbc:"+nextPrd;
      System.out.println("URL : " + dbase);


      Class.forName ("sun.jdbc.odbc.JdbcOdbcDriver");
      con = DriverManager.getConnection(dbase, user, "" );
      // Create a Statement object so we can submit
      // SQL statements to the driver
      stmt = con.createStatement ();
      String sqlText = "insert into "+ tblBarang +" values ('"+
                               tgl   + "','"+
                               kbrg  + "','"+
                               ksupp + "','"+
                               nmBrg + "',"+
                               hrgBl + ", "+
                               hrgJl + ", '"+
                               kms   + "', "+
                               jlhBrg + ")";
      System.out.println(sqlText);
      int ResultCode =stmt.executeUpdate(sqlText);
      con.close();
      stmt.close();
    }
    catch(SQLException ex){
       while(ex!=null){
          System.out.println(ex.getMessage());
          ex = ex.getNextException();
       }
    }catch (java.lang.Exception ex) {
      // Print description of the exception.
      //insert = false;
      ex.printStackTrace ();
    }
  }

  public String getNextPeriod(String mnth, int year)
  {
       tools tl = new tools();
       String nextPrd = tl.setNxtPrdNew(mnth, year);
       return nextPrd;
  }

  void dispErrorMsg(String msg){
    dlgError errPass = new  dlgError(this,"", true);
    errPass.setMessage(msg);
    centerDialog  cDlg = new centerDialog();
    cDlg.centerDialog(errPass, 350, 140);
  }

  void btnReset_actionPerformed(ActionEvent e) {
      Reset();
  }

  void Reset(){
    chcPrd.requestFocus();
    txtThn.setText("");

  }

  void txtUserName_keyPressed(KeyEvent e) {
    if(e.getKeyCode()==KeyEvent.VK_ENTER){
      txtThn.requestFocus();
    }
  }

  void txtThn_keyPressed(KeyEvent e) {
     if(e.getKeyCode()==KeyEvent.VK_ENTER){
      String thn = txtThn.getText().trim();
      if(thn.equals("")){
         dispErrorMsg("Invalid Input...!!!");
      }else{
        btnSave.requestFocus();
       }
    }
  }

  void btnSave_keyPressed(KeyEvent e) {
    if(e.getKeyCode()==KeyEvent.VK_ENTER){
      saveAction();
    }else if(e.getKeyCode()==KeyEvent.VK_RIGHT){
      btnCancel.requestFocus();
    }
  }

  void btnCancel_keyPressed(KeyEvent e) {
     if(e.getKeyCode()==KeyEvent.VK_ENTER){
      this.dispose();
    }else if(e.getKeyCode()==KeyEvent.VK_RIGHT){
      btnReset.requestFocus();
    }else if(e.getKeyCode()==KeyEvent.VK_LEFT){
      btnSave.requestFocus();
    }
  }

  void btnCancel_actionPerformed(ActionEvent e) {
    this.dispose();
  }

  void btnReset_keyPressed(KeyEvent e) {
     if(e.getKeyCode()==KeyEvent.VK_ENTER){
      Reset();
    }else if(e.getKeyCode()==KeyEvent.VK_LEFT){
      btnCancel.requestFocus();
    }
  }

  void this_windowClosing(WindowEvent e) {
    if(e.getID()==WindowEvent.WINDOW_CLOSING){
      this.dispose();
    }
  }

  void chcPrd_itemStateChanged(ItemEvent e) {
     String  prd = (String)e.getItem();
     chcPrd.select(prd);
  }

  void chcPrd_keyPressed(KeyEvent e) {
    if(e.getKeyCode()==KeyEvent.VK_ENTER){
        chcPrd.getSelectedItem();
        txtThn.requestFocus();
    }
  }
}
