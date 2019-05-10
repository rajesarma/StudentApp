package in.education.student.student;

import in.education.student.dto.StudentDto;
import in.education.student.validator.StudentValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
@RequestMapping(value="/super")
public class StudentController {

	private StudentService studentService;

	@Value("${save}")
	String save;

	private static String Role = "/super";

	@Autowired
	StudentController(final StudentService studentService ) {
		this.studentService = studentService;
	}

	private String academicYears = "academicYears";
	private String bloodGroups = "bloodGroups";
	private String branches = "branches";
	private String years = "years";
	private String role = "Role";
	private String message = "";


//	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SUPERVIZOR')")
	@GetMapping(value = "/student/add")
	public ModelAndView save()  {

		ModelAndView mav = new ModelAndView("studentAdd", "studentDto", new StudentDto());

		mav.addObject("buttonValue", save );
		mav.addObject("action",Role + "/student/add");
		mav.addObject(role, Role);
		mav.addObject(academicYears, studentService.getAcademicYears());
		mav.addObject(bloodGroups, studentService.getBloodGroups());
		mav.addObject(branches, studentService.getBranches());
		mav.addObject(years, studentService.getYears());
		mav.addObject(role, Role);

		return mav;
	}

	@PostMapping("/student/add")
	public ModelAndView save(@ModelAttribute("studentDto") StudentDto studentDto,
			BindingResult bindingResult) { //@Valid

		ModelAndView mav = new ModelAndView("studentAdd", "studentDto", studentDto);
		mav.addObject(academicYears, studentService.getAcademicYears());
		mav.addObject(bloodGroups, studentService.getBloodGroups());
		mav.addObject(branches, studentService.getBranches());
		mav.addObject(years, studentService.getYears());
		mav.addObject(role, Role);
		mav.addObject("buttonValue", "Save");
		mav.addObject("action", Role + "/student/add");

		new StudentValidator().validate(studentDto, bindingResult);

		if(bindingResult.hasErrors()) {
			mav.addObject("message", "Problem in Saving Student Data");
			return mav;
		}

		Optional<StudentDto> studentDtoOptional = studentService.save(studentDto);

		if(!studentDtoOptional.isPresent()) {
			mav.addObject("message", "Problem in Saving Student Data");
			return mav;
		}

		mav.addObject("message", "Student Information is added successfully");

		return mav;
	}

	/*@Autowired
	@Qualifier("studentValidator")
	private Validator studentValidator;

	@InitBinder
	private void dataBinding(WebDataBinder binder) {
		binder.setValidator(studentValidator);
	}*/

	@GetMapping("/student/list")
	public ModelAndView list() {

		ModelAndView mav = new ModelAndView("studentList", "studentDto",
				new StudentDto());
		mav.addObject(academicYears, studentService.getAcademicYears());
		mav.addObject(bloodGroups, studentService.getBloodGroups());
		mav.addObject(branches, studentService.getBranches());
		mav.addObject(years, studentService.getYears());

		mav.addObject(role, Role);
		return mav;
	}

	@PostMapping("/student/list")
	public ModelAndView list(@ModelAttribute("studentDto") StudentDto studentDto) {

		ModelAndView mav = new ModelAndView("studentList");
		mav.addObject(academicYears, studentService.getAcademicYears());
		mav.addObject(bloodGroups, studentService.getBloodGroups());
		mav.addObject(branches, studentService.getBranches());
		mav.addObject(years, studentService.getYears());
		mav.addObject(role, Role);
		mav.addObject("studentList", studentService.list(studentDto.getBranchId(),
				studentDto.getAcademicYearId()));
		return mav;
	}

	@GetMapping(value = "/student/{type}/{value}")
	public ResponseEntity<?> find(@PathVariable("type") String type,
			@PathVariable("value") String value) {

		String result = null;

		if(type.equalsIgnoreCase("checkRollNo")) {
			Optional<StudentDto> studentDto = studentService.findByRollNo(value);

			if(studentDto.isPresent()) {
				result = "{\"rollNoExists\":\"true\", \"message\":\"Roll No. " +
						"already exists\"  }";
			} else {
				result = "{\"rollNoExists\":\"false\" }";
			}
		}
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
}

	/*@GetMapping(value = "/student/add")
	public ResponseEntity<?> save()  {

		List<Semester> semesters = new ArrayList<>();

		StreamSupport.stream(yearRepository.findByYearId(1).spliterator(), false)
				.forEach(year -> semesters.addAll(year.getSemseters()));

		return new ResponseEntity<>(semesters, HttpStatus.OK);
	}*/
