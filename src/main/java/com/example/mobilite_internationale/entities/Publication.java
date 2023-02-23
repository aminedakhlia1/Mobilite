package com.example.mobilite_internationale.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Publication implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPub;
    private  String title,contenu,path;
    @OneToMany(mappedBy = "publication", fetch = FetchType.EAGER,
            cascade = CascadeType.ALL)
    private Set<Commentaire> commentaires;
}
