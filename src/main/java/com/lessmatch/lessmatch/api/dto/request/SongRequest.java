package com.lessmatch.lessmatch.api.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SongRequest {
    @NotBlank(message = "Song title is required")
    @Size(max = 120, message = "Title must not exceed 120 characters")
    private String title;
    
    @NotBlank(message = "Artist name is required")
    @Size(max = 120, message = "Artist name must not exceed 120 characters")
    private String artist;
    
    @NotBlank(message = "Album image URL is required")
    @Size(max = 250, message = "Album image URL must not exceed 250 characters")
    private String albumImage;
    
    @Min(value = 1, message = "Song must have at least 1 verse")
    @Max(value = 100, message = "Song cannot have more than 100 verses")
    private int verseCount;
}
