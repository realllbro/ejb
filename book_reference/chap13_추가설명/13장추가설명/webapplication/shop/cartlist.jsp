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
<title>��ٱ��� ��� ����</title>
</head>
<body>
<center>
<h3>��ٱ��� ���� ����</h3>

<jsp:useBean id="scart" class="kr.co.hanbitbook.ejb.examples.shop.ShoppingCart" scope="session" />

<table width="600" border="1">
<tr>
<td>������ �̸�</td><td>������ ����</td><td>������ ����</td><td>��û����</td>
</tr>

<%
	java.util.Collection list = scart.getItems();
	if(list == null){
%>
<td colspan=3>������ ��ǰ�� �����ϴ�.</td>
<%
	}else{
		java.util.Iterator iter = list.iterator();
		while(iter.hasNext()){
			ItemDataBean ibean = (ItemDataBean)iter.next();
%>
<tr><td><%=ibean.getName()%></td>
<td><%=ibean.getText()%></td>
<td><%=ibean.getPrice()%></td>
<td><%=ibean.getCount()%></td>
<%
		}
	}
%>
</table>

<a href="categorylist.jsp">��ǰ ī�װ� ����</a>
<a href="buy.jsp">������ ���� �����ϱ�</a>

</body>
</html>
