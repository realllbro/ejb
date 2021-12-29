package kr.co.hanbitbook.ejb.examples.board;

import javax.ejb.*;
import java.sql.*;
import javax.sql.*;
import javax.naming.*;
import java.io.*;

public class BoardManagerBean implements SessionBean {
  SessionContext sessionContext;
  DataSource ds;
  public void ejbCreate() throws CreateException {
  }
  public void ejbRemove() {
  }
  public void ejbActivate() {
  }
  public void ejbPassivate() {
  }
  public void setSessionContext(SessionContext sessionContext) {
    this.sessionContext = sessionContext;
    try{
      Context initial = new InitialContext();
      Object obj = initial.lookup("ora9");
      ds = (DataSource)obj;
    }catch(Exception ex){
      System.out.println(ex.toString());
    }
  }

  public void addBoardData(kr.co.hanbitbook.ejb.examples.board.BoardData boardData) {
    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    try{
      con = ds.getConnection();
      ps = con.prepareStatement("insert into board values ( board_seq.NEXTVAL, ?, ?, ?, empty_clob() , ?, 0)");
      ps.setString(1, boardData.getName());
      ps.setString(2, boardData.getPasswd());
      ps.setString(3, boardData.getTitle());
      ps.setTimestamp(4, boardData.getCurrentTimestamp());
      ps.executeUpdate();
      ps.close();

      ps = con.prepareStatement("select board_seq.CURRVAL from dual");
      rs = ps.executeQuery();
      rs.next();
      int iseq = rs.getInt(1);
      ps = con.prepareStatement("select content from board where seq = ? for update");
      ps.setInt(1, iseq);
      rs = ps.executeQuery();
      rs.next();
      java.sql.Clob clob_content = rs.getClob(1);
      Writer writer = ((weblogic.jdbc.common.OracleClob)clob_content).getCharacterOutputStream();
      StringReader sr = new StringReader(boardData.getContent());
      char[] buffer = new char[512];
      int readCount = 0;
      while((readCount = sr.read(buffer)) != -1){
        writer.write(buffer, 0, readCount);
      }
      sr.close();
      writer.close();
    }catch(Exception ex){
      System.out.println("addBoardData()메소드 호출시 오류 :" + ex.toString());
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
  public void deleteBoardData(int seq) {
    Connection con = null;
    PreparedStatement ps = null;
    try{
      con = ds.getConnection();
      ps = con.prepareStatement("delete from board where seq = ?");
      ps.setInt(1, seq);
      ps.executeUpdate();
    }catch(Exception ex){
      System.out.println("deleteBoardData()메소드 호출시 오류 :" + ex.toString());
    }finally{
      if(ps != null){
        try{ ps.close(); }catch(Exception ex2){}
      }
      if(con != null){
        try{ con.close(); }catch(Exception ex2){}
      }
    } // finally
  }
  public void updateBoardData(kr.co.hanbitbook.ejb.examples.board.BoardData boardData) {
    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    try{
      con = ds.getConnection();
      ps = con.prepareStatement("update board set name = ?, title = ?, content = empty_clob() where seq = ?");
      ps.setString(1, boardData.getName());
      ps.setString(2, boardData.getTitle());
      ps.setInt(3, boardData.getSeq());
      ps.executeUpdate();
      ps.close();

      ps = con.prepareStatement("select content from board where seq = ? for update");
      ps.setInt(1, boardData.getSeq());
      rs = ps.executeQuery();
      rs.next();
      java.sql.Clob clob_content = rs.getClob(1);
      Writer writer = ((weblogic.jdbc.common.OracleClob)clob_content).getCharacterOutputStream();
      StringReader sr = new StringReader(boardData.getContent());
      char[] buffer = new char[512];
      int readCount = 0;
      while((readCount = sr.read(buffer)) != -1){
        writer.write(buffer, 0, readCount);
      }
      sr.close();
      writer.close();
    }catch(Exception ex){
      System.out.println("updateBoardData()메소드 호출시 오류 :" + ex.toString());
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

  public kr.co.hanbitbook.ejb.examples.board.BoardData getBoardData(int seq) {
    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    BoardData bd = null;
    try{
      con = ds.getConnection();
      ps = con.prepareStatement("select seq, name, passwd, title, content, regdate, readcount from board where seq = ?");
      ps.setInt(1, seq);
      rs = ps.executeQuery();
      if(rs.next()){
        bd = makeBoardData(rs);
        return bd;
      }
    }catch(Exception ex){
      System.out.println("getBoardData()메소드 호출시 오류 :" + ex.toString());
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
    return bd;
  }

  private BoardData makeBoardData(ResultSet rs){
    BoardData bd = new BoardData();
    try{
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
      bd = new BoardData();
      bd.setSeq(seq);
      bd.setName(name);
      bd.setPasswd(passwd);
      bd.setTitle(title);
      bd.setContent(content);
      bd.setRegdate(regdate);
      bd.setReadcount(readcount);
      return bd;
    }catch(Exception ex){
      System.out.println("makeBoardData() error :" + ex.toString());
    }
    return null;
  }

  public java.util.Collection getBoardDataList(int page) {
    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    java.util.Collection list = new java.util.ArrayList();
    int startnum = page * 10 - 9;
    int endnum = page * 10;
    try{
      con = ds.getConnection();
      ps = con.prepareStatement("select seq, name, passwd, title, content, regdate, readcount, r from (select seq, name, passwd, title, content, regdate, readcount, rownum r from board order by seq desc) where r >= ? and r <= ?");
      ps.setInt(1, startnum);
      ps.setInt(2, endnum);
      rs = ps.executeQuery();
      while(rs.next()){
        BoardData bd = makeBoardData(rs);
        list.add(bd);
      }
      return list;
    }catch(Exception ex){
      System.out.println("getBoardDataList()메소드 호출시 오류 :" + ex.toString());
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
    return list;
  }
  public int getBoardDataCount() {
    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    int count = 0;
    try {
      con = ds.getConnection();
      ps = con.prepareStatement("select count(*) from board");
      rs = ps.executeQuery();
      rs.next();
      count = rs.getInt(1);
    }catch(Exception ex){
      System.out.println("getBoardDataCount()메소드 호출시 오류 :" + ex.toString());
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
    return count;
  }
  public boolean isWriter(int seq, java.lang.String passwd) {
    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    int count = 0;
    try {
      con = ds.getConnection();
      ps = con.prepareStatement("select count(*) from board where seq = ? and passwd = ?");
      ps.setInt(1, seq);
      ps.setString(2, passwd);
      rs = ps.executeQuery();
      rs.next();
      count = rs.getInt(1);
      if(count == 0)
        return false;
    }catch(Exception ex){
      System.out.println("isWriter()메소드 호출시 오류 :" + ex.toString());
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
    return true;
  }
  public boolean isNextPage(int page) {
    if(page < getPageCount())
      return true;
    return false;
  }
  public boolean isPrevPage(int page) {
    if(page > getPageCount())
      return false;
    else if (page >= 2)
      return true;
    return false;
  }
  public int getPageCount() {
    int count = getBoardDataCount();
    int ipage = count / 10;
    int m = count % 10;
    if(m > 0)
      ipage ++;
    return ipage;
  }
  public void updateReadCount(int seq) {
    Connection con = null;
    PreparedStatement ps = null;
    int count = 0;
    try {
      con = ds.getConnection();
      ps = con.prepareStatement("update board set readcount = readcount + 1 where seq = ?");
      ps.setInt(1, seq);
      ps.executeUpdate();
    }catch(Exception ex){
      System.out.println("updateReadCount()메소드 호출시 오류 :" + ex.toString());
    }finally{
      if(ps != null){
        try{ ps.close(); }catch(Exception ex2){}
      }
      if(con != null){
        try{ con.close(); }catch(Exception ex2){}
      }
    } // finally
  }
}