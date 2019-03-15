<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
		 pageEncoding="ISO-8859-1" %>

<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Site</title>

	<meta http-equiv="pragma" content="no-cache"/>
	<meta http-equiv="cache-control" content="no-cache"/>
	<meta http-equiv="expires" content="0"/>
	<meta http-equiv="keywords" content="Site"/>
	<meta http-equiv="description" content="Site"/>
	<%--<meta http-equiv="Refresh" content="1800"; url="/logout"/>--%>

	<link rel="icon" href="<c:url value="/images/my_logo.jpg" />" type="image/x-icon" />
	<link rel="shortcut icon" href="<c:url value="/images/my_logo.jpg" />"
		  type="image/x-icon" />

	<%--<script>
		if(window.history.length>0)
		{
			window.history.forward(4);
		}
	</script>--%>

</head>
<body>

		<tiles:insertAttribute name="body" />

</body>
</html>