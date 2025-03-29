package com.uni_verso.uni_verso.infrastructure.abstract_service;

import java.util.List;

import com.uni_verso.uni_verso.api.dto.SongBasicInfo;
import com.uni_verso.uni_verso.api.dto.request.SongRequest;

public interface ISongService extends CrudService<SongRequest, SongBasicInfo, Long>{
    List<SongBasicInfo> getMostPopularSongs();
    SongBasicInfo findByCode(String code);
}
