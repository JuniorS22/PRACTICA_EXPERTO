package com.PracticaExperto.Practica_Experto_Junior_Salinas.controller;

import com.PracticaExperto.Practica_Experto_Junior_Salinas.model.Autor;
import com.PracticaExperto.Practica_Experto_Junior_Salinas.service.AutorService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/autores")
public class AutorController {

    private final AutorService autorService;

    public AutorController(AutorService autorService) {
        this.autorService = autorService;
    }


    @GetMapping
    public List<Autor> listarAutores(){

         return autorService.obtenerTodosAutores();

    }

    @GetMapping("/{id}")
    public ResponseEntity<Autor> obtenerAutorPorId(@PathVariable Long id) {
        Autor autor = autorService.obtenerAutorXid(id);
        return ResponseEntity.ok(autor);
    }

    @PostMapping("/buscarPorNombre")
    public ResponseEntity<Autor> obtenerAutorPorNombre2(@RequestBody String nombre) throws JsonProcessingException {
        Autor autor = autorService.obtenerAutorXnombre(nombre);
        return ResponseEntity.ok(autor);
    }

    @PostMapping
    public ResponseEntity<Autor> crearAutor(@RequestBody Autor autor) {
        Autor nuevoAutor = autorService.crearAutor(autor);
        return new ResponseEntity<>(nuevoAutor, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Autor> actualizarAutor(@PathVariable Long id, @RequestBody Autor autorActualizado) {
        Autor autor= autorService.actualziarAutor(id, autorActualizado);
        return ResponseEntity.ok(autor);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id){
        autorService.eliminarPorId(id);
    }
}
