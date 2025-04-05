package com.uni_verso.uni_verso.api.dto.response;

import com.uni_verso.uni_verso.api.dto.PublicUserInfo;
import com.uni_verso.uni_verso.api.dto.SongBasicInfo;

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
