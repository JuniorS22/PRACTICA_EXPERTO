package com.PracticaExperto.Practica_Experto_Junior_Salinas.controller;

import com.PracticaExperto.Practica_Experto_Junior_Salinas.model.Direccion;
import com.PracticaExperto.Practica_Experto_Junior_Salinas.model.Libro;
import com.PracticaExperto.Practica_Experto_Junior_Salinas.service.LibroService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/libros")
public class LibroController {

    private final LibroService libroService;

    public LibroController(LibroService libroService) {
        this.libroService = libroService;
    }

    @GetMapping
    public List<Libro> listarLibros(){

        return libroService.obtenerTodosLibros();

    }

    @GetMapping("/{id}")
    public ResponseEntity<Libro> obtenerLibroPorId(@PathVariable Long id) {
        Libro libro = libroService.obtenerLibroXid(id);
        return ResponseEntity.ok(libro);
    }

    @PostMapping("/buscarPorNombre")
    public ResponseEntity<Libro> obtenerLibroPorNombre2(@RequestBody String nombre) throws JsonProcessingException {
        Libro libro = libroService.obtenerLibroXnombre(nombre);
        return ResponseEntity.ok(libro);
    }

    @PostMapping
    public ResponseEntity<Libro> crearLibro(@RequestBody Libro libro) {
        Libro nuevoLibro = libroService.crearLibro(libro);
        return new ResponseEntity<>(nuevoLibro, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Libro> actualizarLibro(@PathVariable Long id, @RequestBody Libro libroActualizado) {
        Libro libro = libroService.actualziarLibro(id, libroActualizado);
        return ResponseEntity.ok(libro);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id){
        libroService.eliminarPorId(id);
    }
}
