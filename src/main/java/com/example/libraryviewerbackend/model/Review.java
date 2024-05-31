package com.example.libraryviewerbackend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "reviews")
@Entity
public class Review implements Serializable {
    @Id
    @Column(name = "id", length = 64)
    private String reviewId;

    @Column(length = 64)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;

    @Column(name = "created_by", length = 32)
    private String createdBy;

    @Column(name = "book_id", length = 64)
    private String bookId;
}
