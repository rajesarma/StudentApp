package in.education.student.model.repository;

import in.education.student.model.Service;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ServiceRepository extends CrudRepository<Service, Long> {

	@Query(value = "select s.service_id, s.service_url, s.service_name, s.disabled," +
			" s.parent_id, s.display_order, s.menu_display " +
			" from users u join role_services rs on (u.role_id = rs.role_id ) " +
			" join services s on (s.service_id = rs.service_id) " +
			" where user_name = :username " +
			" order by parent_id, service_id", nativeQuery = true)
	Iterable<Service> findByServiceName(@Param("username") String userName);
}
