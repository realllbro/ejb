import java.io.*;
import javax.jms.*;
import javax.naming.*;
import java.util.Properties;

public class MessageQueueSender
{
	public static void main(String args[]){
		if(args.length != 1){
			System.out.println("사용법 : java MessageQueueSender 전송할메시지");
			System.exit(0);
		}
		try{
	        Properties h = new Properties();
		    h.put(Context.INITIAL_CONTEXT_FACTORY, "weblogic.jndi.WLInitialContextFactory");
	        h.put(Context.PROVIDER_URL, "t3://localhost:7001");
            
			Context ctx = new InitialContext(h);
			QueueConnectionFactory factory = (QueueConnectionFactory)ctx.lookup("weblogic.jms.ConnectionFactory");
			Queue queue = (Queue)ctx.lookup("jmsqueue");
			QueueConnection con = factory.createQueueConnection();
			QueueSession session = con.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
			QueueSender qsender = session.createSender(queue);
			TextMessage message = session.createTextMessage();
			message.setText(args[0]);
			qsender.send(message);
			qsender.send(session.createMessage());
			con.close();
		}catch(Exception ex){
			System.out.println(ex);
		} // catch
	}// main
}// class