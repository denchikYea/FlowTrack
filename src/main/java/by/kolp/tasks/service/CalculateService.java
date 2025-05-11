package by.kolp.tasks.service;

import by.kolp.tasks.model.dto.NumericDataEntryDTO;
import by.kolp.tasks.model.entity.NumericDataEntry;
import by.kolp.tasks.model.entity.NumericalData;
import by.kolp.tasks.repository.interfaces.NumericDataEntryRepository;
import by.kolp.tasks.repository.interfaces.NumericalDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CalculateService{


    @Autowired
    private NumericalDataRepository numericalDataRepository;

    @Autowired
    private NumericDataEntryRepository numericDataEntryRepository;

    public Integer sumAllValues(NumericDataEntryDTO data) {

        Integer sum = 0;
        NumericalData newNumericaldata = new NumericalData();
        newNumericaldata.addEntry(data.getKey(), data.getValue());

        numericalDataRepository.save(newNumericaldata);

        List<NumericDataEntry> AllEntries = numericDataEntryRepository.findAll();

        sum = AllEntries.stream()
                .mapToInt(e -> e.getValue())
                .sum();

        for (NumericDataEntry entry : AllEntries) {
            if(entry != null) {
                sum += entry.getValue();
            }
        }

        return sum;
    }
}
