package com.PracticaExperto.Practica_Experto_Junior_Salinas.service;

import com.PracticaExperto.Practica_Experto_Junior_Salinas.model.Autor;
import com.PracticaExperto.Practica_Experto_Junior_Salinas.repository.AutorRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AutorService {
    private final AutorRepository autorRepository;

    public AutorService(AutorRepository autorRepository) {
        this.autorRepository = autorRepository;
    }



    public List<Autor> obtenerTodosAutores(){
        return autorRepository.findAll();
    }

    public Autor obtenerAutorXid(Long id){
        Optional<Autor> autor = autorRepository.findById(id);

        if(autor.isPresent()){
            return autor.get();
        }else{
            throw new RuntimeException("Autor no encontrado");
        }
    }
    public Autor crearAutor(Autor autor){

        return autorRepository.save(autor);
    }

    public Autor actualziarAutor(Long id, Autor autorActualizado){
        Autor autorExistente = obtenerAutorXid(id);

        autorExistente.setId(autorActualizado.getId());
        autorExistente.setNombre(autorActualizado.getNombre());
        return autorRepository.save(autorExistente);

    }


    public void  eliminarPorId(Long id){
        autorRepository.deleteById(id);

    }

    public Autor obtenerAutorXnombre(String nombre) throws JsonProcessingException {

        Autor autor1 = new Autor();
        if(nombre.contains("{")){
            ObjectMapper objectMapper = new ObjectMapper();
            autor1 = objectMapper.readValue(nombre, Autor.class);
        }else {
            autor1.setNombre(nombre);
        }


        Optional<Autor> autor = autorRepository.findByNombre(autor1.getNombre());

        if(autor.isPresent()){
            return autor.get();
        }else{
            throw new RuntimeException("Autor no encontrado");
        }
    }
}
