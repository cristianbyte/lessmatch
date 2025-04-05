package com.uni_verso.uni_verso.infrastructure.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uni_verso.uni_verso.api.dto.PairingScore;
import com.uni_verso.uni_verso.api.dto.request.LineSelectionRequest;
import com.uni_verso.uni_verso.api.dto.request.PairingRequest;
import com.uni_verso.uni_verso.api.dto.response.PairingResponse;
import com.uni_verso.uni_verso.api.dto.response.PairingResponseFull;
import com.uni_verso.uni_verso.api.error.CodeExpiredException;
import com.uni_verso.uni_verso.api.error.IdNotFoundException;
import com.uni_verso.uni_verso.api.error.InvalidOperationException;
import com.uni_verso.uni_verso.api.error.InvalidRequestException;
import com.uni_verso.uni_verso.domain.entity.Pairing;
import com.uni_verso.uni_verso.domain.entity.Song;
import com.uni_verso.uni_verso.domain.repo.PairingRepo;
import com.uni_verso.uni_verso.infrastructure.abstract_service.IPairingService;
import com.uni_verso.uni_verso.infrastructure.mapper.PairingMapper;
import com.uni_verso.uni_verso.util.PairingScoreCalculator;

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
    @Transactional
    public PairingResponse udpate(String pairingCode, String pairedUserId) {
        if (!pairingCode.matches("[A-Z0-9]{6}")) {
            throw new InvalidRequestException("Invalid pairing code format");
        }
             
        Pairing pairing = pairingRepository.findByPairingCode(pairingCode)
            .orElseThrow(() -> new CodeExpiredException("Pairing code not found or expired"));
        
        if (pairing.getPairedUser() != null) {
            throw new InvalidOperationException("This pairing code has already been used");
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

    @Override
    public void cleanExpiredPairings() {
        LocalDateTime now = LocalDateTime.now();
        int affectedRows = pairingRepository.deleteExpiredPairings(now.minusHours(24), now.minusHours(72));
        System.out.println("Deleted " + affectedRows + " expired pairings.");
    }

    public Pairing find(Long id) {
        return pairingRepository.findById(id).orElseThrow(() -> new IdNotFoundException("Pairing not found with id: " + id));
    }

    public Pairing findByPairingCode(String pairingCode) {
        return pairingRepository.findByPairingCode(pairingCode)
            .orElseThrow(() -> new InvalidOperationException("Pairing not found with pairing code: " + pairingCode));
    }

}
