package com.balkis.delivery.services;

import com.balkis.delivery.models.Userproduct;
import com.balkis.delivery.repository.UserproductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserproductService {
    private final UserproductRepository userproductrepository;

    @Autowired
    public UserproductService(UserproductRepository userproductrepository) {
        this.userproductrepository = userproductrepository;
    }

    public List<Userproduct> getAllUserProducts() {
        return (List<Userproduct>) userproductrepository.findAll();
    }

    public Optional<Userproduct> getUserProductById(Long id) {
        return userproductrepository.findById(id);
    }

    public Userproduct addUserProduct(Userproduct userproduct) {
        return userproductrepository.save(userproduct);
    }

    public Userproduct saveUserProduct(Userproduct userproduct) {
        return userproductrepository.save(userproduct);
    }

    public void deleteUserProduct(Long productId) {
        // Use the repository to find and delete the user product by ID
        userproductrepository.deleteById(productId);
    }

}
