package com.notificationapi.notificationapi.repository;

import com.notificationapi.notificationapi.entity.PersonaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

public interface PersonaRepository extends JpaRepository<PersonaEntity, UUID> {
    @Query(value = "SELECT * FROM persona WHERE correo_electronico = ?1",nativeQuery = true)
    PersonaEntity findBycorreoElectronico(String correoElectronico);
}
