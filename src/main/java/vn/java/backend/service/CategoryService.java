package vn.java.backend.service;


import org.springframework.stereotype.Service;
import vn.java.backend.model.entity.Category;
import vn.java.backend.model.response.CategoryResponse;
import vn.java.backend.repository.CategoryRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    private CategoryRepository categoryRepository;

    public List<CategoryResponse> getAllCategories(){
        return categoryRepository.findAll().stream().map(this:: convertToDto).collect(Collectors.toList());
    }

    public CategoryResponse convertToDto(Category category){
        CategoryResponse dto = new CategoryResponse();
        dto.setId(category.getId());
        dto.setName(category.getName());
        dto.setDescription(category.getDescription());
        return dto;
    }

}
