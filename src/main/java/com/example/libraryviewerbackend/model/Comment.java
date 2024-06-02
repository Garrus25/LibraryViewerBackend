package com.example.libraryviewerbackend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "comments")
@Data
public class Comment implements Serializable {
    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer commentId;

    private String body;
    private String username;

    @Column(name="user_id")
    private String userId;

    @Column(name="parent_id")
    private Integer parentId;

    @Column(name="created_at")
    private LocalDate createdAt;
}
