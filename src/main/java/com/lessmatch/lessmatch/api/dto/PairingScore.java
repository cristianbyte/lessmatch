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
    private int verseCount;
    private int criteria;
    private double score;
    private SongBasicInfo song;
    private PublicUserInfo user1;
    private PublicUserInfo user2;
}
