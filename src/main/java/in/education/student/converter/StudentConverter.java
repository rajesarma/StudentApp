package in.education.student.converter;

import in.education.student.dto.StudentDto;
import in.education.student.model.Student;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class StudentConverter {

	public static Student convert(final StudentDto studentDto) {

		Calendar cal = Calendar.getInstance();
		Student student = new Student();
		student.setStudentId(studentDto.getStudentId());
		student.setName(studentDto.getName().trim());
		student.setFatherName(studentDto.getFatherName().trim());
		student.setMotherName(studentDto.getMotherName().trim());
		try {
			cal.setTime(new SimpleDateFormat("dd-MM-yyyy").parse(studentDto.getDob().replace("/","-")));
			student.setDob(cal.getTime());
			cal.setTime(new SimpleDateFormat("dd-MM-yyyy").parse(studentDto.getDoj().replace("/","-")));
			student.setDoj(cal.getTime());
		} catch (ParseException e) {

		}
		student.setAadharNo(studentDto.getAadharNo().trim());
		student.setAddress(studentDto.getAddress().trim());
		student.setEmail(studentDto.getEmail().trim());
		student.setParentPhoneNo(studentDto.getParentPhoneNo());
		student.setAlternativePhoneNo(studentDto.getAlternativePhoneNo());
		student.setBloodGroupId(studentDto.getBloodGroupId());
		student.setAcademicYearId(studentDto.getAcademicYearId());
		student.setBranchId(studentDto.getBranchId());
		student.setRollNo(studentDto.getRollNo().trim());
		student.setGender(studentDto.getGender());
		student.setHeight(studentDto.getHeight());
		student.setJoiningYearNo(studentDto.getJoiningYearNo());
		if(studentDto.getImage() != null) {
			student.setPhotoName(studentDto.getImage().getOriginalFilename());
			try {
				student.setPhoto(studentDto.getImage().getBytes());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return student;
	}

	public static StudentDto convert(final Student student) {

		StudentDto studentDto = new StudentDto();
		studentDto.setStudentId(student.getStudentId());
		studentDto.setName(student.getName());
		studentDto.setFatherName(student.getFatherName());
		studentDto.setMotherName(student.getMotherName());
		studentDto.setDob(new SimpleDateFormat("dd-MM-yyyy").format(student.getDob()));
		studentDto.setDoj(new SimpleDateFormat("dd-MM-yyyy").format(student.getDoj()));
		studentDto.setAadharNo(student.getAadharNo());
		studentDto.setAddress(student.getAddress());
		studentDto.setEmail(student.getEmail());
		studentDto.setParentPhoneNo(student.getParentPhoneNo());
		studentDto.setAlternativePhoneNo(student.getAlternativePhoneNo());
		studentDto.setBloodGroupId(student.getBloodGroupId());
		studentDto.setAcademicYearId(student.getAcademicYearId());
		studentDto.setBranchId(student.getBranchId());
		studentDto.setRollNo(student.getRollNo());
		studentDto.setGender(student.getGender());
		studentDto.setHeight(student.getHeight());
		studentDto.setJoiningYearNo(student.getJoiningYearNo());

		if (student.getPhoto() != null && student.getPhoto().length > 0) {
			studentDto.setPhotoData(new String(java.util.Base64.getEncoder().encode(student.getPhoto())));
			studentDto.setPhotoName(student.getPhotoName());
		}

		return studentDto;
	}
}
