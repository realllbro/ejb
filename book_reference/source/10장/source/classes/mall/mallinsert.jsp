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
			
			tx.begin(); // UserTransaction ����

			home.create("urstory", "�輺��", "u1234"); // ����� �߰�

			Object h2 = ctx.lookup("Moreinfo");
			MoreinfoHome moreinfohome = (MoreinfoHome)PortableRemoteObject.narrow(h2, MoreinfoHome.class);
			moreinfohome.create("urstory", 100); // �߰� ���� �Է�

			Object h3 = ctx.lookup("Selectitem");
			SelectitemHome selecthome = (SelectitemHome)PortableRemoteObject.narrow(h3, SelectitemHome.class);
			selecthome.create("urstory", "a1", "ī�޶�"); // ��ǰ ����
			selecthome.create("urstory", "a2", "�ڵ���"); // ��ǰ ����
			tx.commit(); // UserTransaction ����
			out.println("����Ǿ����ϴ�.");
		}catch(Exception e){
			out.println(e.toString() + "<br><br>");
			out.println("�̹� �߰��� ������Դϴ�. sql*plus���� ������ �� �׽�Ʈ�Ͽ� �ּ���. sql*plus���� ������ �Ŀ��� �ݵ�� commit����� �����ؾ� �մϴ�.");
		}finally{
			try{
				tx.rollback();
			}catch(Exception ex){}
		}
%>
