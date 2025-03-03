package com.lessmatch.lessmatch.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SongBasicInfo {

    private String title;
    private String artist;
    private String albumImage;
    private int verseCount;
    private String lyricsApiUrl;
    
}
