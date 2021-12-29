package student;

import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import javax.ejb.DuplicateKeyException;
import javax.ejb.EntityContext;
import javax.ejb.EntityBean;
import javax.ejb.EJBException;
import javax.ejb.NoSuchEntityException;
import javax.ejb.ObjectNotFoundException;
import javax.ejb.RemoveException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class StudentBean implements EntityBean {

  private EntityContext ctx;
  private DataSource dataSource;
  private String tableName = "studenttable";

  private String name;
  private Integer ssn; // primary key
  private int grade;


  public void setEntityContext(EntityContext c) {

    ctx = c;

    try {
      Context envCtx = new InitialContext();

      dataSource = (DataSource) envCtx.lookup("ora9");
    } catch (NamingException ne) {
      throw new EJBException(ne);
    }

  }

  public void unsetEntityContext() {
    ctx = null;
  }


  public Integer ejbCreate(String name, int ssn, int grade)
  {

    this.name      = name;
    this.ssn       = new Integer(ssn);
    this.grade     = grade;

    Connection       con = null;
    PreparedStatement ps = null;

    try {

      con = dataSource.getConnection();

      ps = con.prepareStatement("insert into "+tableName+
        " (name, ssn, grade) values (?,?,?)");

      ps.setString(1, name);
      ps.setInt(2, ssn);
      ps.setInt(3, grade);

      ps.executeUpdate();

      return this.ssn;

    } catch (SQLException sqe) {


      try {
        ejbFindByPrimaryKey(this.ssn);

        throw new DuplicateKeyException("A student with social "+
          "security number: "+ssn+" already exists.");
      } catch (Exception Ignore) {}

      sqe.printStackTrace();

      throw new EJBException (sqe);

    } finally {
      try {
        if (ps != null) ps.close();
        if (con != null) con.close();
      } catch (Exception ignore) {}
    }


  }

  public void ejbPostCreate(String name, int ssn, int grade) {}

  public void ejbRemove() 
    throws RemoveException
  {

    Connection con = null;
    PreparedStatement ps = null;

    try {
      con = dataSource.getConnection();
      ps  = con.prepareStatement("delete from "+tableName+
        " where ssn=?");
      ps.setInt(1, ssn.intValue());

      if (ps.executeUpdate() < 1) {
        throw new RemoveException ("Error removing Student with ssn: "+ssn);
      }
    } catch (SQLException sqe) {
      throw new EJBException (sqe);
    } finally {
      try {
        if(ps != null) ps.close();
        if(con != null) con.close();
      } catch (Exception ignore) {}
    }
  }


  public void ejbLoad() {

    ssn = (Integer) ctx.getPrimaryKey();

    Connection con       = null;
    PreparedStatement ps = null;
    ResultSet rs         = null;

    try {
      con = dataSource.getConnection();
      ps  = con.prepareStatement("select name, grade from "
        +tableName+ " where ssn=?");
      ps.setInt(1, ssn.intValue());
      ps.executeQuery();
      rs = ps.getResultSet();

      if (rs.next()) {
        name  = rs.getString(1);
        grade = rs.getInt(2);

      } else {
        throw new NoSuchEntityException("Student with social "+
          "security number: "+ssn+" no longer exists.");
      }

    } catch (SQLException sqe) {
      throw new EJBException(sqe);
    } finally {
      try {
        if (rs != null) rs.close();
        if (ps != null) ps.close();
        if (con != null) con.close();
      } catch (Exception ignore) {}
    }
  }


  public void ejbStore() {

    Connection con       = null;
    PreparedStatement ps = null;

    try {
      con = dataSource.getConnection();
      ps  = con.prepareStatement("update "+tableName+
        " SET name=?, grade=? " +
        " where ssn=?");

      ps.setString(1, name);
      ps.setInt(2, grade);
      ps.setInt(3, ssn.intValue());

      ps.executeUpdate();

    } catch (SQLException sqe) {
      throw new EJBException(sqe);
    } finally {
      try {
        if (ps != null) ps.close();
        if (con != null) con.close();
      } catch (Exception ignore) {}
    }
  }

  public void ejbActivate() {}
  public void ejbPassivate() {}

  public Integer ejbFindByPrimaryKey(Integer pk) 
    throws ObjectNotFoundException
  {

    Connection con       = null;
    PreparedStatement ps = null;
    ResultSet rs         = null;

    try {
      con = dataSource.getConnection();
      ps  = con.prepareStatement("select ssn from "+tableName+
        " where ssn=?");
      ps.setInt(1, pk.intValue());
      ps.executeQuery();

      rs = ps.getResultSet();

      if (rs.next()) {
        return pk;
      } else {
        throw new ObjectNotFoundException ("Student with social "+
          "security number: "+ssn+" no longer exists.");
      }
    } catch (SQLException sqe) {
      throw new EJBException (sqe);
    } finally {
      try {
        if(rs != null) rs.close();
        if(ps != null) ps.close();
        if(con != null) con.close();
      } catch (Exception ignore) {}
    }

  }


  public Collection ejbFindStudentsInGrade(int gradeValue) {

    Connection con       = null;
    PreparedStatement ps = null;
    ResultSet rs         = null;

    ArrayList keys = new ArrayList();

    try {
      con = dataSource.getConnection();
      ps  = con.prepareStatement("select ssn from "+tableName+
        " where grade=?");
      ps.setInt(1, gradeValue);
      ps.executeQuery();

      rs = ps.getResultSet();

      while (rs.next()) {
        keys.add(new Integer(rs.getInt(1)));
      }

      return keys;

    } catch (SQLException sqe) {
      throw new EJBException (sqe);
    } finally {
      try {
        if(rs != null) rs.close();
        if(ps != null) ps.close();
        if(con != null) con.close();
      } catch (Exception ignore) {}
    }


  }


  public String getName() { return name; }

  public Integer getSsn() { return ssn; }

  public int getGrade() { return grade; }

  public void setGrade(int grade) { this.grade = grade; }


}


