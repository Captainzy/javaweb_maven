<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div>
		<form:form method="POST" action="/javaweb/valid/validTest" commandName="testBean">
			<form:input path="age" />
			<form:errors path="age" />
			<form:input path="name" />
			<form:errors path="name" />
			<form:input path="telPhone" />
			<form:errors path="telPhone" />
			<button type="submit">提交</button>
		</form:form>
	</div>
</body>
</html>