<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<%
		String ID = (String) request.getAttribute("id");
	%>
	
<body>
	<form action="http://192.168.1.89:8080/v2/apps">
		your program  <%=ID%>  created successfully!
	</form>
</body>
</html>