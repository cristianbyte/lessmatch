package com.lessmatch.lessmatch.domain.repo;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lessmatch.lessmatch.domain.entity.Pairing;

public interface PairingRepo extends JpaRepository<Pairing,Long>{
    List<Pairing> findByCreatedAtBeforeAndPairedUserIsNull(LocalDateTime dateTime);
    Optional<Pairing> findByPairingCode(String pairingCode);
}
