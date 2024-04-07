package com.balkis.delivery.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "messages")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String subject;
    private String content;
    private String last_name;
    private String email;


    @ManyToOne
    @JoinColumn(name = "sender_id")
    private User sender;

    // Constructors, other fields, and relationships

    public Message(String subject, String content,String last_name,String email)
    {
        this.subject = subject;
        this.content = content;
        this.last_name = last_name;
        this.email = email;
    }
}
