<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<h1>Admin</h1>
	<form action="/logout" method="post">
	  	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
		<button>?α׾ƿ?</button>
	</form>
</body>
</html>