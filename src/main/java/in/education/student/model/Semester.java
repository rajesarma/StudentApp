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

//@Entity
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

	@NotNull
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="year_no")
	private Year year;

}


