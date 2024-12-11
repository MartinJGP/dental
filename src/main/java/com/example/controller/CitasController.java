package com.example.controller;

import com.example.models.Citas;
import com.example.service.CitasService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "**")
@RestController
@RequestMapping("/api/citas/")
public class CitasController {
    private final CitasService citasService;
    public CitasController(CitasService citasService) {
        this.citasService = citasService;
    }

    @GetMapping("/getall")
    public ResponseEntity<List<Citas>> getAll(){
        return ResponseEntity.ok(citasService.getAll());
    }
    @GetMapping("/getbyuser/{id}")
    public ResponseEntity<List<Citas>> getAllByUser(@PathVariable("id") Long id){
        return ResponseEntity.ok(citasService.getAllByUser(id));
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Citas> get(@PathVariable("id") Long id){
        return ResponseEntity.ok(citasService.get(id));
    }
    @PostMapping("/create")
    public ResponseEntity<Citas> create(@RequestBody Citas citas){
        Citas cita = citasService.create(citas);
        return new ResponseEntity<>(cita, HttpStatus.CREATED);
    }
    @PutMapping("/update")
    public ResponseEntity<Citas> update(@RequestBody Citas citas){
        Citas cita = citasService.update(citas);
        return new ResponseEntity<>(cita, HttpStatus.CREATED);
    }
    @PutMapping("/cancelar/{id}")
    public ResponseEntity<?> cancelar(@PathVariable("id") Long id){
        citasService.Cancelar(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
