<%@ page contentType="text/html;charset=EUC-KR"%>
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
	String passwd = request.getParameter("passwd");
	if(passwd == null){
%>
<script language="JavaScript">
	window.alert("��ȣ�� ���޵��� �ʾҽ��ϴ�.");
	history.go(-1);
</script>
<%
		return;
	}
%>
<html>
<head>
<title>�Խ��� �۾���</title>
</head>
<body>
<%
	BoardData bd = new BoardData();
	bd.setSeq(iseq);
	bd.setName(request.getParameter("name"));
	bd.setTitle(request.getParameter("title"));
	bd.setContent(request.getParameter("content"));

	Context ctx = null;

	try{
		ctx = new InitialContext();
		Object h = ctx.lookup("BoardManager");
		BoardManagerHome home = (BoardManagerHome)PortableRemoteObject.narrow(h, BoardManagerHome.class);
		BoardManager boardManager = home.create();
		if(!boardManager.isWriter(iseq, passwd)){
%>
<script language="JavaScript">
	window.alert("��ȣ�� ��ġ���� �ʽ��ϴ�.");
	history.go(-1);
</script>
<%
			return;
		}
		boardManager.updateBoardData(bd);
	}catch(Exception e){
		out.println(e.toString());
	}
%>
<h3>�����Ǿ����ϴ�.</h3>
<a href=list.jsp>�Խ��� ����Ʈ ����</a>
</body>
</html>
