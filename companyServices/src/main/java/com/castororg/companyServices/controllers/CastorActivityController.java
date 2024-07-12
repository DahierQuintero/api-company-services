package com.castororg.companyServices.controllers;

import com.castororg.companyServices.dtos.CastorActivityDTO;
import com.castororg.companyServices.dtos.SimpleMessageResponseDTO;
import com.castororg.companyServices.services.impl.CastorActivityServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dilan.com/api/v1/activity")
public class CastorActivityController {

    @Autowired
    private CastorActivityServiceImpl castorAttentionService;

    public CastorActivityController(CastorActivityServiceImpl castorAttentionService) {
        this.castorAttentionService = castorAttentionService;
    }

    @PostMapping("/create")
    public ResponseEntity<SimpleMessageResponseDTO> createCastorAttention(@RequestBody CastorActivityDTO castorAttentionDTO) {
        return castorAttentionService.createCastorAttention(castorAttentionDTO);
    }
}
