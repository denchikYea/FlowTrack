package by.kolp.tasks.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Entity
@Data
@NoArgsConstructor
@Table(name = "numeric_data_entries")
public class NumericDataEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String key;
    private Integer value;

    @ManyToOne
    @JoinColumn(name = "numeric_data_id")
    private NumericData numericData;


    private Instant createdAt = Instant.now();

    public NumericDataEntry(String key, Integer value, NumericData numericData) {
        this.key = key;
        this.value = value;
        this.numericData = numericData;
    }
}
