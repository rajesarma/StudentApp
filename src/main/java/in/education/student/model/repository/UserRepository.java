package in.education.student.model.repository;

import in.education.student.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

	Optional<User> findByUserName(String username);

}