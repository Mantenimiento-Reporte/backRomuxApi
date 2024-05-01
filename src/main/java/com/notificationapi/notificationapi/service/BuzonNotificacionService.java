package com.notificationapi.notificationapi.service;


import com.notificationapi.notificationapi.MessengerService.buzonNotificacion.MessageSenderBuzonNotificacion;
import com.notificationapi.notificationapi.config.buzonNotificacionQueueConfig.BuzonNotificacionQueueConfigConsultar;
import com.notificationapi.notificationapi.config.buzonNotificacionQueueConfig.BuzonNotificacionQueueConfigCrear;
import com.notificationapi.notificationapi.config.buzonNotificacionQueueConfig.BuzonNotificacionQueueConfigEliminar;
import com.notificationapi.notificationapi.domain.BuzonNotificacionDomain;
import com.notificationapi.notificationapi.domain.NotificacionDomain;
import com.notificationapi.notificationapi.domain.PersonaDomain;
import com.notificationapi.notificationapi.entity.PersonaEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class BuzonNotificacionService {

    @Autowired
    private MessageSenderBuzonNotificacion messageSenderBuzonNotificacion;
    @Autowired
    private BuzonNotificacionQueueConfigCrear buzonNotificacionQueueConfigCrear;
    @Autowired
    private BuzonNotificacionQueueConfigConsultar buzonNotificacionQueueConfigConsultar;
    @Autowired
    private BuzonNotificacionQueueConfigEliminar buzonNotificacionQueueConfigEliminar;

    public List<BuzonNotificacionDomain> getBuzonNotificacionesPorPropietario(String correo){
        BuzonNotificacionDomain buzon = new BuzonNotificacionDomain();
        PersonaDomain propietario = new PersonaDomain();
        propietario.setCorreoElectronico(correo);
        buzon.setPropietario(propietario);
        messageSenderBuzonNotificacion.execute(buzon,"312",buzonNotificacionQueueConfigConsultar.getExchangeName(), buzonNotificacionQueueConfigConsultar.getRoutingKeyName());

        return null;
    }



    public List<BuzonNotificacionDomain> findAll(){
        BuzonNotificacionDomain buzon = new BuzonNotificacionDomain();
        messageSenderBuzonNotificacion.execute(buzon,"1323",buzonNotificacionQueueConfigConsultar.getExchangeName(), buzonNotificacionQueueConfigConsultar.getRoutingKeyName());
        return null;
    }

    public BuzonNotificacionDomain findById(UUID identificador){
        BuzonNotificacionDomain buzon = new BuzonNotificacionDomain();
        buzon.setIdentificador(identificador);
        messageSenderBuzonNotificacion.execute(buzon,"212",buzonNotificacionQueueConfigConsultar.getExchangeName(), buzonNotificacionQueueConfigConsultar.getRoutingKeyName());
        return null;
    }

    public void saveBuzonNotificacion(BuzonNotificacionDomain buzonNotificacion){
        messageSenderBuzonNotificacion.execute(buzonNotificacion,"4312",buzonNotificacionQueueConfigCrear.getExchangeName(),buzonNotificacionQueueConfigCrear.getRoutingKeyName());

    }


    private PersonaDomain personaToDomain(PersonaEntity entity){
        return new PersonaDomain(entity.getIdentificador(), entity.getPrimerNombre(), entity.getSegundoNombre(), entity.getPrimerApellido(), entity.getSegundoApellido(), entity.getCorreoElectronico());
    }

    private PersonaEntity personaToEntity(PersonaDomain domain){
        return new PersonaEntity(domain.getIdentificador(), domain.getPrimerNombre(), domain.getSegundoNombre(), domain.getPrimerApellido(), domain.getSegundoApellido(), domain.getCorreoElectronico());
    }

    public void eliminar(UUID identificador){
        BuzonNotificacionDomain buzon = new BuzonNotificacionDomain();
        buzon.setIdentificador(identificador);
        messageSenderBuzonNotificacion.execute(buzon,"312", buzonNotificacionQueueConfigEliminar.getExchangeName(), buzonNotificacionQueueConfigEliminar.getRoutingKeyName());
    }
}
