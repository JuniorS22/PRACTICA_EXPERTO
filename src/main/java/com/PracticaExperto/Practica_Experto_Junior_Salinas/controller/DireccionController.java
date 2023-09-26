package com.PracticaExperto.Practica_Experto_Junior_Salinas.controller;

import com.PracticaExperto.Practica_Experto_Junior_Salinas.model.Autor;
import com.PracticaExperto.Practica_Experto_Junior_Salinas.model.Direccion;
import com.PracticaExperto.Practica_Experto_Junior_Salinas.service.DireccionService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/direccion")
public class DireccionController {
    private final DireccionService direccionService;

    public DireccionController(DireccionService direccionService) {
        this.direccionService = direccionService;
    }


    @GetMapping
    public List<Direccion> listarDirecciones(){

        return direccionService.obtenerTodasDirecciones();

    }

    @GetMapping("/{id}")
    public ResponseEntity<Direccion> obtenerDireccionPorId(@PathVariable Long id) {
        Direccion direccion = direccionService.obtenerDireccionXid(id);
        return ResponseEntity.ok(direccion);
    }

    @PostMapping("/buscarPorNombre")
    public ResponseEntity<Direccion> obtenerDireccionPorNombre2(@RequestBody String nombre) throws JsonProcessingException {
        Direccion direccion = direccionService.obtenerDireccionXnombre(nombre);
        return ResponseEntity.ok(direccion);
    }

    @PostMapping
    public ResponseEntity<Direccion> crearDireccion(@RequestBody Direccion direccion) {
        Direccion nuevoDireccion = direccionService.crearDireccion(direccion);
        return new ResponseEntity<>(nuevoDireccion, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Direccion> actualizarDireccion(@PathVariable Long id, @RequestBody Direccion direccionActualizado) {
        Direccion direccion= direccionService.actualziarDireccion(id, direccionActualizado);
        return ResponseEntity.ok(direccion);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id){
        direccionService.eliminarPorId(id);
    }
}
