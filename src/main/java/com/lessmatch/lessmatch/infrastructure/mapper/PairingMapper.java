package com.lessmatch.lessmatch.infrastructure.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.lessmatch.lessmatch.api.dto.request.PairingRequest;
import com.lessmatch.lessmatch.api.dto.response.PairingResponse;
import com.lessmatch.lessmatch.domain.entity.Pairing;

@Mapper(componentModel = "spring")
public interface PairingMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "creatorUser", ignore = true)
    @Mapping(target = "creatorLines", ignore = true)
    @Mapping(target = "pairedUser", ignore = true)
    @Mapping(target = "pairedLines", ignore = true)
    @Mapping(target = "song", ignore = true)
    @Mapping(target = "pairingCode", ignore=true)
    @Mapping(target = "createdAt", ignore = true)
    Pairing toEntity(PairingRequest pairingRequest);
    
    PairingResponse toResponse(Pairing pairing);

    // void toUpdate(PairingRequest pairing, @MappingTarget Pairing target);
}
