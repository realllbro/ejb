
package net.sarang.sunny.ejb.exam;
import javax.ejb.*;
import java.rmi.*;


public interface HelloHome extends EJBHome {
	public Hello create() throws RemoteException, CreateException;

}
