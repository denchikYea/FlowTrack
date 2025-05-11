package by.kolp.tasks.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(schema = "numeric_data")
public class NumericData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "numericData", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<NumericDataEntry> entriesList = new ArrayList<>();

    public void addEntry(String key, Integer value) {
        entriesList.add(new NumericDataEntry(key, value, this));
    }

}
