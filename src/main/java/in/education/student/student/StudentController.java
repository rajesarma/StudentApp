package in.education.student.student;

import in.education.student.common.util.DBDataUtils;
import in.education.student.model.StudentForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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

	private static void loadDbData(DBDataUtils dbDataUtils, ModelAndView mav) {

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

		ModelAndView mav = new ModelAndView("studentAdd", "sudentData", studentForm);

		loadDbData(dbDataUtils, mav);

		mav.addObject("buttonValue","Save");
		mav.addObject("action","/student/add");

		return mav;
	}

	@PostMapping(value = "/add/{type}/{rollNo}")
	public ResponseEntity<?> checkData(@PathVariable("type") String type,
			@PathVariable("rollNo") String rollNo)  {

		String result = null;

		try {
			if(type.equalsIgnoreCase("checkRollNo")) {
				result = dbDataUtils.isRollNoExists(rollNo);
			}

		} catch (SQLException e) {
			return new ResponseEntity<>("Problem in Fetching Data", HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@PostMapping("/add")
	public ModelAndView createStudent(@ModelAttribute("sudentData") StudentForm studentData) {

		StudentForm studentForm = new StudentForm();
		studentForm.setGender("M");
		ModelAndView mav = new ModelAndView("studentAdd", "sudentData",
				studentForm);

		int result = studentService.addStudentData(studentData);

		if(result > 0) {

			mav.addObject("message", "Student Information inserted successfully");
		} else {

			mav = new ModelAndView("studentAdd", "sudentData",
					studentData);
			mav.addObject("message", "Students Information is not inserted");
		}

		mav.addObject("buttonValue","Save");
		mav.addObject("action","/student/add");

		loadDbData(dbDataUtils, mav);

		return mav;
	}


	// list //
	@GetMapping("/list")
	public ModelAndView studentList() {

		ModelAndView mav = new ModelAndView("studentList", "sudentData",
				new StudentForm());
		loadDbData(dbDataUtils, mav);
		return mav;
	}

	@PostMapping("/list")
	public ModelAndView getStudentList(@ModelAttribute("sudentData") StudentForm studentData) {

		ModelAndView mav = new ModelAndView("studentList");
		loadDbData(dbDataUtils, mav);
		mav.addObject("studentList", studentService.getAllStudentsData(studentData));
		return mav;
	}
	// list //

	// Get //
	@GetMapping("/edit/{studentId}/{operation}")
	public ModelAndView getStudentData(@PathVariable("studentId") int studentId,
			@PathVariable("operation") String operation) {

		ModelAndView mav = new ModelAndView("studentAdd");
		StudentForm studentData = studentService.getStudentData(studentId);

		if(studentData.getPhotoData() != null) {
			mav.addObject("photoExt", studentData.getPhotoName().substring(studentData.getPhotoName().indexOf('.') + 1));
			mav.addObject("photoData", studentData.getPhotoData());
		}

		mav.addObject("sudentData", studentData);

		mav.addObject("buttonValue", operation.toUpperCase());
		mav.addObject("action","/student/" + operation); // operation as Update / Delete

		loadDbData(dbDataUtils, mav);

		return mav;
	}
	// Get //

	// Update //
	@PostMapping("/update")
	public ModelAndView updateStudentData(@ModelAttribute("sudentData") StudentForm studentData)  {

		StudentForm studentForm = new StudentForm();

		ModelAndView mav = new ModelAndView("studentAdd", "sudentData", studentForm);

		loadDbData(dbDataUtils, mav);

		int result = studentService.updateStudentData(studentData);

		if(result > 0) {

			mav.addObject("message", "Student Information updated successfully");

			studentForm.setBranchId(studentData.getBranchId());
			studentForm.setAcademicYearId(studentData.getAcademicYearId());

			/*mav.addObject("branchId", studentData.getBranchId());
			mav.addObject("academicYearId", studentData.getAcademicYearId());*/

		} else {

			mav = new ModelAndView("studentAdd", "sudentData",
					studentData);
			mav.addObject("message", "Students Information is not updated");
		}


		mav.addObject("buttonValue","Save");
		mav.addObject("action","/student/add");

		return mav;
	}

	// Delete //
	@PostMapping("/delete")
	public ModelAndView deleteStudentData(@ModelAttribute("sudentData") StudentForm studentData)  {

		ModelAndView mav = new ModelAndView("studentAdd", "sudentData",
				new StudentForm());

		int result = studentService.deleteStudentData(studentData);

		if(result > 0) {

			mav.addObject("message", "Student Information deleted successfully");

			mav.addObject("buttonValue","Save");
			mav.addObject("action","/student/add");

		} else {

			mav = new ModelAndView("studentAdd", "sudentData",
					studentData);
			mav.addObject("message", "Students Information is not deleted");

			mav.addObject("buttonValue","Delete");
			mav.addObject("action","/student/delete");
		}

		loadDbData(dbDataUtils, mav);

		return mav;
	}

}
