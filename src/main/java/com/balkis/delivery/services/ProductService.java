package com.balkis.delivery.services;
import com.balkis.delivery.models.Product;
import com.balkis.delivery.repository.ProductRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
@Service
public class ProductService{


    private final ProductRepository productrepository;

    public ProductService(ProductRepository productRepository) {
        this.productrepository = productRepository;
    }

    public List<Product> getAllProducts() {
        return (List<Product>) productrepository.findAll();
    }

    public Optional<Product> getProductById(Long id) {
        return productrepository.findById(id);
    }

    public Product saveProduct(Product product) {
        return productrepository.save(product);
    }
    public Product addProduct(Product product) {
        // Add logic to save the product to the database
        return productrepository.save(product);
    }


    public void deleteProduct(Long id) {
        productrepository.deleteById(id);
    }




}
