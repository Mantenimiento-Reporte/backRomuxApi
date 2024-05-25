package com.notificationapi.notificationapi.service;


import com.notificationapi.notificationapi.MessengerService.buzonNotificacion.MessageSenderBuzonNotificacion;
import com.notificationapi.notificationapi.config.buzonNotificacionQueueConfig.BuzonNotificacionQueueConfigConsultar;
import com.notificationapi.notificationapi.config.buzonNotificacionQueueConfig.BuzonNotificacionQueueConfigCrear;
import com.notificationapi.notificationapi.config.buzonNotificacionQueueConfig.BuzonNotificacionQueueConfigEliminar;
import com.notificationapi.notificationapi.crossCutting.utils.UtilDefaultObject;
import com.notificationapi.notificationapi.crossCutting.utils.UtilText;
import com.notificationapi.notificationapi.crossCutting.utils.UtilUUID;
import com.notificationapi.notificationapi.domain.BuzonNotificacionDomain;
import com.notificationapi.notificationapi.domain.NotificacionDomain;
import com.notificationapi.notificationapi.domain.PersonaDomain;
import com.notificationapi.notificationapi.entity.PersonaEntity;
import org.springframework.amqp.AmqpException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;


@Service
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

    private List<BuzonNotificacionDomain> respuesta = new ArrayList<>();

    private String mensajeExcepcion;

    public void getBuzonNotificacionesPorPropietario(String correo){
        BuzonNotificacionDomain buzon = new BuzonNotificacionDomain();
        PersonaDomain propietario = new PersonaDomain();
        propietario.setCorreoElectronico(correo);
        buzon.setPropietario(propietario);
        messageSenderBuzonNotificacion.execute(buzon,"312",buzonNotificacionQueueConfigConsultar.getExchangeName(), buzonNotificacionQueueConfigConsultar.getRoutingKeyName());

    }



    public void findAll(){
        BuzonNotificacionDomain buzon = new BuzonNotificacionDomain();
        messageSenderBuzonNotificacion.execute(buzon,"1323",buzonNotificacionQueueConfigConsultar.getExchangeName(), buzonNotificacionQueueConfigConsultar.getRoutingKeyName());
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

    public void eliminar(BuzonNotificacionDomain buzonNotificacion){
        if(buzonNotificacion.getIdentificador().equals(UtilUUID.getUuidDefaultValue())){
            mensajeExcepcion = "Error, debe estar presente el identificador del buzon para realizar la operacion";
        }
        try{
        messageSenderBuzonNotificacion.execute(buzonNotificacion,"312", buzonNotificacionQueueConfigEliminar.getExchangeName(), buzonNotificacionQueueConfigEliminar.getRoutingKeyName());
    }catch (AmqpException e){
            throw e;
        }
    }

    public void eliminar(PersonaDomain persona){
        BuzonNotificacionDomain buzonNotificacion = new BuzonNotificacionDomain();
        buzonNotificacion.setPropietario(persona);
        try{
            messageSenderBuzonNotificacion.execute(buzonNotificacion,"312", buzonNotificacionQueueConfigEliminar.getExchangeName(), buzonNotificacionQueueConfigEliminar.getRoutingKeyName());
        }catch (AmqpException e){
            throw e;
        }
    }

    public void listaRecibida(List<BuzonNotificacionDomain> mensaje){
        respuesta.clear();
        respuesta.addAll(mensaje);
    }

    public void setMensajeExcepcion(String message){
        this.mensajeExcepcion = (String) UtilDefaultObject.defaultValue(message, UtilText.getDefaultTextValue());
    }
    public List<BuzonNotificacionDomain>  getRespuesta() {
        return respuesta;
    }
    public String getMensajeExcepcion(){
        return mensajeExcepcion;
    }

}
