package com.example.mobilite_internationale.entities;



import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


import com.fasterxml.jackson.annotation.JsonIgnore;




@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = "username"),
        @UniqueConstraint(columnNames = "email")
})

public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    @NotBlank
    @Size(max = 20)
    private String username;

    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    @NotBlank
    @Size(max = 120)
    private String password;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy="user")
    private Set<Reservation> reservations;
 @JsonIgnore
    @OneToMany(mappedBy ="user")
    private Set<Publication> publications;
    @JsonIgnore
    @OneToMany(mappedBy ="user")
    private Set<Commentaire> commentaires;
    @JsonIgnore
    @OneToMany(mappedBy ="user")
    private Set<Reaction> reacts;
    @JsonIgnore
    @OneToMany(mappedBy ="user")
    private Set<Favoris> favoris;

    public User(Long userId) {
        this.id=userId;
    }



}

