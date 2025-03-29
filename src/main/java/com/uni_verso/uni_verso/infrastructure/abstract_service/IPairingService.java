package com.uni_verso.uni_verso.infrastructure.abstract_service;

import java.util.List;

import com.uni_verso.uni_verso.api.dto.request.LineSelectionRequest;
import com.uni_verso.uni_verso.api.dto.request.PairingRequest;
import com.uni_verso.uni_verso.api.dto.response.PairingResponse;
import com.uni_verso.uni_verso.api.dto.response.PairingResponseFull;
import com.uni_verso.uni_verso.infrastructure.abstract_service.generic.CreateService;
import com.uni_verso.uni_verso.infrastructure.abstract_service.generic.DeleteService;
import com.uni_verso.uni_verso.infrastructure.abstract_service.generic.ReadService;

public interface IPairingService extends
    CreateService<PairingRequest, PairingResponse>,
    ReadService<PairingResponse, Long>,
    DeleteService<Long>{
        PairingResponse udpate(String pairingCode, String pairedUserId);
        PairingResponse updateLineSelections(LineSelectionRequest request);
        List<PairingResponse> getPairingsByUserId(String userId);
        PairingResponseFull getFullPairingByCode(String pairingCode);
}
