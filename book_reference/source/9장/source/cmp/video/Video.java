package kr.co.hanbitbook.ejb.examples.video;
import javax.ejb.*;
import java.rmi.*;

public interface Video extends EJBObject {
	public String getIsbn() throws RemoteException;
	public String getName() throws RemoteException;
	public int getPrice() throws RemoteException;
	
	public void setName(String name) throws RemoteException;
	public void setPrice(int price) throws RemoteException;
}