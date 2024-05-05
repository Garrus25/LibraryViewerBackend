package com.example.libraryviewerbackend.controller;

import com.example.libraryviewerbackend.service.RateService;
import com.openapi.gen.springboot.api.RateApiController;
import com.openapi.gen.springboot.dto.RateDTO;
import com.openapi.gen.springboot.dto.RateIdentityDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class RateController extends RateApiController {

    RateService rateService;

    public RateController(RateService rateService) {
        super(null);
        this.rateService = rateService;
    }

    @Override
    public ResponseEntity<RateDTO> addRate(RateDTO rateDTO) {
        return ResponseEntity.ok(rateService.addRate(rateDTO));
    }

    @Override
    public ResponseEntity<RateDTO> getRateById(RateIdentityDTO rateIdentityDTO) {
        return ResponseEntity.ok(rateService.findRateById(rateIdentityDTO));
    }

    @Override
    public ResponseEntity<Float> getAverageRateForObject(Integer ratedObjectId, String rateType) {
        return ResponseEntity.ok(rateService.getAverageRateForObject(ratedObjectId, rateType));
    }
}
