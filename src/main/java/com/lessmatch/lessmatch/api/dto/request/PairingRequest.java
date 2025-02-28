package com.lessmatch.lessmatch.api.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PairingRequest {

    @NotBlank(message = "Creator user id is required")
    private String creatorUserId;

    private String pairedUserId;

    @NotBlank(message = "Song id is required")
    private SongRequest song;

}
