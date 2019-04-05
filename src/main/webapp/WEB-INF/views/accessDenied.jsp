<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
		 pageEncoding="ISO-8859-1" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Site</title>
</head>
<body>
	Dear <strong>${user}</strong>, You are not authorized to perform this action.
	Click to back to <a href="/home">Home</a>
	or
	<a href="/logout">Logout</a>
</body>
</html>