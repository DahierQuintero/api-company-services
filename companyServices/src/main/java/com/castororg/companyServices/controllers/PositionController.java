package com.castororg.companyServices.controllers;

import com.castororg.companyServices.dtos.PositionDTO;
import com.castororg.companyServices.dtos.SimpleMessageResponseDTO;
import com.castororg.companyServices.services.impl.PositionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dilan.com/api/v1/position")
public class PositionController {

    @Autowired
    private PositionServiceImpl positionServiceImpl;

    public PositionController(PositionServiceImpl positionServiceImpl) {
        this.positionServiceImpl = positionServiceImpl;
    }

    @PostMapping("/create")
    public ResponseEntity<SimpleMessageResponseDTO> createPosition(@RequestBody PositionDTO positionDTO) {
        return positionServiceImpl.createPosition(positionDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PositionDTO> getPositionById(@PathVariable("id") Integer id) {
        return positionServiceImpl.getPositionById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SimpleMessageResponseDTO> updatePositionById(@PathVariable("id") Integer id, @RequestBody PositionDTO positionRequest) {
        return positionServiceImpl.updatePositionById(id, positionRequest);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<SimpleMessageResponseDTO> deletePotitionById(@PathVariable("id") Integer id) {
        return positionServiceImpl.deletePositionById(id);
    }

    @GetMapping("/all")
    public ResponseEntity<List<PositionDTO>> getAllPositions() {
        return positionServiceImpl.getAllPositions();
    }



}
