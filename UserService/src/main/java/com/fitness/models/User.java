package com.fitness.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;


    @Column(unique = true)
    private String email;

    @Column(nullable = false)
    private String password;
    private String firstName;
    private String lastName;


    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private UserRole user = UserRole.USER;


    @CreationTimestamp
    LocalDateTime createdAt;


    @UpdateTimestamp
    LocalDateTime updateAt;



}
