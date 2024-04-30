package com.notificationapi.notificationapi.MessengerService.buzonNotificacion;

import com.notificationapi.notificationapi.crossCutting.utils.gson.MapperJsonObjeto;
import com.notificationapi.notificationapi.domain.BuzonNotificacionDomain;
import com.notificationapi.notificationapi.domain.NotificacionDomain;
import com.notificationapi.notificationapi.service.BuzonNotificacionService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ReciverMessageBuzonNotificacion {

    private final BuzonNotificacionService buzonNotificacionService = new BuzonNotificacionService();

    @Autowired
    private final MapperJsonObjeto mapperJsonObjeto;

    public ReciverMessageBuzonNotificacion(MapperJsonObjeto mapperJsonObjeto) {
        this.mapperJsonObjeto = mapperJsonObjeto;
    }

    //@RabbitListener(queues = "")
    public void receiveMessageProcessClient(String message) {
        try {

        } catch (Exception e) {
            System.out.println(e);
        }
    }
    private Optional<BuzonNotificacionDomain> obtenerObjetoDeMensaje(String mensaje) {
        return mapperJsonObjeto.ejecutar(mensaje, BuzonNotificacionDomain.class);
    }
}
