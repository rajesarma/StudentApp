<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8" %>
<!DOCTYPE html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Student Add</title>

	<script>

		function submitData(action) {
			var student = document.getElementById('student');
			student.action = action;
			student.method = "post";
			student.submit();
		}


		function getData(type,obj, targetId) {
			if(obj.value.length > 0) {
				var url = '${Role}/student/' + type + '/' + obj.value;
				var message;
				$.ajax( {
					type: "GET",
					url:url,
					cache: false,
					success: function(response) {
						$("#wait").html("");
						var response = jQuery.parseJSON(response);

						if(type == "getSemesters") {

							if(response.semestersExists == "true")
							{
								var semesters =
										response.semesters.replace("{","").replace("}","").replace(",","");
								var semOptions =
										JSON.parse('{"'+semesters.replace(/ /g, '", "').replace(/=/g, '": "')+ '"}');

								$("#"+targetId).empty().append('<option selected="selected" value="0">Select</option>');

								$.each(semOptions, function(val, text) {
									$("#"+targetId).append($('<option></option>').val(val).html(text));
								});
							}
						}
					}, error: function(response) {
						//alert("No data");
						$("#wait").html("<center style='font-size: 14px' ><span id='loading' style='font-size: 14px; color:red'><i class='fa fa-spinner fa-spin' style='font-size:24px'></i> <b>" + response.error + "</b></span></center>");
					},
					beforeSend: function( event, ui ) {
						$("#wait").html("<center style='font-size: 14px' ><span id='loading' style='font-size: 14px; color:red'><i class='fa fa-spinner fa-spin' style='font-size:24px'></i> <b>Loading... </b></span></center>");
					}
				});
			}
		}

	</script>
	<style>
		.error {
			color: #ff0000;
			font-style: italic;
			font-weight: bold;
			font-size: 10px;
		}

		.align-left {
			text-align: left !important;
		}
	</style>
</head>
<body>

	<section id="subintro">
		<div class="jumbotron subhead" id="overview">
			<div class="container">
				<div class="row">
					<div class="span12">
						<div class="centered">
							<h3>
								<spring:message code="student.add.attendance"/>
							</h3>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>

	<section id="maincontent">
		<div class="container">
			<div class="row ">
				<div class="span12">
					<div class="centered">

						<form:form action="${Role}/student/addStudentAttendance"
								   cssClass="form-inline"
								   id="studentListForm"
								   modelAttribute="studentAttendanceDto">

							<label class="control-label align-left" for="batchId">
								<spring:message code="student.batch"/>
							</label>

							<form:select path="batchId" name="batchId" id="batchId"
										 multiple="false" cssClass="span2">
								<form:option value="0" label="Select" />
								<%--<form:options items="${batches}" />--%>

								<c:forEach items="${batches}" var="batch">
									<option value="${batch.key}"
											<c:if
													test="${batch.key eq
													selectedBatchId}">selected="true"</c:if>
									> ${batch.value}
									</option>
								</c:forEach>

							</form:select>

							<span style="padding: 10px"></span>

							<label class="control-label align-left" for="branchId">
								<spring:message code="student.branch"/>
							</label>

							<form:select path="branchId" name="branchId" id="branchId"
										 multiple="false" cssClass="span2">
								<form:option value="0" label="Select" />

								<c:forEach items="${branches}" var="branch">
									<option value="${branch.key}"
											<c:if test="${branch.key eq selectedBranchId}">selected="true"</c:if>
									> ${branch.value}
									</option>
								</c:forEach>

							</form:select>

							<span style="padding: 10px"></span>

							<label class="control-label align-left" for="academicYearId">
								<spring:message code="student.academicYear"/>
							</label>

							<form:select path="academicYearId" name="academicYearId" id="academicYearId"
										 multiple="false" cssClass="span2" onChange="getData('getSemesters', this, semesterId.id)">
								<form:option value="0" label="Select" />
								<c:forEach items="${years}" var="batch">
									<option value="${batch.key}"
											<c:if test="${batch.key eq selectedYearId}">selected="true"</c:if>
									> ${batch.value}
									</option>
								</c:forEach>
							</form:select>

							<span style="padding: 10px"></span>

							<label class="control-label align-left" for="semesterId">
								<spring:message code="student.semester"/>
							</label>

							<form:select path="semesterId" name="semesterId"
										 id="semesterId"
										 multiple="false" cssClass="span2">
								<form:option value="0" label="Select" />

								<c:forEach items="${semesters}" var="batch">
									<option value="${batch.key}"
											<c:if test="${batch.key eq selectedYearId}">selected="true"</c:if>
									> ${batch.value}
									</option>
								</c:forEach>
							</form:select>

							<span style="padding: 10px"></span>

							<button type="submit" class="btn btn-primary">
								Get Data
							</button>
						</form:form>
					</div>
				</div>
			</div>


			<div class="row ">
				<div class="span12">
					<div class="centered">
						<c:if test="${not empty message}">
							<div class="err-message" style="text-align:center"> ${message}</div>
						</c:if>

						<c:if test="${not empty studentList}">

							<table class="table " id="studentList" >
								<thead>
									<th>S. No.</th>

									<th scope="row">Batch</th>
									<th scope="row">Branch</th>
									<th scope="row">Semester</th>
									<th scope="row">Roll No</th>
									<th scope="row">Name</th>
									<th scope="row">DOJ</th>
								</thead>
								<tbody>
								<c:forEach items="${studentList }" var="student" varStatus="loop">
									<tr>
										<td style="text-align: center">${loop.index}</td>
										<td>${student.batch }</td>
										<td>${student.branch }</td>
										<td>${student.academicYear }</td>
										<td>${student.semester }</td>
										<td>${student.rollNo }</td>
										<td>${student.name }</td>
										<td>${student.doj }</td>
									</tr>
								</c:forEach>
								</tbody>
							</table>
						</c:if>
					</div>
				</div>
			</div>

		</div>
	</section>
</body>
</html>