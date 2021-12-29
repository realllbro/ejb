package kr.co.hanbitbook.ejb.examples.board;

import javax.ejb.*;
import java.util.*;
import java.math.*;
import java.sql.*;

public interface Board extends javax.ejb.EJBLocalObject {
  public Integer getSeq();
  public void setName(String name);
  public String getName();
  public void setPasswd(String passwd);
  public String getPasswd();
  public void setTitle(String title);
  public String getTitle();
  public void setContent(String content);
  public String getContent();
  public void setRegdate(Timestamp regdate);
  public Timestamp getRegdate();
  public void setReadcount(int readcount);
  public int getReadcount();
}