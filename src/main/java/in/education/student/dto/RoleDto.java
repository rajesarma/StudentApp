package in.education.student.dto;

import in.education.student.model.Service;

import java.io.Serializable;
import java.util.List;

public class RoleDto implements Serializable {

	private static final long serialVersionUID = 1L;
	private long roleId;
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
	private List<Service> services;

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public List<Service> getServices() {
		return services;
	}

	public void setServices(List<Service> services) {
		this.services = services;
	}

	public String getRole() {
		return "ROLE_" + this.getRoleName();
	}
}
