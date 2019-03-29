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

	<%--<meta http-equiv="Refresh" content="5"; url="/logout"/>--%>
	<%--<META HTTP-EQUIV="Refresh" CONTENT="1800;URL=/expireSession">--%>

	<link rel="icon" href="<c:url value="${pageContext.request.contextPath}/images/my_logo.jpg" />" type="image/x-icon" />
	<link rel="stylesheet" href="<c:url
		value="${pageContext.request.contextPath}/css/loading.css" />" type="text/css" />
	<link rel="shortcut icon" href="<c:url value="${pageContext.request.contextPath}/images/my_logo.jpg" />"
		  type="image/x-icon" />
	<link rel="stylesheet" type="text/css"  href="<c:url
	value="${pageContext.request.contextPath}/css/index.css" />" />
	<link rel="stylesheet" type="text/css"  href="<c:url
	value="${pageContext.request.contextPath}/css/styles.css" />" />
	<link rel="stylesheet" type="text/css"  href="<c:url
	value="${pageContext.request.contextPath}/css/menus.css" />"/>

	<script src="${pageContext.request.contextPath}/js/jquery-3.3.1.js"></script>

	<%--<script
			src="${pageContext.request.contextPath}/webjars/jquery/3.3.1-1/jquery.js"></script>
	<script src="${pageContext.request.contextPath}/webjars/popper.js"></script>
	<script
			src="${pageContext.request.contextPath}/webjars/bootstrap/4.1.1/js/bootstrap.js"></script>
	<link
			href="${pageContext.request.contextPath}/webjars/bootstrap/4.1.1/scss/bootstrap.scss"
		  rel="stylesheet" media="screen"/>--%>

	<script
			src="<c:url value="${pageContext.request.contextPath}/js/menus.js" />"></script>

	<script>
		if(window.history.length>0) {
			window.history.forward(4);
		}

		function clickIE4() {
			if (event.button==2) {
				return false;
			}
		}

		function clickOpera() {
			if (event.button==2) {
				return false;
			}
		}

		function clickNS4(e) {
			if (document.layers||document.getElementById&&!document.all) {
				if (e.which==2||e.which==3) {
					return false;
				}
			}
		}

		if (document.layers) {
			document.captureEvents(Event.MOUSEDOWN);
			document.onmousedown=clickNS4;
		} else if (document.all&&!document.getElementById) {
			document.onmousedown=clickIE4;
		} else if (navigator.appName=="Opera") {
			document.captureEvents(Event.MOUSEDOWN);
			document.onmousedown=clickOpera;
		}

		document.oncontextmenu=new Function("return false");


		function disableKey(event) {
			if (!event) event = window.event;
			if (!event) return;

			var keyCode = event.keyCode ? event.keyCode : event.charCode;

			if (event.charCode==116) {
				return true;
			} else if (keyCode == 116) {
				window.status = "F5 key detected! Attempting to disabling default response.";
				window.setTimeout("window.status='';", 2000);

				// Standard DOM (Mozilla):
				if (event.preventDefault) event.preventDefault();

				//IE (exclude Opera with !event.preventDefault):
				if (document.all && window.event && !event.preventDefault) {
					event.cancelBubble = true;
					event.returnValue = false;
					event.keyCode = 0;
				}

				return false;
			}
		}

		function setEventListener(eventListener) {
			if (document.addEventListener) document.addEventListener('keypress', eventListener, true);
			else if (document.attachEvent) document.attachEvent('onkeydown', eventListener);
			else document.onkeydown = eventListener;
		}

		function unsetEventListener(eventListener) {
			if (document.removeEventListener) document.removeEventListener('keypress', eventListener, true);
			else if (document.detachEvent) document.detachEvent('onkeydown', eventListener);
			else document.onkeydown = null;
		}

		setEventListener(disableKey)
		//unsetEventListener(disableKey)


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

				<div class="min-ht-50" style="background: #084B8A">  <%--#1cc7d0--%>
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