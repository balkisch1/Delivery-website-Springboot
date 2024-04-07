package com.balkis.delivery.services;
import com.balkis.delivery.models.SubCategory;
import com.balkis.delivery.repository.SubCategoryRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
@Service
public class SubCategoryService{

    private final SubCategoryRepository SubCategoryrepository;

    public SubCategoryService(SubCategoryRepository SubCategoryrepository) {
        this.SubCategoryrepository = SubCategoryrepository;
    }

    public List<SubCategory> getAllSubCategory() {
        return (List<SubCategory>) SubCategoryrepository.findAll();
    }

    public Optional<SubCategory> getSubCategoryById(Long id) {
        return SubCategoryrepository.findById(id);
    }

    public SubCategory saveSubCategory(SubCategory client) {
        return SubCategoryrepository.save(client);
    }

    public void deleteSubCategory(Long id) {
        SubCategoryrepository.deleteById(id);
    }




}
