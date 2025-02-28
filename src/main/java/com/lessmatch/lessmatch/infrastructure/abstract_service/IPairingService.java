package com.lessmatch.lessmatch.infrastructure.abstract_service;

import com.lessmatch.lessmatch.api.dto.request.LineSelectionRequest;
import com.lessmatch.lessmatch.api.dto.request.PairingRequest;
import com.lessmatch.lessmatch.api.dto.response.PairingResponse;
import com.lessmatch.lessmatch.infrastructure.abstract_service.generic.CreateService;
import com.lessmatch.lessmatch.infrastructure.abstract_service.generic.DeleteService;
import com.lessmatch.lessmatch.infrastructure.abstract_service.generic.ReadService;

public interface IPairingService extends
    CreateService<PairingRequest, PairingResponse>,
    ReadService<PairingResponse, Long>,
    DeleteService<Long>{
        PairingResponse udpate(String pairingCode, String pairedUserId);
        PairingResponse updateLineSelections(LineSelectionRequest request);
}
