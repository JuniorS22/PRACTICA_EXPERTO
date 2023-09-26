package com.PracticaExperto.Practica_Experto_Junior_Salinas.service;


import com.PracticaExperto.Practica_Experto_Junior_Salinas.model.Persona;
import com.PracticaExperto.Practica_Experto_Junior_Salinas.repository.PersonaRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

class PersonaServiceTest {

    @Mock
    private PersonaRepository personaRepository;

    @InjectMocks
    private PersonaService personaService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testObtenerTodosPersona() {

        List<Persona> personas = new ArrayList<>();
        personas.add(new Persona(1L, "Persona 1"));
        personas.add(new Persona(2L, "Persona 2"));

        Mockito.when(personaRepository.findAll()).thenReturn(personas);

        List<Persona> resultado = personaService.obtenerTodosPersona();

        assertEquals(2, resultado.size());
    }

    @Test
    public void testObtenerPersonaXidExistente() {

        Long id = 1L;
        Persona personaMock = new Persona(id, "Persona 1");

        Mockito.when(personaRepository.findById(id)).thenReturn(Optional.of(personaMock));

        Persona resultado = personaService.obtenerPersonaXid(id);

        assertNotNull(resultado);
        assertEquals(id, resultado.getId());
        assertEquals("Persona 1", resultado.getNombre());
    }

    @Test
    public void testObtenerPersonaXidNoExistente() {

        Long id = 1L;

        Mockito.when(personaRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> personaService.obtenerPersonaXid(id));
    }

    @Test
    public void testCrearPersona() {

        Persona personaNueva = new Persona(1L, "Nueva Persona");

        Mockito.when(personaRepository.save(Mockito.any(Persona.class))).thenReturn(personaNueva);

        Persona resultado = personaService.crearPersona(personaNueva);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        assertEquals("Nueva Persona", resultado.getNombre());
    }

    @Test
    public void testActualizarPersona() {

        Long id = 1L;
        Persona personaExistente = new Persona(id, "Persona Existente");
        Persona personaActualizada = new Persona(id, "Persona Actualizada");

        when(personaRepository.findById(id)).thenReturn(Optional.of(personaExistente));
        when(personaRepository.save(Mockito.any(Persona.class))).thenReturn(personaActualizada);

        Persona resultado = personaService.actualziarPersona(id, personaActualizada);

        assertNotNull(resultado);
        assertEquals(id, resultado.getId());
        assertEquals("Persona Actualizada", resultado.getNombre());
    }

    @Test
    public void testEliminarPorId() {

        Long id = 1L;

        personaService.eliminarPorId(id);

        verify(personaRepository, times(1)).deleteById(id);
    }
    @Test
    public void testObtenerPersonaXnombre_WithValidName() throws JsonProcessingException {

        String nombre = "Juan";
        Persona personaMock = new Persona();
        personaMock.setNombre(nombre);

        when(personaRepository.findByNombre(nombre)).thenReturn(Optional.of(personaMock));


        Persona resultado = personaService.obtenerPersonaXnombre(nombre);


        assertNotNull(resultado);
        assertEquals(nombre, resultado.getNombre());
    }

    @Test
    public void testObtenerPersonaXnombre_WithJsonInput() throws JsonProcessingException {
        String jsonInput = "{\"nombre\":\"Juan\"}";
        Persona personaMock = new Persona();
        personaMock.setNombre("Juan");

        when(personaRepository.findByNombre("Juan")).thenReturn(Optional.of(personaMock));

        Persona resultado = personaService.obtenerPersonaXnombre(jsonInput);


        assertNotNull(resultado);
        assertEquals("Juan", resultado.getNombre());
    }

    @Test
    public void testObtenerPersonaXnombre_PersonaNoEncontrada() {

        String nombre = "Junior";

        when(personaRepository.findByNombre(nombre)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> personaService.obtenerPersonaXnombre(nombre));
    }


}