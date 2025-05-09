package by.kolp.tasks.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@Entity
public class NumericalData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "NumericalData", cascade = CascadeType.ALL,orphanRemoval = true)
    private List<NumericDataEntry> entriesList = new ArrayList<>();

    public void addEntry(String key, Integer value) {
        entriesList.add(new NumericDataEntry(key , value, this));
    }

}
