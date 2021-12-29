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
			
			tx.begin();

			Customer customer = home.findByPrimaryKey("urstory"); // urstory 사용자 반환

			Collection col = customer.getSelectitem(); // 사용자가 선택한 물품 반환
			Iterator iter = col.iterator();
			out.println("ursotry 사용자가 선택한 물품<br>");
			while(iter.hasNext()){
				Selectitem tmp = (Selectitem)iter.next();
				out.println("아이디 :" + tmp.getItemid() + "<br>");
				out.println("이름 :" + tmp.getItemname() + "<br>");
				out.println("<hr>");
			}

			
			Moreinfo moreinfo2 = customer.getMoreinfo(); // 사용자의 추가 정보 반환
			out.println("urstory 사용자의 추가 정보<br>");
			out.println("point :" + moreinfo2.getPoint());
			tx.commit();
		}catch(Exception e){
			out.println(e.toString() + "<br><br>");
			out.println("이미 추가된 사용자입니다. sql*plus에서 삭제한 후 테스트하여 주세요. sql*plus에서 삭제한 후에는 반드시 commit명령을 실행해야 합니다.");
		}finally{
			try{
				tx.rollback();
			}catch(Exception ex){}
		}
%>
