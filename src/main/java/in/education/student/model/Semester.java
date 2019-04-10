package in.education.student.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="semester")
public class Semester {

	@Id
	@NotNull
	@Column(name="semester_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private long semesterId;

	@NotNull
	@Column(name="semester_name")
	private String semesterName;

	/*@NotNull
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="year_no")
	private Year year;*/

	/*@ManyToOne
	@JoinColumn(name = "year_no")
	private Year year;*/

//	private Year year;

	public long getSemesterId() {
		return semesterId;
	}

	public void setSemesterId(long semesterId) {
		this.semesterId = semesterId;
	}

	public String getSemesterName() {
		return semesterName;
	}

	public void setSemesterName(String semesterName) {
		this.semesterName = semesterName;
	}

	/*public Year getYear() {
		return year;
	}

	public void setYear(Year year) {
		this.year = year;
	}*/
}


