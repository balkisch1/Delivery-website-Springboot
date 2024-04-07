package com.balkis.delivery.services;
import com.balkis.delivery.models.Category;
import com.balkis.delivery.repository.CategoryRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
@Service
public class CategoryService{

    private final CategoryRepository categoryrepository;

    public CategoryService(CategoryRepository categoryrepository) {
        this.categoryrepository = categoryrepository;
    }

    public List<Category> getAllCategory() {
        return (List<Category>) categoryrepository.findAll();
    }
    public Optional<Category> getCategoryById(Long id) {
        return categoryrepository.findById(id);
    }

    public Category saveCategory(Category client) {
        return categoryrepository.save(client);
    }

    public void deleteCategory(Long id) {
        categoryrepository.deleteById(id);
    }




}
