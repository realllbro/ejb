 <%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%> 
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%> 
<html> 
	<head>
		<meta name = "Generator" content = "Easy Struts Xslt generator for Eclipse (http://easystruts.sf.net).">

		<title>방명록 글쓰기 폼</title>
	</head>
	<body>
		<html:form action="/write">
			</br>
			이름 : <html:text property="name"/><html:errors property="name"/></br>
			내용 : <html:textarea property="content"/><html:errors property="content"/><br/>
			<html:submit value="입력"/><html:cancel value="취소"/>
		</html:form>
	<body>
</html>
