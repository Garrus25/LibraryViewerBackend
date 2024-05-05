package com.example.libraryviewerbackend.modelmapper;

import com.example.libraryviewerbackend.model.Rate;
import com.openapi.gen.springboot.dto.RateDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RateModelMapper {
    RateModelMapper INSTANCE = Mappers.getMapper(RateModelMapper.class);

    @Mapping(source = "rate.rateIdentity.userId", target = "userId")
    @Mapping(source = "rate.rateIdentity.ratedObjectId", target = "ratedObjectId")
    @Mapping(source = "rate.rateIdentity.rateType", target = "rateType")
    RateDTO toDTO(Rate rate);

    @Mapping(source = "ratedObjectId", target = "rateIdentity.ratedObjectId")
    @Mapping(source = "userId", target = "rateIdentity.userId")
    @Mapping(source = "rateType", target = "rateIdentity.rateType")
    @Mapping(source = "rateValue", target = "rateValue")
    Rate toEntity(RateDTO rateDTO);
}