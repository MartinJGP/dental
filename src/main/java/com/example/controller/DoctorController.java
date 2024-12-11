package com.example.controller;

import com.example.models.Doctor;
import com.example.service.DoctorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "**")
@RestController
@RequestMapping("/api/doctor/")
public class DoctorController {
    private final DoctorService doctorService;

    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }
    @GetMapping("/getall")
    public ResponseEntity<List<Doctor>> getAll(){
        return ResponseEntity.ok(doctorService.getAll());
    }
    @GetMapping("/get/{id}")
    public ResponseEntity<Doctor> get(@PathVariable("id") Long id){
        return ResponseEntity.ok(doctorService.get(id));
    }
    @PostMapping("/create")
    public ResponseEntity<Doctor> create(@RequestBody Doctor doctor){
        Doctor doc = doctorService.create(doctor);
        return new ResponseEntity<>(doc, HttpStatus.CREATED);
    }
    @PutMapping("/update")
    public ResponseEntity<Doctor> update(@RequestBody Doctor doctor){
        Doctor doc = doctorService.update(doctor);
        return new ResponseEntity<>(doc, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
        doctorService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }



}
