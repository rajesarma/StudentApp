package in.education.student.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "qualifications")
public class Qualification {

	@Id
	@NotNull
	@Column(name = "qly_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private long qlyId;

	@Column(name="qly_name")
	private String qlyName;
}
