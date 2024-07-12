package com.castororg.companyServices.entities;

import com.castororg.companyServices.dtos.CastorActivityDTO;
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
public class CastorActivity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer ActivityNumber;
    private String name;

    public CastorActivity(Integer activityNumber, String name) {
        ActivityNumber = activityNumber;
        this.name = name;
    }

    public static CastorActivityDTO castorAttentionToCastorAttentionDTO(CastorActivity castorActivity) {
        return new CastorActivityDTO(castorActivity.ActivityNumber, castorActivity.name);
    }

    public static CastorActivity castorActivityDTOToCastorActivity(CastorActivityDTO castorActivityDTO) {
        return new CastorActivity(castorActivityDTO.activityNumber(),castorActivityDTO.name());
    }
}
