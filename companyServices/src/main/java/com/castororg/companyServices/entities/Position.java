package com.castororg.companyServices.entities;

import com.castororg.companyServices.dtos.PositionDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Position {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Integer idPosition;
    private String name;

    public Position(String name) {
        this.name = name;
    }

    public static PositionDTO positionToPositionDTO(Position position) {
        return new PositionDTO(position.idPosition, position.name);
    }

    public static Position positionDTOToPosition(PositionDTO position) {
        return new Position(position.idPosition(), position.name());
    }
}


