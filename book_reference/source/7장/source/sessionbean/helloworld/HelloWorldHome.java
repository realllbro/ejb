package kr.co.hanbitbook.ejb.examples;
import java.rmi.RemoteException;
import javax.ejb.CreateException;
import javax.ejb.EJBHome;

public interface HelloWorldHome extends EJBHome{
	public HelloWorld create() throws CreateException, RemoteException;
}