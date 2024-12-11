package com.example.controller;


import com.example.models.UserEntity;
import com.example.repositories.UserRepository;
import com.example.service.Impl.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.stream.Collectors;
@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "**")
@Slf4j
public class PrincipalController {
    BCryptPasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
    private final UserRepository userRepository;
    private final UserService userService;

    public PrincipalController(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    //verificar si una contraseña es igual a la encriptada, luego la nueva contraseña se encripte y guarde, que pida "password"y "newPassword"
    @PutMapping("/changePassword")
    public ResponseEntity<?> changePassword(@RequestParam("password") String password, @RequestParam("newpassword") String newPassword, @RequestParam("id") Long id){
        log.info("Solicitu de cambio de contraseña para el usurio con Id: {}", id);
        UserEntity user=userRepository.findById(id).orElseThrow(() -> {
            log.error("Usuario no encontrado con Id: Id", id);
            return new RuntimeException("Usuario no Encontrado");
        });
        if(passwordEncoder.matches(password, user.getPassword())){
            String newPasswordEncripted = passwordEncoder.encode(newPassword);
            userService.updatePassword(newPasswordEncripted, id);
            log.info("Contraseña actualizada para el usuario con Id: {}", id);
            return ResponseEntity.ok().build();
        }
        log.warn("Contraseña incorrecta para el usuario con Id: {}", id);
        return ResponseEntity.badRequest().build();
    }

    //actualizar todo menos contraseña y rol
    @PutMapping("/updateUser")
    public ResponseEntity<?> updateUser(@RequestBody UserEntity user){
        log.info("Solicitud de actualización de usuario con Id: {}", user.getId());
        userService.updateUser(user.getEmail(), user.getUsername(), user.getPhone(), user.getDocumento(), user.getId());
        log.info("Usuario actualizado con Id: {}", user.getId());
        return ResponseEntity.ok().build();
    }

}
