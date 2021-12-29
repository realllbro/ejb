package student;

import java.rmi.RemoteException;
import java.util.Collection;

import javax.ejb.CreateException;
import javax.ejb.FinderException;
import javax.ejb.EJBHome;


public interface StudentHome extends EJBHome {

  // create method

  public Student create(String name, int ssn, int grade)
    throws CreateException, RemoteException;

  // finders

  public Student findByPrimaryKey(Integer ssn)
    throws FinderException, RemoteException;

  public Collection findStudentsInGrade(int grade)
    throws FinderException, RemoteException;

}
