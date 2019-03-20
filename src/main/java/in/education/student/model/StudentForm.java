package in.education.student.model;

import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "student_details")
public class StudentForm {
	@Id
	@Column(name = "student_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private long studentId;

	@Column(name = "student_name")
	private String name=null;

	@Column(name = "father_name")
	private String fatherName=null;

	@Column(name = "dob")
	private String dob=null;

	@Column(name = "doj")
	private String doj=null;

	@Column(name = "photo_name")
	private String photoName=null;

	@Column(name = "aadhar")
	private String aadharNo=null;

	@Column(name = "address")
	private String address=null;

	@Column(name = "email")
	private String email=null;

	@Column(name = "guardian_mobile")
	private String parentPhoneNo=null;

	@Column(name = "blood_group_id")
	private int bloodGroupId = 0;

	@Column(name = "academic_year_id")
	private int academicYearId = 0;

	@Column(name = "branch_id")
	private String branchId= null;

	@Column(name = "roll_no")
	private String rollNo =null;

	@Column(name = "mobile_no")
	private String alternativePhoneNo=null;

	@Column(name = "mother_name")
	private String motherName=null;

	@Column(name = "gender")
	private String gender=null;

	@Column(name = "photo")
	private MultipartFile photo;

	@Column(name = "height")
	private int height= 0;

	@Column(name = "joining_year_no")
	private String joiningYearNo= null;

	private int subjectId= 0;
	private int batchId = 0;
	private int examTypeId = 0;
	private int semesterId = 0;

	private String branch = null;
	private String batch = null;
	private String bloodGroup = null;

	private String year = null;

	private String photoData;

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

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getDoj() {
		return doj;
	}

	public void setDoj(String doj) {
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

	public int getBloodGroupId() {
		return bloodGroupId;
	}

	public void setBloodGroupId(int bloodGroupId) {
		this.bloodGroupId = bloodGroupId;
	}

	public int getAcademicYearId() {
		return academicYearId;
	}

	public void setAcademicYearId(int academicYearId) {
		this.academicYearId = academicYearId;
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

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public MultipartFile getPhoto() {
		return photo;
	}

	public void setPhoto(MultipartFile photo) {
		this.photo = photo;
	}

	public int getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
	}

	public int getBatchId() {
		return batchId;
	}

	public void setBatchId(int batchId) {
		this.batchId = batchId;
	}

	public int getExamTypeId() {
		return examTypeId;
	}

	public void setExamTypeId(int examTypeId) {
		this.examTypeId = examTypeId;
	}

	public int getSemesterId() {
		return semesterId;
	}

	public void setSemesterId(int semesterId) {
		this.semesterId = semesterId;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public String getBatch() {
		return batch;
	}

	public void setBatch(String batch) {
		this.batch = batch;
	}

	public String getBloodGroup() {
		return bloodGroup;
	}

	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public String getJoiningYearNo() {
		return joiningYearNo;
	}

	public void setJoiningYearNo(String joiningYearNo) {
		this.joiningYearNo = joiningYearNo;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getPhotoData() {
		return photoData;
	}

	public void setPhotoData(String photoData) {
		this.photoData = photoData;
	}
}



