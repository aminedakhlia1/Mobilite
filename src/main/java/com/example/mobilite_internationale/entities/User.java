package com.example.mobilite_internationale.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUser;

    private  String Firstname;

    private  String Lastname;

    private  String Email;

    private  String Login;

    private  String Password;

    private  String Photo;

    private  String Universityname;

    private  String Societyname;

    @Enumerated(EnumType.STRING)
    private Role role;


}
