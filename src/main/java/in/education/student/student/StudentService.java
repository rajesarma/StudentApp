package in.education.student.student;

import in.education.student.converter.StudentConverter;
import in.education.student.converter.UserConverter;
import in.education.student.dto.StudentDto;
import in.education.student.dto.UserDto;
import in.education.student.model.AcademicYear;
import in.education.student.model.BloodGroup;
import in.education.student.model.Branch;
import in.education.student.model.Student;
import in.education.student.model.Year;
import in.education.student.model.repository.AcademicYearRepository;
import in.education.student.model.repository.BloodGroupRepository;
import in.education.student.model.repository.BranchRepository;
import in.education.student.model.repository.SemesterRepository;
import in.education.student.model.repository.YearRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class StudentService {

	private StudentRepository studentRepository;
	private BloodGroupRepository bloodGroupRepository;
	private AcademicYearRepository academicYearRepository;
	private BranchRepository branchRepository;
	private YearRepository yearRepository;
	private SemesterRepository semesterReposiorty;

	@Autowired
	StudentService(final StudentRepository studentRepository,
			BloodGroupRepository bloodGroupRepository,
			AcademicYearRepository academicYearRepository,
			BranchRepository branchRepository,
			YearRepository yearRepository,
			SemesterRepository semesterReposiorty
	) {
		this.studentRepository = studentRepository;
		this.bloodGroupRepository = bloodGroupRepository;
		this.academicYearRepository = academicYearRepository;
		this.branchRepository = branchRepository;
		this.yearRepository = yearRepository;
		this.semesterReposiorty = semesterReposiorty;
	}

	Map<Long,String> getAcademicYears() {
		return StreamSupport.stream(academicYearRepository.findAll().spliterator(), false)
				.collect(Collectors.toMap(AcademicYear::getYearId, AcademicYear::getYear));
	}

	Map<Long,String> getBloodGroups() {
		return StreamSupport.stream(bloodGroupRepository.findAll().spliterator(), false)
				.collect(Collectors.toMap(BloodGroup::getBloodGroupId,
						BloodGroup::getBloodGroup));
	}

	Map<String,String> getBranches() {

		return StreamSupport.stream(branchRepository.findAll().spliterator(), false)
						.collect(Collectors.toMap(Branch::getBranchId,Branch::getBranchName));
	}

	Map<Long,String> getYears() {

		return StreamSupport.stream(yearRepository.findAll().spliterator(), false)
						.collect(Collectors.toMap(Year::getYearId, Year::getYear));
	}

	Optional<StudentDto> save(StudentDto studentDto) {

		Student student = StudentConverter.convert(studentDto);
		Student savedStudent = studentRepository.save(student);

		BeanUtils.copyProperties(studentDto, student);

		if(savedStudent.getStudentId() > 0) {
			return Optional.of(StudentConverter.convert(savedStudent));
		}

		return Optional.empty();
	}

	List<StudentDto> list(String branchId, long academicYearId) {
		List<Student> studentsList =
				studentRepository.findByBranchIdAndJoiningYearNo(branchId,
						Integer.parseInt(academicYearId+""));

		List<StudentDto> studentDtos = new ArrayList<>();

		studentsList.stream().forEach(student -> studentDtos.add(StudentConverter.convert(student)));

		studentDtos.stream()
				.forEach(student -> student.setBranch(branchRepository.findById(student.getBranchId()).get().getBranchName()));

		studentDtos.stream()
				.forEach(student -> student.setBloodGroup(bloodGroupRepository.findById(student.getBloodGroupId()).get().getBloodGroup()));

		studentDtos.stream()
				.forEach(student -> student.setYear(yearRepository.findById(student
				.getAcademicYearId()).get().getYear()));

//		studentDto.setBloodGroup(student.getBloodGroup());
//		studentDto.setYear(student.getYear());


		return studentDtos;
	}


	String findBranchById(String brachId) {
		Optional<Branch> branchOptional = branchRepository.findById(brachId);

		if(branchOptional.isPresent()) {
			return branchOptional.get().getBranchName();
		}

		return "";
	}

	Optional<StudentDto> findByRollNo(String rollNo) {
		Optional<Student> studentOptional = studentRepository.findByRollNo(rollNo);

		if(studentOptional.isPresent()) {
			return Optional.of(StudentConverter.convert(studentOptional.get()));
		}

		return Optional.empty();
	}

}
