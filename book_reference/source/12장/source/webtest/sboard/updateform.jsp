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
<title>게시판 글 수정하기</title>
</head>
<body>
<center>
<h3>게시판 글 수정하기</h3>
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
<form method=post action=update.jsp>
<tr>
<td>제목 </td><td><h4><input type=text name=title value="<%=bd.getTitle()%>"></h4></td>
</tr>
<tr><td>작석자 </td><td><input type=text name=name value="<%=bd.getName()%>"></td>
</tr>
<tr><td>내  용 </td><td>
<textarea name=content cols=50 rows=4><%=bd.getContent()%></textarea>
</td>
</tr>
<tr><td>작성일 </td><td><%=bd.getRegdateString()%></td>
</tr>
<tr><td>읽은수 </td><td><%=bd.getReadcount()%></td>
</tr>
<tr><td colspan=2><font color=red>해당 글의 암호를 입력한 후 확인 버튼을 클릭하여주세요.</font></td></tr>
<input type=hidden name=seq value=<%=iseq%>>
<input type=hidden name=page value=<%=ipage%>>
<tr>
<td>암호</td><td><input type=password name=passwd><input type=submit value="확인"></td>
</tr>
</form>
</table>
<%
	}catch(Exception ex){
		System.out.println("글 상세보기 :" + ex.toString());
	}
%>
<a href="list.jsp?page=<%=ipage%>">글 리스트 보기</a>&nbsp;&nbsp;
</center>
</body>
</html>
