package by.kolp.tasks.factories;

import by.kolp.tasks.model.dto.CategoryDTO;
import by.kolp.tasks.model.entity.Category;

public class CategoryDtoFactory {

    public CategoryDTO makeCategoryDto(Category category) {

        return CategoryDTO.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }
}
