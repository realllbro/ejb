import java.io.*;
import javax.jms.*;
import javax.naming.*;
import java.util.Properties;

public class JNDILookupExample
{
	public static void main(String args[]){
		if(args.length != 1){
			System.out.println("���� : java JNDILookupExample JNDI�̸�");
			System.exit(0);
		}
		try{
	        Properties h = new Properties();
		    h.put(Context.INITIAL_CONTEXT_FACTORY, "weblogic.jndi.WLInitialContextFactory");
		    h.put(Context.PROVIDER_URL, "t3://localhost:7001");
            
		    Context ctx = new InitialContext(h);
			Object obj = ctx.lookup(args[0]);
			String str = (String)obj;
			System.out.println(args[0] + " JNDI �̸����� ��ϵ� ���ڿ� :" + str);
		}catch(Exception ex){
			System.out.println(ex);
		} // catch
	}// main
}// class