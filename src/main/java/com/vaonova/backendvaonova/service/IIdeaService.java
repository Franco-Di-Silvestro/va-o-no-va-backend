package com.vaonova.backendvaonova.service;

import com.vaonova.backendvaonova.dto.RequestIdeaDto;
import com.vaonova.backendvaonova.dto.ResponseAnalizedIdeaDto;
import org.springframework.stereotype.Service;

@Service
public interface IIdeaService {
    ResponseAnalizedIdeaDto analizeIdea(RequestIdeaDto idea);
}
