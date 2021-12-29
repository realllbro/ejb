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
<title>ī�װ� ����</title>
</head>
<body>
<center>
<h3>ī�װ� ����Ʈ</h3>
<table width="600" border="1">
<tr>
<td>ī�װ� �̸�</td><td>ī�װ� ����</td>
<%
	Context ctx = null;
	CategoryManager categoryManager = null;
	try{
		ctx = new InitialContext();
		Object h = ctx.lookup("CategoryManager");
		CategoryManagerHome home = (CategoryManagerHome)PortableRemoteObject.narrow(h, CategoryManagerHome.class);
		categoryManager = home.create();
		Collection list = categoryManager.getCategory();
		Iterator iter = list.iterator();
		while(iter.hasNext()){
			CategoryDataBean bd = (CategoryDataBean)iter.next();
%>
<tr>
<td width="300"><a href="read.jsp?name=<%=java.net.URLEncoder.encode(bd.getName())%>"><%=bd.getName()%></a></td>
<td><%=bd.getText()%></td>
</tr>
<%
		}
		
	}catch(Exception e){
		out.println(e.toString());
	}
%>
</table>
<a href=cartlist.jsp>��ٱ��� ����</a>&nbsp;&nbsp;
<%
	Object obj = session.getAttribute("userd");
	if(obj != null){
%>
<a href=logout.jsp>�α׾ƿ�</a>
<%
	}else{
%>
<a href=loginform.html>�α���</a>
<%
	}
%>
</body>
</html>
