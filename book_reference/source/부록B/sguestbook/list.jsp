<%@ page language="java" pageEncoding="euc-kr" %>
<%@ page import="kr.co.hanbitbook.ejb.guestbook.*" %>
<html>
<head>
<title>���� ����Ʈ ����</title>
</head>
<body bgcolor="#FFFFFF">
<%
   boolean loginflag = false;
	Cookie[] c = request.getCookies();
	for(int i = 0; i < c.length; i++){
		String cname = c[i].getName();
		if(cname.equals("id")){
			if(c[i].getValue().equals("admin")){
				loginflag = true;
				break;
			}
		}
	} // for
%>

<h3>���� ����Ʈ ����</h3>
<%
	java.util.ArrayList list = (java.util.ArrayList)request.getAttribute("list");
	String spage = (String)request.getAttribute("spage");
%>

<%
	for(int i = 0; i < list.size(); i++){
		GuestbookDataBean gdb = (GuestbookDataBean)list.get(i);
%>
<table border=1 width=500>
<tr>
<td width=100>����</td>
<td width=400><%=gdb.getSeq()%>
<% if(loginflag){
%>
<a href=delete.do?seq=<%=gdb.getSeq()%>>����</a>
<%
}
%>
</td>
</tr>
<td>�̸�</td>
<td><%=gdb.getName()%></td>
</tr>
<td>����</td>
<td><%=gdb.getContent()%></td>
</tr>
</table><br/>

<%
	}
%>
<br><br>
<a href=writeform.do>�۾���</a>
&nbsp; &nbsp; &nbsp;

<%
	if(!loginflag){
%>
<a href=loginform.do>�α���</a>
<%
	}else{
%>
<a href=logout.do>�α׾ƿ�</a>
<%
	}
%>
</table>
</body>
</html>