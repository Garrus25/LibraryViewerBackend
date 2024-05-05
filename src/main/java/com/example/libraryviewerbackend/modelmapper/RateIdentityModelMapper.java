package com.example.libraryviewerbackend.modelmapper;

import com.example.libraryviewerbackend.model.RateIdentity;
import com.openapi.gen.springboot.dto.RateIdentityDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RateIdentityModelMapper {
    RateIdentityModelMapper INSTANCE = Mappers.getMapper(RateIdentityModelMapper.class);

    RateIdentityDTO toDTO(RateIdentity rateIdentity);

    RateIdentity toEntity(RateIdentityDTO rateIdentityDTO);
}