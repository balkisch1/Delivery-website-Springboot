package com.balkis.delivery.models;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class Driver  extends User{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Setter
    private String adresse;


    @OneToMany(mappedBy = "driver", cascade = CascadeType.ALL)
    private List<Ordre> orders;

    public String getAdresse() {
        return adresse;
    }

    public Driver() {
        // Constructeur par défaut
    }
    public Driver(String firstName, String lastName, String phone, String email, String password, String username, String adresse ,String role) {
        super(firstName, lastName, phone, email, password, username);
        this.adresse = adresse;

    }


    public Driver(String lastName, String firstName, String phone, String email, String username, String password, String adresse) {
        this.setLastName(lastName);
        this.setFirstName(firstName);
        this.setEmail(email);
        this.setPhone(phone);
        this.setPassword(password);
        this.setUsername(username);
        this.adresse = adresse;

    }


    public boolean checkPassword(String rawPassword) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.matches(rawPassword, this.getPassword());
    }

    }





