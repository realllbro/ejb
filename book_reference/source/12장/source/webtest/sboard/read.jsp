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
%>
<html>
<head>
<title>게시판 글 상세보기</title>
</head>
<body>
<center>
<h3>게시판 글 상세보기</h3>
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
<td>제목 </td><td><h4> <%=bd.getTitle()%> </h4></td>
</tr>
<tr><td>작석자 </td><td> <%=bd.getName()%></td>
</tr>
<tr><td>내  용 </td><td> <pre>
<%=bd.getContent()%>
</pre></td>
</tr>
<tr><td>작성일 </td><td> <%=bd.getRegdateString()%></td>
</tr>
<tr><td>읽은수 </td><td> <%=bd.getReadcount()%></td>
</tr>
</table>
<%
	}catch(Exception ex){
		System.out.println("글 상세보기 :" + ex.toString());
	}
%>
<a href="list.jsp?page=<%=ipage%>">글 리스트 보기</a>&nbsp;&nbsp;
<a href=writeform.html>게시판 글쓰기</a>&nbsp;&nbsp;
<a href="updateform.jsp?seq=<%=iseq%>&page=<%=ipage%>">글 수정하기</a>&nbsp;&nbsp;
<a href="deleteform.jsp?seq=<%=iseq%>&page=<%=ipage%>">글 삭제하기</a>&nbsp;&nbsp;
</center>
</body>
</html>
