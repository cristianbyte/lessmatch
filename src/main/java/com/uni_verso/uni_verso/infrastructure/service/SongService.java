package com.uni_verso.uni_verso.infrastructure.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uni_verso.uni_verso.api.dto.SongBasicInfo;
import com.uni_verso.uni_verso.api.dto.request.SongRequest;
import com.uni_verso.uni_verso.api.error.IdNotFoundException;
import com.uni_verso.uni_verso.api.error.InvalidRequestException;
import com.uni_verso.uni_verso.domain.entity.Song;
import com.uni_verso.uni_verso.domain.repo.SongRepo;
import com.uni_verso.uni_verso.infrastructure.abstract_service.ISongService;
import com.uni_verso.uni_verso.infrastructure.mapper.SongMapper;

import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class SongService implements ISongService {

    private final SongRepo songRepository;
    private final SongMapper songMapper;

    public Song getOrCreateSong(SongRequest songRequest) {
        return songRepository.findByTitleAndArtist(songRequest.getTitle(), songRequest.getArtist())
            .orElseGet(() -> {
                Song newSong = songMapper.toEntity(songRequest);
                return songRepository.save(newSong);
            });
    }

    @Override
    public SongBasicInfo findByCode(String code) {

        Song song = songRepository.findByPairingsPairingCode(code);
        if (song == null) {
            throw new InvalidRequestException("No song found for pairing code: " + code);
        }
        return songMapper.toResponse(song);
    }

    @Override
    public SongBasicInfo create(SongRequest request) {
        Song song = songMapper.toEntity(request);
        return songMapper.toResponse(songRepository.save(song));
    }

    @Override
    public SongBasicInfo getById(Long id) {
        return songMapper.toResponse(this.find(id));
    }

    @Override
    public SongBasicInfo update(Long id, SongRequest request) {
        Song song = find(id);
        songMapper.toUpdate(request, song);
        return songMapper.toResponse(songRepository.save(song));
    }

    @Override
    public void delete(Long id) {
        songRepository.delete(this.find(id));
    }

    public Song find(Long id) {
        return songRepository.findById(id).orElseThrow(() -> new IdNotFoundException("Song not found with id: " + id));
    }

    @Override
    public List<SongBasicInfo> getMostPopularSongs() {
        return songRepository.findMostPopularSongs().stream()
        .map(songMapper::toResponse)
        .collect(Collectors.toList());
    }


    
}
