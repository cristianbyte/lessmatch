package com.lessmatch.lessmatch.infrastructure.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lessmatch.lessmatch.api.dto.PairingScore;
import com.lessmatch.lessmatch.api.dto.request.LineSelectionRequest;
import com.lessmatch.lessmatch.api.dto.request.PairingRequest;
import com.lessmatch.lessmatch.api.dto.response.PairingResponse;
import com.lessmatch.lessmatch.api.dto.response.PairingResponseFull;
import com.lessmatch.lessmatch.api.error.IdNotFoundException;
import com.lessmatch.lessmatch.api.error.InvalidOperationException;
import com.lessmatch.lessmatch.api.error.InvalidRequestException;
import com.lessmatch.lessmatch.domain.entity.Pairing;
import com.lessmatch.lessmatch.domain.entity.Song;
import com.lessmatch.lessmatch.domain.repo.PairingRepo;
import com.lessmatch.lessmatch.infrastructure.abstract_service.IPairingService;
import com.lessmatch.lessmatch.infrastructure.mapper.PairingMapper;
import com.lessmatch.lessmatch.util.PairingScoreCalculator;

import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class PairingService implements IPairingService{

    private final PairingMapper pairingMapper;
    private final UserService userService;
    private final SongService songService;
    private final PairingRepo pairingRepository;
    
    
    @Override
    public PairingResponse create(PairingRequest pairingRequest) {
        Pairing pairing = pairingMapper.toEntity(pairingRequest);
        
        // Set the relationships manually
        pairing.setCreatorUser(userService.find(pairingRequest.getCreatorUserId()));
        
        // Get or create song
        Song songInfo = songService.getOrCreateSong(pairingRequest.getSong());
        pairing.setSong(songService.find(songInfo.getId()));
        
        return pairingMapper.toResponse(pairingRepository.save(pairing));
    }
    
    @Override
    public PairingResponse getById(Long id) {
        return pairingMapper.toResponse(this.find(id));
    }

    @Override
    public PairingResponse udpate(String pairingCode, String pairedUserId) {
        
        if (!pairingCode.matches("[A-Z0-9]{6}")) {
            throw new InvalidRequestException("Invalid pairing code format");
        }
        Pairing pairing = findByPairingCode(pairingCode);
        if (pairing.getPairedUser() != null) {
            throw new InvalidOperationException("This pairing code has already been used");
        }else if (pairing.isExpired()) {
            throw new InvalidOperationException("This pairing code has expired");
        }

        pairing.setPairedUser(userService.find(pairedUserId));
        return pairingMapper.toResponse(pairingRepository.save(pairing));
    }

    @Override
    public PairingResponseFull getFullPairingByCode(String pairingCode) {
        Pairing pairing = this.findByPairingCode(pairingCode);
        PairingResponseFull response = pairingMapper.toFullResponse(pairing);

        PairingScore pairingScore = new PairingScore();
        
        pairingScore.setSelectedVerses(PairingScoreCalculator.calculateSelectedVerses(pairing.getCreatorLines(), pairing.getPairedLines()));
        pairingScore.setMatchedVerses(PairingScoreCalculator.calculateMatchedVerses(pairing.getCreatorLines(), pairing.getPairedLines()));
        pairingScore.setMatchPercentage(pairingScore.getSelectedVerses() > 0 ? (double) pairingScore.getMatchedVerses() / pairingScore.getSelectedVerses() : 0);
        pairingScore.setConnectionScore(pairingScore.getMatchPercentage() * 100);
    
        response.setPairingScore(pairingScore);

        return response;
    }
        
    @Override
    public List<PairingResponse> getPairingsByUserId(String userId) {
        return pairingMapper.toResponseList(pairingRepository.findByCreatorUserIdOrPairedUserId(userId, userId));
    }

    @Override
    public PairingResponse updateLineSelections(LineSelectionRequest request) {
        Pairing pairing = this.findByPairingCode(request.getPairingCode());
        
        if (pairing.getCreatorUser().getId().equals(request.getUserId())){
            pairing.setCreatorLines(request.getSelectedLines());
            
        }else if( pairing.getPairedUser() != null &&
        pairing.getPairedUser().getId().equals(request.getUserId())){
            pairing.setPairedLines(request.getSelectedLines());
            
        }else{
            throw new InvalidOperationException("User is not authorized to update this pairing");
        }
        
        return pairingMapper.toResponse(pairingRepository.save(pairing));
    }

    @Override
    public void delete(Long id) {
        // only can delete the creator of the pairing
        pairingRepository.delete(this.find(id));
    }

    public Pairing find(Long id) {
        return pairingRepository.findById(id).orElseThrow(() -> new IdNotFoundException("Pairing not found with id: " + id));
    }

    public Pairing findByPairingCode(String pairingCode) {
        return pairingRepository.findByPairingCode(pairingCode)
            .orElseThrow(() -> new InvalidOperationException("Pairing not found with pairing code: " + pairingCode));
    }

}
