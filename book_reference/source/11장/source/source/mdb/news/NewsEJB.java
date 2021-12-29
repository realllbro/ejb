package kr.co.hanbitbook.ejb.examples.news;

import javax.ejb.*;

abstract public class NewsEJB implements EntityBean {
  EntityContext entityContext;
  public java.lang.String ejbCreate(java.lang.String seq, java.lang.String name, java.lang.String title, java.lang.String content) throws CreateException {
	setSeq(seq);
	setName(name);
	setTitle(title);
	setContent(content);
    return null;
  }
  public void ejbPostCreate(java.lang.String seq, java.lang.String name, java.lang.String title, java.lang.String content) throws CreateException {
  }
  public void ejbRemove() throws RemoveException {
  }
  public abstract void setSeq(java.lang.String seq);
  public abstract void setName(java.lang.String name);
  public abstract void setTitle(java.lang.String title);
  public abstract void setContent(java.lang.String content);
  public abstract java.lang.String getSeq();
  public abstract java.lang.String getName();
  public abstract java.lang.String getTitle();
  public abstract java.lang.String getContent();
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