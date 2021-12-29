package kr.co.hanbitbook.ejb.examples.news;

import javax.ejb.*;
import java.util.*;
import java.rmi.*;
import java.math.*;

public interface News extends javax.ejb.EJBObject {
  public String getSeq() throws RemoteException;
  public void setName(String name) throws RemoteException;
  public String getName() throws RemoteException;
  public void setTitle(String title) throws RemoteException;
  public String getTitle() throws RemoteException;
  public void setContent(String content) throws RemoteException;
  public String getContent() throws RemoteException;
}