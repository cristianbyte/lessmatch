package com.uni_verso.uni_verso.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SongBasicInfo {

    private Long id;
    private String title;
    private String artist;
    private String preview;
    private String albumImage;
    private int verseCount;
    private String lyricsApiUrl;
    
}
