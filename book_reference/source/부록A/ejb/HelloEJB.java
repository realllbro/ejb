
package net.sarang.sunny.ejb.exam;
import java.rmi.RemoteException;

import javax.ejb.*;


public class HelloEJB implements SessionBean {
	public String hello(Message msg){
		return "hello" + msg.getMessage();
	}
	
	public HelloEJB(){}
	
	public void ejbCreate() throws CreateException{}

	public void setSessionContext(SessionContext arg0)
		throws EJBException, RemoteException {
	}

	public void ejbRemove() throws EJBException, RemoteException {
	}

	public void ejbActivate() throws EJBException, RemoteException {
	}

	public void ejbPassivate() throws EJBException, RemoteException {
	}

}
