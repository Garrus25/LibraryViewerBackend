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
@Table(name = "authors")
@Data
public class Author implements Serializable {
    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="author_id")
    private Integer authorId;
    private String name;
    private String surname;
    private String description;
    private String pictureName;
    @Column(name="addition_date")
    private LocalDate additionDate;
    @Column(name="average_rate")
    private Float averageRating;
    @Column(name="created_by")
    private String createdBy;
}
