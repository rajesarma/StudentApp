package in.education.student.student;

import in.education.student.common.util.DBUtils;
import in.education.student.common.util.GeneralQueries;
import in.education.student.model.StudentForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Repository
public class StudentRepository {

	private DBUtils dbUtils;

	@Autowired
	StudentRepository(DBUtils dbUtils) {
		this.dbUtils = dbUtils;
	}

	int addStudentData(StudentForm studentForm) {

		PreparedStatement pstmt;

		String sql;
		String studentId = "";
		int index = 0;
		int result = 0;
		String photoName = "";
		ResultSet rs;

		Statement stmt = dbUtils.getDBStatement();

		try {

			rs = stmt.executeQuery("select nextval('student_details_student_id_seq') as student_id");

			if (rs.next()) {
				studentId = rs.getString("student_id");
			}

			if(studentForm.getPhoto()!=null && !"".equals(studentForm.getPhoto().toString().trim()) && studentForm.getPhoto().getBytes().length >0 ) {
				photoName =
						"stu_"+studentId+"_"+studentForm.getPhoto().getOriginalFilename().toLowerCase().replace("_","-");
			}

			sql = " insert into student_details " +
					" (" +
					" student_id, " +
					" student_name, father_name, dob, " +
					" doj, address, email, " +
					" guardian_mobile, blood_group_id, branch_id," +
					" roll_no, mobile_no, mother_name, " +
					" gender, academic_year_id, aadhar, " +
					" height, joining_year_no " +
					"" + ((studentForm.getPhoto().getBytes().length >0) ? ",photo_name, photo" :
					"" )+
					" ) " +
					" values (" +
					" ?, "+
					" ?, ?, STR_TO_DATE(?,'%d/%m/%Y'), " +
					" STR_TO_DATE(?,'%d/%m/%Y'), ?, ?, " +
					" ?, ?, ?," + //?" +
					" ?, ?, ?," +
					" ?, ?, ?," +
					" ?, ?" +
					"" + ((studentForm.getPhoto().getBytes().length >0) ? ",? , ?" : "" ) +
					" )";

			pstmt = dbUtils.getDBConnection().prepareStatement(sql);

			pstmt.setObject( (++index), Long.parseLong(studentId));

			pstmt.setObject( (++index), studentForm.getName());
			pstmt.setObject((++index), studentForm.getFatherName());
			pstmt.setObject((++index), studentForm.getDob());

			pstmt.setObject((++index), studentForm.getDoj());
			pstmt.setObject((++index), studentForm.getAddress());
			pstmt.setObject((++index), studentForm.getEmail());

			pstmt.setObject((++index), studentForm.getParentPhoneNo());
			pstmt.setObject((++index), studentForm.getBloodGroupId());
			pstmt.setObject((++index), studentForm.getBranchId());

			pstmt.setObject((++index), studentForm.getRollNo());
			pstmt.setObject((++index), studentForm.getAlternativePhoneNo());
			pstmt.setObject((++index), studentForm.getMotherName());

			pstmt.setObject((++index), studentForm.getGender());
			pstmt.setObject((++index), studentForm.getAcademicYearId());
			pstmt.setObject((++index), studentForm.getAadharNo());

			pstmt.setObject((++index), studentForm.getHeight());
			pstmt.setObject((++index), studentForm.getJoiningYearNo());

			if(studentForm.getPhoto().getBytes().length >0) {
				pstmt.setObject((++index), photoName);
				pstmt.setBlob((++index), studentForm.getPhoto().getInputStream());
			}

			result = pstmt.executeUpdate();

		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}

		return result;
	}

	List getSpecifiedStudentsData(StudentForm studentData) {

		String sql;
		ResultSet rs;
		ArrayList<StudentForm> studentList = new ArrayList<>();

		Statement stmt = dbUtils.getDBStatement();

		try {

			String branchPart = " ";

			if(!studentData.getBranchId().equals("0")) {
				branchPart = " and b.branch_id ='" + studentData.getBranchId() + "' ";
			}

			sql = "select sd.student_id, ay.year, b.branch_name,  roll_no, student_name, father_name," +
					" date_format(dob,'%d/%m/%Y') as dob, date_format(doj,'%d/%m/%Y') " +
					"as doj, photo, photo_name" +
					" from student_details sd" +
					" left join academic_year ay on (ay.year_id = sd.academic_year_id)" +
					" left join branch b on (b.branch_id = sd.branch_id)" +
					" where" +
					" ay.year_id = " +studentData.getAcademicYearId()+
					" " + branchPart +
					" order by branch_name, roll_no, student_name";

			rs = stmt.executeQuery(sql);

			while(rs!=null && rs.next())
			{
				StudentForm studentForm = new StudentForm();
				studentForm.setStudentId(rs.getInt("student_id"));
				studentForm.setYear(rs.getString("year"));

				studentForm.setBranch(rs.getString("branch_name"));
				studentForm.setRollNo(rs.getString("roll_no"));

				studentForm.setName(rs.getString("student_name"));
				studentForm.setFatherName(rs.getString("father_name"));
				studentForm.setDob(rs.getString("dob"));
				studentForm.setDoj(rs.getString("doj"));
				studentForm.setPhotoName(rs.getString("photo_name").split("_")[2]);
				studentForm.setPhotoData(new String(Base64.getEncoder().encode(rs.getBytes("photo"))) );

				studentList.add(studentForm);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return studentList;
	}

	StudentForm getStudentData(int studentId) {

		String sql;
		ResultSet rs;
		StudentForm studentForm = new StudentForm();

		Statement stmt = dbUtils.getDBStatement();

		try {

			sql = " select student_id, student_name, father_name,  " +
					" date_format(dob,'%d/%m/%Y') as dob, date_format(doj,'%d/%m/%Y') as " +
					"doj, photo, photo_name, " +
					" aadhar, address, email, " +
					" guardian_mobile, (select bg_name from blood_group where bg_id = sd.blood_group_id) as blood_group, academic_year_id, " +
					" branch_id, is_active, roll_no, " +
					" mobile_no, mother_name, " +
					" gender, mentor_name, address," +
					" coalesce(joining_year_no,1) as joining_year_no, blood_group_id, height " +
					" from student_details sd " +
					" where student_id = " + studentId;

			rs = stmt.executeQuery(sql);

			if(rs.next())
			{
				studentForm.setStudentId(rs.getInt("student_id"));
				studentForm.setName(rs.getString("student_name"));
				studentForm.setFatherName(rs.getString("father_name"));
				studentForm.setDob(rs.getString("dob"));
				studentForm.setDoj(rs.getString("doj"));
				studentForm.setPhotoName(rs.getString("photo"));
				studentForm.setAadharNo(rs.getString("aadhar"));
				studentForm.setEmail(rs.getString("email"));
				studentForm.setParentPhoneNo(rs.getString("guardian_mobile"));
				studentForm.setBloodGroup(rs.getString("blood_group"));
				studentForm.setBatchId(rs.getInt("academic_year_id"));
				studentForm.setAcademicYearId(rs.getInt("academic_year_id"));
				studentForm.setBranchId(rs.getString("branch_id"));
				studentForm.setRollNo(rs.getString("roll_no"));
				studentForm.setAlternativePhoneNo(rs.getString("mobile_no"));
				studentForm.setMotherName(rs.getString("mother_name"));
				studentForm.setGender(rs.getString("gender"));
				studentForm.setAddress(rs.getString("address"));
				studentForm.setJoiningYearNo(rs.getInt("joining_year_no"));
				studentForm.setBloodGroupId(rs.getInt("blood_group_id"));
				studentForm.setHeight(rs.getInt("height"));
				//studentForm.setPhotoName(rs.getString("photo_name").split("_")[2]);
				studentForm.setPhotoName(rs.getString("photo_name"));
				studentForm.setPhotoData(new String(Base64.getEncoder().encode(rs.getBytes("photo"))) );
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return studentForm;
	}

	int updateStudentData(StudentForm studentForm) {

		PreparedStatement pstmt;

		String sql;
		int index = 0;
		int result = 0;
		String photoName = "";

		try {

	sql = " update student_details " +
			" set " +
			" student_name = ?, " +
			" father_name = ?, " +
			" dob = STR_TO_DATE(?,'%d/%m/%Y'), " +
			" doj = STR_TO_DATE(?,'%d/%m/%Y'), " +
			" address = ?," +
			" email = ?, " +
			" guardian_mobile = ?, " +
			" blood_group_id = ?, " +
			" branch_id = ?," +
			" roll_no = ?, " +
			" mobile_no = ?, " +
			" mother_name = ?, " +
			" gender = ?, " +
			" academic_year_id = ?, " +
			" aadhar = ?, " +
			" height = ?, " +
			" joining_year_no = ? " +
			((studentForm.getPhoto().getBytes().length >0) ? ",photo_name = ?, photo = ?" :"" )+
			"  " +
			" where student_id = ?";

			pstmt = dbUtils.getDBConnection().prepareStatement(sql);

			pstmt.setObject( (++index), studentForm.getName());
			pstmt.setObject((++index), studentForm.getFatherName());
			pstmt.setObject((++index), studentForm.getDob());

			pstmt.setObject((++index), studentForm.getDoj());
			pstmt.setObject((++index), studentForm.getAddress());
			pstmt.setObject((++index), studentForm.getEmail());

			pstmt.setObject((++index), studentForm.getParentPhoneNo());
			pstmt.setObject((++index), studentForm.getBloodGroupId());
			pstmt.setObject((++index), studentForm.getBranchId());

			pstmt.setObject((++index), studentForm.getRollNo());
			pstmt.setObject((++index), studentForm.getAlternativePhoneNo());
			pstmt.setObject((++index), studentForm.getMotherName());

			pstmt.setObject((++index), studentForm.getGender());
			pstmt.setObject((++index), studentForm.getAcademicYearId());
			pstmt.setObject((++index), studentForm.getAadharNo());

			pstmt.setObject((++index), studentForm.getHeight());
			pstmt.setObject((++index), studentForm.getJoiningYearNo());

			if(studentForm.getPhoto().getBytes().length >0) {
				pstmt.setObject((++index), photoName);
				pstmt.setBlob((++index), studentForm.getPhoto().getInputStream());
			}

			pstmt.setObject((++index), studentForm.getStudentId());

			result = pstmt.executeUpdate();

		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}

		return result;
	}

	int deleteStudentData(StudentForm studentForm) {

		String sql;
		int saResult = 1;
		int smResult = 1;
		int sdResult = 1;

		Statement stmt = dbUtils.getDBStatement();

		try {
			if(dbUtils.recordExists(GeneralQueries.isAttendanceExists(studentForm.getStudentId()))) {
				saResult = stmt.executeUpdate(GeneralQueries.deleteStudentAttendance(studentForm.getStudentId()));
			}

			if(dbUtils.recordExists(GeneralQueries.isMarksExists(studentForm.getStudentId()))) {
				smResult = stmt.executeUpdate(GeneralQueries.deleteStudentMarks(studentForm.getStudentId()));
			}

			if(saResult > 0 && smResult > 0 ) {
				sdResult = stmt.executeUpdate(GeneralQueries.deleteStudenData(studentForm.getStudentId()));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return sdResult;
	}

}
