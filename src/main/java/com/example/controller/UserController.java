package com.example.controller;

import com.example.controller.request.CreateUserDTO;
import com.example.models.ERole;
import com.example.models.RoleEntity;
import com.example.models.UserEntity;
import com.example.repositories.RoleRepository;
import com.example.repositories.UserRepository;
import com.example.service.Impl.UserService;
import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin(origins = "**")
@RestController
@RequestMapping("/api")
public class UserController {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository ;
    private final PasswordEncoder passwordEncoder;

    private final UserService userService;

    public UserController(PasswordEncoder passwordEncoder, UserRepository userRepository, RoleRepository roleRepository, UserService userService) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.userService = userService;
    }
    //obtener usuario por id
    @GetMapping("/getUser")
    public ResponseEntity<CreateUserDTO> getUser(@RequestParam Long id){
        UserEntity userEntity = userRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuario no Encontrado"));
        CreateUserDTO createUserDTO = CreateUserDTO.builder()
                .email(userEntity.getEmail())
                .username(userEntity.getUsername())
                .password(userEntity.getPassword())
                .phone(userEntity.getPhone())
                .documento(userEntity.getDocumento())
                .roles(userEntity.getRoles().stream()
                        .map(role -> role.getName().name())
                        .collect(Collectors.toSet()))
                .build();
        return ResponseEntity.ok(createUserDTO);
    }
    //Crear usuario
    @PostMapping("/createUser")
    public ResponseEntity<?> createUser(@Valid @RequestBody CreateUserDTO createUserDTO) {
        Set<RoleEntity> roles = new HashSet<>();

        for (String roleName : createUserDTO.getRoles()) {
            ERole roleEnum = ERole.valueOf(roleName);
            RoleEntity roleEntity = roleRepository.findByName(roleEnum).orElseGet(() -> {
                RoleEntity newRole = RoleEntity.builder().name(roleEnum).build();
                return roleRepository.save(newRole);
            });
            roles.add(roleEntity);
        }

        UserEntity userEntity = UserEntity.builder()
                .username(createUserDTO.getUsername())
                .password(passwordEncoder.encode(createUserDTO.getPassword()))
                .email(createUserDTO.getEmail())
                .roles(roles)
                .phone(createUserDTO.getPhone())
                .documento(createUserDTO.getDocumento())
                .build();
        userRepository.save(userEntity);
        return ResponseEntity.ok(createUserDTO);
    }
    //eliminar usuario por id
    @DeleteMapping("/deleteUser")
    public String deleteUser(@RequestParam Long id){
        userRepository.deleteById(id);
        return "Se ha borrado el user con id: "+id;
    }

    @GetMapping("/get/{username}")
    public ResponseEntity<UserEntity> get(@PathVariable("username") String username){
        Optional<UserEntity> userEntity = userRepository.findByUsername(username);
    return userEntity.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }



}
