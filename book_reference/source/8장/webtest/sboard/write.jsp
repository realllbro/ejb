<%@ page contentType="text/html;charset=EUC-KR"%>
<%@ page import="java.sql.*"%>
<%@ page import="javax.sql.*"%>
<%@ page import="javax.rmi.*"%>
<%@ page import="javax.naming.*"%>
<%@ page import="javax.transaction.*"%>
<%@ page import="kr.co.hanbitbook.ejb.examples.board.*"%>
<html>
<head>
<title>�Խ��� �۾���</title>
</head>
<body>
	<h3>����Ǿ����ϴ�.</h3><br>
<%
	BoardData bd = new BoardData();
	bd.setName(request.getParameter("name"));
	bd.setPasswd(request.getParameter("passwd"));
	bd.setTitle(request.getParameter("title"));
	bd.setContent(request.getParameter("content"));

	Context ctx = null;

	try{
		ctx = new InitialContext();
		Object h = ctx.lookup("BoardManager");
		BoardManagerHome home = (BoardManagerHome)PortableRemoteObject.narrow(h, BoardManagerHome.class);
		BoardManager boardManager = home.create();
		boardManager.addBoardData(bd);
	}catch(Exception e){
		out.println(e.toString());
	}
%>
<a href=list.jsp>�Խ��� ����Ʈ ����</a>
</body>
</html>
