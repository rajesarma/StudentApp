package in.education.student.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

//@Entity
@Table(name = "academic_year")
public class AcademicYear {

	@Id
	@NotNull
	@Column(name = "year_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private long yearId;

	@Column(name="year")
	private String year;
}
