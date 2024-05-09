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
@Table(name="books")
@Data
public class Book implements Serializable {
    @Id
    private String isbn;

    @Column(name="author_id")
    private Integer authorId;

    private String title;
    private String description;

    @Column(name="cover_name")
    private String coverName;

    @Column(name="publish_date")
    private LocalDate publishDate;

    @Column(name="addition_date")
    private LocalDate additionDate;

    @Column(name="average_rate")
    private Float averageRating;

}
