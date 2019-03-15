package in.education.student.common;

public class GeneralQueries {

	public static String getUserData(String userName, String password) {
		return "select id, user_name, user_desc, email, r.role_id, role_name " +
				" from users u join roles r on r.role_id = u.role_id " +
				" where user_name = '"+userName+"'" +
				" and password ='"+password+"'";
	}

	public static String getUserData(long userId) {
		return "select id, user_name, email, r.role_id, role_name" +
				" from users u join roles r on r.role_id = u.role_id" +
				" where id="+userId;
	}

	public static String isUserExists(String userName) {
		String sql = "select count(*) as count from users where user_name ='"+userName+"' ";
		return sql;
	}

	public static String isValidUser(String userName, String password) {
		return " select count(*) as count from users " +
				" where user_name = '"+userName+ "'" +
				" and password = '"+password+"' ";
	}

	public static String updateUserLogin(long userId) {
		return "update users set last_login = now() where id = "+userId;
	}

	public static String getUserFailureLoginCount(String userName) {
		return "select case when failed_login_attempts is not null then " +
				"failed_login_attempts else 0 end as count from users where user_name ='"+userName+"'";
	}

	public static String resetUserFailureAttempt(long userId) {
		String sql = "update users set failed_login_attempts = 0 where id = " + userId;
		return sql;
	}

	public static String updateUserFailureAttempt(String userName) {
		return "update users set failed_login_attempts = (coalesce(failed_login_attempts,0) + 1) " +
				"where user_name = '" + userName+"'";
	}

	public static String getUsersByRole(long roleId) {
		return "select id, user_id, user_name, email, r.role_id, role_name from users u join roles r on r.role_id = u.role_id where r.role_id = "+roleId+ " order by id";
	}

	public static String addUser()
	{
		return "insert into users(id, user_id, user_name, email,role_id) values (" +
				"(SELECT MAX( id )+1 FROM users user), ?, ?, ?,?)";
	}

	public static String getAllUsers = "select id, user_id, user_name, email, r.role_id, role_name from users u join roles r on r.role_id = u.role_id order by id";
	public static String getRoles = "select role_id, role_name from roles";

	public static String getAcademicYears = "select year_id, year from academic_year";
	public static String getBloodGroups = "select bg_id, bg_name from blood_group";
	public static String getBranches = "select branch_id, branch_name from branch";
	public static String getExamTypes = "select exam_type_id, exam_type from exam_type";
	public static String getQualifications = "select qly_id, qly_name from " +
			"qualifications";

	public static String getSemesters = "select semester_id, semester_name from semester";

	public static String getSemestersByYear(long yearId) {
		return "select semester_id, semester_name from semester where year_id = "+yearId+ " order by semester_id";
	}

	public static String getSubjects = "select subject_id, subject_name from subjects";

	public static String getSubjectsBySemester(long semesterId) {
		return "select subject_id, subject_name from subjects where semester_id = "+semesterId+ " order by subject_id";
	}

	public static String getSubjectsBySemester(String branchId) {
		return "select subject_id, subject_name from subjects where branch_id = '"+branchId+ "' order by subject_id";
	}

	public static String getSubjectsBySemesterAndBranch(long semesterId,
			String branchId) {
		return "select subject_id, subject_name from subjects where semester_id = "+semesterId+" and branch_id = '"+branchId+ "' order by subject_id";
	}

	public static String getYears = "select year_no, year_name from years";

	public static String getMaxMarks(long examTypeId) {
		return "select max_marks from exam_type where exam_type_id = "+examTypeId;
	}
}
