package com.example.libraryviewerbackend.service;

import com.openapi.gen.springboot.dto.RateDTO;
import com.openapi.gen.springboot.dto.RateIdentityDTO;

public interface IRateService {
    RateDTO addRate(RateDTO rateDTO);

    RateDTO findRateById(RateIdentityDTO rateIdentity);

    Float getAverageRateForObject(String ratedObjectId, String rateType);


}
