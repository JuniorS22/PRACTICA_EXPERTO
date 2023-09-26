package com.PracticaExperto.Practica_Experto_Junior_Salinas.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Persona {
    public Persona() {
    }

    public Persona(Long id, String nombre) {
        this.id = id;
        this.nombre = nombre;

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;

    @OneToMany(mappedBy="persona")
    @JsonIgnore
    private List<Direccion> direccion=new ArrayList<>();
}
