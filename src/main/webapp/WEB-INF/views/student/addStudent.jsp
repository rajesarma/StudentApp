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
	<%--<link rel="stylesheet" type="text/css" href="css/index.css">--%>

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
						<%--<div class="err-message"> ${message} </div>--%>


							<div class="student-form-input ">
								<label>Full name </label>
								<div class="form-text">
									<i class="fa fa-user" aria-hidden="true"></i>
									<form:input path="name" name="name" id="name"
												cssClass="student-form-text"/>
								</div>
							</div>

							<div class="student-form-input ">
								<label>Father Name </label>
								<div class="form-text">
									<i class="fa fa-user" aria-hidden="true"></i>

									<form:input path="fatherName" name="fatherName" id="fatherName"
												cssClass="student-form-text"/>
								</div>
							</div>

							<div class="student-form-input ">
								<label>Mother Name </label>
								<div class="form-text">
									<i class="fa fa-user" aria-hidden="true"></i>

									<form:input path="motherName" name="motherName" id="motherName"
												cssClass="student-form-text"/>
								</div>
							</div>

							<div class="student-form-input ">
								<label>Academic Year </label>
								<div class="form-text">
									<form:select path="batch" name="batch" id="branch"
												 multiple="false" cssClass="student-form-select">
										<form:option value="NONE" label="Select" />
										<form:options items="${academicYears}" />
									</form:select>
								</div>
							</div>

							<div class="student-form-input ">
								<label>Branch </label>
								<div class="form-text">
									<form:select path="branch" name="branch" id="branch"
												 multiple="false" cssClass="student-form-select">
										<form:option value="NONE" label="Select" />
										<form:options items="${branches}" />
									</form:select>
								</div>
							</div>

							<div class="student-form-input ">
								<label>Blood Group</label>
								<div class="form-text">
									<i class="fa fa-user" aria-hidden="true"></i>

									<form:select path="bloodGroup" name="bloodGroup" id="bloodGroup"
												 multiple="false" cssClass="student-form-select">
										<form:option value="NONE" label="Select" />
										<form:options items="${bloodGroups}" />
									</form:select>
								</div>
							</div>

							<div class="student-form-input ">
								<label>Parent Phone No.</label>
								<div class="form-text">
									<i class="fa fa-user" aria-hidden="true"></i>

									<form:input path="parentPhoneNo" name="parentPhoneNo" id="parentPhoneNo"
												cssClass="student-form-text"/>
								</div>
							</div>

							<div class="student-form-input ">
								<label>Alternate Phone No. </label>
								<div class="form-text">
									<i class="fa fa-user" aria-hidden="true"></i>

									<form:input path="alternativePhoneNo" name="alternativePhoneNo" id="alternativePhoneNo"
												cssClass="student-form-text"/>
								</div>
							</div>

							<div class="student-form-input ">
								<label>Aadhar Card No.</label>
								<div class="form-text">
									<i class="fa fa-user" aria-hidden="true"></i>

									<form:input path="aadharNo" name="aadharNo" id="aadharNo"
												cssClass="student-form-text"/>
								</div>
							</div>

							<div class="student-form-input ">
								<label>DOB</label>
								<div class="form-text">
									<i class="fa fa-user" aria-hidden="true"></i>

									<form:input path="dob" name="dob" id="dob" maxlength="10"
												cssClass="student-form-text"/> (DD/MM/YYYY)
								</div>
							</div>

							<div class="student-form-input ">
								<label>DOJ</label>
								<div class="form-text">
									<i class="fa fa-user" aria-hidden="true"></i>

									<form:input path="doj" name="doj" id="doj"
												maxlength="10"
												cssClass="student-form-text"/> (DD/MM/YYYY)
								</div>
							</div>

							<div class="student-form-input ">
								<label>Roll No. </label>
								<div class="form-text">
									<i class="fa fa-user" aria-hidden="true"></i>

									<form:input path="rollno" name="rollno" id="rollno"
												cssClass="student-form-text"/>
								</div>
							</div>

							<div class="student-form-input ">
								<label>eMail</label>
								<div class="form-text">
									<i class="fa fa-user" aria-hidden="true"></i>

									<form:input path="email" name="email" id="email"
												cssClass="student-form-text"/>
								</div>
							</div>



							<div class="student-form-input " style="width: 64%">
								<label>Gender</label>
								<div class="form-text">
									<i class="fa fa-user" aria-hidden="true"></i>

									<form:radiobutton path="gender" value="M"
													  id="gender"/> Male
									<form:radiobutton path="gender" value="F"
													  id="gender"/> Female

								</div>
							</div>

							<div class="student-form-input " style="float: left">
								<label>Photo</label>
								<div class="form-text">
									<i class="fa fa-user" aria-hidden="true"></i>

									<input type="file" name="photoName" id="photoName"
										   cssClass="student-form-text" onchange="openFile(event)" />



									<div id="applicantPhotoName" ></div>
									<script>
										var openFile = function(event)
										{
											var input = event.target;
											var fileSize = input.files[0].size;
											var output = document.getElementById('passportPhoto');

											var reader = new FileReader();
											reader.onload = function()
											{
												var dataURL = reader.result;
												output.src = dataURL;
												output.width = 100;
												output.height = 100;
												var applicantPhotoName = document.getElementById('applicantPhotoName');
												applicantPhotoName.style.display="none";
											};
											reader.readAsDataURL(input.files[0]);
										};
									</script>
								</div>
								<img src='${photo}' width='80px' height='80px' id="passportPhoto" style='border: 1px solid black' ></img>
							</div>

							<div class="student-form-input " >
								<label>Address </label>
								<div class="form-text">
									<i class="fa fa-user" aria-hidden="true"></i>

									<form:textarea path="address" name="address" id="address"
												cssClass="student-form-text"
												   cssStyle="height: 100px"/>
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