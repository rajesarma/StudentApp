package in.education.student.common.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "years")
public class Year {

	@Id
	@NotNull
	@Column(name = "year_no")
	private String yearId;

	@Column(name = "year_name")
	private String year;
}
