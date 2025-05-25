package com.vaonova.backendvaonova.mapper;

import com.vaonova.backendvaonova.dto.ResponseAnalizedIdeaDto;
import com.vaonova.backendvaonova.model.AnalizedIdea;

public class AnalizedIdeaMapper {
    public static ResponseAnalizedIdeaDto toAnalizedIdeaDto(AnalizedIdea idea) {
        return new ResponseAnalizedIdeaDto(idea.getLongitude(), idea.getLatitude(), idea.getBusinessType(), idea.getBudget(), idea.getRisk(), idea.getCompetition(), idea.getViabilityScore(), idea.getRecommendations());
    }
}
