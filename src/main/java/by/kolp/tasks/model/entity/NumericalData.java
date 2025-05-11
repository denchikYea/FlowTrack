package by.kolp.tasks.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(schema = "numerical_data")
public class NumericalData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "numericalData", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<NumericDataEntry> entriesList = new ArrayList<>();

    public void addEntry(String key, Integer value) {
        entriesList.add(new NumericDataEntry(key, value, this));
    }

}
