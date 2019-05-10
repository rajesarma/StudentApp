package in.education.student.converter;

import in.education.student.dto.RoleDto;
import in.education.student.model.Role;

public class RoleConverter {

	public static Role dtoToEntity(final RoleDto roleDto) {

		Role role = new Role();
		role.setRoleId(roleDto.getRoleId());
		role.setRoleName(roleDto.getRoleName());
		role.setServices(roleDto.getServices());
		return role;
	}

	public static RoleDto entityToDto(final Role role) {

		RoleDto roleDto = new RoleDto();
		roleDto.setRoleId(role.getRoleId());
		roleDto.setRoleName(role.getRoleName());
		roleDto.setServices(role.getServices());

		return roleDto;
	}
}
