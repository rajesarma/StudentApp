package in.education.student.model.repository;

import in.education.student.model.Year;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface YearRepository extends CrudRepository<Year, Long> {

	List<Year> findByYearId(long yearId);

}
