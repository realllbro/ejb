import javax.rmi.*;
import javax.naming.*;
import javax.transaction.*;
import kr.co.hanbitbook.ejb.examples.video.*;

public class VideoUpdate{
	public static void main(String args[]){
		Context ctx = null;
		try{
			java.util.Properties p = new java.util.Properties();
			p.put(Context.INITIAL_CONTEXT_FACTORY, "weblogic.jndi.WLInitialContextFactory");
			p.put(Context.PROVIDER_URL, "t3://localhost:7001");
			ctx = new InitialContext(p);
			Object h = ctx.lookup("VideoEJB");
			VideoHome home = (VideoHome)PortableRemoteObject.narrow(h, VideoHome.class);
			Video video = home.findByPrimaryKey("v-001");
			video.setName("강아지똥");
			video.setPrice(30000);
			System.out.println("다음과 같이 변경되었습니다.");
			System.out.println("isbn :" + video.getIsbn());
			System.out.println("이름 :" + video.getName());
			System.out.println("가격 :" + video.getPrice());
		}catch(Exception e){
			System.out.println(e.toString());
		}
	}
}
