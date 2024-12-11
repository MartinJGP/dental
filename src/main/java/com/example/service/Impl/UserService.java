package com.example.service.Impl;

import com.example.repositories.UserRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public void updatePassword(String password, Long id) {
        log.info("Actualizando contraseña para el usuario con Id: {}", id);
        userRepository.updatePassword(password, id);
        log.info("Contraseña actualizada exitosamente para el usuario con Id: {}", id);
    }
    @Transactional
    public void updateUser(String email, String username, String phone, String documento, Long id) {
        log.info("Actualizando datos para el usuario con Id: {}", id);
        userRepository.updateUser(email, username, phone, documento, id);
        log.info("Usuario actualizado exitosamente con ID: {}", id);
    }
}
