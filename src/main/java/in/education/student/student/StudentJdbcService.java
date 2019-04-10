package in.education.student.student;

import in.education.student.model.StudentForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.Blob;
import java.util.List;

@Service
public class StudentJdbcService {

	private StudentJdbcRepository studentRepository;

	@Autowired
	StudentJdbcService(StudentJdbcRepository studentRepository) {
		this.studentRepository = studentRepository;
	}

	public int addStudentData(StudentForm studentForm) {

//		Blob file = studentForm.getPhotoFile();

		/*try {
			studentForm.setPhoto(file.getBytes());
			studentForm.setPhotoName(file.getOriginalFilename());
		} catch (IOException e) {
			e.printStackTrace();
		}*/

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
