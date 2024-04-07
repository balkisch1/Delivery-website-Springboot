package com.balkis.delivery.controllers;
import com.balkis.delivery.models.SubCategory;
import com.balkis.delivery.services.SubCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/subcategory")
public class SubCategoryController {

    private final SubCategoryService subcategoryService;

    @Autowired
    public SubCategoryController(SubCategoryService subcategoryService) {
        this.subcategoryService = subcategoryService;

    }

    @GetMapping
    public List<SubCategory> getAllSubCategory() {
        return subcategoryService.getAllSubCategory();
    }

    @GetMapping("/{id}")
    public Optional<SubCategory> getUserById(@PathVariable Long id) {
        return subcategoryService.getSubCategoryById(id);
    }

    @PostMapping
    public SubCategory saveUser(@RequestBody SubCategory subcategory) {
        return subcategoryService.saveSubCategory(subcategory);
    }

    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable Long id) {
        subcategoryService.deleteSubCategory(id);
    }
}
