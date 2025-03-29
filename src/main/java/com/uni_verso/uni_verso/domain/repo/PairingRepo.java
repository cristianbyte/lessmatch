package com.uni_verso.uni_verso.domain.repo;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uni_verso.uni_verso.domain.entity.Pairing;

public interface PairingRepo extends JpaRepository<Pairing,Long>{
    List<Pairing> findByCreatedAtBeforeAndPairedUserIsNull(LocalDateTime dateTime);
    Optional<Pairing> findByPairingCode(String pairingCode);
    List<Pairing> findByCreatorUserIdOrPairedUserId(String creatorUserId, String pairedUserId);
}
