package in.education.student.student;

import in.education.student.model.AcademicYear;
import in.education.student.model.BloodGroup;
import in.education.student.model.Branch;
import in.education.student.model.StudentForm;
import in.education.student.model.Year;
import in.education.student.model.repository.AcademicYearRepository;
import in.education.student.model.repository.BloodGroupRepository;
import in.education.student.model.repository.BranchRepository;
import in.education.student.model.repository.SemesterRepository;
import in.education.student.model.repository.YearRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Controller
@RequestMapping(value="/super")
public class StudentController {

	private StudentRepository studentRepository;
	private BloodGroupRepository bloodGroupRepository;
	private AcademicYearRepository academicYearRepository;
	private BranchRepository branchRepository;
	private YearRepository yearRepository;
	private SemesterRepository semesterReposiorty;

	@Value("${save}")
	String save;

	static String Role = "/super";

	@Autowired
	StudentController(final StudentRepository studentRepository,
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

	private void loadDbData(ModelAndView mav) {

		mav.addObject("academicYears", StreamSupport.stream(academicYearRepository.findAll().spliterator(), false)
				.collect(Collectors.toMap(AcademicYear::getYearId, AcademicYear::getYear)));

		mav.addObject("bloodGroups",
				StreamSupport.stream(bloodGroupRepository.findAll().spliterator(), false)
						.collect(Collectors.toMap(BloodGroup::getBloodGroupId,
								BloodGroup::getBloodGroup)));

		mav.addObject("branches",
				StreamSupport.stream(branchRepository.findAll().spliterator(), false)
						.collect(Collectors.toMap(Branch::getBranchId,
								Branch::getBranchName)));

		mav.addObject("years",
				StreamSupport.stream(yearRepository.findAll().spliterator(), false)
						.collect(Collectors.toMap(Year::getYearId,
								Year::getYear)));

		mav.addObject("Role", Role);
	}


//	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SUPERVIZOR')")
	@GetMapping(value = "/student/add")
	public ModelAndView save()  {

//		System.out.println(yearRepository.findAll());

		ModelAndView mav = new ModelAndView("studentAdd", "studentData", new StudentForm());

		mav.addObject("buttonValue", save );
		mav.addObject("action",Role + "/student/add");

		mav.addObject("Role", Role);

		loadDbData(mav);

		return mav;
	}

	/*@GetMapping(value = "/student/add")
	public ResponseEntity<?> save()  {

		List<Semester> semesters = new ArrayList<>();

		StreamSupport.stream(yearRepository.findByYearId(1).spliterator(), false)
				.forEach(year -> semesters.addAll(year.getSemseters()));

		return new ResponseEntity<>(semesters, HttpStatus.OK);
	}*/


	@PostMapping("/student/add")
	public ModelAndView save( @Valid @ModelAttribute("studentData") StudentForm studentData
			,BindingResult bindingResult
			) {

		try {
			studentData.setPhotoName(studentData.getImage().getOriginalFilename());
			studentData.setPhoto(studentData.getImage().getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}

		StudentForm studentForm = studentRepository.save(studentData);

		ModelAndView mav = new ModelAndView("studentAdd", "studentData",
				studentForm);

		loadDbData(mav);
		mav.addObject("buttonValue","Save");
		mav.addObject("action",Role + "/student/add");

		if( !(studentForm.getStudentId() > 0) ) {

			mav.addObject("studentData", studentData);
			mav.addObject("message", "Students Information is not inserted");

		} else {

			mav.addObject("message", "Student Information inserted successfully");
		}

		mav.addObject("Role", Role);
		return mav;
	}

	@Autowired
	@Qualifier("studentValidator")
	private Validator studentValidator;

	@InitBinder
	private void dataBinding(WebDataBinder binder) {
		binder.setValidator(studentValidator);
	}

	@GetMapping("/student/list")
	public ModelAndView list() {

		ModelAndView mav = new ModelAndView("studentList", "studentData",
				new StudentForm());
		loadDbData(mav);

		mav.addObject("Role", Role);
		return mav;
	}

	@PostMapping("/student/list")
	public ModelAndView list(@ModelAttribute("studentData") StudentForm studentData) {

		ModelAndView mav = new ModelAndView("studentList");
		loadDbData(mav);

		List<StudentForm> studentsList =
				studentRepository.findByBranchIdAndJoiningYearNo(studentData.getBranchId(),
				studentData.getAcademicYearId());

		mav.addObject("studentList", studentsList);

		mav.addObject("Role", Role);
		return mav;
	}

}
