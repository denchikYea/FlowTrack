package by.kolp.tasks.repository.interfaces;

import by.kolp.tasks.model.entity.NumericData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NumericDataRepository extends JpaRepository<NumericData, Long> {
}
