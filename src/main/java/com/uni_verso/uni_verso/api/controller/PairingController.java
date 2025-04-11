package com.uni_verso.uni_verso.api.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uni_verso.uni_verso.api.dto.request.LineSelectionRequest;
import com.uni_verso.uni_verso.api.dto.request.PairingRequest;
import com.uni_verso.uni_verso.api.dto.response.PairingResponse;
import com.uni_verso.uni_verso.api.dto.response.PairingResponseFull;
import com.uni_verso.uni_verso.api.error.InvalidRequestException;
import com.uni_verso.uni_verso.infrastructure.abstract_service.IPairingService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/pairing")
public class PairingController {

    private final IPairingService pairingService;
    

    @PostMapping("/create")
    public  ResponseEntity<PairingResponse> create(@Valid @RequestBody PairingRequest request) {
        PairingResponse pairingResponse = this.pairingService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(pairingResponse);
    }

    @PatchMapping("/{pairingCode}/pair")
    public ResponseEntity<PairingResponse> pairUser(@PathVariable String pairingCode, @RequestBody Map<String, String> request) {
        String userId = request.get("userId");
        if (userId == null || userId.isEmpty()) {
            throw new InvalidRequestException("User ID is required");
        }
        pairingService.cleanExpiredPairings();
        PairingResponse pairingResponse = this.pairingService.udpate(pairingCode, userId);
        return ResponseEntity.ok(pairingResponse);
    }

    @PatchMapping("/{pairingCode}/lines")
    public ResponseEntity<PairingResponse> updateLineSelections(
            @PathVariable String pairingCode,
            @Valid @RequestBody LineSelectionRequest request) {
        
        if (!pairingCode.equals(request.getPairingCode())) {
            throw new InvalidRequestException("Pairing code mismatch");
        }
        
        PairingResponse response = pairingService.updateLineSelections(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<PairingResponse>> getPairingsByUserId(@PathVariable String userId) {
        List<PairingResponse> pairingResponse = this.pairingService.getPairingsByUserId(userId);
        return ResponseEntity.ok(pairingResponse);
    }

    @GetMapping("refresh/{pairingCode}")
    public ResponseEntity<PairingResponseFull> getPairingByCode(@PathVariable String pairingCode) {
        PairingResponseFull pairingResponse = this.pairingService.getFullPairingByCode(pairingCode);
        return ResponseEntity.ok(pairingResponse);
    }

    
}
