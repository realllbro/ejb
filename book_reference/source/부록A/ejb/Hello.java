
package net.sarang.sunny.ejb.exam;
import javax.ejb.EJBObject;
import java.rmi.*;


public interface Hello extends EJBObject {
	public String hello(Message msg) throws RemoteException;
}
