package kr.co.hanbitbook.ejb.examples.mall;

import javax.ejb.*;

abstract public class CustomerEJB implements EntityBean {
  EntityContext entityContext;
  public java.lang.String ejbCreate(java.lang.String id) throws CreateException {
    setId(id);
    return null;
  }
  public java.lang.String ejbCreate(java.lang.String id, java.lang.String password, java.lang.String name) throws CreateException  {
    setId(id);
    setPassword(password);
    setName(name);
    return null;
  }
  public void ejbPostCreate(java.lang.String id) throws CreateException {
  }
  public void ejbPostCreate(java.lang.String id, java.lang.String password, java.lang.String name) throws CreateException  {
  }
  public void ejbRemove() throws RemoveException {
  }
  public abstract void setId(java.lang.String id);
  public abstract void setPassword(java.lang.String password);
  public abstract void setName(java.lang.String name);
  public abstract void setSelectitem(java.util.Collection selectitem);
  public abstract void setMoreinfo(kr.co.hanbitbook.ejb.examples.mall.Moreinfo moreinfo);
  public abstract void setInterestitem(java.util.Collection interestitem);
  public abstract java.lang.String getId();
  public abstract java.lang.String getPassword();
  public abstract java.lang.String getName();
  public abstract java.util.Collection getSelectitem();
  public abstract kr.co.hanbitbook.ejb.examples.mall.Moreinfo getMoreinfo();
  public abstract java.util.Collection getInterestitem();
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