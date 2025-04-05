package com.uni_verso.uni_verso.infrastructure.service;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import com.uni_verso.uni_verso.api.dto.response.PairingResponse;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PairingNotificationService {
    private final SimpMessagingTemplate messagingTemplate;
    
    public void notifyPairingCreated(PairingResponse pairing) {
        // Notificar al creador
        sendPairingUpdate(pairing.getCreatorUser().getId(), "PAIRING_CREATED", pairing);
    }
    
    public void notifyPairingCompleted(PairingResponse pairing) {
        // Notificar a ambos usuarios
        sendPairingUpdate(pairing.getCreatorUser().getId(), "PAIRING_COMPLETED", pairing);
        sendPairingUpdate(pairing.getPairedUser().getId(), "PAIRING_COMPLETED", pairing);
    }
    
    private void sendPairingUpdate(String userId, String eventType, PairingResponse data) {
        // El destino podría ser /user/{userId}/queue/updates
        messagingTemplate.convertAndSendToUser(
            userId,
            "/queue/updates", 
            new WebSocketMessage(eventType, data)
        );
    }
}