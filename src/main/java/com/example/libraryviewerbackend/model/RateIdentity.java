package com.example.libraryviewerbackend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class RateIdentity implements Serializable {
    private Integer ratedObjectId;
    private Integer userId;
    private String rateType;
}