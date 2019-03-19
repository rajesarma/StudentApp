<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
		 pageEncoding="ISO-8859-1" %>
<!DOCTYPE html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Site</title>

	<script src="<c:url value="${pageContext.request.contextPath}/js/md5.js" />"></script>
	<script>
		function checkValues()
		{
			if (document.forms[0].userName.value=="")
			{
				alert("Enter Username");
				document.forms[0].userName.value="";
				return false;
			} else if (document.forms[0].password.value=="")
			{
				alert("Enter Password");
				document.forms[0].password.value="";
				return false;
			} else
			{
				document.forms[0].password.value = md5(document.forms[0].password.value);
				document.forms[0].submit();
			}
		}
	</script>

</head>
<body>

	<div class="main-bg">

	<h1><!-- title --></h1>

	<div class="sub-main-w3">
		<div class="bg-content-w3pvt">
			<div class="top-content-style">
			<!-- <img src="images/model.jpg" alt="" /> -->
				<%--Enter into My Site--%>

			</div>
	  		<form:form action="/login" method="post" modelAttribute="user">
				<p class="legend">Login Here<span class="fa fa-hand-o-down"></span></p>
				<div class="input">
					<form:input path="userName" name="userName" id="userName"
								placeholder="User Name" />
						<span class="fa fa-envelope"></span>
					</div>
				<div class="input">
					<form:input path="password" name="password" id="password"
								placeholder="Password" />

					<span class="fa fa-unlock"></span>
				</div>
				<div class="err-message">
					${message}
				</div>
				<button type="submit" class="btn submit" onclick="checkValues()">
					Login
				</button>
			</form:form>
			<a href="#" class="bottom-text-w3ls">Forgot Password?</a>
		</div>
	</div>


		<div class="copyright">
			<h2>	</h2>
		</div>
	</div>
</body>
</html>