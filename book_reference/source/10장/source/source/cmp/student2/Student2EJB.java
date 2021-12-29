package kr.co.hanbitbook.ejb.examples.student2;

import javax.ejb.*;

abstract public class Student2EJB implements EntityBean {
  EntityContext entityContext;
  public java.lang.Integer ejbCreate(java.lang.Integer ssn) throws CreateException {
    setSsn(ssn);
    return null;
  }
  public void ejbPostCreate(java.lang.Integer ssn) throws CreateException {
  }
  public void ejbRemove() throws RemoveException {
  }
  public abstract void setName(java.lang.String name);
  public abstract void setSsn(java.lang.Integer ssn);
  public abstract void setGrade(java.math.BigDecimal grade);
  public abstract java.lang.String getName();
  public abstract java.lang.Integer getSsn();
  public abstract java.math.BigDecimal getGrade();
  public void ejbLoad() {
  }
  public void ejbStore() {
  }
  public void ejbActivate() {
  }
  public void ejbPassivate() {
  }
  public void unsetEntityContext() {
    this.entityContext = null;
  }
  public void setEntityContext(EntityContext entityContext) {
    this.entityContext = entityContext;
  }
}