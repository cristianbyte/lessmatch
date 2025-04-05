package com.uni_verso.uni_verso.api.dto.request;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LineSelectionRequest {
    private String pairingCode;
    private String userId;
    private List<Boolean> selectedLines;
}
