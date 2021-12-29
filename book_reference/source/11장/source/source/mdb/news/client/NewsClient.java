import javax.rmi.*;
import javax.naming.*;
import javax.jms.*;
import javax.transaction.*;
import kr.co.hanbitbook.ejb.examples.news.*;

public class NewsClient{
	public static void main(String args[]){
		Context ctx = null;
		try{
			java.util.Properties p = new java.util.Properties();
			p.put(Context.INITIAL_CONTEXT_FACTORY, "weblogic.jndi.WLInitialContextFactory");
			p.put(Context.PROVIDER_URL, "t3://localhost:7001");
			ctx = new InitialContext(p);
			
			TopicConnectionFactory tcf = (TopicConnectionFactory)ctx.lookup("weblogic.jms.ConnectionFactory");
			TopicConnection qc = tcf.createTopicConnection();
			qc.start();

			Topic Topic = null;
			TopicSession tsession = null;
			try{
				tsession = qc.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
				Topic = (Topic)ctx.lookup("jmstopic");
			}catch(NamingException ne){
				Topic = tsession.createTopic("jmstopic");
				ctx.bind("jmstopic", Topic);
			}

			TopicPublisher qsender = tsession.createPublisher(Topic);
			MapMessage mm = tsession.createMapMessage();
			String[] msg = new String[]{ "김성박", "새로운 영화", "반지의 제왕 3" };
			
			mm.setString("seq", "1");
			mm.setString("name",msg[0]);
			mm.setString("title",msg[1]);
			mm.setString("content", msg[2]);

			qsender.publish(mm);
			System.out.println("새로운 뉴스를 전송하였습니다.");
			qc.close();
			
		}catch(Exception e){
			System.out.println(e.toString());
		}
	}
}
