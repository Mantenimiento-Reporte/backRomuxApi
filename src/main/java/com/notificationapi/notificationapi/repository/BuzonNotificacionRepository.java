package com.notificationapi.notificationapi.repository;

import com.notificationapi.notificationapi.entity.BuzonNotificacionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;


public interface BuzonNotificacionRepository extends JpaRepository<BuzonNotificacionEntity, UUID> {
}
