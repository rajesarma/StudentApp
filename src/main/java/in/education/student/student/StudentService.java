package in.education.student.student;

import in.education.student.model.StudentForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
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

	public List getAllStudentsData(StudentForm studentData) {

		return studentRepository.getAllStudentsData(studentData);
	}

	public StudentForm getStudentsData(int studentId) {

		return studentRepository.getStudentsData(studentId);
	}

}
