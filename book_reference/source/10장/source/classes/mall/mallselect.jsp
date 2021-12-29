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

			Customer customer = home.findByPrimaryKey("urstory"); // urstory ����� ��ȯ

			Collection col = customer.getSelectitem(); // ����ڰ� ������ ��ǰ ��ȯ
			Iterator iter = col.iterator();
			out.println("ursotry ����ڰ� ������ ��ǰ<br>");
			while(iter.hasNext()){
				Selectitem tmp = (Selectitem)iter.next();
				out.println("���̵� :" + tmp.getItemid() + "<br>");
				out.println("�̸� :" + tmp.getItemname() + "<br>");
				out.println("<hr>");
			}

			
			Moreinfo moreinfo2 = customer.getMoreinfo(); // ������� �߰� ���� ��ȯ
			out.println("urstory ������� �߰� ����<br>");
			out.println("point :" + moreinfo2.getPoint());
			tx.commit();
		}catch(Exception e){
			out.println(e.toString() + "<br><br>");
			out.println("�̹� �߰��� ������Դϴ�. sql*plus���� ������ �� �׽�Ʈ�Ͽ� �ּ���. sql*plus���� ������ �Ŀ��� �ݵ�� commit����� �����ؾ� �մϴ�.");
		}finally{
			try{
				tx.rollback();
			}catch(Exception ex){}
		}
%>
