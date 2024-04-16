package com.notificationapi.notificationapi.repository;

import com.notificationapi.notificationapi.entity.UsuarioEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, UUID> {

    Optional<UsuarioEntity> findByCorreo(String correo);

    @Query(value = "SELECT * FROM usuario WHERE correo_electronico =?1",nativeQuery = true)
    UsuarioEntity findByCorreoElectronico(String correoElectronico);

    @Modifying
    @Transactional
    @Query(value = "UPDATE public.usuario SET contrasena =?1 WHERE correo_electronico =?2",nativeQuery = true)
    void updateByCorreoElectronico(String contraseña,String correoElectronico);
}
