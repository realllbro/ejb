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
	window.alert("seq값이 전달되지 않았습니다.");
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
	window.alert("잘못된 seq값이 전달되었습니다.");
	history.go(-1);
</script>
<%
		return;
	}

	String passwd = request.getParameter("passwd");
	if(passwd == null){
%>
<script language="JavaScript">
	window.alert("암호가 전달되지 않았습니다.");
	history.go(-1);
</script>
<%
		return;
	}
%>
<html>
<head>
<title>게시판 글 삭제하기</title>
</head>
<body>
<center>
<h3>게시판 글 삭제하기</h3>
<%
	Context ctx = null;
	BoardManager boardManager = null;

	try{
		ctx = new InitialContext();
		Object h = ctx.lookup("BoardManager");
		BoardManagerHome home = (BoardManagerHome)PortableRemoteObject.narrow(h, BoardManagerHome.class);
		boardManager = home.create();
		if(!boardManager.isWriter(iseq, passwd)){
%>
<script language="JavaScript">
	window.alert("암호가 일치하지 않습니다.");
	history.go(-1);
</script>
<%
			return;
		}
		boardManager.deleteBoardData(iseq);
%>
		<h3><%=iseq%> 글이 삭제되었습니다.</h3>
<%
	}catch(Exception ex){
		System.out.println("글 삭제하기 :" + ex.toString());
	}
%>
<a href="list.jsp?page=<%=ipage%>">글 리스트 보기</a>&nbsp;&nbsp;
</center>
</body>
</html>
