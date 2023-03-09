package com.example.mobilite_internationale.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    private String username;



    private String email;


    private String password;

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


}
