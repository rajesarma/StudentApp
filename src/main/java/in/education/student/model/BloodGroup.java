package in.education.student.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

//@Entity
@Table(name="blood_group")
public class BloodGroup {

	@Id
	@NotNull
	@Column(name="bg_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private long bloodGroupId;

	@Column(name="bg_name")
	private String bloodGroup;
}


