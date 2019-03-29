package in.education.student.student;

import in.education.student.model.StudentForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

	private StudentRepository studentRepository;

	@Autowired
	StudentService(StudentRepository studentRepository) {
		this.studentRepository = studentRepository;
	}

	public int addStudentData(StudentForm studentForm) {

		return studentRepository.addStudentData(studentForm);
	}

	public List getSpecifiedStudentsData(StudentForm studentData) {

		return studentRepository.getSpecifiedStudentsData(studentData);
	}

	public StudentForm getStudentData(int studentId) {
		StudentForm studentData = studentRepository.getStudentData(studentId);
		return studentData;
	}

	public int updateStudentData(StudentForm studentForm) {

		return studentRepository.updateStudentData(studentForm);
	}

	public int deleteStudentData(StudentForm studentForm) {

		return studentRepository.deleteStudentData(studentForm);
	}

}
