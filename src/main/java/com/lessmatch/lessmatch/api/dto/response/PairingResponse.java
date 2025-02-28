package com.lessmatch.lessmatch.api.dto.response;

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
public class PairingResponse {
    private String pairingCode;
    private PublicUserInfo creatorUser;
    private PublicUserInfo pairedUser;
    private SongBasicInfo song;
}
