 <%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%> 
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%> 
<html> 
	<head>
		<meta name = "Generator" content = "Easy Struts Xslt generator for Eclipse (http://easystruts.sf.net).">

		<title>Struts Form for loginForm</title>
	</head>
	<body>
		<html:form action="/login">
			���̵� : <html:text property="id"/><html:errors property="id"/></br>
			��ȣ : <html:password property="passwd"/><html:errors property="passwd"/></br>
			<html:submit value="�Է�"/><html:cancel value="���"/>
		</html:form>
	<body>
</html>
