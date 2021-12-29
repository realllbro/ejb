package kr.co.hanbitbook.ejb.examples.student2;

import javax.ejb.*;
import java.util.*;
import java.rmi.*;
import java.math.*;

public interface Student2Home extends javax.ejb.EJBHome {
  public Student2 create(Integer ssn) throws CreateException, RemoteException;
  public Collection findStudentsInGrade(int gradeValue) throws FinderException, RemoteException;
  public Collection findStudentsInGrade(int startGradeValue, int endGradeValue) throws FinderException, RemoteException;
  public Collection findName(String name) throws FinderException, RemoteException;
  public Student2 findByPrimaryKey(Integer ssn) throws FinderException, RemoteException;
}