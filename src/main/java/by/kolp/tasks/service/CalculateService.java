package by.kolp.tasks.service;

import by.kolp.tasks.model.dto.NumericDataEntryDTO;
import by.kolp.tasks.model.entity.NumericData;
import by.kolp.tasks.model.entity.NumericDataEntry;
import by.kolp.tasks.repository.interfaces.NumericDataEntryRepository;
import by.kolp.tasks.repository.interfaces.NumericDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CalculateService {

    @Autowired
    private NumericDataRepository numericDataRepository;

    @Autowired
    private NumericDataEntryRepository numericDataEntryRepository;

    public Integer sumAllValues(NumericDataEntryDTO data) {

        Integer sum = 0;
        NumericData numericData = new NumericData();
        numericData.addEntry(data.key(), data.value());

        numericDataRepository.save(numericData);

        List<NumericDataEntry> AllEntries = numericDataEntryRepository.findAll();

        sum = AllEntries.stream()
                .mapToInt(NumericDataEntry::getValue)
                .sum();

        for (NumericDataEntry entry : AllEntries) {
            if (entry != null) {
                sum += entry.getValue();
            }
        }

        return sum;
    }
}
