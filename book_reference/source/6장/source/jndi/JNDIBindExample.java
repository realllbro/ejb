import java.io.*;
import javax.jms.*;
import javax.naming.*;
import java.util.Properties;

public class JNDIBindExample
{
	public static void main(String args[]){
		if(args.length != 2){
			System.out.println("���� : java JNDIBindExample JNDI�̸� �����ҹ��ڿ�");
			System.exit(0);
		}
		try{
	        Properties h = new Properties();
		    h.put(Context.INITIAL_CONTEXT_FACTORY, "weblogic.jndi.WLInitialContextFactory");
		    h.put(Context.PROVIDER_URL, "t3://localhost:7001");
            
		    Context ctx = new InitialContext(h);
			ctx.bind(args[0], args[1]);
			System.out.println("JNDI ���̹� ���񽺿� ����Ͽ����ϴ�.");
		}catch(Exception ex){
			System.out.println(ex);
		} // catch
	}// main
}// class