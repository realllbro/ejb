<%@ page contentType="text/html;charset=EUC-KR"%>
<%     // �������� ���� ���޵� Integer��ü�� �о���� �κ�.
	Integer icount = (Integer)request.getAttribute("count");
	String count = icount.toString();
%>
<html>
<head>
<title> ī��Ʈ ��� </title>
</head>
<body>
	<h3>
	���ݱ��� �湮�� ����ڴ� <%=count%> �Դϴ�.
	</h3>
</body>
</html>
