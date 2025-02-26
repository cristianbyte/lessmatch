package com.lessmatch.lessmatch.domain.entity;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.Random;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "pairings")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "pairings",
    indexes = {
        @Index(name = "idx_creator_user", columnList = "creator_user_id"),
        @Index(name = "idx_paired_user", columnList = "paired_user_id"),
        @Index(name = "idx_pairing_code", columnList = "pairingCode")
    })
    
public class Pairing {

    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final Random RANDOM = new SecureRandom();

    @Id
    @Column(updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, length = 6, nullable = false)
    private String pairingCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "creator_user_id", nullable = false)
    private User creatorUser;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "paired_user_id")
    private User pairedUser;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "song_id", nullable = false)
    private Song song;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        pairingCode = generatePairingCode();
    }

    public boolean isExpired() {
        if (pairedUser == null) {
            return createdAt.plusHours(24).isBefore(LocalDateTime.now());
        }else{
            return createdAt.plusHours(72).isBefore(LocalDateTime.now());
        }
    }

    private String generatePairingCode() {
        StringBuilder code = new StringBuilder(6);
        for (int i = 0; i < 6; i++) {
            code.append(CHARACTERS.charAt(RANDOM.nextInt(CHARACTERS.length())));
        }
        return code.toString();
    }
}
