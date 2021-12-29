<%@ page contentType="text/html;charset=EUC-KR"%>
<%@ page import="java.util.*"%>
<%@ page import="java.sql.*"%>
<%@ page import="javax.sql.*"%>
<%@ page import="javax.rmi.*"%>
<%@ page import="javax.naming.*"%>
<%@ page import="javax.transaction.*"%>
<%@ page import="kr.co.hanbitbook.ejb.examples.board.*"%>
<%
	String spage = request.getParameter("page");
	int ipage = 1;
	if(spage != null){
		try{
			ipage = Integer.parseInt(spage);
		}catch(Exception ex){
			ipage = 1;
		}
	}
%>
<html>
<head>
<title>�Խ��� ����Ʈ ����</title>
</head>
<body>
<center>
<h3>�Խ��� ����Ʈ</h3>
<table width="800" border="1">
<tr>
<td>����</td><td width="400">����</td><td>�ۼ���</td><td>�ۼ���</td><td>������</td>
</tr>
<%
	Context ctx = null;
	BoardManager boardManager = null;

	try{
		ctx = new InitialContext();
		Object h = ctx.lookup("BoardManager");
		BoardManagerHome home = (BoardManagerHome)PortableRemoteObject.narrow(h, BoardManagerHome.class);
		boardManager = home.create();
		Collection list = boardManager.getBoardDataList(ipage);
		Iterator iter = list.iterator();
		while(iter.hasNext()){
			BoardData bd = (BoardData)iter.next();
%>
<tr>
<td><%=bd.getSeq()%></td>
<td width="400"><a href="read.jsp?seq=<%=bd.getSeq()%>&page=<%=ipage%>"><%=bd.getTitle()%></a></td>
<td><%=bd.getName()%></td>
<td><%=bd.getRegdateString()%></td>
<td><%=bd.getReadcount()%></td>
</tr>
<%
		}
		
	}catch(Exception e){
		out.println(e.toString());
	}
%>
</table>
<%
	if(boardManager.isPrevPage(ipage)){
%>
<a href=list.jsp?page=<%=ipage -1%>>����������</a>
<%
	}else{
%>
����������
<%
	}
%>
&nbsp;&nbsp;
<%
	if(boardManager.isNextPage(ipage)){
%>
<a href=list.jsp?page=<%=ipage +1%>>����������</a>
<%
	}else{
%>
����������
<%
	}
%>
&nbsp;&nbsp;
<a href=writeform.html>�Խ��� �۾���</a>
</center>
</body>
</html>
