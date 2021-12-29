package kr.co.hanbitbook.ejb.examples.board;

import javax.ejb.*;
import java.sql.*;
import javax.sql.*;
import javax.naming.*;
import java.io.*;
import javax.rmi.*;

public class BoardBean implements EntityBean {
  EntityContext entityContext;
  java.lang.Integer seq;
  java.lang.String name;
  java.lang.String passwd;
  java.lang.String title;
  java.lang.String content;
  java.sql.Timestamp regdate;
  int readcount;
  DataSource ds;
  Context initial;

  public void ejbRemove() throws RemoveException {
    Connection con = null;
    PreparedStatement ps = null;
    try{
      con = ds.getConnection();
      ps = con.prepareStatement("delete from board where seq = ?");
      ps.setInt(1, seq.intValue());
      ps.executeUpdate();
    }catch(Exception ex){
      System.out.println("ejbRemove()메소드 호출시 오류 :" + ex.toString());
    }finally{
      if(ps != null){
        try{ ps.close(); }catch(Exception ex2){}
      }
      if(con != null){
        try{ con.close(); }catch(Exception ex2){}
      }
    } // finally
  }

  public void setSeq(java.lang.Integer seq) {
    this.seq = seq;
  }
  public void setName(java.lang.String name) {
    this.name = name;
  }
  public void setPasswd(java.lang.String passwd) {
    this.passwd = passwd;
  }
  public void setTitle(java.lang.String title) {
    this.title = title;
  }
  public void setContent(java.lang.String content) {
    this.content = content;
  }
  public void setRegdate(java.sql.Timestamp regdate) {
    this.regdate = regdate;
  }
  public void setReadcount(int readcount) {
    this.readcount = readcount;
  }
  public java.lang.Integer getSeq() {
    return seq;
  }
  public java.lang.String getName() {
    return name;
  }
  public java.lang.String getPasswd() {
    return passwd;
  }
  public java.lang.String getTitle() {
    return title;
  }
  public java.lang.String getContent() {
    return content;
  }
  public java.sql.Timestamp getRegdate() {
    return regdate;
  }
  public int getReadcount() {
    return readcount;
  }

  public void ejbLoad() {
    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    try{
      con = ds.getConnection();
      ps = con.prepareStatement("select seq, name, passwd, title, content, regdate, readcount from board where seq = ?");
      ps.setInt(1, seq.intValue());
      rs = ps.executeQuery();
      if(rs.next()){
        int seq = rs.getInt(1);
        String name = rs.getString(2);
        String passwd = rs.getString(3);
        String title = rs.getString(4);
        java.sql.Clob clob_content = rs.getClob(5);
        java.sql.Timestamp regdate = rs.getTimestamp(6);

        int readcount = rs.getInt(7);
        Reader reader = clob_content.getCharacterStream();
        StringWriter writer = new StringWriter();
        char[] buffer = new char[512];
        int readCount = 0;
        while ( (readCount = reader.read(buffer)) != -1) {
          writer.write(buffer, 0, readCount);
        }
        String content = writer.toString();
        this.seq = new Integer(seq);
        this.name = name;
        this.passwd = passwd;
        this.title = title;
        this.content = content;
        this.regdate = regdate;
        this.readcount = readcount;
      }
    }catch(Exception ex){
      System.out.println("ejbLoad()메소드 호출시 오류 :" + ex.toString());
    }finally{
      if(rs != null){
        try{ rs.close(); }catch(Exception ex2){}
      }
      if(ps != null){
        try{ ps.close(); }catch(Exception ex2){}
      }
      if(con != null){
        try{ con.close(); }catch(Exception ex2){}
      }
    } // finally
  }

  public void ejbStore() {
    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    try{
      con = ds.getConnection();
      ps = con.prepareStatement("update board set name = ?, title = ?, content = empty_clob() where seq = ?");
      ps.setString(1, name);
      ps.setString(2, title);
      ps.setInt(3, seq.intValue());
      ps.executeUpdate();
      ps.close();

      ps = con.prepareStatement("select content from board where seq = ? for update");
      ps.setInt(1, seq.intValue());
      rs = ps.executeQuery();
      rs.next();
      java.sql.Clob clob_content = rs.getClob(1);
      Writer writer = ((weblogic.jdbc.common.OracleClob)clob_content).getCharacterOutputStream();
      StringReader sr = new StringReader(content);
      char[] buffer = new char[512];
      int readCount = 0;
      while((readCount = sr.read(buffer)) != -1){
        writer.write(buffer, 0, readCount);
      }
      sr.close();
      writer.close();
    }catch(Exception ex){
      System.out.println("ejbStore()메소드 호출시 오류 :" + ex.toString());
    }finally{
      if(rs != null){
        try{ rs.close(); }catch(Exception ex2){}
      }
      if(ps != null){
        try{ ps.close(); }catch(Exception ex2){}
      }
      if(con != null){
        try{ con.close(); }catch(Exception ex2){}
      }
    } // finally
  }

  public void ejbActivate() {
    try{
      initial = new InitialContext();
      Object obj = initial.lookup("ora9");
      ds = (DataSource)obj;
    }catch(Exception ex){
      System.out.println(ex.toString());
    }
  }

  public void ejbPassivate() {
    initial = null;
    ds = null;
  }

  public void unsetEntityContext() {
    this.entityContext = null;
  }
  public java.lang.Integer ejbCreate(java.lang.String name, java.lang.String passwd, java.lang.String title, java.lang.String content, java.sql.Timestamp regdate, int readcount) throws CreateException  {
    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    int iseq = 0;
    try{
      con = ds.getConnection();
      ps = con.prepareStatement("insert into board values ( board_seq.NEXTVAL, ?, ?, ?, empty_clob() , ?, 0)");
      ps.setString(1, name);
      ps.setString(2, passwd);
      ps.setString(3, title);
      ps.setTimestamp(4, regdate);
      ps.executeUpdate();
      ps.close();

      ps = con.prepareStatement("select board_seq.CURRVAL from dual");
      rs = ps.executeQuery();
      rs.next();
      iseq = rs.getInt(1);
      ps = con.prepareStatement("select content from board where seq = ? for update");
      ps.setInt(1, iseq);
      rs = ps.executeQuery();
      rs.next();
      java.sql.Clob clob_content = rs.getClob(1);
      Writer writer = ((weblogic.jdbc.common.OracleClob)clob_content).getCharacterOutputStream();
      StringReader sr = new StringReader(content);
      char[] buffer = new char[512];
      int readCount = 0;
      while((readCount = sr.read(buffer)) != -1){
        writer.write(buffer, 0, readCount);
      }
      sr.close();
      writer.close();
    }catch(Exception ex){
      System.out.println("ejbCreate()메소드 호출시 오류 :" + ex.toString());
    }finally{
      if(rs != null){
        try{ rs.close(); }catch(Exception ex2){}
      }
      if(ps != null){
        try{ ps.close(); }catch(Exception ex2){}
      }
      if(con != null){
        try{ con.close(); }catch(Exception ex2){}
      }
    } // finally
    setSeq(new Integer(iseq));
    setName(name);
    setPasswd(passwd);
    setTitle(title);
    setContent(content);
    setRegdate(regdate);
    setReadcount(0);
    return new Integer(iseq);
  }

  public void ejbPostCreate(java.lang.String name, java.lang.String passwd, java.lang.String title, java.lang.String content, java.sql.Timestamp regdate, int readcount) throws CreateException  {
  }

  public java.lang.Integer ejbFindByPrimaryKey(java.lang.Integer seq) throws FinderException  {
    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    int iseq = 0;
    try {
      con = ds.getConnection();
      ps = con.prepareStatement("select seq from board where seq = ?");
      ps.setInt(1, seq.intValue());
      rs = ps.executeQuery();
      rs.next();
      iseq = rs.getInt(1);
    }catch(Exception ex){
      System.out.println("ejbFindByPrimaryKey()메소드 호출시 오류 :" + ex.toString());
      return null;
    }finally{
      if(rs != null){
        try{ rs.close(); }catch(Exception ex2){}
      }
      if(ps != null){
        try{ ps.close(); }catch(Exception ex2){}
      }
      if(con != null){
        try{ con.close(); }catch(Exception ex2){}
      }
    } // finally
    return new Integer(iseq);
  }

  public void setEntityContext(EntityContext entityContext) {
    this.entityContext = entityContext;
    try{
      initial = new InitialContext();
      Object obj = initial.lookup("ora9");
      ds = (DataSource)obj;
    }catch(Exception ex){
      System.out.println(ex.toString());
    }
  }
}