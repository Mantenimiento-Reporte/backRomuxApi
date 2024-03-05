package com.notificationapi.notificationapi.repository;

import com.notificationapi.notificationapi.entity.PersonaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

public interface PersonaRepository extends JpaRepository<PersonaEntity, UUID> {
}
