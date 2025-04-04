package com.uni_verso.uni_verso.domain.repo;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.uni_verso.uni_verso.domain.entity.Pairing;

public interface PairingRepo extends JpaRepository<Pairing,Long>{
    @Modifying(flushAutomatically = true, clearAutomatically = true)
    @Transactional
    @Query("DELETE FROM Pairing p WHERE (p.pairedUser IS NULL AND p.createdAt < :unpairedLimit) OR (p.pairedUser IS NOT NULL AND p.createdAt < :pairedLimit)")
    int deleteExpiredPairings(@Param("unpairedLimit") LocalDateTime unpairedLimit, @Param("pairedLimit") LocalDateTime pairedLimit);
    List<Pairing> findByCreatedAtBeforeAndPairedUserIsNull(LocalDateTime dateTime);
    Optional<Pairing> findByPairingCode(String pairingCode);
    List<Pairing> findByCreatorUserIdOrPairedUserId(String creatorUserId, String pairedUserId);
}
