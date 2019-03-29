package in.education.student.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

//@Entity
@Table(name = "branch")
public class Branch {

	@Id
	@NotNull
	@Column(name = "branch_id")
	private String branchId;

	@Column(name = "branch_name")
	private String branchName;
}
