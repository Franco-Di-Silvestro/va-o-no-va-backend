package com.vaonova.backendvaonova.controller;

import com.vaonova.backendvaonova.dto.RequestIdeaDto;
import com.vaonova.backendvaonova.dto.ResponseAnalizedIdeaDto;
import com.vaonova.backendvaonova.service.IIdeaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/idea")
public class IdeaController {
    @Autowired
    private IIdeaService service;

    @PostMapping
    public ResponseEntity<ResponseAnalizedIdeaDto> analizeIdea(@RequestBody RequestIdeaDto idea) {
        return new ResponseEntity<>(service.analizeIdea(idea), HttpStatus.OK);
    }
}
