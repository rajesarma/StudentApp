package in.education.student.student;

import in.education.student.model.Student;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends CrudRepository<Student, Long> {

	Optional<Student> findByRollNo(String rollNo);
	List<Student> findByNameContains(String name);
	List<Student> findByRollNoAndNameContains(String rollNo, String name);

	List<Student> findByBranchId(String branchId);
	List<Student> findByBatchId(long academicYearNo);
	List<Student> findByJoiningYearNo(long joiningYearNo);
	List<Student> findByBranchIdAndBatchId(String branchId, long academicYearNo);
	List<Student> findByBranchIdAndJoiningYearNo(String branchId, long joiningYearNo);
	List<Student> findByBatchIdAndJoiningYearNo(long academicYearNo, long joiningYearNo);
	List<Student> findByBranchIdAndBatchIdAndJoiningYearNo(String branchId,
			long academicYearNo, long joiningYearNo);
}
