package kr.co.hanbitbook.ejb.examples.board;

import javax.ejb.*;
import java.sql.*;
import javax.sql.*;
import javax.naming.*;
import java.io.*;
import javax.rmi.*;

public class BoardManagerBean implements SessionBean {
  SessionContext sessionContext;
  DataSource ds;
  Context initial;
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
      initial = new InitialContext();
      Object obj = initial.lookup("ora9");
      ds = (DataSource)obj;
    }catch(Exception ex){
      System.out.println(ex.toString());
    }
  }

  public void addBoardData(kr.co.hanbitbook.ejb.examples.board.BoardData boardData) {
    try{
      Object obj = initial.lookup("Board");
      BoardHome home = (BoardHome)PortableRemoteObject.narrow(obj, BoardHome.class);
      Board board = home.create(boardData.getName(), boardData.getPasswd(), boardData.getTitle(), boardData.getContent(), boardData.getCurrentTimestamp(), boardData.getReadcount());
    }catch(Exception ex){
      System.out.println("addBoardData exception :" + ex.toString());
    }
  }
  public void deleteBoardData(int seq) {
    try{
      Object obj = initial.lookup("Board");
      BoardHome home = (BoardHome)PortableRemoteObject.narrow(obj, BoardHome.class);
      home.remove(new Integer(seq));
    }catch(Exception ex){
      System.out.println("deleteBoardData exception :" + ex.toString());
    }
  }
  public void updateBoardData(kr.co.hanbitbook.ejb.examples.board.BoardData boardData) {
    try{
      Object obj = initial.lookup("Board");
      BoardHome home = (BoardHome)PortableRemoteObject.narrow(obj, BoardHome.class);
      Board board = home.findByPrimaryKey(new Integer(boardData.getSeq()));
      board.setName(boardData.getName());
      board.setTitle(boardData.getTitle());
      board.setContent(boardData.getContent());
    }catch(Exception ex){
      System.out.println("updateBoardData exception :" + ex.toString());
    }
  }

  public kr.co.hanbitbook.ejb.examples.board.BoardData getBoardData(int seq) {
    BoardData bd = new BoardData();
    try{
      Object obj = initial.lookup("Board");
      BoardHome home = (BoardHome)PortableRemoteObject.narrow(obj, BoardHome.class);
      Board board = home.findByPrimaryKey(new Integer(seq));
      bd.setSeq(board.getSeq().intValue());
      bd.setName(board.getName());
      bd.setPasswd(board.getPasswd());
      bd.setTitle(board.getTitle());
      bd.setContent(board.getContent());
      bd.setRegdate(board.getRegdate());
      bd.setReadcount(board.getReadcount());
    }catch(Exception ex){
      System.out.println("getBoardData exception :" + ex.toString());
    }
    return bd;
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
      ps = con.prepareStatement("select seq from (select seq, name, passwd, title, content, regdate, readcount, rownum r from board order by seq desc) where r >= ? and r <= ?");
      ps.setInt(1, startnum);
      ps.setInt(2, endnum);
      rs = ps.executeQuery();
      while(rs.next()){
        BoardData bd = getBoardData(rs.getInt(1));
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