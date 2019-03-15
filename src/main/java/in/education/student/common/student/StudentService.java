package in.education.student.common.student;

import in.education.student.common.model.StudentData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

	private StudentRepository studentRepository;

	@Autowired
	StudentService(StudentRepository studentRepository) {
		this.studentRepository = studentRepository;
	}

	public StudentData getStudentData() {

		return studentRepository.getStudentData();
	}

}
