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
	String sseq = request.getParameter("seq");
	int iseq = 0;
	if(sseq == null){
%>
<script language="JavaScript">
	window.alert("seq���� ���޵��� �ʾҽ��ϴ�.");
	history.go(-1);
</script>
<%
		return;
	}
	try{
		iseq = Integer.parseInt(sseq);
	}catch(Exception ex){
%>
<script language="JavaScript">
	window.alert("�߸��� seq���� ���޵Ǿ����ϴ�.");
	history.go(-1);
</script>
<%
		return;
	}
%>
<html>
<head>
<title>�Խ��� �� �󼼺���</title>
</head>
<body>
<center>
<h3>�Խ��� �� �󼼺���</h3>
<%
	Context ctx = null;
	BoardManager boardManager = null;

	try{
		ctx = new InitialContext();
		Object h = ctx.lookup("BoardManager");
		BoardManagerHome home = (BoardManagerHome)PortableRemoteObject.narrow(h, BoardManagerHome.class);
		boardManager = home.create();
		boardManager.updateReadCount(iseq);
		BoardData bd = boardManager.getBoardData(iseq);
%>
<table width=800 border=1>
<tr>
<td>���� </td><td><h4> <%=bd.getTitle()%> </h4></td>
</tr>
<tr><td>�ۼ��� </td><td> <%=bd.getName()%></td>
</tr>
<tr><td>��  �� </td><td> <pre>
<%=bd.getContent()%>
</pre></td>
</tr>
<tr><td>�ۼ��� </td><td> <%=bd.getRegdateString()%></td>
</tr>
<tr><td>������ </td><td> <%=bd.getReadcount()%></td>
</tr>
</table>
<%
	}catch(Exception ex){
		System.out.println("�� �󼼺��� :" + ex.toString());
	}
%>
<a href="list.jsp?page=<%=ipage%>">�� ����Ʈ ����</a>&nbsp;&nbsp;
<a href=writeform.html>�Խ��� �۾���</a>&nbsp;&nbsp;
<a href="updateform.jsp?seq=<%=iseq%>&page=<%=ipage%>">�� �����ϱ�</a>&nbsp;&nbsp;
<a href="deleteform.jsp?seq=<%=iseq%>&page=<%=ipage%>">�� �����ϱ�</a>&nbsp;&nbsp;
</center>
</body>
</html>
