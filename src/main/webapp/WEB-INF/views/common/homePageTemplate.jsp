<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
		 pageEncoding="ISO-8859-1" %>

<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Site</title>

	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta name="description" content="StudentApp">
	<meta name="author" content="Lakshmi Rajeswara Sarma">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta http-equiv="pragma" content="no-cache" />
	<meta http-equiv="cache-control" content="no-cache" />
	<meta http-equiv="expires" content="0" />
	<meta http-equiv="AUTOCOMPLETE" content="OFF" />
	<meta http-equiv="keywords" content="Site"/>
	<META HTTP-EQUIV="Refresh" CONTENT="2699;URL=/expireSession">

	<link rel="stylesheet" type="text/css"  href="<c:url
	value="${pageContext.request.contextPath}/css/index.css" />" />
	<link rel="icon" href="<c:url value="${pageContext.request.contextPath}/images/my_logo.jpg" />" type="image/x-icon" />
	<link rel="shortcut icon" href="<c:url value="${pageContext.request.contextPath}/images/my_logo.jpg" />"
		  type="image/x-icon" />

</head>
<body>

		<tiles:insertAttribute name="body" />

</body>
</html>