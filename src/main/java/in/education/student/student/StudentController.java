package in.education.student.student;

import in.education.student.common.util.DBDataUtils;
import in.education.student.model.StudentForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.sql.SQLException;

@Controller
@RequestMapping(value="/student")
public class StudentController {

	private DBDataUtils dbDataUtils;
	private StudentService studentService;

	@Autowired
	StudentController(StudentService studentService, DBDataUtils dbDataUtils) {
		this.studentService = studentService;
		this.dbDataUtils = dbDataUtils;
	}

	StudentForm studentForm = new StudentForm();
	ModelAndView mav = new ModelAndView("studentAdd", "sudentData", studentForm);

	static void loadDbData(DBDataUtils dbDataUtils, ModelAndView mav) {

		try {
			mav.addObject("academicYears", dbDataUtils.getAcademicYears());
			mav.addObject("bloodGroups", dbDataUtils.getBloodGroups());
			mav.addObject("branches", dbDataUtils.getBranches());
			mav.addObject("batches", dbDataUtils.getBatches());
		}catch (SQLException e) {
			mav.addObject("message", "Problem in Fetching Data");
		}
	}

	@GetMapping(value = "/add")
	public ModelAndView addStudent()  {

		StudentForm studentForm = new StudentForm();
		studentForm.setGender("M");

		mav.setViewName("studentAdd");
		loadDbData(dbDataUtils, mav);
		return mav;
	}

	@PostMapping("/add")
	public ModelAndView createStudent(@ModelAttribute("sudentData") StudentForm studentData) {

		/*StudentForm studentForm = new StudentForm();
		studentForm.setGender("M");*/
		/*ModelAndView mav = new ModelAndView("studentAdd", "sudentData",
				studentForm);*/

		mav.setViewName("studentAdd");
		studentForm.setGender("M");

		int result = studentService.addStudentData(studentData);

		if(result > 0) {

			mav.addObject("message", "Student Information inserted successfully");
		} else {

			mav = new ModelAndView("studentAdd", "sudentData",
					studentData);
			mav.addObject("message", "Students Information is not inserted");
		}

		loadDbData(dbDataUtils, mav);

		return mav;
	}


	// list //
	@GetMapping("/list")
	public ModelAndView studentList() {
		mav.setViewName("studentList");
		loadDbData(dbDataUtils, mav);
		return mav;
	}

	@PostMapping("/list")
	public ModelAndView getStudentList(@ModelAttribute("sudentData") StudentForm studentData) {
		mav.setViewName("studentList");
		loadDbData(dbDataUtils, mav);
		mav.addObject("studentList", studentService.getAllStudentsData(studentData));
		return mav;
	}
	// list //

	// Get //
	@GetMapping("/edit/{studentId}")
	public ModelAndView getStudentData(@PathVariable("studentId") int studentId) {

		/*StudentForm studentForm = studentService.getStudentsData(studentId);

		ModelAndView mav = new ModelAndView("studentAdd", "sudentData",
				studentForm);*/

		mav.setViewName("studentAdd");
		mav.addObject("sudentData", studentService.getStudentsData(studentId));

		loadDbData(dbDataUtils, mav);

		return mav;
	}
	// Get //

	// Update //
	@PutMapping(value = "/update")
	public ModelAndView updateStudentData(@ModelAttribute("sudentData") StudentForm studentData)  {

		StudentForm studentForm = new StudentForm();
		studentForm.setGender("M");

		ModelAndView mav = new ModelAndView("studentAdd", "sudentData",
				studentForm);

		loadDbData(dbDataUtils, mav);
		return mav;
	}

}
