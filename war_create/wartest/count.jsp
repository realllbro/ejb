<%@ page contentType="text/html;charset=EUC-KR"%>
<%     // 서블릿으로 부터 전달된 Integer객체를 읽어오는 부분.
	Integer icount = (Integer)request.getAttribute("count");
	String count = icount.toString();
%>
<html>
<head>
<title> 카운트 출력 </title>
</head>
<body>
	<h3>
	지금까지 방문한 사용자는 <%=count%> 입니다.
	</h3>
</body>
</html>
