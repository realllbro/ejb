package kr.co.hanbitbook.ejb.examples.video;
import javax.ejb.*;

public abstract class VideoEJB implements EntityBean {
	private EntityContext  context;
	
	public abstract String getIsbn();
	public abstract String getName();
	public abstract int getPrice();

	public abstract void setIsbn(String isbn);
	public abstract void setName(String name);
	public abstract void setPrice(int price);
	
	public String ejbCreate(String isbn, String name, int price) throws CreateException { 
		setIsbn(isbn);
		setName(name);
		setPrice(price);
	
		return null;
	}
	
	public void ejbPostCreate(String isbn, String name, int price) throws CreateException { }	
		
	public void setEntityContext(EntityContext ctxt) {
		context = ctxt;	
	}
	
	public void unsetEntityContext() {
		context = null;	
	}
	
	public void ejbRemove() { }
	public void ejbLoad() { }
	public void ejbStore() { }
	public void ejbPassivate() { }
	public void ejbActivate() { }
}