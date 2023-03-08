package com.example.mobilite_internationale.dto;

import com.example.mobilite_internationale.entities.Role;
import lombok.*;

import javax.jnlp.IntegrationService;
import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private Role role;
    private List<Integer> favoriteOpportunities;
}
