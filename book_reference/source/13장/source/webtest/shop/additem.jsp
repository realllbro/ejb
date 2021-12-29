<%@ page contentType="text/html;charset=EUC-KR"%>
<%@ page import="java.util.*"%>
<%@ page import="java.sql.*"%>
<%@ page import="javax.sql.*"%>
<%@ page import="javax.rmi.*"%>
<%@ page import="javax.naming.*"%>
<%@ page import="javax.transaction.*"%>
<%@ page import="kr.co.hanbitbook.ejb.examples.shop.*"%>
<%
	String sid = request.getParameter("id");
	if(sid == null || sid.trim().equals("")){
%>
<script language="JavaScript">
	window.alert("������ ���̵� ���޵��� �ʾҽ��ϴ�.");
	history.go(-1);
</script>
<%
		return;
	}
%>
<html>
<head>
<title>������ ��ǰ���</title>
</head>
<body>
<center>
<h3>������ �������� ��ٱ��Ͽ� ��ҽ��ϴ�.</h3>

<jsp:useBean id="scart" class="kr.co.hanbitbook.ejb.examples.shop.ShoppingCart" scope="session" />

<%
	Context ctx = null;
	CategoryManager categoryManager = null;
	try{
		ctx = new InitialContext();
		Object h = ctx.lookup("CategoryManager");
		CategoryManagerHome home = (CategoryManagerHome)PortableRemoteObject.narrow(h, CategoryManagerHome.class);
		categoryManager = home.create();
		ItemDataBean ibean = categoryManager.getItem(sid);
		ibean.setCount(1);
		scart.addItem(ibean);
	}catch(Exception e){
		out.println(e.toString());
	}
%>

<a href="categorylist.jsp">��ǰ ī�װ� ����</a>&nbsp;&nbsp;&nbsp;
<a href="cartlist.jsp">������ ��ǰ ����</a>

</body>
</html>
