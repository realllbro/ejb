package kr.co.hanbitbook.ejb.examples.sum;
import java.rmi.RemoteException;
import javax.ejb.*;

public interface Sum extends EJBObject
{
    
    public void setOp2    (int op1) throws RemoteException;
    
    public void setOp1    (int op2) throws RemoteException;

    public int getSum    () throws RemoteException;
}