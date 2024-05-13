package com.notificationapi.notificationapi.MessengerService.buzonNotificacion;

import com.notificationapi.notificationapi.crossCutting.utils.gson.MapperJsonObjeto;
import com.notificationapi.notificationapi.domain.BuzonNotificacionDomain;
import com.notificationapi.notificationapi.service.BuzonNotificacionService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ReciverMessageBuzonNotificacion {

    private final BuzonNotificacionService buzonNotificacionService;
    private final MapperJsonObjeto mapperJsonObjeto;

    @Autowired
    public ReciverMessageBuzonNotificacion(BuzonNotificacionService buzonNotificacionService, MapperJsonObjeto mapperJsonObjeto) {
        this.buzonNotificacionService = buzonNotificacionService;
        this.mapperJsonObjeto = mapperJsonObjeto;
    }


    @RabbitListener(queues = "cola.buzon.lista")
    public  void receiveMessageConsultaBuzon(String message) {
        var mensajeRecibido = obtenerObjetoDeMensaje(message).get();
        try {
            buzonNotificacionService.listaRecibida(mensajeRecibido);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    private Optional<List> obtenerObjetoDeMensaje(String mensaje) {
        return mapperJsonObjeto.ejecutar(mensaje, List.class);
    }
}
