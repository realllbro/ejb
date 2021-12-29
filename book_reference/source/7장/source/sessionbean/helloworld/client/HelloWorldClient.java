import javax.rmi.*;
import javax.naming.*;
import javax.transaction.*;
import kr.co.hanbitbook.ejb.examples.*;

public class HelloWorldClient{
	public static void main(String args[]){
		Context ctx = null;
		try{
			java.util.Properties p = new java.util.Properties();
			p.put(Context.INITIAL_CONTEXT_FACTORY, "weblogic.jndi.WLInitialContextFactory");
			p.put(Context.PROVIDER_URL, "t3://localhost:7001");
			ctx = new InitialContext(p);
			Object h = ctx.lookup("HelloWorldBean");
			HelloWorldHome home = (HelloWorldHome)PortableRemoteObject.narrow(h, HelloWorldHome.class);
			HelloWorld helloWorld = home.create();
			System.out.println(helloWorld.helloWorld());
		}catch(Exception e){
			System.out.println(e.toString());
		}
	}
}
