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
	<%--<meta http-equiv="Refresh" content="5"; url="/logout"/>--%>
	<%--<META HTTP-EQUIV="Refresh" CONTENT="1800;URL=/expireSession">--%>



	<link rel="icon" href="<c:url value="/images/my_logo.jpg" />" type="image/x-icon" />

	<link rel="stylesheet" href="<c:url value="css/loading.css" />" type="text/css" />

	<link rel="shortcut icon" href="<c:url value="/images/my_logo.jpg" />"
		  type="image/x-icon" />

	<link rel="stylesheet" type="text/css"  href="<c:url value="css/index.css" />" />
	<link rel="stylesheet" type="text/css"  href="<c:url value="css/styles.css" />" />
	<link rel="stylesheet" type="text/css"  href="<c:url value="css/menus.css" />"/>

	<script src="<c:url value="js/jquery-1.12.4.min.js" />"></script>
	<script src="<c:url value="js/menus.js" />"></script>

	<script>
		if(window.history.length>0)
		{
			window.history.forward(4);
		}
	</script>

	<script type="text/javascript">
		$(function() {
			$("#loading").show().delay(400).fadeOut(1000);
		});

		function loading() {
			$("#loading").show();
		}

	</script>

	<style>
		.loading-div {
			top: 50%;
			padding-top: 300px;
			text-align: center;
			font-size: 20px;
		}

		.mt-minus-20 {
			margin-top: -20px;
		}

		.min-ht-50 {
			min-height: 50px;
		}

		.min-ht-500 {
			min-height: 500px;
		}
	</style>

</head>
<body style="overflow-x:hidden;" >
	<div class="main-bg">

		<%--<div id="loading" class="loading">
			<div class="loading-div">
				<div id="cssload-loader" >
					<ul>
						<li></li><li></li><li></li><li></li><li></li><li></li><li></li><li></li>
					</ul>
				</div>
				<div style="padding-top: 10px">
					<b>Loading... </b>
				</div>
			</div>
		</div>--%>

		<div class="page mt-minus-20">

				<div class="min-ht-50" style="background: #1cc7d0">
					<tiles:insertAttribute name="menu" />
				</div>
				<div class="min-ht-500 bg-white" > <%--style="background: #cacaca"--%>
					<br />
					<tiles:insertAttribute name="body" />
					<tiles:insertAttribute name="footer" />
				</div>
		</div>
	</div>
</body>
</html>