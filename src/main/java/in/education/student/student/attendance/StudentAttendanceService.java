package in.education.student.student.attendance;

import in.education.student.converter.StudentAttendanceConverter;
import in.education.student.dto.StudentAttendanceDto;
import in.education.student.model.AcademicYear;
import in.education.student.model.StudentAttendance;
import in.education.student.model.repository.AcademicYearRepository;
import in.education.student.model.repository.StudentAttendanceRepository;
import in.education.student.student.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class StudentAttendanceService {

	private StudentRepository studentRepository;
	private StudentAttendanceRepository studentAttendanceRepository;
	private AcademicYearRepository academicYearRepository;
	private StudentAttendanceConverter studentAttendanceConverter;

	@Autowired
	StudentAttendanceService(
			final StudentAttendanceRepository studentAttendanceRepository,
			final StudentRepository studentRepository,
			final AcademicYearRepository academicYearRepository,
			final StudentAttendanceConverter studentAttendanceConverter) {
		this.studentAttendanceRepository = studentAttendanceRepository;
		this.studentRepository = studentRepository;
		this.academicYearRepository = academicYearRepository;
		this.studentAttendanceConverter = studentAttendanceConverter;
	}

	Map<Long,String> getAcademicYears() {

		return StreamSupport.stream(academicYearRepository.findAll().spliterator(), false)
				.collect(Collectors.toMap(AcademicYear::getAcademicYearId, AcademicYear::getAcademicYear));
	}

	List<StudentAttendanceDto> getAttendanceData(StudentAttendanceDto studentAttendance){

		List<StudentAttendanceDto> studentAttendanceDtos = new ArrayList<>();

		List<StudentAttendance> studentAttendances =
				studentAttendanceRepository.findAllByBatchIdAndBranchIdAndAcademicYearIdAndSemesterId(
				studentAttendance.getBatchId(),
				studentAttendance.getBranchId(),
				studentAttendance.getAcademicYearId(),
				studentAttendance.getSemesterId()
				);

		System.out.println(studentAttendances.size());
		if(!studentAttendances.isEmpty()) {
			studentAttendances.forEach(student -> studentAttendanceDtos.add(studentAttendanceConverter.convert(student)));
		}

		return studentAttendanceDtos;
	}
}
