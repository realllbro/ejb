package kr.co.hanbitbook.ejb.examples;

import java.rmi.RemoteException;
import javax.ejb.EJBObject;

public interface HelloWorld extends EJBObject{
    public String helloWorld() throws RemoteException;
}