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
<title>�α����ϱ�</title>
</head>
<body>
<center>
<h3>�α����ϱ�</h3>
<%
	String id = request.getParameter("id");
	String passwd = request.getParameter("passwd");
	if(id == null || id.trim().equals("")){
%>
<script language="JavaScript">
alert("���̵� �Է����� �����̽��ϴ�.");
history.go(-1);
</script>
<%
		return;
	}
	if(passwd == null || passwd.trim().equals("")){
%>
<script language="JavaScript">
alert("��ȣ�� �Է����� �����̽��ϴ�.");
history.go(-1);
</script>
<%
		return;
	}
%>

<jsp:useBean id="userd" class="kr.co.hanbitbook.ejb.examples.shop.UserDataBean" scope="session" />
<%
	Context ctx = null;
	UserManager userManager = null;
	boolean uflag = false;
	try{
		ctx = new InitialContext();
		Object h = ctx.lookup("UserManager");
		UserManagerHome home = (UserManagerHome)PortableRemoteObject.narrow(h, UserManagerHome.class);
		userManager = home.create();
		UserDataBean ubean = userManager.getUser(id);
		if(id.equals(ubean.getId()) && passwd.equals(ubean.getPasswd())){
			userd.setId(ubean.getId());
			userd.setName(ubean.getName());
			userd.setPasswd(ubean.getPasswd());
			userd.setUserlevel(ubean.getUserlevel());
			uflag = true;
		}
	}catch(Exception e){
%>
<script language="JavaScript">
alert("�ش� ���̵��� ����ڴ� �������� �ʽ��ϴ�.");
history.go(-1);
</script>
<%
		return;
	}
	
	if(uflag){
%>
	<meta http-equiv="refresh" content="0;url=categorylist.jsp" /> 
<%
	}else{
%>
<script language="JavaScript">
alert("��ȣ�� ��ġ���� �ʽ��ϴ�..");
history.go(-1);
</script>
<%
		return;
	}
%>
</body>
</html>
