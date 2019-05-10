package in.education.student.student;

import in.education.student.model.Student;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends CrudRepository<Student, Long> {

	List<Student> findByBranchIdAndJoiningYearNo(String branchId, int joiningYearNo);

	Optional<Student> findByRollNo(String rollNo);

}
