package kr.co.hanbitbook.ejb.examples.mall;

import javax.ejb.*;

abstract public class InterestitemEJB implements EntityBean {
  EntityContext entityContext;
  public java.lang.String ejbCreate(java.lang.String itemid) throws CreateException {
    setItemid(itemid);
    return null;
  }
  public java.lang.String ejbCreate(java.lang.String itemid, java.lang.String itemname) throws CreateException  {
    setItemid(itemid);
    setItemname(itemname);
    return null;
  }
  public void ejbPostCreate(java.lang.String itemid) throws CreateException {
  }
  public void ejbPostCreate(java.lang.String itemid, java.lang.String itemname) throws CreateException  {
  }
  public void ejbRemove() throws RemoveException {
  }
  public abstract void setItemid(java.lang.String itemid);
  public abstract void setItemname(java.lang.String itemname);
  public abstract void setCustomer(java.util.Collection customer);
  public abstract java.lang.String getItemid();
  public abstract java.lang.String getItemname();
  public abstract java.util.Collection getCustomer();
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