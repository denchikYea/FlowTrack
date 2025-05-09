package by.kolp.tasks.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "numerical_data_entries")
public class NumericDataEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String key;
    private Integer value;

    @ManyToOne
    @JoinColumn(name = "numerical_data_id")
    private NumericalData numericalData;


    public NumericDataEntry(String key, Integer value, NumericalData numericalData) {
        this.key = key;
        this.value = value;
        this.numericalData = numericalData;
    }
}
