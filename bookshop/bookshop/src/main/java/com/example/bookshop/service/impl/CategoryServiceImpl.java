package com.example.bookshop.service.impl;//package com.example.bookshop.service.impl;
//
//
//import com.example.bookshop.dto.CategoryDTO;
//import com.example.bookshop.entity.Category;
//import com.example.bookshop.mapper.CategoryMapper;
//import com.example.bookshop.repository.CategoryRepository;
//import com.example.bookshop.service.CategoryService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import java.util.List;
//import java.util.stream.Collectors;
////import org.modelmapper.ModelMapper;
//@Service
//public class CategoryServiceImpl implements CategoryService {
//
//    @Autowired
//    private CategoryRepository categoryRepository;
//
//    @Autowired
//    private CategoryMapper categoryMapper;
//
//    @Override
//    public List<CategoryDTO> getAllCategories() {
//        List<Category> categories = categoryRepository.findAll();
//        return categories.stream()
//                .map(CategoryMapper.INSTANCE::toDTO)
//                .collect(Collectors.toList());
//    }
//
//    @Override
//    public List<Category> getall() {
//        return categoryRepository.findAll();
//    }
//    @Override
//    public CategoryDTO getCategoryById(Integer id) {
//        Category category = categoryRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("Category not found"));
//        return categoryMapper.categoryToCategoryDTO(category);
//    }
//
//    @Override
//    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
//        Category category = categoryMapper.categoryDTOToCategory(categoryDTO);
//        category = categoryRepository.save(category);
//        return categoryMapper.categoryToCategoryDTO(category);
//    }
//
//    @Override
//    public CategoryDTO updateCategory(Integer id, CategoryDTO categoryDTO) {
//        Category category = categoryRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("Category not found"));
//
//        categoryMapper.updateCategoryFromDTO(categoryDTO, category);
//        category = categoryRepository.save(category);
//        return categoryMapper.categoryToCategoryDTO(category);
////        Category category = categoryRepository.findById(id)
////                .orElseThrow(() -> new RuntimeException("Category not found"));
////
////        // Update properties of category entity with those from categoryDTO
////
////        category = categoryRepository.save(category);
////        return CategoryMapper.INSTANCE.categoryToCategoryDTO(category);
//    }
//
//    @Override
//    public void deleteCategory(Integer id) {
//        Category category = categoryRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("Category not found"));
//        categoryRepository.delete(category);
//    }
//
//}
//
//

import com.example.bookshop.dto.CategoryDTO;
import com.example.bookshop.entity.Category;
import com.example.bookshop.mapper.CategoryMapper;
import com.example.bookshop.repository.CategoryRepository;
import com.example.bookshop.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryMapper categoryMapper;

//    @Override
//    public List<Category> getAlalCategories() {
//        return categoryRepository.findAll();
//    }

//    @Override
//    public List<CategoryDTO> getAllCategories() {
//        return categoryRepository.findAll().stream()
//                .map(CategoryMapper.INSTANCE::toDTO)
//                .collect(Collectors.toList());
//    }
    @Override
    public List<CategoryDTO> getAllCategories() {
        return categoryRepository.findAll().stream()
                .map(categoryMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CategoryDTO getCategoryById(int id) {
        return categoryRepository.findById(id)
                .map(categoryMapper::toDTO)
                .orElse(null);
    }

    @Override
    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
        Category category = categoryMapper.toEntity(categoryDTO);
        return categoryMapper.toDTO(categoryRepository.save(category));
    }

    @Override
    public CategoryDTO updateCategory(int id, CategoryDTO categoryDTO) {
        if (categoryRepository.existsById(id)) {
            Category category = categoryMapper.toEntity(categoryDTO);
            category.setId(id);
            return categoryMapper.toDTO(categoryRepository.save(category));
        }
        return null;
    }

    @Override
    public void deleteCategory(int id) {
        categoryRepository.deleteById(id);
    }
}

