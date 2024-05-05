package com.example.libraryviewerbackend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="rate")
@Data
public class Rate implements Serializable {

    @EmbeddedId
    private RateIdentity rateIdentity;

    @Column(name = "rate_value")
    private Integer rateValue;
}
