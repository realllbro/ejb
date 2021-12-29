<%@ page contentType="text/html;charset=EUC-KR"%>
<%@ page import="javax.rmi.*"%>
<%@ page import="javax.naming.*"%>
<%@ page import="javax.transaction.*"%>
<%@ page import="kr.co.hanbitbook.ejb.examples.mall.*"%>
<%
		Context ctx = null;
		try{
			java.util.Properties p = new java.util.Properties();
			p.put(Context.INITIAL_CONTEXT_FACTORY, "weblogic.jndi.WLInitialContextFactory");
			p.put(Context.PROVIDER_URL, "t3://localhost:7001");
			ctx = new InitialContext(p);
			Object h = ctx.lookup("Customer");
			CustomerHome home = (CustomerHome)PortableRemoteObject.narrow(h, CustomerHome.class);
			Customer customer = home.create("urstory", "김성박", "u1234");
		}catch(Exception e){
			System.out.println(e.toString());
		}
%>
저장 되었습니다.