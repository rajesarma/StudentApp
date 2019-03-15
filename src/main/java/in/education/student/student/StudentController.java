package in.education.student.student;

import in.education.student.common.util.DBDataUtils;
import in.education.student.common.util.GeneralQueries;
import in.education.student.model.StudentForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.sql.SQLException;
import java.util.Map;

@Controller
//@RequestMapping(value="/student")
public class StudentController {

	private DBDataUtils dbDataUtils;
	private StudentService studentService;

	@Autowired
	StudentController(StudentService studentService, DBDataUtils dbDataUtils) {
		this.studentService = studentService;
		this.dbDataUtils = dbDataUtils;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/studentAdd")
	public ModelAndView getStudentData()  {

		ModelAndView mav = new ModelAndView("addStudent", "sudentData",
				new StudentForm());
		try {
			mav.addObject("academicYears", dbDataUtils.getAcademicYears());
			mav.addObject("bloodGroups", dbDataUtils.getBloodGroups());
			mav.addObject("branches", dbDataUtils.getBranches());

		}catch (SQLException e) {
			mav.addObject("message", "Problem in Fetching Data");
		}

		return mav;
	}

	@PostMapping("/add")
	public ModelAndView createStudent() {

		ModelAndView mav = new ModelAndView("addStudent");
		//mav.addObject("subjects", );
		return mav;
	}

}
