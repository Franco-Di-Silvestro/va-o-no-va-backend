package com.vaonova.backendvaonova.mapper;

import com.vaonova.backendvaonova.dto.RequestIdeaDto;
import com.vaonova.backendvaonova.model.Idea;

public class IdeaMapper {
    public static Idea toIdea(RequestIdeaDto idea) {
        return new Idea(idea.getLongitude(), idea.getLatitude(), idea.getBusinessType(), idea.getBudget());
    }
}
