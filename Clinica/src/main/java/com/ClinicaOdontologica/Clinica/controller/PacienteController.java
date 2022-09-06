package com.ClinicaOdontologica.Clinica.controller;

import com.ClinicaOdontologica.Clinica.entity.PacienteEntity;
import com.ClinicaOdontologica.Clinica.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/paciente")


public class PacienteController {

    @Autowired
    PacienteService service;

    @PostMapping
    public PacienteEntity salvar(@RequestBody PacienteEntity pacienteEntity) throws SQLException {
        return service.salvar(pacienteEntity);
    }

    @GetMapping
    public List<PacienteEntity> buscarTodos() throws SQLException {
        return service.buscarTodos();
    }

    @RequestMapping
    public PacienteEntity buscarPorId(@RequestParam ("id") int id) throws SQLException {
        return service.buscarPorId(id).isEmpty()? new PacienteEntity() : service.buscarPorId(id).get();
    }

    @PatchMapping
    public ResponseEntity<PacienteEntity> alterar(@RequestBody PacienteEntity pacienteEntity) throws SQLException {
        ResponseEntity responseEntity = null;

        if(service.buscarPorId(pacienteEntity.getId()) == null){
            responseEntity = new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return responseEntity;
    }

    @DeleteMapping
    public ResponseEntity excluir(@PathVariable Integer id) throws SQLException {
        ResponseEntity responseEntity = null;

        if(service.buscarPorId(id) == null){
            responseEntity = new ResponseEntity(HttpStatus.NOT_FOUND);
        }else{
            responseEntity = new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return responseEntity;
    }
}

