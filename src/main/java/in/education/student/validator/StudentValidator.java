package in.education.student.validator;

import in.education.student.dto.StudentDto;
//import in.education.student.model.Student;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.regex.Pattern;

@Component
public class StudentValidator implements Validator {


	private static final Pattern EMAIL_REGEX =
			Pattern.compile("^[\\w\\d._-]+@[\\w\\d.-]+\\.[\\w\\d]{2,6}$");

	private static final Pattern NUMERIC_REGEX =
			Pattern.compile("\\d+");


	@Override
	public boolean supports(Class<?> candidate) {
		return StudentDto.class.isAssignableFrom(candidate);
	}

	@Override
	public void validate(Object obj, Errors errors) {

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "required.student.name");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "fatherName", "required.student.fatherName");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "motherName", "required.student.motherName");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "rollNo", "required.student.rollNo");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "parentPhoneNo", "required.student.parentPhoneNo");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "alternativePhoneNo", "required.student.alternativePhoneNo");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "required.student.email");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address", "required.student.address");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dob", "required.student.dob");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "doj", "required.student.doj");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "aadharNo", "required.student.aadharNo");

		StudentDto studentDto = (StudentDto) obj;

		if(studentDto.getBatchId() == 0) {
			errors.rejectValue("batchId", "zeroValue.student.batchId",
					new Object[]{
					"Batch"}, "Please select this");
		}

		if(studentDto.getBranchId().equalsIgnoreCase("0")) {
			errors.rejectValue("branchId", "zeroValue.student.branchId", new Object[]{
					"Branch Id"}, "Please select this");
		}

		/*if(studentDto.getBloodGroupId() == 0 ) {
			errors.rejectValue("bloodGroupId", "zeroValue.student.bloodGroupId", new Object[]{
					"Blood Group"}, "Please select this");
		}*/

		if(studentDto.getJoiningYearNo() == 0) {
			errors.rejectValue("joiningYearNo", "zeroValue.student.joiningYearNo", new Object[]{
					"Joining Year"}, "Please select this");
		}

		if(studentDto.getJoiningSemesterId() == 0) {
			errors.rejectValue("joiningSemesterId", "zeroValue.student.joiningSemesterId",
					new Object[]{
					"Joining Semester"}, "Please select this");
		}

		if (studentDto.getEmail() != null && !EMAIL_REGEX.matcher(studentDto.getEmail()).matches()) {
			errors.rejectValue("email", "invalid.student.email");
		}

		if(studentDto.getHeight() <= 0 ) {
			errors.rejectValue("height", "required.student.height", null, "Please Enter" +
					" this");
		}

		if(studentDto.getParentPhoneNo() != null && !NUMERIC_REGEX.matcher(studentDto.getParentPhoneNo()).matches()
			&& studentDto.getParentPhoneNo().length() != 10) {
			errors.rejectValue("parentPhoneNo", "invalid.student.parentPhoneNo",
					new Object[]{"Parent Phone No."}, "Please Enter valid Parent Phone " +
							"No.");
		}

		if(studentDto.getAlternativePhoneNo() != null && !NUMERIC_REGEX.matcher(studentDto.getAlternativePhoneNo()).matches()
				&& studentDto.getAlternativePhoneNo().length() != 10) {
			errors.rejectValue("alternativePhoneNo", "invalid.student.alternativePhoneNo",
					new Object[]{"Alternate Phone No."}, "Please Enter valid Alternate " +
							"Phone " +
							"No.");
		}
	}
}
