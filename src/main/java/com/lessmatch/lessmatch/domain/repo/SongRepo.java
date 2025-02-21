package com.lessmatch.lessmatch.domain.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lessmatch.lessmatch.domain.entity.Song;

public interface SongRepo extends JpaRepository<Song,Long>{
    
}
