package in.education.student.student;

import in.education.student.model.StudentForm;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface StudentRepository extends CrudRepository<StudentForm, Long> {

	List<StudentForm> findByBranchIdAndJoiningYearNo(String branchId, long joiningYearNo);

}
