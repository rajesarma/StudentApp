package in.education.student.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "student_details")
public class Student implements Serializable {

	private static final long serialVersionUID = -5889999597722728860L;

	@Id
	@Column(name = "student_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private long studentId;

//	@NotEmpty(message = "Student Name can not be empty")
	@Column(name = "student_name")
	private String name=null;

//	@NotEmpty(message = "Father Name can not be empty")
	@Column(name = "father_name")
	private String fatherName=null;

//	@NotEmpty(message = "Mother Name can not be empty")
	@Column(name = "mother_name")
	private String motherName=null;

//	@DateTimeFormat(pattern="dd/mm/yyyy")
//	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	@Temporal(TemporalType.DATE)
//	@NotEmpty(message = "DOB can not be empty")
	@Column(name = "dob")
	private Date dob=null;

//	@DateTimeFormat(pattern="dd/mm/yyyy")
//	@NotEmpty(message = "DOJ can not be empty")
//	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	@Temporal(TemporalType.DATE)
	@Column(name = "doj")
	private Date doj=null;

	@Column(name = "photo_name")
	private String photoName=null;

//	@NotEmpty(message = "Aadhar No. can not be empty")
	@Column(name = "aadhar")
	private String aadharNo=null;

//	@NotEmpty(message = "Address can not be empty")
	@Column(name = "address")
	private String address=null;

//	@NotEmpty(message = "email can not be empty")
	@Column(name = "email")
	private String email=null;

	@Column(name = "guardian_mobile")
	@Size(min = 10, max = 10, message = "Phone No. must be 10 Digits")
	private String parentPhoneNo=null;

	@Column(name = "mobile_no")
	@Size(min = 10, max = 10, message = "Phone No. must be 10 Digits")
	private String alternativePhoneNo=null;

	@Column(name = "blood_group_id")
	private long bloodGroupId = 0;

	@Column(name = "batch_id")
	private long batchId = 0;

	@Column(name = "branch_id")
	private String branchId= null;

//	@NotEmpty(message = "Roll No. can not be empty")
	@Column(name = "roll_no")
	private String rollNo =null;

	@Enumerated(EnumType.STRING)
	@Column(name = "gender", length = 10)
	private Gender gender = Gender.MALE;

	@Column(name = "photo")
	@Lob
	private byte[] photo;

	@Column(name = "height")
	private int height;

	@Column(name = "joining_year_no")
	private long joiningYearNo;

	@Column(name = "joining_semester_id")
	private long joiningSemesterId;

	public long getStudentId() {
		return studentId;
	}

	public void setStudentId(long studentId) {
		this.studentId = studentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFatherName() {
		return fatherName;
	}

	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public Date getDoj() {
		return doj;
	}

	public void setDoj(Date doj) {
		this.doj = doj;
	}

	public String getPhotoName() {
		return photoName;
	}

	public void setPhotoName(String photoName) {
		this.photoName = photoName;
	}

	public String getAadharNo() {
		return aadharNo;
	}

	public void setAadharNo(String aadharNo) {
		this.aadharNo = aadharNo;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getParentPhoneNo() {
		return parentPhoneNo;
	}

	public void setParentPhoneNo(String parentPhoneNo) {
		this.parentPhoneNo = parentPhoneNo;
	}

	public long getBloodGroupId() {
		return bloodGroupId;
	}

	public void setBloodGroupId(long bloodGroupId) {
		this.bloodGroupId = bloodGroupId;
	}

	public long getBatchId() {
		return batchId;
	}

	public void setBatchId(long batchId) {
		this.batchId = batchId;
	}

	public String getBranchId() {
		return branchId;
	}

	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}

	public String getRollNo() {
		return rollNo;
	}

	public void setRollNo(String rollNo) {
		this.rollNo = rollNo;
	}

	public String getAlternativePhoneNo() {
		return alternativePhoneNo;
	}

	public void setAlternativePhoneNo(String alternativePhoneNo) {
		this.alternativePhoneNo = alternativePhoneNo;
	}

	public String getMotherName() {
		return motherName;
	}

	public void setMotherName(String motherName) {
		this.motherName = motherName;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public long getJoiningYearNo() {
		return joiningYearNo;
	}

	public void setJoiningYearNo(long joiningYearNo) {
		this.joiningYearNo = joiningYearNo;
	}

	public long getJoiningSemesterId() {
		return joiningSemesterId;
	}

	public void setJoiningSemesterId(long joiningSemesterId) {
		this.joiningSemesterId = joiningSemesterId;
	}
}



