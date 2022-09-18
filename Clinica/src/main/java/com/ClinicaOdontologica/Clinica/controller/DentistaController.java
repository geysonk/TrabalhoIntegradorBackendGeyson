package com.ClinicaOdontologica.Clinica.controller;

import com.ClinicaOdontologica.Clinica.entity.DentistaEntity;
import com.ClinicaOdontologica.Clinica.service.DentistaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping(value = "/dentista")
public class DentistaController {

    @Autowired
    DentistaService service;

    @PostMapping("/salvar")
    public DentistaEntity salvar(@RequestBody DentistaEntity dentistaEntity) {
        return service.salvar(dentistaEntity);
    }

    @GetMapping
    public List<DentistaEntity> buscarTodos() throws SQLException {
        return service.buscarTodos();
    }

    @GetMapping("/{id}")
    public DentistaEntity buscarPorId(@PathVariable ("id") int id) throws SQLException {
        DentistaEntity dentista = service.buscarPorId(id).orElseThrow(EntityNotFoundException::new);
        return dentista;
    }

    @GetMapping("/nome")
    public DentistaEntity buscarPorNome(@RequestParam("nome") String nome) throws SQLException {
        return service.buscarPorNome(nome);
    }

    @PatchMapping
    public ResponseEntity<DentistaEntity> alterar(@RequestBody DentistaEntity dentistaEntity) throws SQLException {
        ResponseEntity responseEntity = null;

        if(service.buscarPorId(dentistaEntity.getId()) == null){
            responseEntity = new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return responseEntity;
    }

    @DeleteMapping("/dentista/delete/{id}")
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

