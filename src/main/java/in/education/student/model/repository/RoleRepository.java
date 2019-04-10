package in.education.student.model.repository;

import in.education.student.model.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {

	List<Role> findByRoleId(long roleId);

	List<Role> findByRoleName(String roleName);

	List<Role> findByRoleNameIn(String... roles);
//	List<Role> findByRoleNameInOrderByServices(String... roles);

}
