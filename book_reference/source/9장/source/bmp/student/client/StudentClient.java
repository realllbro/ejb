import javax.rmi.*;
import javax.naming.*;
import javax.transaction.*;
import student.*;

public class StudentClient{
	public static void main(String args[]){
		Context ctx = null;
		try{
			java.util.Properties p = new java.util.Properties();
			p.put(Context.INITIAL_CONTEXT_FACTORY, "weblogic.jndi.WLInitialContextFactory");
			p.put(Context.PROVIDER_URL, "t3://localhost:7001");
			ctx = new InitialContext(p);
			Object h = ctx.lookup("StudentBean");
			StudentHome home = (StudentHome)PortableRemoteObject.narrow(h, StudentHome.class);
			Student student = home.create("홍길동", 10, 90);
			System.out.println("저장되었습니다.");
		}catch(Exception e){
			System.out.println(e.toString());
		}
	}
}
