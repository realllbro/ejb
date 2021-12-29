package kr.co.hanbitbook.ejb.examples.sum;
import java.rmi.RemoteException;
import javax.ejb.*;

public class SumEJB implements SessionBean
{
    public SessionContext EJB_Context = null;
    private int op1;
    private int op2;
    
    public SumEJB() {     }
    
    public void ejbCreate(int op1, int op2)  
    { 
		this.op1 = op1;
		this.op2 = op2;
    }

    public void setOp1(int op1){
		this.op1 = op1;
	}
    
    public void setOp2(int op2){
		this.op2 = op2;
	}

    public int getSum(){
		return op1 + op2;
	}
	
    public void ejbRemove() { }
    
    public void ejbActivate(){ }
    
    public void ejbPassivate() { }
    
    public void setSessionContext(SessionContext sc)  { }
}