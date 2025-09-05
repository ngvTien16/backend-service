package vn.java.backend.service;


import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.java.backend.model.entity.Category;
import vn.java.backend.model.request.CategoryRequest;
import vn.java.backend.model.response.CategoryResponse;
import vn.java.backend.repository.CategoryRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    //get all category - CUSTOMER - SELLER - ADMIN
    public List<CategoryResponse> getAllCategories(){
        return categoryRepository.findAll().stream().map(this:: convertToDto).collect(Collectors.toList());
    }

    public CategoryResponse getById(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Category not found"));
        return new CategoryResponse(category.getId(), category.getName(), category.getDescription());
    }

    @Transactional
    public CategoryResponse create(CategoryRequest req) {
        if (categoryRepository.existsByName(req.getName())) {
            throw new RuntimeException("Category already exists");
        }
        Category c = Category.builder()
                .name(req.getName())
                .description(req.getDescription())
                .build();
        categoryRepository.save(c);
        return new CategoryResponse(c.getId(), c.getName(), c.getDescription());
    }

    @Transactional
    public CategoryResponse update(Long id, CategoryRequest req) {
        Category c = categoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Category not found"));
        c.setName(req.getName());
        c.setDescription(req.getDescription());
        categoryRepository.save(c);
        return new CategoryResponse(c.getId(), c.getName(), c.getDescription());
    }

    @Transactional
    public void delete(Long id) {
        categoryRepository.deleteById(id);
    }
    public CategoryResponse convertToDto(Category category){
        CategoryResponse dto = new CategoryResponse();
        dto.setId(category.getId());
        dto.setName(category.getName());
        dto.setDescription(category.getDescription());
        return dto;
    }
}
