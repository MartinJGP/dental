package com.example.repositories;

import com.example.models.UserEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long> {

    Optional<UserEntity> findByUsername(String username);

    @Query("select u from UserEntity u where u.username = ?1")
    Optional<UserEntity> getName(String username);

    //actualizar contraseña
    @Modifying
    @Query("update UserEntity u set u.password = ?1 where u.id = ?2")
    void updatePassword(String password, Long id);

    //actualizar todo menos contraseña y rol
    @Modifying
    @Query("update UserEntity u set u.email = ?1, u.username = ?2, u.phone = ?3, u.documento = ?4 where u.id = ?5")
    void updateUser(String email, String username, String phone, String documento, Long id);
}
