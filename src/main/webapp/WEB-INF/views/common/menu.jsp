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
	<div class="container">
		<%--<div id="logo" >
			<img alt="" src="images/logo.jpg" style="height: 80px; margin-left: 20px">
		</div>--%>
		<div id="title" style="text-align: left;  margin-left: 20px" >
			<%--<H1 style="font-size: 36px; margin-top: -5px; margin-bottom:-25px; font-stretch: extra-expanded;">ALIET</H1>--%>
			<%--<h3 style="font-size: 30px; margin-bottom: 0px">Student Information Retrieval</h3>--%>
			<%--<H1></H1>--%>
			<div class="heading">Hi</div>

		</div>
	</div>
	<div class="container">
		<div id="menu" style="margin-top: 5px; width: 100%; border-radius:0 !important; border-top: 3px solid #ff8100; " class="navbg" >

		</div>

	</div>
	<div class="user-info" >
		Welcome : ${user_desc }

		<span style="padding: 10px"></span>
		<a href="/logout">Log Out</a>
	</div>

	<script type="text/javascript">
		buildMenu('menu', eval('${servicesMenu}'));
	</script>

	<%--<script type="text/javascript">
		$(function()
		{
			$('#main-menu').smartmenus({
				mainMenuSubOffsetX: -1,
				mainMenuSubOffsetY: 4,
				subMenusSubOffsetX: 6,
				subMenusSubOffsetY: -6
			});
		});
	</script>--%>


</body>
</html>