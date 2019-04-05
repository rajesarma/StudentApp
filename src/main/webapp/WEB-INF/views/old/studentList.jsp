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

	<link rel="stylesheet" type="text/css"  href="<c:url
	value="${pageContext.request.contextPath}/css/custom/form-data.css" />" />

	<link rel="stylesheet" type="text/css"  href="<c:url
	value="${pageContext.request.contextPath}/css/custom/dataTables.jqueryui.min.css" />" />

	<%--<link rel="stylesheet" type="text/css"  href="<c:url
	value="${pageContext.request.contextPath}/css/custom/jquery-ui.css" />" />--%>

	<%--<script src="${pageContext.request.contextPath}/js/jquery-3.3.1.js"></script>--%>
	<script
			src="${pageContext.request.contextPath}/js/custom/jquery.dataTables.min.js"></script>
	<%--<script src="${pageContext.request.contextPath}/js/dataTables.jqueryui.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/jquery-ui.js"></script>--%>

	<script>
		$(document).ready(function() {
			$('#studentList').DataTable();
		} );

	</script>


</head>
<body>
	<div class="container_form">

			<div class="sub-main-w2">
				<div class="bg-content-w2pvt">
					<div class="top-content-style-pages">
						<p class="title-heading">List</p>
					</div>

					<form:form action="${Role}/student/list" modelAttribute="studentData">
						<%--<div class="err-message"> ${message} </div>--%>
							<div class="student-form-input ">
									<label><spring:message code="student.academicYear"/></label>
								<div class="form-text">
									<form:select path="academicYearId" name="academicYearId" id="academicYearId"
												 multiple="false" cssClass="student-form-select">
										<form:option value="0" label="Select" />
										<form:options items="${academicYears}" />
									</form:select>
								</div>
							</div>

							<div class="student-form-input ">
								<label><spring:message code="student.branch"/></label>
								<div class="form-text">
									<form:select path="branchId" name="branchId" id="branchId"
												 multiple="false" cssClass="student-form-select">
										<form:option value="0" label="Select" />
										<form:options items="${branches}" />
									</form:select>
								</div>
							</div>


						<button type="submit" class="student-form-select-submit" onclick="checkValues()">
							Get Data
						</button>
					</form:form>
				</div>
			</div>
		<br>
		<c:if test="${not empty message}">
			<div class="err-message" style="text-align:center"> ${message}</div>
		</c:if>

		<c:if test="${not empty studentList}">
		
			<table class="table " id="studentList" >
				<thead>
					<th scope="row">Year</th>
					<th scope="row">Branch</th>
					<th scope="row">Roll No</th>
					<th scope="row">Name</th>
					<th scope="row">Father Name</th>
					<th scope="row">Photo</th>
					<th scope="row">DOB</th>
					<th scope="row">DOJ</th>
					<th scope="row">Action</th>
				</thead>
				<tbody>
					<c:forEach items="${studentList }" var="student" >
						<tr>
							<td>${student.year }</td>
							<td>${student.branch }</td>
							<td>${student.rollNo }</td>
							<td>${student.name }</td>
							<td>${student.fatherName }</td>
							<td style="text-align: center; vertical-align: baseline">
								<c:if test="${not empty student.photoData}">
									<%--<img src="data:image/ext;base64,${student.photoData
									 }" />--%>

									<img src="data:image;base64, ${student.photoData }"
										 style='border: 1px solid black' width='25px'
										 height='30px'>
								</c:if>
							</td>
							<td>${student.dob }</td>
							<td>${student.doj }</td>
							<td>
								<a style="color: green; text-decoration: underline;" href="${Role}/student/edit/${student.studentId}/update
"> <spring:message code="edit"/>
								</a>
								<span style="padding: 10px"></span>
								<a style="color: red; text-decoration: underline;"
								   href="${Role}/student/edit/${student.studentId}/delete
"><spring:message code="delete"/>
								</a>
								<span style="padding: 10px"></span>
									<%--<spring:url value="/article/updateArticle/${article.id }" var="updateURL" />--%>
									<%--<a class="btn btn-primary" href="${updateURL }" role="button" >Update</a>--%>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</c:if>

	</div>

</body>
</html>