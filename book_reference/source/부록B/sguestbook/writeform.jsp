 <%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%> 
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%> 
<html> 
	<head>
		<meta name = "Generator" content = "Easy Struts Xslt generator for Eclipse (http://easystruts.sf.net).">

		<title>���� �۾��� ��</title>
	</head>
	<body>
		<html:form action="/write">
			</br>
			�̸� : <html:text property="name"/><html:errors property="name"/></br>
			���� : <html:textarea property="content"/><html:errors property="content"/><br/>
			<html:submit value="�Է�"/><html:cancel value="���"/>
		</html:form>
	<body>
</html>
