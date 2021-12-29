package student;

import java.rmi.RemoteException;

import javax.ejb.EJBObject;


public interface Student extends EJBObject {

  public String getName() throws RemoteException;

  public Integer getSsn() throws RemoteException;

  public int getGrade() throws RemoteException;

  public void setGrade(int grade) throws RemoteException;


}

