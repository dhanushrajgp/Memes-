package com.crio.starter.service;

import java.util.List;
import com.crio.starter.data.MemeEntity;
import com.crio.starter.exchange.MemeDto;
import com.crio.starter.exchange.MemePostResponseDto;

public interface IMemeService {
    
    MemePostResponseDto createMeme(MemeDto memeDto);

    MemeDto fetchMeme(String id);

    List<MemeDto> getLatest100Memes();
}
