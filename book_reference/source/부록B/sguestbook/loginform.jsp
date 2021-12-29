 <%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%> 
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%> 
<html> 
	<head>
		<meta name = "Generator" content = "Easy Struts Xslt generator for Eclipse (http://easystruts.sf.net).">

		<title>Struts Form for loginForm</title>
	</head>
	<body>
		<html:form action="/login">
			아이디 : <html:text property="id"/><html:errors property="id"/></br>
			암호 : <html:password property="passwd"/><html:errors property="passwd"/></br>
			<html:submit value="입력"/><html:cancel value="취소"/>
		</html:form>
	<body>
</html>
