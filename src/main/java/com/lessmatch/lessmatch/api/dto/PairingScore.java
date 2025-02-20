package com.lessmatch.lessmatch.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PairingScore {
    private Long id;
    private PublicUserInfo user1;
    private PublicUserInfo user2;
    private double compatibilityScore;
    private int matchingCriteria;
    private SongBasicInfo song;
    private int verseCount;
}
