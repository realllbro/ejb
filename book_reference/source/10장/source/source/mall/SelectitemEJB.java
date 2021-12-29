package kr.co.hanbitbook.ejb.examples.mall;

import javax.ejb.*;

abstract public class SelectitemEJB implements EntityBean {
  EntityContext entityContext;
  public java.lang.String ejbCreate(java.lang.String id) throws CreateException {
    setId(id);
    return null;
  }
  public java.lang.String ejbCreateMethod1(java.lang.String id, java.lang.String itemid, java.lang.String itemname) throws CreateException  {
    setId(id);
    setItemid(itemid);
    setItemname(itemname);
    return null;
  }
  public void ejbPostCreate(java.lang.String id) throws CreateException {
  }
  public void ejbPostCreateMethod1(java.lang.String id, java.lang.String itemid, java.lang.String itemname) throws CreateException  {
  }
  public void ejbRemove() throws RemoveException {
  }
  public abstract void setId(java.lang.String id);
  public abstract void setItemid(java.lang.String itemid);
  public abstract void setItemname(java.lang.String itemname);
  public abstract void setCustomer(kr.co.hanbitbook.ejb.examples.mall.Customer customer);
  public abstract java.lang.String getId();
  public abstract java.lang.String getItemid();
  public abstract java.lang.String getItemname();
  public abstract kr.co.hanbitbook.ejb.examples.mall.Customer getCustomer();
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