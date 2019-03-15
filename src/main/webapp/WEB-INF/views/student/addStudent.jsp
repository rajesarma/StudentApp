<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
		 pageEncoding="ISO-8859-1" %>
<!DOCTYPE html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Student Add</title>

</head>
<body>
	<div class="container_form">
	<div class="header" >
		<h3 style="color:#fff; text-align: center;">Add Student</h3>
	</div>

		<div class="main-bg">

		<h1><!-- title --></h1>

			<div class="sub-main-w3">
				<div class="bg-content-w3pvt">
					<div class="top-content-style">
						<!-- <img src="images/model.jpg" alt="" /> -->
						<%--Enter into My Site--%>
					</div>
					<form:form action="/studentAdd" method="post" modelAttribute="sudentData">
						<p class="legend">Login Here<span class="fa fa-hand-o-down"></span></p>
						<div class="input">
							<form:select path="branches" name="branches" id="branches"
										 multiple="false">
							<form:option value="NONE" label="Select" />
							<form:options items="${branches}" />
							</form:select>
						</div>
						</div>
						<div class="err-message">
								${message}
						</div>
						<%--<button type="submit" class="btn submit" onclick="checkValues()">
							Add
						</button>--%>
					</form:form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>