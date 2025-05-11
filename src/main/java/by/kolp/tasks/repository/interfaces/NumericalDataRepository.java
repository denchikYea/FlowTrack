package by.kolp.tasks.repository.interfaces;

import by.kolp.tasks.model.entity.NumericalData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NumericalDataRepository extends JpaRepository<NumericalData, Long> {
}
