<%@ page contentType="text/html;charset=EUC-KR"%>
<html>
<head>
<title> 1���� 100������ �� ���ϱ�</title>
</head>
<body>
	<h3>
	1���� 100������ �� :

	<%
		int sum = 0;
		for(int i = 1; i <=100; i++){
			sum += i;
		}
		out.println(sum);
	%>
	</h3>
</body>
</html>
