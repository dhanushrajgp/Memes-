package com.crio.starter.service.Implementation;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.crio.starter.data.MemeEntity;
import com.crio.starter.exceptions.MemeAlreadyExistException;
import com.crio.starter.exceptions.ResourceNotFoundException;
import com.crio.starter.exchange.MemeDto;
import com.crio.starter.exchange.MemePostResponseDto;
import com.crio.starter.repository.MemeRepository;
import com.crio.starter.service.IMemeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

@Service
public class MemeService implements IMemeService{

    
    @Autowired
    private ModelMapper modelmapper;


    @Autowired
    private MemeRepository memeRepository;

    @Override
    public MemePostResponseDto createMeme(MemeDto memeDto) {
        MemeEntity meme = modelmapper.map(memeDto, MemeEntity.class);
        Optional<MemeEntity> optionalMeme = memeRepository.findByNameAndUrlAndCaption(memeDto.getName(),memeDto.getUrl(),memeDto.getCaption());

        if(optionalMeme.isPresent()){
            if(optionalMeme.get().getUrl().equals(memeDto.getUrl()) && optionalMeme.get().getCaption().equals(memeDto.getCaption()) && 
            optionalMeme.get().getName().equals(memeDto.getName())){
                throw new MemeAlreadyExistException("Meme already Exists with same caption and Image");
            }
        }
        MemeEntity savedMeme =  memeRepository.save(meme);
        System.out.println(savedMeme.toString());
        MemePostResponseDto postResponse = new MemePostResponseDto();
        postResponse.setId(savedMeme.getId().toString());
        return postResponse;
    }

    @Override
    public MemeDto fetchMeme(String id) {
        MemeEntity meme = memeRepository.findById(id).orElseThrow(
            ()-> new ResourceNotFoundException("Meme", "id", id)
        );
        return modelmapper.map(meme, MemeDto.class);
    }


    public List<MemeDto> getLatest100Memes() {
        List<MemeEntity> latestMemes = memeRepository.findTop100ByOrderByCreatedAtDesc();
        return latestMemes.stream().map((memeEntity)-> modelmapper.map(memeEntity, MemeDto.class)).collect(Collectors.toList());
    }
    
}
