package com.example.libraryviewerbackend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="users")
@Data
public class User implements Serializable {
    @Id
    @Column(name="user_id")
    private Integer id;
    private String username;
    private String password;
    private String email;
}
