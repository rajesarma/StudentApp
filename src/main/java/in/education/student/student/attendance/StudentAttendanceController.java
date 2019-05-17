package in.education.student.student.attendance;

import in.education.student.dto.StudentAttendanceDto;
import in.education.student.student.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping(value="/super")
public class StudentAttendanceController {

	private StudentAttendanceService studentAttendanceService;
	private StudentService studentService;

	@Value("${save}")
	String save;
	@Value("${update}")
	String update;

	private static String Role = "/super";

	@Autowired
	StudentAttendanceController(
			final StudentAttendanceService studentAttendanceService,
			final StudentService studentService ) {
		this.studentAttendanceService = studentAttendanceService;
		this.studentService = studentService;
	}

	private String batches = "batches";
	private String academicYears = "academicYears";
	private String semesters = "semesters";
	private String branches = "branches";
	private String years = "years";
	private String role = "Role";
	private String message = "";


	private void getInitialData(ModelAndView mav) {
		mav.addObject(batches, studentService.getBatches());
		mav.addObject(branches, studentService.getBranches());
		mav.addObject(years, studentService.getYears());
		mav.addObject(academicYears, studentAttendanceService.getAcademicYears());
	}

	@GetMapping(value = "/student/addStudentAttendance")
	public ModelAndView getData()  {

		ModelAndView mav = new ModelAndView("addStudentAttendance", "studentAttendanceDto",
				new StudentAttendanceDto());

		mav.addObject("buttonValue", save );
		mav.addObject("action",Role + "/student/addStudentAttendance");
		mav.addObject(role, Role);

		getInitialData(mav);
		return mav;
	}

	@PostMapping("/student/addStudentAttendance")
	public ModelAndView retrieveData(@ModelAttribute("studentAttendanceDto") StudentAttendanceDto studentAttendanceDto,
			BindingResult bindingResult) { //@Valid

		ModelAndView mav = new ModelAndView("addStudentAttendance", "studentAttendanceDto", studentAttendanceDto);

		List<StudentAttendanceDto> studentAttendanceDtos =
				studentAttendanceService.getAttendanceData(studentAttendanceDto);

		mav.addObject(role, Role);
		mav.addObject("buttonValue", "Save");
		mav.addObject("action", Role + "/student/addStudentAttendance");

		/*Optional<StudentDto> studentAttendanceDtoOptional = studentAttendanceService.save(studentAttendanceDto);

		if(!studentAttendanceDtoOptional.isPresent()) {
			mav.addObject("message", "Problem in Saving Student Data");
			return mav;
		}

		mav.addObject("message", "Student Information is added successfully");*/

		return mav;
	}
}
