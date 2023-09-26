package com.PracticaExperto.Practica_Experto_Junior_Salinas.service;

import com.PracticaExperto.Practica_Experto_Junior_Salinas.model.Direccion;
import com.PracticaExperto.Practica_Experto_Junior_Salinas.model.Libro;
import com.PracticaExperto.Practica_Experto_Junior_Salinas.model.Persona;
import com.PracticaExperto.Practica_Experto_Junior_Salinas.repository.PersonaRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonaService {

    private final PersonaRepository personaRepository;

    public PersonaService(PersonaRepository personaRepository) {
        this.personaRepository = personaRepository;
    }

    public List<Persona> obtenerTodosPersona(){
        return personaRepository.findAll();
    }

    public Persona obtenerPersonaXid(Long id){
        Optional<Persona> persona = personaRepository.findById(id);

        if(persona.isPresent()){
            return persona.get();
        }else{
            throw new RuntimeException("Persona no encontrada");
        }
    }
    public Persona crearPersona(Persona persona){

        return personaRepository.save(persona);
    }

    public Persona actualziarPersona(Long id, Persona personaActualizada){
        Persona personaExistente = obtenerPersonaXid(id);

        personaExistente.setId(personaActualizada.getId());
        personaExistente.setNombre(personaActualizada.getNombre());
        return personaRepository.save(personaExistente);

    }


    public void  eliminarPorId(Long id){
        personaRepository.deleteById(id);

    }

    public Persona obtenerPersonaXnombre(String nombre) throws JsonProcessingException {

        Persona persona1 = new Persona();
        if(nombre.contains("{")){
            ObjectMapper objectMapper = new ObjectMapper();
            persona1 = objectMapper.readValue(nombre, Persona.class);
        }else {
            persona1.setNombre(nombre);
        }


        Optional<Persona> persona = personaRepository.findByNombre(persona1.getNombre());

        if(persona.isPresent()){
            return persona.get();
        }else{
            throw new RuntimeException("Persona no encontrada");
        }
    }
}
