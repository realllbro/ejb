<%@ page contentType="text/html;charset=EUC-KR"%>
<%@ page import="java.sql.*"%>
<%@ page import="javax.sql.*"%>
<%@ page import="javax.rmi.*"%>
<%@ page import="javax.naming.*"%>
<%@ page import="javax.transaction.*"%>
<%@ page import="kr.co.hanbitbook.ejb.guestbook.*"%>
<html>
<head>
<title>���� �۾���</title>
</head>
<body>
	<h3>����Ǿ����ϴ�.</h3><br>
<%
	GuestbookDataBean gdb = new GuestbookDataBean();
	gdb.setName(request.getParameter("name"));
	gdb.setContent(request.getParameter("content"));

	Context ctx = null;

	try{
		ctx = new InitialContext();
		Object h = ctx.lookup("GuestbookBean");
		GuestbookHome home = (GuestbookHome)PortableRemoteObject.narrow(h, GuestbookHome.class);
		Guestbook guestbook = home.create();
		guestbook.addGuestbookBean(gdb);
	}catch(Exception e){
		out.println(e.toString());
	}
%>
<a href=list.jsp>���� ����Ʈ ����</a>
</body>
</html>
