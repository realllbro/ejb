package kr.co.hanbitbook.ejb.examples.news;

import javax.ejb.*;
import javax.jms.*;
import javax.naming.*;

public class AddNewsBean implements MessageDrivenBean, MessageListener {
  MessageDrivenContext messageDrivenContext;
  NewsHome home;


  public void ejbCreate() throws CreateException {
  }

  public void ejbRemove() {
  }

  public void onMessage(Message msg) {
	System.out.println("on Message 호출");
	try{
		Context initial = new InitialContext();
		Object obj = initial.lookup("news");
		home = (NewsHome)obj;
	}catch(Exception ex){
		System.out.println(ex);
	}

	
	try{
		if(msg instanceof MapMessage){
			MapMessage mm = (MapMessage)msg;
			String seq = mm.getString("seq");
			String name = mm.getString("name");
			String title = mm.getString("title");
			String content = mm.getString("content");
			System.out.println(seq + ":" + name + ":" + title + ":" + content);
			
			try{
				News news = null;
				try{
					news = home.findByPrimaryKey(seq);
				}catch(Exception ex1){
					System.out.println("findByPrimaryKey :" + ex1.toString());
				}

				if(news == null){
					System.out.println("같은 seq값이 없을 경우......");
					home.create(seq, name, title, content);
					System.out.println("새로운 값을 저장하였습니다.");
				}
			}catch(Exception ex2){
				System.out.println("CMP Exception :" + ex2.toString());
			}
		} // if end
	}catch(JMSException ex){
		System.out.println("JMSException :" + ex.toString());
		// messageDrivenContext.setRollbackOnly();
	}
  }

  public void setMessageDrivenContext(MessageDrivenContext messageDrivenContext) {
    this.messageDrivenContext = messageDrivenContext;
  }
}