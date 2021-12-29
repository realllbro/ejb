package kr.co.hanbitbook.ejb.examples.student2;

import javax.ejb.*;
import java.util.*;
import java.rmi.*;
import java.math.*;

public interface Student2 extends javax.ejb.EJBObject {
  public void setName(String name) throws RemoteException;
  public String getName() throws RemoteException;
  public Integer getSsn() throws RemoteException;
  public void setGrade(BigDecimal grade) throws RemoteException;
  public BigDecimal getGrade() throws RemoteException;
}