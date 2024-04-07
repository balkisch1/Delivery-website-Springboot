package com.balkis.delivery.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)//  Les attributs hérités sont répétés dans chaque table.
//@DiscriminatorColumn(name = "usertype")//
@Getter
@Setter
@AllArgsConstructor

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    private String lastName;
    private String firstName;
    private String phone;
    private String email;
    private String username;
    private String password;
    private String token;
    @OneToMany(mappedBy = "sender")
    private List<Message> sentMessages = new ArrayList<>();



    // Default constructor (required by JPA)
    public User() {
    }

    // Parameterized constructor
    public User(String lastName, String firstName, String phone, String email, String username, String password ,String role)
     {
        this.lastName = lastName;
        this.firstName = firstName;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.username = username;
    }

    public User(String firstName, String lastName, String phone, String email, String password, String username) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.username = username;
    }


    public void hashPassword(){
        this.password=new BCryptPasswordEncoder().encode(this.password);
    }
    // Getters and setters for private fields (not shown in your provided code)
    public boolean checkPassword(String rawPassword) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.matches(rawPassword, this.password );
    }

}
