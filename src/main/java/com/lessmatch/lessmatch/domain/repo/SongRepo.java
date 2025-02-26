package com.lessmatch.lessmatch.domain.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.lessmatch.lessmatch.domain.entity.Song;

public interface SongRepo extends JpaRepository<Song,Long>{
    @Query("SELECT s FROM songs s LEFT JOIN s.pairings p GROUP BY s.id ORDER BY COUNT(p) DESC LIMIT 5")
    List<Song> findMostPopularSongs();

    Optional<Song> findByTitleAndArtist(String title, String artist);
}
