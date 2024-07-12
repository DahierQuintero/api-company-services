package com.castororg.companyServices.services;

import com.castororg.companyServices.dtos.CastorActivityDTO;
import com.castororg.companyServices.dtos.SimpleMessageResponseDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ICastorActivityService {
    ResponseEntity<SimpleMessageResponseDTO> createCastorAttention(CastorActivityDTO castorAttentionDTO);
    ResponseEntity<CastorActivityDTO> getCastorActivityById(Integer id);
    ResponseEntity<SimpleMessageResponseDTO> updateCastorActivityById(Integer id, CastorActivityDTO castorAttentionDTO);
    ResponseEntity<SimpleMessageResponseDTO> deleteCastorActivityById(Integer id);
    ResponseEntity<List<CastorActivityDTO>> getAllCastorActivities();
}
