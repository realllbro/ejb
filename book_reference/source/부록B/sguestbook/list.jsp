<%@ page language="java" pageEncoding="euc-kr" %>
<%@ page import="kr.co.hanbitbook.ejb.guestbook.*" %>
<html>
<head>
<title>방명록 리스트 보기</title>
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

<h3>방명록 리스트 보기</h3>
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
<td width=100>순번</td>
<td width=400><%=gdb.getSeq()%>
<% if(loginflag){
%>
<a href=delete.do?seq=<%=gdb.getSeq()%>>삭제</a>
<%
}
%>
</td>
</tr>
<td>이름</td>
<td><%=gdb.getName()%></td>
</tr>
<td>내용</td>
<td><%=gdb.getContent()%></td>
</tr>
</table><br/>

<%
	}
%>
<br><br>
<a href=writeform.do>글쓰기</a>
&nbsp; &nbsp; &nbsp;

<%
	if(!loginflag){
%>
<a href=loginform.do>로그인</a>
<%
	}else{
%>
<a href=logout.do>로그아웃</a>
<%
	}
%>
</table>
</body>
</html>