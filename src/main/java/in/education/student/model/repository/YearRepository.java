package in.education.student.model.repository;

import in.education.student.model.Year;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface YearRepository extends CrudRepository<Year, Long> {

	Optional<Year> findByYearId(long yearId);

}
