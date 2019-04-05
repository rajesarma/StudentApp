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

	<%--<link rel="stylesheet" type="text/css"  href="<c:url
		value="${pageContext.request.contextPath}/css/form-data.css" />" />--%>

	<script
			src="${pageContext.request.contextPath}/js/custom/form_validations.js"></script>

	<script>

		var openFile = function(event)
		{
			var input = event.target;
			var output = document.getElementById('photoData');

			var reader = new FileReader();
			reader.onload = function() {
				output.src = reader.result;
				output.width = 80;
				output.height = 90;
			};
			reader.readAsDataURL(input.files[0]);


		};

		function checkSelection(obj, message)
		{
			//alert(obj.value)
			var id = obj.id;
			var e = document.getElementById(obj.id);
			var optionSelIndex = e.options[e.selectedIndex].value;
			var optionSelectedText = e.options[e.selectedIndex].text;

			if (optionSelIndex == 0)
			{
				alert("Please select " + message);
				document.getElementById(id).focus();
				document.getElementById(id).value = obj.value;
				return false;
			}
		}

		function submitData(action)
		{
			var studentForm = document.getElementById('studentForm');
			studentForm.action = action;
			studentForm.method = "post";
			studentForm.submit();
		}

		function deleteStudentData(actionType)
		{
			document.forms[0].mode.value= actionType;
			document.forms[0].submit();
		}

		function checkData(type,obj)
		{
			var url = '${Role}/student/add/' + type + '/' + obj.value;
			var message;
			$.ajax( {
				type: "POST",
				url:url,
				cache: false,
				success: function(response) {
					$("#wait").html("");
					var response = jQuery.parseJSON(response);

					if(type == "checkRollNo")
					{
						var rollNoExists = response.rollNoExists;
						var message = response.message;
						if(rollNoExists == "true")
						{
							$("#wait").html("<center style='font-size: 14px' ><span id='loading' style='font-size: 14px; color:red'><i class='fa fa-spinner fa-spin' style='font-size:24px'></i> <b>" + message + "</b></span></center>");
							document.forms[0].rollNo.value = "";
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
								<spring:message code="student.add"/>
							</h3>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>

	<section id="maincontent">
		<div class="container">


			<form:form action="${Role}/student/add" id="studentForm"
					   modelAttribute="studentData" enctype="multipart/form-data"
					   cssClass="form-horizontal">
			<form:hidden path="studentId" name="studentId" id="studentId" />

			<%--<aside>--%>
			<div class="row ">
				<div class="span6">
					<div class="centered">
								<%--<a style="color: green; text-decoration: underline;" href="${Role}/student/list">Back to Student Report</a>--%>

					<div id="wait"></div>
					<div class="err-message" style="text-align:center"> ${message}</div>



						<div class="control-group">
							<label class="control-label align-left" for="name">
								<spring:message code="student.fullName"/>
							</label>

							<div class="controls">
								<form:input path="name" name="name" id="name"
											cssClass="span3"
											onkeyup="charOnly(this)"
								/>
								<span class="help-block">
									<form:errors path="name" cssClass="error" />
								</span>
							</div>
						</div>

						<div class="control-group">

							<label class="control-label align-left" for="fatherName">
								<spring:message code="student.fatherName"/>
							</label>
							<div class="controls">
								<form:input path="fatherName" name="fatherName" id="fatherName"
											cssClass="span3"
											onkeyup="charOnly(this)" />
								<span class="help-block">
									<form:errors path="fatherName" cssClass="error" />
								</span>
							</div>
						</div>

						<div class="control-group">
							<label class="control-label align-left" for="motherName">
								<spring:message code="student.motherName"/>
							</label>
							<div class="controls">
								<form:input path="motherName" name="motherName" id="motherName"
											cssClass="span3" onkeyup="charOnly(this)" />
								<span class="help-block">
									<form:errors path="motherName" cssClass="error" />
								</span>
							</div>
						</div>

						<div class="control-group">
							<label class="control-label align-left" for="parentPhoneNo">
								<spring:message code="student.parentPhoneNo"/>
							</label>
							<div class="controls">
								<form:input path="parentPhoneNo" name="parentPhoneNo" id="parentPhoneNo"
											cssClass="span3"
											onkeyup="intOnly(this)"/>
								<span class="help-block">
									<form:errors path="parentPhoneNo" cssClass="error" />
								</span>
							</div>
						</div>

						<div class="control-group">
							<label class="control-label align-left" for="alternativePhoneNo">
								<spring:message code="student.alternativePhoneNo"/>
							</label>
							<div class="controls">
								<form:input path="alternativePhoneNo" name="alternativePhoneNo" id="alternativePhoneNo"
											cssClass="span3" onkeyup="intOnly(this)"/>
								<span class="help-block">
									<form:errors path="alternativePhoneNo" cssClass="error" />
								</span>
							</div>
						</div>

						<div class="control-group">
							<label class="control-label align-left" for="aadharNo">
								<spring:message code="student.aadharNo"/>
							</label>
							<div class="controls">
								<form:input path="aadharNo" name="aadharNo" id="aadharNo"
											cssClass="span3" onkeyup="intOnly(this)"/>
								<span class="help-block">
									<form:errors path="aadharNo" cssClass="error" />
								</span>
							</div>
						</div>

						<div class="control-group">
							<label class="control-label align-left" for="rollNo">
								<spring:message code="student.rollNo"/>
							</label>
							<div class="controls">
								<form:input path="rollNo" name="rollNo" id="rollNo" cssClass="span3"
											onkeypress="javascript:return isAlphaNumeric(event,this.value);"
											onblur="checkData('checkRollNo', this)" />
								<span class="help-block">
									<form:errors path="rollNo" cssClass="error" />
								</span>
							</div>
						</div>

						<div class="control-group">
							<label class="control-label align-left" for="email">
								<spring:message code="student.eMail"/>
							</label>
							<div class="controls">
								<form:input path="email" name="email" id="email"
											cssClass="span3" onblur="checkEmail(this);"/>
								<span class="help-block">
									<form:errors path="email" cssClass="error" />
								</span>
							</div>
						</div>

						<div class="control-group">
							<label class="control-label align-left" for="email">
								<spring:message code="student.photo"/>
							</label>
							<div class="controls">
								<input type="file" name="photo" id="photo"
									   cssClass="span3" onchange="openFile(event)" />
								<div id="applicantPhotoName" ></div>

								<span class="help-block">
									<c:if test="${empty photoData}">
											<img src="data:image;base64,${photoData }" id="photoData"
												 style='border: 1px solid black; visibility: collapse'
												 width='80px' class="img-polaroid"
												 height='80px'>
									</c:if>
									<c:if test="${not empty photoData}">
										<img src="data:image;base64,${photoData }" id="photoData"
											 style='border: 1px solid black; visibility: visible'
											 width='80px' class="img-polaroid"
											 height='80px'>
									</c:if>
								</span>
							</div>
						</div>

					</div>
				</div>

				<%--First Box--%>



				<%--Second Box--%>
				<div class="span6">
					<div class="centered">
						<%--<a style="color: green; text-decoration: underline;" href="${Role}/student/list">Back to Student Report</a>--%>

						<div id="wait"></div>
						<div class="err-message" style="text-align:center"> ${message}</div>


							<div class="control-group">
								<label class="control-label align-left" for="academicYearId">
									<spring:message code="student.academicYear"/>
								</label>

								<div class="controls">
									<form:select path="academicYearId" name="academicYearId" id="academicYearId"
												 multiple="false"
												 cssClass="span3"
												 onchange="checkSelection(this, 'Academic Year')">
										<form:option value="0" label="Select" />
										<form:options items="${academicYears}" />
									</form:select>
									<span class="help-block">
									<form:errors path="academicYearId" cssClass="error" />
								</span>
								</div>
							</div>

							<div class="control-group">

								<label class="control-label align-left" for="branchId">
									<spring:message code="student.branch"/>
								</label>
								<div class="controls">
									<form:select path="branchId" name="branchId" id="branchId"
												 multiple="false"
												 cssClass="span3" onchange="checkSelection(this, 'Branch')">
										<form:option value="0" label="Select" />
										<form:options items="${branches}"
										/>
									</form:select>
									<span class="help-block">
									<form:errors path="branchId" cssClass="error" />
								</span>
								</div>
							</div>

							<div class="control-group">

								<label class="control-label align-left" for="joiningYearNo">
									<spring:message code="student.joiningYearNo"/>
								</label>
								<div class="controls">
									<form:select path="joiningYearNo" name="joiningYearNo" id="joiningYearNo"
												 multiple="false"
												 cssClass="span3" onchange="checkSelection(this, 'Joined in Year')">
										<form:option value="0" label="Select" />
										<form:options items="${batches}" />
									</form:select>
									<span class="help-block">
									<form:errors path="joiningYearNo" cssClass="error" />
								</span>
								</div>
							</div>

							<div class="control-group">
								<label class="control-label align-left" for="bloodGroupId">
									<spring:message code="student.bloodGroup"/>
								</label>
								<div class="controls">
									<form:select path="bloodGroupId" name="bloodGroupId" id="bloodGroupId"
												 multiple="false"
												 cssClass="span3"
												 onchange="checkSelection(this, 'Blood Group')">
										<form:option value="0" label="Select" />
										<form:options items="${bloodGroups}" />
									</form:select>
									<span class="help-block">
									<form:errors path="bloodGroupId" cssClass="error" />
								</span>
								</div>
							</div>

							<div class="control-group">
								<label class="control-label align-left" for="height">
									<spring:message code="student.height"/>
								</label>
								<div class="controls">
									<form:input path="height" name="height" id="height"
												cssClass="span3" onkeyup="intOnly(this)"/>
									<span class="help-block">
									<form:errors path="height" cssClass="error" />
								</span>
								</div>
							</div>



							<div class="control-group">
								<label class="control-label align-left" for="dob">
									<spring:message code="student.dob"/>
								</label>
								<div class="controls">
									<form:input path="dob" name="dob" id="dob" maxlength="10"
												cssClass="span2"/>
									<span class="help-inline date-format">
										<spring:message code="dateFormat"/>
									</span>
									<span class="help-block">
									<form:errors path="dob" cssClass="error" />
								</span>
								</div>
							</div>

							<div class="control-group">
								<label class="control-label align-left" for="doj">
									<spring:message code="student.doj"/>
								</label>
								<div class="controls">
									<form:input path="doj" name="doj" id="doj" maxlength="10"
												cssClass="span2"/>
									<span class="help-inline date-format">
										<spring:message code="dateFormat"/>
									</span>
									<span class="help-block">
									<form:errors path="doj" cssClass="error" />
								</span>
								</div>
							</div>


							<div class="control-group">
								<label class="control-label align-left" >
									<spring:message code="student.gender"/>
								</label>
								<div class="controls pull-left">

									<label class="radio inline" for="gender">
										<form:radiobutton path="gender" value="M"
													  id="gender"/> Male
									</label>
									<label class="radio inline" for="gender">
										<form:radiobutton path="gender" value="F"
													  id="gender"/> Female
									</label>
									<span class="help-block">
									<form:errors path="gender" cssClass="error" />
								</span>
								</div>
							</div>

							<div class="control-group">
								<label class="control-label align-left" >
									<spring:message code="student.address"/>
								</label>
								<div class="controls pull-left">

									<form:textarea path="address" name="address" id="address"
												   cssClass="span3" rows="3"
												   cssStyle="height: 100px"/>
									<span class="help-block">
									<form:errors path="address" cssClass="error" />
								</span>
								</div>
							</div>
					</div>
				</div>

				<div class="span12">
					<div align="center" style="width: 100%;">
						<input type="button" class="btn btn-primary" value="${buttonValue }"
							   onclick="submitData('${action}')" />
					</div>
				</div>

			</div>
			</form:form>
			<%--</aside>--%>
		</div>

	</section>

</body>
</html>