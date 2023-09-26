package com.PracticaExperto.Practica_Experto_Junior_Salinas.repository;

import com.PracticaExperto.Practica_Experto_Junior_Salinas.model.Direccion;
import com.PracticaExperto.Practica_Experto_Junior_Salinas.model.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonaRepository extends JpaRepository<Persona,Long> {

    Optional<Persona> findByNombre(String nombre);
}
