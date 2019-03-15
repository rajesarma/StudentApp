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
	<link rel="stylesheet" type="text/css" href="css/index.css">

	<link rel="stylesheet" type="text/css"  href="<c:url value="css/form-data.css" />" />

</head>
<body>
	<div class="container_form">

			<div class="sub-main-w2">
				<div class="bg-content-w2pvt">
					<div class="top-content-style-pages">
						<p class="legend">Add Student</p>
					</div>
					<form:form action="/studentAdd" method="post" modelAttribute="sudentData">
						<div class="err-message"> ${message} </div>

						<div class="student-form-input">
							<label>Full name : </label>
							<div class="form-text">
								<i class="fa fa-user" aria-hidden="true"></i>
								<form:input path="name" name="name" id="name"
											placeholder="Student Name" cssClass="student-form-text"/>
							</div>
						</div>

						<div class="student-form-input">
							<label>Student Roll No. : </label>
							<div class="form-text">
								<i class="fa fa-user" aria-hidden="true"></i>

								<form:input path="rollno" name="rollno" id="rollno"
											placeholder="Student Roll No." cssClass="student-form-text"/>
							</div>
						</div>

						<div class="input">
							<form:select path="branch" name="branch" id="branch"
										 multiple="false">
							<form:option value="NONE" label="Select" />
							<form:options items="${branches}" />
							</form:select>
						</div>


						</div>

						<%--<button type="submit" class="btn submit" onclick="checkValues()">
							Add
						</button>--%>
					</form:form>
				</div>
			</div>
	</div>
</body>
</html>