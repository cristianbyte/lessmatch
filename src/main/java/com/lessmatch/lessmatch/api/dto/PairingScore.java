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
    private int selectedVerses; // Cuántos versos seleccionaron en total (individualmente)
    private int matchedVerses;  // Cuántos versos coincidieron (ambos eligieron)
    private double matchPercentage; // Porcentaje de coincidencia
    private double connectionScore; // Métrica principal de conexión
    private SongBasicInfo song;
    private PublicUserInfo user1;
    private PublicUserInfo user2;
}
