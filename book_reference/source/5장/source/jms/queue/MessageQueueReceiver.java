import java.io.*;
import javax.jms.*;
import javax.naming.*;
import java.util.Properties;

public class MessageQueueReceiver
{
	public static void main(String args[]){
		try{
	        Properties h = new Properties();
		    h.put(Context.INITIAL_CONTEXT_FACTORY, "weblogic.jndi.WLInitialContextFactory");
	        h.put(Context.PROVIDER_URL, "t3://localhost:7001");
            
			Context ctx = new InitialContext(h);
			QueueConnectionFactory factory = (QueueConnectionFactory)ctx.lookup("weblogic.jms.ConnectionFactory");
			Queue queue = (Queue)ctx.lookup("jmsqueue");
			QueueConnection con = factory.createQueueConnection();
			QueueSession session = con.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
			QueueReceiver qreceiver = session.createReceiver(queue);
			con.start();
			while(true){
				Message msg = qreceiver.receive(1);
				if(msg != null){
					if(msg instanceof TextMessage){
						TextMessage tmpMsg = (TextMessage)msg;
						System.out.println("읽어온 메시지 :" + tmpMsg.getText());
					}
				}else
					break;
			}
			con.close();
		}catch(Exception ex){
			System.out.println(ex);
		} // catch
	}// main
}// class