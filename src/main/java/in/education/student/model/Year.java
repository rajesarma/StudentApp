package in.education.student.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "year")
public class Year {

	@Id
	@NotNull
	@Column(name = "year_no")
	private long yearId;

	@Column(name = "year_name")
	private String year;

	/*@OneToMany(targetEntity = Semester.class,
			cascade = CascadeType.ALL,
			fetch = FetchType.EAGER)
	private List<Semester> semseters;*/

	@OneToMany(cascade= CascadeType.ALL, fetch= FetchType.EAGER)
	@JoinColumn(name="year_no", referencedColumnName="year_no")
	private List<Semester> semseters;

	public long getYearId() {
		return yearId;
	}

	public void setYearId(long yearId) {
		this.yearId = yearId;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public List<Semester> getSemseters() {
		return semseters;
	}

	public void setSemseters(List<Semester> semseters) {
		this.semseters = semseters;
	}
}
