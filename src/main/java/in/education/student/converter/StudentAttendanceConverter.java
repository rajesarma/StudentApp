package in.education.student.converter;

import in.education.student.dto.StudentAttendanceDto;
import in.education.student.model.AcademicYear;
import in.education.student.model.Batch;
import in.education.student.model.Branch;
import in.education.student.model.Semester;
import in.education.student.model.Student;
import in.education.student.model.StudentAttendance;
import in.education.student.model.repository.AcademicYearRepository;
import in.education.student.model.repository.BatchRepository;
import in.education.student.model.repository.BranchRepository;
import in.education.student.model.repository.SemesterRepository;
import in.education.student.model.repository.YearRepository;
import in.education.student.student.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class StudentAttendanceConverter {

	private BranchRepository branchRepository;
	private BatchRepository batchRepository;
	private AcademicYearRepository academicYearRepository;
	private SemesterRepository semesterRepository;
	private StudentRepository studentRepository;

	@Autowired
	StudentAttendanceConverter(
			final BranchRepository branchRepository,
			final BatchRepository batchRepository,
			final AcademicYearRepository academicYearRepository,
			final SemesterRepository semesterRepository,
			final StudentRepository studentRepository) {
		this.branchRepository = branchRepository;
		this.batchRepository = batchRepository;
		this.academicYearRepository = academicYearRepository;
		this.semesterRepository = semesterRepository;
		this.studentRepository = studentRepository;
	}

	public StudentAttendance convert(final StudentAttendanceDto studentAttendanceDto) {

		StudentAttendance studentAttendance = new StudentAttendance();
		studentAttendance.setStudentAttendanceId(studentAttendanceDto.getStudentAttendanceId());
		studentAttendance.setBatchId(studentAttendanceDto.getBatchId());
		studentAttendance.setBranchId(studentAttendanceDto.getBranchId());
		studentAttendance.setAcademicYearId(studentAttendanceDto.getAcademicYearId());
		studentAttendance.setSemesterId(studentAttendanceDto.getSemesterId());
		studentAttendance.setStudentId(studentAttendanceDto.getStudentId());
		studentAttendance.setNoOfDays(studentAttendanceDto.getNoOfDays());
		studentAttendance.setDaysPresent(studentAttendanceDto.getDaysPresent());

		return studentAttendance;
	}

	public StudentAttendanceDto convert(final StudentAttendance studentAttendance) {

		StudentAttendanceDto studentAttendanceDto = new StudentAttendanceDto();
		studentAttendanceDto.setStudentAttendanceId(studentAttendance.getStudentAttendanceId());
		studentAttendanceDto.setBatchId(studentAttendance.getBatchId());
		studentAttendanceDto.setBranchId(studentAttendance.getBranchId());
		studentAttendanceDto.setAcademicYearId(studentAttendance.getAcademicYearId());
		studentAttendanceDto.setSemesterId(studentAttendance.getSemesterId());
		studentAttendanceDto.setStudentId(studentAttendance.getStudentId());
		studentAttendanceDto.setNoOfDays(studentAttendance.getNoOfDays());
		studentAttendanceDto.setDaysPresent(studentAttendance.getDaysPresent());

		Optional<Batch> batchOptional  =
				batchRepository.findById(studentAttendance.getBatchId());
		if(batchOptional.isPresent()) {
			studentAttendanceDto.setBatch(batchOptional.get().getBatchName());
		}

		Optional<Branch> branchOptional  =
				branchRepository.findById(studentAttendance.getBranchId());
		if(branchOptional.isPresent()) {
			studentAttendanceDto.setBranch(branchOptional.get().getBranchName());
		}

		Optional<AcademicYear> academicYearOptional  =
				academicYearRepository.findById(studentAttendance.getAcademicYearId());
		if(academicYearOptional.isPresent()) {
			studentAttendanceDto.setAcademicYear(academicYearOptional.get().getAcademicYear());
		}

		Optional<Semester> semesterOptional  =
				semesterRepository.findById(studentAttendance.getSemesterId());
		if(semesterOptional.isPresent()) {
			studentAttendanceDto.setSemester(semesterOptional.get().getSemesterName());
		}

		Optional<Student> studentOptional  =
				studentRepository.findById(studentAttendance.getStudentId());
		if(studentOptional.isPresent()) {
			studentAttendanceDto.setName(studentOptional.get().getName());
			studentAttendanceDto.setRollNo(studentOptional.get().getRollNo());
		}

		return studentAttendanceDto;
	}
}

