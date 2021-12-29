<%@ page contentType="text/html;charset=EUC-KR"%>
<%@ page import="java.util.*"%>
<%@ page import="java.sql.*"%>
<%@ page import="javax.sql.*"%>
<%@ page import="javax.rmi.*"%>
<%@ page import="javax.naming.*"%>
<%@ page import="javax.transaction.*"%>
<%@ page import="kr.co.hanbitbook.ejb.examples.shop.*"%>
<%
	String sname = request.getParameter("name");
	if(sname == null || sname.trim().equals("")){
%>
<script language="JavaScript">
	window.alert("ī�װ� �̸��� ���޵��� �ʾҽ��ϴ�.");
	history.go(-1);
</script>
<%
		return;
	}
%>
<html>
<head>
<title>������ ��� ����</title>
</head>
<body>
<center>
<h3><%=sname%> ������ ��� ����</h3>
<table width="600" border="1">
<tr>
<td>������ �̸�</td><td>������ ����</td><td>������ ����</td><td>��ٱ��ϴ��</td>
<%
	Context ctx = null;
	CategoryManager categoryManager = null;
	try{
		ctx = new InitialContext();
		Object h = ctx.lookup("CategoryManager");
		CategoryManagerHome home = (CategoryManagerHome)PortableRemoteObject.narrow(h, CategoryManagerHome.class);
		categoryManager = home.create();
		Collection list = categoryManager.getItems(sname);
		Iterator iter = list.iterator();
		while(iter.hasNext()){
			ItemDataBean bd = (ItemDataBean)iter.next();
%>
<tr>
<td width="300"><%=bd.getName()%></td>
<td><%=bd.getText()%></td>
<td><%=bd.getPrice()%></td>
<td><a href=additem.jsp?id=<%=bd.getId()%>>����</a></td>
</tr>
<%
		}
		
	}catch(Exception e){
		out.println(e.toString());
	}
%>
</table>

<a href="javascript:history.go(-1)">��ǰ ī�װ� ����</a>

</body>
</html>
