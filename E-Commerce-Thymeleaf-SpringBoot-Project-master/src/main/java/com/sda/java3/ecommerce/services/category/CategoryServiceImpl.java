package com.sda.java3.ecommerce.services.category;

import com.sda.java3.ecommerce.domains.Category;
import com.sda.java3.ecommerce.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CategoryServiceImpl implements CategoryService {
    protected final CategoryRepository categoryRepository;

    public CategoryServiceImpl(final CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Optional<Category> getCategoryById(UUID id) {
        return categoryRepository.findById(id);
    }

    @Override
    public Category saveCategory(SaveCategoryRequest request) {
        Category category = Category.builder()
                .name(request.getName())
                .description(request.getDescription())
                .createdAt(LocalDateTime.now())
                .build();
        
        return categoryRepository.save(category);
    }

    @Override
    public Optional<Category> updateCategory(UUID id, SaveCategoryRequest request) {
        return categoryRepository.findById(id).map(category -> {
            category.setName(request.getName());
            category.setDescription(request.getDescription());
            return categoryRepository.save(category);
        });
    }

    @Override
    public boolean deleteCategory(UUID id) {
        if (categoryRepository.existsById(id)) {
            categoryRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public List<Category> getRootCategories() {
        return categoryRepository.getRootCategories();
    }

    @Override
    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public void delete(UUID id) {
        var item = categoryRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Id is invalid"));
        categoryRepository.delete(item);
    }

    @Override
    public UUID save(SaveCategoryRequest request) {
        var dbItem = categoryRepository.findById(request.getId());
        if (dbItem.isPresent()) {
            dbItem.get().setName(request.getName());
            categoryRepository.save(dbItem.get());
            return dbItem.get().getId();
        }
        var newItem = Category.builder()
                .id(UUID.randomUUID())
                .name(request.getName())
                .build();
        categoryRepository.save(newItem);
        return newItem.getId();
    }

    @Override
    public Optional<Category> findById(UUID id) {
        return categoryRepository.findById(id);
    }
}
