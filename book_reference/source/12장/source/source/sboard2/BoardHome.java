package kr.co.hanbitbook.ejb.examples.board;

import javax.ejb.*;
import java.util.*;
import java.math.*;

public interface BoardHome extends javax.ejb.EJBLocalHome {
  public Board create(java.lang.String name, java.lang.String passwd, java.lang.String title, java.lang.String content, java.sql.Timestamp regdate, int readcount) throws CreateException;
  public Board findByPrimaryKey(Integer seq) throws FinderException;
}