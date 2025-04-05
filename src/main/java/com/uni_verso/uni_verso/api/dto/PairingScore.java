package com.uni_verso.uni_verso.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PairingScore {
    private int selectedVerses; // Total verses selected (together)
    private int matchedVerses;  // Verses matched (both selected)
    private double matchPercentage; // Match percentage
    private double connectionScore; // Main connection metric
}
