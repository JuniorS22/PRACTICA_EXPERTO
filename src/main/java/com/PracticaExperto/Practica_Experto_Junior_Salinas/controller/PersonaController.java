package com.PracticaExperto.Practica_Experto_Junior_Salinas.controller;

import com.PracticaExperto.Practica_Experto_Junior_Salinas.model.Libro;
import com.PracticaExperto.Practica_Experto_Junior_Salinas.model.Persona;
import com.PracticaExperto.Practica_Experto_Junior_Salinas.service.PersonaService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/persona")
public class PersonaController {
    private final PersonaService personaService;

    public PersonaController(PersonaService personaService) {
        this.personaService = personaService;
    }


    @GetMapping
    public List<Persona> listarPersona(){

        return personaService.obtenerTodosPersona();

    }

    @GetMapping("/{id}")
    public ResponseEntity<Persona> obtenerLibroPorId(@PathVariable Long id) {
        Persona persona = personaService.obtenerPersonaXid(id);
        return ResponseEntity.ok(persona);
    }

    @PostMapping("/buscarPorNombre")
    public ResponseEntity<Persona> obtenerPersonaPorNombre2(@RequestBody String nombre) throws JsonProcessingException {
        Persona persona = personaService.obtenerPersonaXnombre(nombre);
        return ResponseEntity.ok(persona);
    }

    @PostMapping
    public ResponseEntity<Persona> crearPersona(@RequestBody Persona persona) {
        Persona nuevaPersona = personaService.crearPersona(persona);
        return new ResponseEntity<>(nuevaPersona, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Persona> actualizarPersona(@PathVariable Long id, @RequestBody Persona personaActualizado) {
        Persona persona = personaService.actualziarPersona(id, personaActualizado);
        return ResponseEntity.ok(persona);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id){
        personaService.eliminarPorId(id);
    }

}
