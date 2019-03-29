package in.education.student.student;

import in.education.student.model.StudentForm;
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
		return StudentForm.class.isAssignableFrom(candidate);
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

		StudentForm studentForm = (StudentForm) obj;

		if(studentForm.getAcademicYearId() == 0) {
			errors.rejectValue("academicYearId", "zeroValue.student.academicYearId", new Object[]{
					"Academic Year"}, "Please select this");
		}

		if(studentForm.getBranchId().equalsIgnoreCase("0")) {
			errors.rejectValue("branchId", "zeroValue.student.branchId", new Object[]{
					"Branch Id"}, "Please select this");
		}

		if(studentForm.getBloodGroupId() == 0 ) {
			errors.rejectValue("bloodGroupId", "zeroValue.student.bloodGroupId", new Object[]{
					"Blood Group"}, "Please select this");
		}

		if(studentForm.getJoiningYearNo() == 0) {
			errors.rejectValue("joiningYearNo", "zeroValue.student.joiningYearNo", new Object[]{
					"Joining Year"}, "Please select this");
		}

		if (studentForm.getEmail() != null && !EMAIL_REGEX.matcher(studentForm.getEmail()).matches()) {
			errors.rejectValue("email", "invalid.student.email");
		}

		if(studentForm.getHeight() <= 0 ) {
			errors.rejectValue("height", "required.student.height", null, "Please Enter" +
					" this");
		}

		if(studentForm.getParentPhoneNo() != null && !NUMERIC_REGEX.matcher(studentForm.getParentPhoneNo()).matches()
			&& studentForm.getParentPhoneNo().length() != 10) {
			errors.rejectValue("parentPhoneNo", "invalid.student.parentPhoneNo",
					new Object[]{"Parent Phone No."}, "Please Enter valid Parent Phone " +
							"No.");
		}

		if(studentForm.getAlternativePhoneNo() != null && !NUMERIC_REGEX.matcher(studentForm.getAlternativePhoneNo()).matches()
				&& studentForm.getAlternativePhoneNo().length() != 10) {
			errors.rejectValue("alternativePhoneNo", "invalid.student.alternativePhoneNo",
					new Object[]{"Alternate Phone No."}, "Please Enter valid Alternate " +
							"Phone " +
							"No.");
		}
	}
}
