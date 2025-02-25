package com.lessmatch.lessmatch.infrastructure.abstract_service;

import com.lessmatch.lessmatch.api.dto.SongBasicInfo;
import com.lessmatch.lessmatch.api.dto.request.SongRequest;

public interface ISongService extends CrudService<SongRequest, SongBasicInfo, Long> {
    
}
