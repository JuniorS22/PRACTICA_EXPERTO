package com.PracticaExperto.Practica_Experto_Junior_Salinas.service;

import com.PracticaExperto.Practica_Experto_Junior_Salinas.model.Autor;
import com.PracticaExperto.Practica_Experto_Junior_Salinas.model.Direccion;
import com.PracticaExperto.Practica_Experto_Junior_Salinas.repository.DireccionRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DireccionService {

    private final DireccionRepository direccionRepository;

    public DireccionService(DireccionRepository direccionRepository) {
        this.direccionRepository = direccionRepository;
    }

    public List<Direccion> obtenerTodasDirecciones(){
        return direccionRepository.findAll();
    }

    public Direccion obtenerDireccionXid(Long id){
        Optional<Direccion> direccion = direccionRepository.findById(id);

        if(direccion.isPresent()){
            return direccion.get();
        }else{
            throw new RuntimeException("Direccion no encontrada");
        }
    }
    public Direccion crearDireccion(Direccion direccion){

        return direccionRepository.save(direccion);
    }

    public Direccion actualziarDireccion(Long id, Direccion direccionActualizada){
        Direccion direccionExistente = obtenerDireccionXid(id);

        direccionExistente.setId(direccionActualizada.getId());
        direccionExistente.setCalle(direccionActualizada.getCalle());
        direccionExistente.setPersona(direccionActualizada.getPersona());
        return direccionRepository.save(direccionExistente);

    }

    public void  eliminarPorId(Long id){
        direccionRepository.deleteById(id);

    }

    public Direccion obtenerDireccionXnombre(String nombre) throws JsonProcessingException {

        Direccion direccion1 = new Direccion();
        if(nombre.contains("{")){
            ObjectMapper objectMapper = new ObjectMapper();
            direccion1 = objectMapper.readValue(nombre, Direccion.class);
        }else {
            direccion1.setCalle(nombre);
        }


        Optional<Direccion> direccion = direccionRepository.findByCalle(direccion1.getCalle());

        if(direccion.isPresent()){
            return direccion.get();
        }else{
            throw new RuntimeException("Direccion no encontrada");
        }
    }
}
