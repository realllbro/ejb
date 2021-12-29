import java.io.*;
import javax.jms.*;
import javax.naming.*;
import java.util.Properties;

public class MessageTopicPublisher
{
	public static void main(String args[]){
		if(args.length != 1){
			System.out.println("사용법 : java MessageTopicPublisher 전송할메시지");
			System.exit(0);
		}
		try{
	        Properties h = new Properties();
		    h.put(Context.INITIAL_CONTEXT_FACTORY, "weblogic.jndi.WLInitialContextFactory");
	        h.put(Context.PROVIDER_URL, "t3://localhost:7001");
            
			Context ctx = new InitialContext(h);
			TopicConnectionFactory factory = (TopicConnectionFactory)ctx.lookup("weblogic.jms.ConnectionFactory");
			Topic topic = (Topic)ctx.lookup("jmstopic");
			TopicConnection con = factory.createTopicConnection();
			TopicSession session = con.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
			TopicPublisher publisher = session.createPublisher(topic);
			TextMessage message = session.createTextMessage();
			message.setText(args[0]);
			publisher.publish(message);
			con.close();
		}catch(Exception ex){
			System.out.println(ex);
		} // catch
	}// main
}// class