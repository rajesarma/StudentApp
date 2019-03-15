package in.education.student.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "users")
public class User {

	public User() {

	}

	public User(long userId, String userName, String email, long roleId, String userDesc)
	{
		super();
		this.id = userId;
		this.userName = userName;
		this.email = email;
		this.userDesc = userDesc;
		this.role = new Role(roleId, "");
	}

	@Id
	@Column(name="id", updatable = false, nullable = false)
	@NotNull
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private long id;

	@Column(name="user_name")
	@NotNull
	private String userName;

	@Column(name="password")
	@NotNull
	private String password;

	@Column(name="is_active")
	@NotNull
	private String isActive;

	@Column(name="role_id")
	@NotNull
	private int roleId;

	@Column(name="user_desc")
	private String userDesc;

	@Column(name="student_id")
	private long studentId;

	@Column(name="faculty_id")
	private long facultyId;

	@Column(name="email")
	private String email;

	@Column(name="last_login")
	private Date lastLogin;

	@Column(name="failed_login_attempts")
	private Long failedLoginAttempts;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="role_id")
	private Role role;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getIsActive() {
		return isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public String getUserDesc() {
		return userDesc;
	}

	public void setUserDesc(String userDesc) {
		this.userDesc = userDesc;
	}

	public long getStudentId() {
		return studentId;
	}

	public void setStudentId(long studentId) {
		this.studentId = studentId;
	}

	public long getFacultyId() {
		return facultyId;
	}

	public void setFacultyId(long facultyId) {
		this.facultyId = facultyId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Role getRole() {
		return role;
	}

	public Date getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}

	public Long getFailedLoginAttempts() {
		return failedLoginAttempts;
	}

	public void setFailedLoginAttempts(Long failedLoginAttempts) {
		this.failedLoginAttempts = failedLoginAttempts;
	}

	public void setRole(Role role) {
		this.role = role;
	}
}
