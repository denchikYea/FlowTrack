package by.kolp.tasks.factories;

import by.kolp.tasks.model.dto.NumericDataEntryDTO;
import by.kolp.tasks.model.entity.NumericDataEntry;

public class NumericDataEntryDtoFactory {

    public NumericDataEntryDTO makeNumericDataEntryDto(NumericDataEntry entry) {
        return new NumericDataEntryDTO(
                entry.getKey(),
                entry.getValue(),
                entry.getCreatedAt()
        );
    }
}
