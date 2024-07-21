package com.example.bookshop.controller;//package com.example.bookshop.controller;//package com.example.bookshop.controller;
////
////import com.example.bookshop.dto.CategoryDTO;
////import com.example.bookshop.service.CategoryService;
////import org.springframework.beans.factory.annotation.Autowired;
////import org.springframework.http.HttpStatus;
////import org.springframework.http.ResponseEntity;
////import org.springframework.web.bind.annotation.RequestMapping;
////import org.springframework.web.bind.annotation.RestController;
////
////import java.util.List;
////
////@RestController
////public class CategoryController {
////    @Autowired
////    private CategoryService categoryService;
////    @RequestMapping("/api/categories")
////    public List<CategoryDTO> getAllCategories(){
////        List<CategoryDTO> categories = categoryService.getAllCategories();
////        return new ResponseEntity<>(categories, HttpStatus.OK).getBody();
////    }
////
////}
//import com.example.bookshop.dto.CategoryDTO;
//import com.example.bookshop.entity.Category;
//import com.example.bookshop.service.CategoryService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/categories")
//public class CategoryController {
//
//    @Autowired
//    private CategoryService categoryService;
//
//    @GetMapping("/get-all")
//    public ResponseEntity<List<CategoryDTO>> getAllCategories() {
//        List<CategoryDTO> categories = categoryService.getAllCategories();
//        return new ResponseEntity<>(categories, HttpStatus.OK);
//    }
//
////    @GetMapping("/getall")
////    public ResponseEntity<List<Category>> getall(){
////        List<Category> categories = categoryService.getall();
////        return new ResponseEntity<>(categories, HttpStatus.OK);
////    }
//    @GetMapping("/{id}")
//    public ResponseEntity<CategoryDTO> getCategoryById(@PathVariable Integer id) {
//        CategoryDTO category = categoryService.getCategoryById(id);
//        return new ResponseEntity<>(category, HttpStatus.OK);
//    }
//
//    @PostMapping("/create")
//    public ResponseEntity<CategoryDTO> createCategory(@RequestBody CategoryDTO categoryDTO) {
//        CategoryDTO createdCategory = categoryService.createCategory(categoryDTO);
//        return new ResponseEntity<>(createdCategory, HttpStatus.CREATED);
//    }
//
//    @PutMapping("/update/{id}")
//    public ResponseEntity<CategoryDTO> updateCategory(@PathVariable Integer id, @RequestBody CategoryDTO categoryDTO) {
//        CategoryDTO updatedCategory = categoryService.updateCategory(id, categoryDTO);
//        return new ResponseEntity<>(updatedCategory, HttpStatus.OK);
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteCategory(@PathVariable Integer id) {
//        categoryService.deleteCategory(id);
//        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//    }
////    @GetMapping("/getall")
////    public List<Category> getall(){
////        return categoryService.getall();
////    }
////    @GetMapping("/{id}")
////    public ResponseEntity<CategoryDTO> getCategoryById(@PathVariable Long id) {
////        CategoryDTO category = categoryService.getCategoryById(id);
////        return new ResponseEntity<>(category, HttpStatus.OK);
////    }
////
////    @PostMapping
////    public ResponseEntity<CategoryDTO> createCategory(@RequestBody CategoryDTO categoryDTO) {
////        CategoryDTO createdCategory = categoryService.createCategory(categoryDTO);
////        return new ResponseEntity<>(createdCategory, HttpStatus.CREATED);
////    }
////
////    @PutMapping("/{id}")
////    public ResponseEntity<CategoryDTO> updateCategory(@PathVariable Long id, @RequestBody CategoryDTO categoryDTO) {
////        CategoryDTO updatedCategory = categoryService.updateCategory(id, categoryDTO);
////        return new ResponseEntity<>(updatedCategory, HttpStatus.OK);
////    }
////
////    @DeleteMapping("/{id}")
////    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
////        categoryService.deleteCategory(id);
////        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
////    }
//}

import com.example.bookshop.dto.CategoryDTO;
import com.example.bookshop.payload.ResponseData;
import com.example.bookshop.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

//    @GetMapping("/getall")
//    public ResponseEntity<List<CategoryDTO>> getAlalCategories() {
//        List<CategoryDTO> categories = categoryService.getAllCategories();
//        return new ResponseEntity<>(categories, HttpStatus.OK);
//    }
    @GetMapping("/get-all")
    public ResponseEntity<ResponseData> getAllCategories() {
        List<CategoryDTO> categories = categoryService.getAllCategories();
        return ResponseEntity.ok(new ResponseData(200, "Success", categories, true));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseData> getCategoryById(@PathVariable int id) {
        CategoryDTO category = categoryService.getCategoryById(id);
        if (category != null) {
            return ResponseEntity.ok(new ResponseData(200, "Success", category, true));
        } else {
            return ResponseEntity.status(404).body(new ResponseData(404, "Category not found", null, false));
        }
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseData> createCategory(@RequestBody CategoryDTO categoryDTO) {
        CategoryDTO createdCategory = categoryService.createCategory(categoryDTO);
        return ResponseEntity.status(201).body(new ResponseData(201, "Category created", createdCategory, true));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ResponseData> updateCategory(@PathVariable int id, @RequestBody CategoryDTO categoryDTO) {
        CategoryDTO updatedCategory = categoryService.updateCategory(id, categoryDTO);
        if (updatedCategory != null) {
            return ResponseEntity.ok(new ResponseData(200, "Category updated", updatedCategory, true));
        } else {
            return ResponseEntity.status(404).body(new ResponseData(404, "Category not found", null, false));
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseData> deleteCategory(@PathVariable int id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.ok(new ResponseData(200, "Category deleted", null, true));
    }
}
