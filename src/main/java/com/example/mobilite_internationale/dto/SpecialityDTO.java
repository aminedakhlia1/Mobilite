package com.example.mobilite_internationale.dto;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SpecialityDTO {

    private String speciality;
    private Integer nb_opportunities;
}
