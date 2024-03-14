package com.notificationapi.notificationapi.repository;

import com.notificationapi.notificationapi.entity.NotificacionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;


public interface NotificacionRepository extends JpaRepository<NotificacionEntity, UUID>{

}
