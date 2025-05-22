package by.kolp.tasks.factories;

import by.kolp.tasks.model.dto.NumericDataDTO;
import by.kolp.tasks.model.dto.NumericDataEntryDTO;
import by.kolp.tasks.model.entity.NumericData;
import by.kolp.tasks.model.entity.NumericDataEntry;

import java.util.List;
import java.util.stream.Collectors;

public class NumericDataDtoFactory {

    public NumericDataDTO makeNumericDataDto(NumericDataEntry entry) {


        NumericData numericData = entry.getNumericData();

        List<NumericDataEntryDTO> entryDtos = numericData.getEntriesList().stream()
                .map(e -> new NumericDataEntryDTO(e.getKey(), e.getValue(),e.getCreatedAt()))
                .collect(Collectors.toList());

        return new NumericDataDTO(numericData.getId(), entryDtos);
    }

}
