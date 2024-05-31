package com.crio.starter.controller;

import lombok.AllArgsConstructor;
import java.util.List;
import com.crio.starter.constants.MemeConstants;
import com.crio.starter.exchange.MemeDto;
import com.crio.starter.exchange.MemePostResponseDto;
import com.crio.starter.exchange.ResponseDto;
import com.crio.starter.service.IMemeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/memes", produces = {MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor
@Validated
public class MemeController {

    private IMemeService memeService;

    @PostMapping("/")
    public ResponseEntity<MemePostResponseDto> createMeme(@RequestBody MemeDto memeDto) {
        MemePostResponseDto getSavedid = memeService.createMeme(memeDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(getSavedid);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MemeDto> fetchMeme(@PathVariable String id){
        MemeDto fetchedMemeDto = memeService.fetchMeme(id); 
        return ResponseEntity.status(HttpStatus.OK).body(fetchedMemeDto);
    }

    @GetMapping("/welcome")
    public ResponseEntity<String> welcomeToMemes(){
        return ResponseEntity.status(HttpStatus.OK).body("Welcome to meme community");
    }

    @GetMapping("")
    public ResponseEntity<List<MemeDto>> fetchLatest100Meme(){
        List<MemeDto> latest100Memes = memeService.getLatest100Memes();
        return ResponseEntity.status(HttpStatus.OK).body(latest100Memes);
    }
}
