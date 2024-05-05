package com.example.libraryviewerbackend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
    @Id
    @Column(name="author_id")
    private Integer authorId;
    private String name;
    private String surname;
    private String description;
    private String pictureName;
    @Column(name="addition_date")
    private LocalDate additionDate;
}
