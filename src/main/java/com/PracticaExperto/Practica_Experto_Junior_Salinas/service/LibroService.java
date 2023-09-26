package com.PracticaExperto.Practica_Experto_Junior_Salinas.service;

import com.PracticaExperto.Practica_Experto_Junior_Salinas.model.Libro;
import com.PracticaExperto.Practica_Experto_Junior_Salinas.repository.LibroRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LibroService {

    private final LibroRepository libroRepository;

    public LibroService(LibroRepository libroRepository) {
        this.libroRepository = libroRepository;
    }

    public List<Libro> obtenerTodosLibros(){
        return libroRepository.findAll();
    }

    public Libro obtenerLibroXid(Long id){
        Optional<Libro> libro = libroRepository.findById(id);

        if(libro.isPresent()){
            return libro.get();
        }else{
            throw new RuntimeException("Libro no encontrada");
        }
    }
    public Libro crearLibro(Libro libro){

        return libroRepository.save(libro);
    }

    public Libro actualziarLibro(Long id, Libro LibroActualizado){
        Libro libroExistente = obtenerLibroXid(id);

        libroExistente.setId(LibroActualizado.getId());
        libroExistente.setTitulo(LibroActualizado.getTitulo());
        return libroRepository.save(libroExistente);

    }


    public void  eliminarPorId(Long id){
        libroRepository.deleteById(id);

    }

    public Libro obtenerLibroXnombre(String nombre) throws JsonProcessingException {

        Libro libro1 = new Libro();
        if(nombre.contains("{")){
            ObjectMapper objectMapper = new ObjectMapper();
            libro1 = objectMapper.readValue(nombre, Libro.class);
        }else {
            libro1.setTitulo(nombre);
        }


        Optional<Libro> libro = libroRepository.findByTitulo(libro1.getTitulo());

        if(libro.isPresent()){
            return libro.get();
        }else{
            throw new RuntimeException("Libro no encontrada");
        }
    }
}
