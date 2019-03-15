package in.education.student.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class Role {

	public Role(){ }

	public Role(long roleId, String roleName){
		//super();
		this.roleId = roleId;
		this.roleName = roleName;
	}

	@Id
	@Column(name="role_id", unique=true, nullable = false)
	@GeneratedValue
	private long roleId;

	@Column(name="role_name")
	private String roleName;

	public long getRoleId() {
		return roleId;
	}

	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
}
