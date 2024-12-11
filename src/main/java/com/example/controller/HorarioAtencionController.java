package com.example.controller;

import com.example.service.HorarioAtencionService;
import com.example.models.HorarioAtencion;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "**")
@RestController
@RequestMapping("/api/horario/")
public class HorarioAtencionController {
    private final HorarioAtencionService horarioAtencionService;
    public HorarioAtencionController(HorarioAtencionService horarioAtencionService) {
        this.horarioAtencionService = horarioAtencionService;
    }

    @GetMapping("/getall")
    public ResponseEntity<List<HorarioAtencion>> getAll(){
        return new ResponseEntity<>(horarioAtencionService.getAll(), HttpStatus.OK);
    }
    @GetMapping("/get/{fecha}")
    public ResponseEntity<List<HorarioAtencion>> get(@PathVariable("fecha") String fecha){
        return new ResponseEntity<>(horarioAtencionService.getallFecha(fecha), HttpStatus.OK);
    }
    @GetMapping("/getid/{id}")
    public ResponseEntity<HorarioAtencion> getid(@PathVariable("id") Long id){
        return new ResponseEntity<>(horarioAtencionService.get(id), HttpStatus.OK);
    }
    @PostMapping("/create")
    public ResponseEntity<HorarioAtencion> create(@RequestBody HorarioAtencion horarioAtencion){
        HorarioAtencion horarioAtencion1 = horarioAtencionService.create(horarioAtencion);
        return new ResponseEntity<>(horarioAtencion1, HttpStatus.CREATED);
    }
    @PutMapping("/update")
    public ResponseEntity<HorarioAtencion> update(@RequestBody HorarioAtencion horarioAtencion){
        HorarioAtencion horarioAtencion1 = horarioAtencionService.update(horarioAtencion);
        return new ResponseEntity<>(horarioAtencion1, HttpStatus.CREATED);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
        horarioAtencionService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @DeleteMapping("/disable/{id}")
    public ResponseEntity<?> disable(@PathVariable("id") Long id){
        horarioAtencionService.disable(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @DeleteMapping("/enable/{id}")
    public ResponseEntity<?> enable(@PathVariable("id") Long id){
        horarioAtencionService.enable(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
