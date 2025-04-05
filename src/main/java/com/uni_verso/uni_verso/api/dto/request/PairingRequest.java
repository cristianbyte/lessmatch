package com.uni_verso.uni_verso.api.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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

    @NotNull(message = "Song information is required")
    @Valid  // validate inside object
    private SongRequest song;

}
