package com.lessmatch.lessmatch.infrastructure.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.lessmatch.lessmatch.api.dto.SongBasicInfo;
import com.lessmatch.lessmatch.api.dto.request.SongRequest;
import com.lessmatch.lessmatch.domain.entity.Song;

@Mapper(componentModel = "spring")
public interface SongMapper {
    @Mapping(target = "pairings", ignore = true)
    Song toEntity(SongRequest songRequest);

    @Mapping(target = "lyricsApiUrl", expression = "java(song.getLyricsApiUrl())")
    SongBasicInfo toResponse(Song song);

    @Mapping(target = "pairings", ignore = true)
    void toUpdate(SongRequest song, @MappingTarget Song target);
}
