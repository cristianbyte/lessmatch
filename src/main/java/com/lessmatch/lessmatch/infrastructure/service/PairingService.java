package com.lessmatch.lessmatch.infrastructure.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lessmatch.lessmatch.api.dto.request.PairingRequest;
import com.lessmatch.lessmatch.api.dto.response.PairingResponse;
import com.lessmatch.lessmatch.domain.entity.Pairing;
import com.lessmatch.lessmatch.domain.entity.Song;
import com.lessmatch.lessmatch.domain.repo.PairingRepo;
import com.lessmatch.lessmatch.infrastructure.abstract_service.IPairingService;
import com.lessmatch.lessmatch.infrastructure.mapper.PairingMapper;

import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class PairingService implements IPairingService{

    private final PairingMapper pairingMapper;
    private final UserService userService;
    private final SongService songService;
    private final PairingRepo pairingRepository;
    
    public Pairing createPairingEntity(PairingRequest pairingRequest) {
        Pairing pairing = pairingMapper.toEntity(pairingRequest);
        
        // Establece las relaciones manualmente
        pairing.setCreatorUser(userService.find(pairingRequest.getCreatorUserId()));
        
        // Obtener o crear la canción
        Song songInfo = songService.getOrCreateSong(pairingRequest.getSong());
        pairing.setSong(songService.find(songInfo.getId()));
        
        return pairing;
    }
    
    @Override
    public PairingResponse create(PairingRequest pairingRequest) {
        Pairing pairing = createPairingEntity(pairingRequest);
        return pairingMapper.toResponse(pairingRepository.save(pairing));
    }

    @Override
    public PairingResponse getById(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getById'");
    }

    @Override
    public PairingResponse update(Long id, PairingRequest request) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public void delete(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }
    
}
