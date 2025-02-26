package com.lessmatch.lessmatch.infrastructure.abstract_service;

import com.lessmatch.lessmatch.api.dto.request.PairingRequest;
import com.lessmatch.lessmatch.api.dto.response.PairingResponse;

public interface IPairingService extends CrudService<PairingRequest, PairingResponse, Long>{
}
