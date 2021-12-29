import javax.rmi.*;
import javax.naming.*;
import javax.transaction.*;
import kr.co.hanbitbook.ejb.examples.video.*;

public class VideoDelete{
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
			video.remove();
			System.out.println("삭제되었습니다.");
		}catch(Exception e){
			System.out.println(e.toString());
		}
	}
}
