package com.uni_verso.uni_verso.api.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uni_verso.uni_verso.api.dto.SongBasicInfo;
import com.uni_verso.uni_verso.api.dto.request.SongRequest;
import com.uni_verso.uni_verso.infrastructure.abstract_service.ISongService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/song")
@CrossOrigin(
    origins = {"https://uni-verso.vercel.app", "https://universo.coder.red"}, 
    allowCredentials = "true", 
    allowedHeaders = {"Authorization", "Content-Type", "Accept"}
)
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

    @GetMapping("/getByParing/{code}")
    public ResponseEntity<SongBasicInfo> getByCode(@PathVariable String code) {
        SongBasicInfo songResponse = this.songService.findByCode(code);
        return ResponseEntity.ok(songResponse);
    }
}
