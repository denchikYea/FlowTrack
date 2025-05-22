package by.kolp.tasks.repository.interfaces;

import by.kolp.tasks.model.entity.NumericDataEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NumericDataEntryRepository extends JpaRepository<NumericDataEntry, Long> {
}
