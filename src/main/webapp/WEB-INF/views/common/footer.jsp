<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
		 pageEncoding="ISO-8859-1" %>
<!DOCTYPE html>

<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>--%>

<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Menu</title>

	<%--<link rel="stylesheet" type="text/css"  href="<c:url value="css/styles.css" />" />
	<link rel="stylesheet" type="text/css"  href="<c:url value="css/menus.css" />"/>--%>

	<%--<script src="<c:url value="js/jquery-1.12.4.min.js" />"></script>
	<script src="<c:url value="js/menus.js" />"></script>--%>

</head>
<body bgcolor="white">

	<script type="text/javascript">
		$(function()
		{
			$('#main-menu').smartmenus({
				mainMenuSubOffsetX: -1,
				mainMenuSubOffsetY: 4,
				subMenusSubOffsetX: 6,
				subMenusSubOffsetY: -6
			});
		});
	</script>


</body>
</html>