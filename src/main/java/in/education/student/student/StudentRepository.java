package in.education.student.student;

import in.education.student.common.util.DBUtils;
import in.education.student.model.StudentForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class StudentRepository {

	private DBUtils dbUtils;
	private StudentForm studentForm;

	@Autowired
	StudentRepository(DBUtils dbUtils) {
		this.dbUtils = dbUtils;
	}

	public StudentForm getStudentData() {
		studentForm = new StudentForm();


			/*studentForm.setAcademicYears(dbUtils.getSelectMap(GeneralQueries.getAcademicYears,
					dbUtils.getDBConnection()));

			studentForm.setBloodGroupNames(dbUtils.getSelectMap(GeneralQueries.getBloodGroups,
					dbUtils.getDBConnection()));

			studentForm.setBranches(dbUtils.getSelectMap(GeneralQueries.getBranches,
					dbUtils.getDBConnection()));*/

//			System.out.println(studentData.getAcademicYears());
//			System.out.println(studentData.getBloodGroupNames());
//			System.out.println(studentData.getBranches());

		return studentForm;
	}


}
