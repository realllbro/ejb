<%@ page contentType="text/html;charset=EUC-KR"%>
<%@ page import="java.util.*"%>
<%@ page import="java.sql.*"%>
<%@ page import="javax.sql.*"%>
<%@ page import="javax.rmi.*"%>
<%@ page import="javax.naming.*"%>
<%@ page import="javax.transaction.*"%>
<%@ page import="kr.co.hanbitbook.ejb.examples.shop.*"%>
<html>
<head>
<title>�α׾ƿ��ϱ�</title>
</head>
<body>
<center>
<h3>�α׾ƿ��ϱ�</h3>
<%
	Object obj = session.getAttribute("userd");
	if(obj != null){
		session.removeAttribute("userd");
	}
%>
<a href="categorylist.jsp">��ǰ ī�װ� ����</a>
</body>
</html>
