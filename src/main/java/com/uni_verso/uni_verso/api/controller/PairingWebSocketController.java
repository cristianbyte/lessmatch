package com.uni_verso.uni_verso.api.controller;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class PairingWebSocketController {
    private final SimpMessagingTemplate messagingTemplate;

    public PairingWebSocketController(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

}
