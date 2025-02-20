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
    private String name;
    private String albumImage;
    private int verseCount;
}
