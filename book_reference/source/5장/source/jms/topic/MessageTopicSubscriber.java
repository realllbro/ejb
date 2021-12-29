import java.io.*;
import javax.jms.*;
import javax.naming.*;
import java.util.Properties;

public class MessageTopicSubscriber
{
	public static void main(String args[]){
		try{
	        Properties h = new Properties();
		    h.put(Context.INITIAL_CONTEXT_FACTORY, "weblogic.jndi.WLInitialContextFactory");
	        h.put(Context.PROVIDER_URL, "t3://localhost:7001");
            
			Context ctx = new InitialContext(h);
			TopicConnectionFactory factory = (TopicConnectionFactory)ctx.lookup("weblogic.jms.ConnectionFactory");
			Topic topic = (Topic)ctx.lookup("jmstopic");
			TopicConnection con = factory.createTopicConnection();
			TopicSession session = con.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
			TopicSubscriber subscriber = session.createSubscriber(topic);
			subscriber.setMessageListener(new MessageListener(){
				public void onMessage(Message message){
					try{
						if(message instanceof TextMessage){
							TextMessage m = (TextMessage) message;
							System.out.println(m.getText());
						}
					}catch(Exception e){
						System.out.println(e.toString());
					}
				}
			});
			con.start();
			while(true){
				char c = (char)System.in.read();
				if(c == 'q')
					break;
			}
			con.close();
		}catch(Exception ex){
			System.out.println(ex);
		} // catch
	}// main
}// class