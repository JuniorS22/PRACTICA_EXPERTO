package com.PracticaExperto.Practica_Experto_Junior_Salinas.service;

import com.PracticaExperto.Practica_Experto_Junior_Salinas.model.Libro;
import com.PracticaExperto.Practica_Experto_Junior_Salinas.model.Persona;
import com.PracticaExperto.Practica_Experto_Junior_Salinas.repository.LibroRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class LibroServiceTest {



    @Mock
    private LibroRepository libroRepository;

    @InjectMocks
    private LibroService libroService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        libroService = new LibroService(libroRepository);
    }

    @Test
    public void testObtenerTodosLibros() {
        // Simular el comportamiento de findAll en el repositorio
        Mockito.when(libroRepository.findAll()).thenReturn(new ArrayList<>());

        List<Libro> libros = libroService.obtenerTodosLibros();

        assertNotNull(libros);
        assertEquals(0, libros.size());
    }

    @Test
    public void testObtenerLibroXidExistente() {
        Long id = 1L;
        Libro libroMock = new Libro();
        libroMock.setId(id);
        libroMock.setTitulo("Marvel");

        Mockito.when(libroRepository.findById(id)).thenReturn(Optional.of(libroMock));

        Libro libro = libroService.obtenerLibroXid(id);

        assertNotNull(libro);
        assertEquals(id, libro.getId());
        assertEquals("Marvel", libro.getTitulo());
    }

    @Test
    public void testObtenerLibroXidNoExistente() {
        Long id = 1L;

        Mockito.when(libroRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> libroService.obtenerLibroXid(id));
    }

    @Test
    public void testCrearLibro() {
        Libro libroMock = new Libro();
        libroMock.setId(1L);
        libroMock.setTitulo("Nuevo Libro");

        Mockito.when(libroRepository.save(libroMock)).thenReturn(libroMock);

        Libro libroCreado = libroService.crearLibro(libroMock);

        assertNotNull(libroCreado);
        assertEquals(1L, libroCreado.getId());
        assertEquals("Nuevo Libro", libroCreado.getTitulo());
    }

    @Test
    public void testActualizarLibro() {
        Long id = 1L;
        Libro libroExistente = new Libro();
        libroExistente.setId(id);
        libroExistente.setTitulo("Libro Existente");

        Libro libroActualizado = new Libro();
        libroActualizado.setId(id);
        libroActualizado.setTitulo("Libro Actualizado");

        Mockito.when(libroRepository.findById(id)).thenReturn(Optional.of(libroExistente));
        Mockito.when(libroRepository.save(libroExistente)).thenReturn(libroActualizado);

        Libro libroModificado = libroService.actualziarLibro(id, libroActualizado);

        assertNotNull(libroModificado);
        assertEquals(id, libroModificado.getId());
        assertEquals("Libro Actualizado", libroModificado.getTitulo());
    }

    @Test
    public void testEliminarPorId() {
        Long id = 1L;

        libroService.eliminarPorId(id);

        // Verificar que se llame al método deleteById en el repositorio con el ID proporcionado
        Mockito.verify(libroRepository, Mockito.times(1)).deleteById(id);
    }

    @Test
    public void testObtenerLibro() throws JsonProcessingException {

        String nombre = "Marvel";
        Libro libro = new Libro();
        libro.setTitulo(nombre);

        when(libroRepository.findByTitulo(nombre)).thenReturn(Optional.of(libro));


        Libro resultado = libroService.obtenerLibroXnombre(nombre);


        assertNotNull(resultado);
        assertEquals(nombre, resultado.getTitulo());
    }

    @Test
    public void testObtenerLibroXnombre_WithJsonInput() throws JsonProcessingException {
        String jsonInput = "{\"titulo\":\"Marvel\"}";
        Libro libroMock = new Libro();
        libroMock.setTitulo("Marvel");

        when(libroRepository.findByTitulo("Marvel")).thenReturn(Optional.of(libroMock));

        Libro resultado = libroService.obtenerLibroXnombre(jsonInput);


        assertNotNull(resultado);
        assertEquals("Marvel", resultado.getTitulo());
    }

    @Test
    public void testObtenerPersonaXnombre_PersonaNoEncontrada() {

        String nombre = "Junior";

        when(libroRepository.findByTitulo(nombre)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> libroService.obtenerLibroXnombre(nombre));
    }

}