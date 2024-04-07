package com.balkis.delivery.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;



@Getter
@Setter
@AllArgsConstructor
@Entity
public class Provider extends User{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String company;
    public Provider() {
        // Constructeur par défaut
    }

    @OneToMany(mappedBy = "provider", cascade = CascadeType.ALL)
    private List<Product> products;


    // Dans la classe Provider
    public Provider(String firstName, String lastName, String phone, String email, String password, String username, String company,String role) {
        super(firstName, lastName, phone, email, password, username,role);
        this.company = company;

    }

    public Provider(String lastName, String firstName, String phone, String email, String username, String password, String company) {

        this.setLastName(lastName);
        this.setFirstName(firstName);
        this.setEmail(email);
        this.setPhone(phone);
        this.setPassword(password);
        this.setUsername(username);
        this.company = company;

    }

    public boolean checkPassword(String rawPassword) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.matches(rawPassword, this.getPassword());
    }

}
