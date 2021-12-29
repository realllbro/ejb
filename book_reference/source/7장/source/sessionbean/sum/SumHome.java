package kr.co.hanbitbook.ejb.examples.sum;
import java.rmi.RemoteException;
import javax.ejb.*;

public interface SumHome extends EJBHome
{
    public Sum create    (int op1, int op2) 
                throws RemoteException, CreateException;
}