package kr.co.hanbitbook.ejb.examples.video;
import javax.ejb.*;
import java.rmi.*;

public interface VideoHome extends EJBHome {
	public Video create(String isbn, String name, int price) 
		throws CreateException, RemoteException;
	
	public Video findByPrimaryKey(String isbn) 
		throws FinderException, RemoteException;
}