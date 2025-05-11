package by.kolp.tasks.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NumericDataDTO {
    private Long id;
    private List<NumericDataDTO> entries;
}
