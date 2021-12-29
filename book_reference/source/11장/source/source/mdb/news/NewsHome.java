package kr.co.hanbitbook.ejb.examples.news;

import javax.ejb.*;
import java.util.*;
import java.rmi.*;
import java.math.*;

public interface NewsHome extends javax.ejb.EJBHome {
  public News create(java.lang.String seq, java.lang.String name, java.lang.String title, java.lang.String content) throws CreateException, RemoteException;
  public News findByPrimaryKey(java.lang.String seq) throws FinderException, RemoteException;
}