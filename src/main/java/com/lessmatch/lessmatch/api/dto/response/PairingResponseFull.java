package com.lessmatch.lessmatch.api.dto.response;

import java.util.List;

import com.lessmatch.lessmatch.api.dto.PairingScore;
import com.lessmatch.lessmatch.api.dto.PublicUserInfo;
import com.lessmatch.lessmatch.api.dto.SongBasicInfo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PairingResponseFull {
    private String pairingCode;
    private PublicUserInfo creatorUser;
    private List<Boolean> creatorLines;
    private PublicUserInfo pairedUser;
    private List<Boolean> pairedLines;
    private SongBasicInfo song;
    private PairingScore pairingScore;
}
