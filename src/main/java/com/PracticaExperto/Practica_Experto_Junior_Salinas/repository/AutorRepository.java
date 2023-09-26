package com.PracticaExperto.Practica_Experto_Junior_Salinas.repository;

import com.PracticaExperto.Practica_Experto_Junior_Salinas.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AutorRepository  extends JpaRepository<Autor,Long> {

    Optional<Autor> findByNombre(String nombre);
}
