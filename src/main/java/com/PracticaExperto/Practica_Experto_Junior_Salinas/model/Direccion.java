package com.PracticaExperto.Practica_Experto_Junior_Salinas.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Direccion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String calle;
    @ManyToOne
    @JoinColumn(name="persona_id")
    private Persona persona;


}
