package in.education.student.common.student;

import in.education.student.common.model.StudentData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
//@RequestMapping(value="/student")
public class StudentController {

	private StudentService studentService;

	@Autowired
	StudentController(StudentService studentService) {
		this.studentService = studentService;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/studentAdd")
	public ModelAndView getStudentData() {

		ModelAndView mav = new ModelAndView("addStudent", "sudentData",
				new StudentData());
		mav.addObject("academicYears",
				studentService.getStudentData().getAcademicYears());

		mav.addObject("bloodGroups",
				studentService.getStudentData().getBloodGroupNames());

		mav.addObject("branches",
				studentService.getStudentData().getBranches());

		return mav;
	}

	@PostMapping("/add")
	public ModelAndView createStudent() {

		ModelAndView mav = new ModelAndView("addStudent");
		//mav.addObject("subjects", );
		return mav;
	}

}
