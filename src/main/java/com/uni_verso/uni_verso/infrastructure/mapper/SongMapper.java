package com.uni_verso.uni_verso.infrastructure.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.uni_verso.uni_verso.api.dto.SongBasicInfo;
import com.uni_verso.uni_verso.api.dto.request.SongRequest;
import com.uni_verso.uni_verso.domain.entity.Song;

@Mapper(componentModel = "spring")
public interface SongMapper {
    @Mapping(target = "pairings", ignore = true)
    Song toEntity(SongRequest songRequest);

    @Mapping(target = "lyricsApiUrl", expression = "java(song.getLyricsApiUrl())")
    SongBasicInfo toResponse(Song song);

    @Mapping(target = "pairings", ignore = true)
    void toUpdate(SongRequest song, @MappingTarget Song target);
}
