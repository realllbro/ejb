<%@ page contentType="text/html;charset=EUC-KR"%>
<html>
<head>
<title> 1부터 100까지의 합 구하기</title>
</head>
<body>
	<h3>
	1부터 100까지의 합 :

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
