package com.example.libraryviewerbackend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="users")
@Data
public class User implements Serializable {
    @Id
    private String id;
    private String username;
    private String password;
    private String email;
}
