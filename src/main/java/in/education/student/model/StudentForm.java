package in.education.student.model;

import org.springframework.web.multipart.MultipartFile;

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
import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name = "student_details")
public class StudentForm {

	@Id
	@Column(name = "student_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private long studentId;

	@NotEmpty(message = "Student Name can not be empty")
	@Column(name = "student_name")
	private String name=null;

	@NotEmpty(message = "Father Name can not be empty")
	@Column(name = "father_name")
	private String fatherName=null;

	@NotEmpty(message = "Mother Name can not be empty")
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

	@NotEmpty(message = "Aadhar No. can not be empty")
	@Column(name = "aadhar")
	private String aadharNo=null;

	@NotEmpty(message = "Address can not be empty")
	@Column(name = "address")
	private String address=null;

	@NotEmpty(message = "email can not be empty")
	@Column(name = "email")
	private String email=null;

	@Column(name = "guardian_mobile")
	@Size(min = 10, max = 10, message = "Phone No. must be 10 Digits")
	private String parentPhoneNo=null;

	@Column(name = "mobile_no")
	@Size(min = 10, max = 10, message = "Phone No. must be 10 Digits")
	private String alternativePhoneNo=null;

	@Column(name = "blood_group_id")
	private int bloodGroupId = 0;

	@Column(name = "academic_year_id")
	private int academicYearId = 0;

	@Column(name = "branch_id")
	private String branchId= null;

	@NotEmpty(message = "Roll No. can not be empty")
	@Column(name = "roll_no")
	private String rollNo =null;

	@Enumerated(EnumType.STRING)
	@Column(name = "gender", length = 10)
	private Gender gender = Gender.MALE;

	/*
	@Lob
	private Blob photo;*/

	@Transient
	private MultipartFile image;

	@Column(name = "photo")
	@Lob
	private byte[] photo;

	@Column(name = "height")
	private int height;

	@Column(name = "joining_year_no")
	private int joiningYearNo;

	private String branch = null;
	private String bloodGroup = null;
	private String year = null;

	@Transient
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

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public MultipartFile getImage() {
		return image;
	}

	public void setImage(MultipartFile image) {
		this.image = image;
	}

	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
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

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getPhotoData() {

		if (getPhoto() != null && getPhoto().length > 0) {
			return new String(java.util.Base64.getEncoder().encode(getPhoto()));
		}
		return null;
	}

	public void setPhotoData(String photoData) {
		this.photoData = photoData;
	}

	public int getJoiningYearNo() {
		return joiningYearNo;
	}

	public void setJoiningYearNo(int joiningYearNo) {
		this.joiningYearNo = joiningYearNo;
	}
}



