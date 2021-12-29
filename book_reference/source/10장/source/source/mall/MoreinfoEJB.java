package kr.co.hanbitbook.ejb.examples.mall;

import javax.ejb.*;

abstract public class MoreinfoEJB implements EntityBean {
  EntityContext entityContext;
  public java.lang.String ejbCreate(java.lang.String id) throws CreateException {
    setId(id);
    return null;
  }
  public java.lang.String ejbCreate(java.lang.String id, int point) throws CreateException {
    setId(id);
    setPoint(point);
    return null;
  }
  public void ejbPostCreate(java.lang.String id) throws CreateException {
  }
  public void ejbPostCreate(java.lang.String id, int point) throws CreateException {
  }
  public void ejbRemove() throws RemoveException {
  }
  public abstract void setId(java.lang.String id);
  public abstract void setPoint(int point);
  public abstract void setCustomer(kr.co.hanbitbook.ejb.examples.mall.Customer customer);
  public abstract java.lang.String getId();
  public abstract int getPoint();
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