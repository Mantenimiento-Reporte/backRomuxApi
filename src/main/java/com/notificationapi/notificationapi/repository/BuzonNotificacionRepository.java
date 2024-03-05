package com.notificationapi.notificationapi.repository;

import com.notificationapi.notificationapi.domain.BuzonNotificacionDomain;
import com.notificationapi.notificationapi.entity.BuzonNotificacionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;


public interface BuzonNotificacionRepository extends JpaRepository<BuzonNotificacionEntity, UUID> {
}
