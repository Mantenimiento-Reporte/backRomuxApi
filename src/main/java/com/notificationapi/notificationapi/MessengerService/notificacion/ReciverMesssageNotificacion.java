package com.notificationapi.notificationapi.MessengerService.notificacion;


import com.notificationapi.notificationapi.crossCutting.utils.gson.MapperJsonObjeto;
import com.notificationapi.notificationapi.domain.NotificacionDomain;
import com.notificationapi.notificationapi.service.NotificacionService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ReciverMesssageNotificacion {

    private final NotificacionService notificacionService = new NotificacionService();

    @Autowired
    private final MapperJsonObjeto mapperJsonObjeto;

    public ReciverMesssageNotificacion(MapperJsonObjeto mapperJsonObjeto) {
        this.mapperJsonObjeto = mapperJsonObjeto;
    }


    //@RabbitListener(queues = "")
    public void receiveMessageProcessClient(String message) {
        try {
            notificacionService.saveNotificacion(obtenerObjetoDeMensaje(message).get());
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private Optional<NotificacionDomain> obtenerObjetoDeMensaje(String mensaje) {
        return mapperJsonObjeto.ejecutar(mensaje, NotificacionDomain.class);
    }
}
