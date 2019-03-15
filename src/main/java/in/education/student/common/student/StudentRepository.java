package in.education.student.common.student;

import in.education.student.common.GeneralQueries;
import in.education.student.common.configuration.DBUtils;
import in.education.student.common.model.StudentData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;

@Repository
public class StudentRepository {

	private DBUtils dbUtils;
	private StudentData studentData;

	@Autowired
	StudentRepository(DBUtils dbUtils) {
		this.dbUtils = dbUtils;
	}

	public StudentData getStudentData() {
		studentData = new StudentData();

		try {
			studentData.setAcademicYears(dbUtils.getSelectMap(GeneralQueries.getAcademicYears,
					dbUtils.getDBConnection()));

			studentData.setBloodGroupNames(dbUtils.getSelectMap(GeneralQueries.getBloodGroups,
					dbUtils.getDBConnection()));

			studentData.setBranches(dbUtils.getSelectMap(GeneralQueries.getBranches,
					dbUtils.getDBConnection()));

//			System.out.println(studentData.getAcademicYears());
//			System.out.println(studentData.getBloodGroupNames());
//			System.out.println(studentData.getBranches());

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return studentData;
	}


}
