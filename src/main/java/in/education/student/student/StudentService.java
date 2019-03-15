package in.education.student.student;

import in.education.student.model.StudentForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

	private StudentRepository studentRepository;

	@Autowired
	StudentService(StudentRepository studentRepository) {
		this.studentRepository = studentRepository;
	}

	public StudentForm getStudentData() {

		return studentRepository.getStudentData();
	}

}
