package com.PracticaExperto.Practica_Experto_Junior_Salinas.repository;

import com.PracticaExperto.Practica_Experto_Junior_Salinas.model.Autor;
import com.PracticaExperto.Practica_Experto_Junior_Salinas.model.Direccion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DireccionRepository  extends JpaRepository<Direccion,Long>{
    Optional<Direccion> findByCalle(String calle);
}
