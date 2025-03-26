package com.lessmatch.lessmatch.domain.entity;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "songs")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Song {
    @Id
    @Column(updatable = false, nullable = false, unique = true)
    private Long id;
    
    @Column(nullable = false)
    private String title;
    
    @Column(nullable = false)
    private String artist;
    
    @Column(nullable = false)
    private String albumImage;
    
    @Column(nullable = false)
    private int verseCount;
    
    @OneToMany(mappedBy = "song", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Pairing> pairings = new HashSet<>();
    
    public String getLyricsApiUrl() {
        String encodedArtist = URLEncoder.encode(this.artist, StandardCharsets.UTF_8);
        String encodedTitle = URLEncoder.encode(this.title, StandardCharsets.UTF_8);
        
        // replace "+" by "%20"
        encodedArtist = encodedArtist.replace("+", "%20");
        encodedTitle = encodedTitle.replace("+", "%20");
        
        return "https://api.lyrics.ovh/v1/" + encodedArtist + "/" + encodedTitle;
    }
}
