import javax.rmi.*;
import javax.naming.*;
import javax.transaction.*;
import kr.co.hanbitbook.ejb.examples.sum.*;

public class SumTestClient{
	public static void main(String args[]){
		Context ctx = null;
		try{
			java.util.Properties p = new java.util.Properties();
			p.put(Context.INITIAL_CONTEXT_FACTORY, "weblogic.jndi.WLInitialContextFactory");
			p.put(Context.PROVIDER_URL, "t3://localhost:7001");
			ctx = new InitialContext(p);
			Object h = ctx.lookup("SumEJB");
			SumHome home = (SumHome)PortableRemoteObject.narrow(h, SumHome.class);
			Sum sum = home.create(5, 10);
			System.out.println("5 + 10 = " + sum.getSum());
			sum.setOp1(100);
			sum.setOp2(200);
			System.out.println("100 + 200 = " + sum.getSum());
		}catch(Exception e){
			System.out.println(e.toString());
		}
	}
}
