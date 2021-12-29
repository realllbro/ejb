import java.io.*;
import javax.jms.*;
import javax.naming.*;
import java.util.Properties;

public class JNDIRebindExample
{
	public static void main(String args[]){
		if(args.length != 2){
			System.out.println("사용법 : java JNDIRebindExample JNDI이름 수정할문자열");
			System.exit(0);
		}
		try{
	        Properties h = new Properties();
		    h.put(Context.INITIAL_CONTEXT_FACTORY, "weblogic.jndi.WLInitialContextFactory");
		    h.put(Context.PROVIDER_URL, "t3://localhost:7001");
            
		    Context ctx = new InitialContext(h);
			ctx.rebind(args[0], args[1]);
			System.out.println(args[0] + " JNDI 이름으로 등록된 내용을 수정하였습니다.");
		}catch(Exception ex){
			System.out.println(ex);
		} // catch
	}// main
}// class