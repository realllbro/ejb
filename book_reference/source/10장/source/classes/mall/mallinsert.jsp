<%@ page contentType="text/html;charset=EUC-KR"%>
<%@ page import="javax.rmi.*"%>
<%@ page import="javax.naming.*"%>
<%@ page import="java.util.*"%>
<%@ page import="javax.transaction.*"%>
<%@ page import="javax.transaction.*"%>
<%@ page import="kr.co.hanbitbook.ejb.examples.mall.*"%>
<%
		Context ctx = null;
		UserTransaction tx = null;
		try{
			java.util.Properties p = new java.util.Properties();
			p.put(Context.INITIAL_CONTEXT_FACTORY, "weblogic.jndi.WLInitialContextFactory");
			p.put(Context.PROVIDER_URL, "t3://localhost:7001");
			ctx = new InitialContext(p);
			tx = (UserTransaction)ctx.lookup("javax.transaction.UserTransaction");
			
			Object h = ctx.lookup("Customer");
			CustomerHome home = (CustomerHome)PortableRemoteObject.narrow(h, CustomerHome.class);
			
			tx.begin(); // UserTransaction 시작

			home.create("urstory", "김성박", "u1234"); // 사용자 추가

			Object h2 = ctx.lookup("Moreinfo");
			MoreinfoHome moreinfohome = (MoreinfoHome)PortableRemoteObject.narrow(h2, MoreinfoHome.class);
			moreinfohome.create("urstory", 100); // 추가 정보 입력

			Object h3 = ctx.lookup("Selectitem");
			SelectitemHome selecthome = (SelectitemHome)PortableRemoteObject.narrow(h3, SelectitemHome.class);
			selecthome.create("urstory", "a1", "카메라"); // 물품 선택
			selecthome.create("urstory", "a2", "자동차"); // 물품 선택
			tx.commit(); // UserTransaction 종료
			out.println("저장되었습니다.");
		}catch(Exception e){
			out.println(e.toString() + "<br><br>");
			out.println("이미 추가된 사용자입니다. sql*plus에서 삭제한 후 테스트하여 주세요. sql*plus에서 삭제한 후에는 반드시 commit명령을 실행해야 합니다.");
		}finally{
			try{
				tx.rollback();
			}catch(Exception ex){}
		}
%>
