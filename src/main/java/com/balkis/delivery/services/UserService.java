package com.balkis.delivery.services;

import com.balkis.delivery.models.Client;
import com.balkis.delivery.models.Driver;
import com.balkis.delivery.models.Provider;
import com.balkis.delivery.models.User;
import com.balkis.delivery.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
;
@Service
public class UserService {
        private final UserRepository userRepository;

        public UserService(UserRepository userRepository) {
            this.userRepository = userRepository;
        }

    public List<User> getAllUsers() {
        return (List<User>) userRepository.findAll();
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
    public void registerUser(User user) {
            String hashedPassword = new BCryptPasswordEncoder().encode(user.getPassword());
            user.setPassword(hashedPassword);

        userRepository.save(user);
    }


    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }


    }






