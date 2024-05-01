package com.notificationapi.notificationapi.config.buzonNotificacionQueueConfig;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
@Setter
@ConfigurationProperties(prefix = "buzon.notificacion.eliminar.rabbitmq")
public class BuzonNotificacionQueueConfigEliminar extends BuzonNotificacionQueueConfig{

}