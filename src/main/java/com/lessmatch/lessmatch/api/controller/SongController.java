package com.lessmatch.lessmatch.api.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lessmatch.lessmatch.api.dto.SongBasicInfo;
import com.lessmatch.lessmatch.api.dto.request.SongRequest;
import com.lessmatch.lessmatch.infrastructure.abstract_service.ISongService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/song")
public class SongController {

    private final ISongService songService;

    @GetMapping("/popular")
    public ResponseEntity<List<SongBasicInfo>> getMostPopularSongs() {
        return ResponseEntity.ok(this.songService.getMostPopularSongs());
    }
    
    @PostMapping
    public ResponseEntity<SongBasicInfo> create(@Validated @RequestBody SongRequest request) {
        SongBasicInfo songResponse = this.songService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(songResponse);
    }
}
