package com.balkis.delivery.models;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@Entity// besh yajouti table f bd
//@DiscriminatorValue("client")
public class Client extends User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//id unique
    private Long id;
    @Setter
    private String localisation;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private List<Ordre> orders;
    public String getLocalisation() {
        return localisation;
    }

    // Default constructor (required for JPA)
    public Client() {
    }

    // Parameterized constructor



    // Dans la classe Client
    public Client(String firstName, String lastName, String phone, String email, String password, String username, String localisation ,String role) {
        super(firstName, lastName, phone, email, password, username,role);
        this.localisation = localisation;

    }


    public Client(String lastName, String firstName, String phone, String email, String username, String password, String localisation) {
        this.setLastName(lastName);
        this.setFirstName(firstName);
        this.setEmail(email);
        this.setPhone(phone);
        this.setPassword(password);
        this.setUsername(username);
        this.localisation = localisation;

    }

    public boolean checkPassword(String rawPassword) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.matches(rawPassword, this.getPassword());
    }





}
