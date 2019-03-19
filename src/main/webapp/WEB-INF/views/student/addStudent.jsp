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
		value="${pageContext.request.contextPath}/css/form-data.css" />" />

	<script src="${pageContext.request.contextPath}/js/form_validations.js"></script>

	<script>


		function batchDetails()
		{
			var e = document.getElementById("batchId");
			var optionSelIndex = e.options[e.selectedIndex].value;
			var optionSelectedText = e.options[e.selectedIndex].text;
			if (optionSelIndex == 0)
			{
				alert("Please select a Batch");
				document.forms[0].branchId.focus();
				return false;
			}

		}
		function branchDetails()
		{
			var e = document.getElementById("branchId");
			var optionSelIndex = e.options[e.selectedIndex].value;
			var optionSelectedText = e.options[e.selectedIndex].text;
			if (optionSelIndex == 0)
			{
				alert("Please select a Branch");
				document.forms[0].branchId.focus();
				return false;
			}
		}
		function onkeydown()
		{
			var ch = String.fromCharCode(event.keyCode);
			var filter = /[a-zA-Z]/   ;
			if(!filter.test(ch))
			{
				event.returnValue = false;
			}
		}
		function isValidNumericValue(obj)
		{
			if (obj.value != null && !obj.value.toString().match(/^[-]?\d*?\d*$/))
			{
				obj.value="";
				window.alert("Enter Numerics Only..!!");
				return false;
			}
			else
			{
				return true;
			}
		}


		function insertStudentData( actionType)
		{
			if(document.forms[0].name.value=="")
			{
				alert("Please Enter Student name");
				document.forms[0].name.focus();
				return false;
			}
			else if(document.forms[0].rollNo.value=="")
			{
				alert("Pelase Enter Student rollNo");
				document.forms[0].rollNo.focus();
				return false;
			}
			else if(document.getElementById("batchId").value == "0")
			{
				alert("Please select Academic Year");
				document.getElementById("batchId").focus();
				return false;
			}
			else if(document.getElementById("branchId").value == "0")
			{
				alert("Please select Student Branch");
				document.getElementById("branchId").focus();
				return false;
			}

			else if(document.forms[0].fatherName.value=="")
			{
				alert("Please Enter Student Father Name");
				document.forms[0].fatherName.focus();
				return false;
			}
			else if(document.forms[0].motherName.value=="")
			{
				alert("Please Enter Student Mother Name");
				document.forms[0].motherName.focus();
				return false;
			}
			else if(document.forms[0].parentPhoneNo.value=="")
			{
				alert("Please Enter Parent phone no");
				document.forms[0].parentPhoneNo.focus();
				return false;
			}
			else if(document.forms[0].alternativePhoneNo.value=="")
			{
				alert("Please Enter alternative phone no");
				document.forms[0].alternativePhoneNo.focus();
				return false;
			}
			else if(document.forms[0].email.value=="")
			{
				alert("Please Enter Student email");
				document.forms[0].email.focus();
				return false;
			}
			else if(document.forms[0].address.value=="")
			{
				alert("Please Enter Student Address");
				document.forms[0].address.focus();
				return false;
			}
			else if(document.forms[0].dob.value=="")
			{
				alert("Please Enter Student Date of Birth");
				document.forms[0].dob.focus();
				return false;
			}
			else if(document.forms[0].doj.value=="")
			{
				alert("Please Enter Student Date of Joining");
				document.forms[0].doj.focus();
				return false;
			}
			else if(document.forms[0].gender.value=="")
			{
				alert("Please Choose gender for Student");
				document.forms[0].gender.focus();
				return false;
			}
			else if(document.forms[0].bloodGroupId.value=="0")
			{
				alert("Please Enter Blood Group for Student");
				document.forms[0].bloodGroupId.focus();
				return false;
			}
			else if(document.forms[0].mentorName.value=="")
			{
				alert("Please Enter Student Mentor Name");
				document.forms[0].mentorName.focus();
				return false;
			}
			else if(document.forms[0].aadharNo.value=="")
			{
				alert("Please Enter Student aadharNo");
				document.forms[0].aadharNo.focus();
				return false;
			}
			else
			{
				document.forms[0].mode.value= actionType;
				document.forms[0].submit();
			}
		}

		function deleteStudentData(actionType)
		{
			document.forms[0].mode.value= actionType;
			document.forms[0].submit();
		}


		function getRequestedData(type,obj)
		{
			var id = obj.id;

			var url = 'student/add';
			$.ajax(
					{
						type: "POST",
						url:url,
						data:
							{
								'property(type)':type,
								'property(id)': obj.value,
							},
						cache: false,
						success: function(response)
						{
							$("#wait").html("");

							var response = jQuery.parseJSON(response);

							if(type == "checkRollNo")
							{
								var roll_no_exists = response.roll_no_exists;
								if(roll_no_exists == "true")
								{
									$("#wait").html("<center style='font-size: 14' ><span id='loading' style='font-size: 14; color:red'><i class='fa fa-spinner fa-spin' style='font-size:24px'></i> <b>Roll No. already Exists</b></span></center>");
									document.forms[0].rollNo.value = "";
								}
							}
						},
						error: function()
						{
							alert("No data");
						},
						beforeSend: function( event, ui )
						{
							$("#wait").html("<center style='font-size: 14px' ><span id='loading' style='font-size: 14px; color:red'><i class='fa fa-spinner fa-spin' style='font-size:24px'></i> <b>Loading... </b></span></center>");
						}
					});
		}

	</script>

</head>
<body>
	<div class="container_form">

			<div class="sub-main-w2">
				<div class="bg-content-w2pvt">
					<div class="top-content-style-pages">
						<p class="title-heading">Add Student</p>
					</div>
					<form:form action="/student/add" method="post"
							   modelAttribute="sudentData" enctype="multipart/form-data">

						<div class="err-message" style="align-content: center"> ${message} </div>

						<div id="wait"></div>

							<div class="student-form-input ">
								<label>Full name </label>
								<div class="form-text">
									<i class="fa fa-user" aria-hidden="true"></i>
									<form:input path="name" name="name" id="name"
												cssClass="student-form-text"
												onkeyup="charOnly(this)"
												 />
								</div>
							</div>

							<div class="student-form-input ">
								<label>Father Name </label>
								<div class="form-text">
									<i class="fa fa-user" aria-hidden="true"></i>

									<form:input path="fatherName" name="fatherName" id="fatherName"
												cssClass="student-form-text"
												onkeyup="charOnly(this)" />
								</div>
							</div>

							<div class="student-form-input ">
								<label>Mother Name </label>
								<div class="form-text">
									<i class="fa fa-user" aria-hidden="true"></i>

									<form:input path="motherName" name="motherName" id="motherName"
												cssClass="student-form-text" onkeyup="charOnly(this)" />
								</div>
							</div>

							<div class="student-form-input ">
								<label>Academic Year </label>
								<div class="form-text">
									<form:select path="academicYearId" name="academicYearId" id="academicYearId"
												 multiple="false" cssClass="student-form-select">
										<form:option value="0" label="Select" />
										<form:options items="${academicYears}" />
									</form:select>
								</div>
							</div>

							<div class="student-form-input ">
								<label>Branch </label>
								<div class="form-text">
									<form:select path="branchId" name="branchId" id="branchId"
												 multiple="false" cssClass="student-form-select">
										<form:option value="0" label="Select" />
										<form:options items="${branches}" />
									</form:select>
								</div>
							</div>

							<div class="student-form-input ">
								<label>Blood Group</label>
								<div class="form-text">
									<i class="fa fa-user" aria-hidden="true"></i>

									<form:select path="bloodGroupId" name="bloodGroupId" id="bloodGroupId"
												 multiple="false" cssClass="student-form-select">
										<form:option value="0" label="Select" />
										<form:options items="${bloodGroups}" />
									</form:select>
								</div>
							</div>

							<div class="student-form-input ">
								<label>Parent Phone No.</label>
								<div class="form-text">
									<i class="fa fa-user" aria-hidden="true"></i>

									<form:input path="parentPhoneNo" name="parentPhoneNo" id="parentPhoneNo"
												cssClass="student-form-text"
												onkeyup="intOnly(this)"/>
								</div>
							</div>

							<div class="student-form-input ">
								<label>Alternate Phone No. </label>
								<div class="form-text">
									<i class="fa fa-user" aria-hidden="true"></i>

									<form:input path="alternativePhoneNo" name="alternativePhoneNo" id="alternativePhoneNo"
												cssClass="student-form-text" onkeyup="intOnly(this)"/>
								</div>
							</div>

							<div class="student-form-input ">
								<label>Aadhar Card No.</label>
								<div class="form-text">
									<i class="fa fa-user" aria-hidden="true"></i>

									<form:input path="aadharNo" name="aadharNo" id="aadharNo"
												cssClass="student-form-text" onkeyup="intOnly(this)"/>
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

									<form:input path="rollNo" name="rollNo" id="rollNo"
												cssClass="student-form-text" />
								</div>
							</div>

							<div class="student-form-input ">
								<label>eMail</label>
								<div class="form-text">
									<i class="fa fa-user" aria-hidden="true"></i>

									<form:input path="email" name="email" id="email"
												cssClass="student-form-text" onblur="checkEmail(this);"/>
								</div>
							</div>



							<div class="student-form-input " > <%--style="width: 64%"--%>
								<label>Gender</label>
								<div class="form-text">
									<i class="fa fa-user" aria-hidden="true"></i>

									<form:radiobutton path="gender" value="M"
													  id="gender"/> Male
									<form:radiobutton path="gender" value="F"
													  id="gender"/> Female

								</div>
							</div>

							<div class="student-form-input ">
								<label>Height</label>
								<div class="form-text">
									<i class="fa fa-user" aria-hidden="true"></i>

									<form:input path="height" name="height" id="height"
												cssClass="student-form-text" onkeyup="intOnly(this)"/>
								</div>
							</div>

							<div class="student-form-input ">
								<label>Joined in Year</label>
								<div class="form-text">
									<i class="fa fa-user" aria-hidden="true"></i>

									<form:select path="joiningYearNo" name="joiningYearNo" id="joiningYearNo"
												 multiple="false" cssClass="student-form-select">
										<form:option value="0" label="Select" />
										<form:options items="${batches}" />
									</form:select>
								</div>
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

							<div class="student-form-input " > <%--style="float: left"--%>
								<label>Photo</label>
								<div class="form-text">
									<i class="fa fa-user" aria-hidden="true"></i>

									<input type="file" name="photo" id="photo"
										   cssClass="student-form-text" onchange="openFile(event)" />

									<div id="applicantPhotoName" ></div>
									<script>
										var openFile = function(event)
										{
											var input = event.target;
											var output = document.getElementById('passportPhoto');

											var reader = new FileReader();
											reader.onload = function()
											{
												var dataURL = reader.result;
												output.src = dataURL;
												output.width = 100;
												output.height = 100;
												//var applicantPhotoName = document.getElementById('applicantPhotoName');
												//applicantPhotoName.style.display="none";
											};
											reader.readAsDataURL(input.files[0]);
										};
									</script>
								</div>
								<img src='${photo}' width='80px' height='80px' id="passportPhoto" style='border: 1px solid black' ></img>
							</div>

							<div>
								<button type="submit" class="btn" onclick="checkValues()">
									Add
								</button>
							</div>

							<br>
						<br>
						<br>
						<br>


					</form:form>
				</div>
			</div>
	</div>
</body>
</html>