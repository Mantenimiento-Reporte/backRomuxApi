package com.notificationapi.notificationapi.service;

import com.notificationapi.notificationapi.MessengerService.notificacion.MessageSenderNotificacion;
import com.notificationapi.notificationapi.domain.NotificacionDomain;
import com.notificationapi.notificationapi.domain.PersonaDomain;
import com.notificationapi.notificationapi.entity.NotificacionEntity;
import com.notificationapi.notificationapi.entity.PersonaEntity;
import com.notificationapi.notificationapi.repository.NotificacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class NotificacionService {

    @Autowired
    private NotificacionRepository notificacionRepository;

    @Autowired
    private MessageSenderNotificacion messageSenderNotificacion;

    public List<NotificacionDomain> findAll(){
        return notificacionRepository.findAll().stream().map(new NotificacionService()::toDomain).toList();
    }

    public List<NotificacionDomain> getNotificacionesPorDestinatario(String correo){
        var entities = notificacionRepository.findAll();
        var notificaciones = entities.stream().filter(notificacion->notificacion.getDestinatario().stream().anyMatch(destinatario->destinatario.getCorreoElectronico().equals(correo))).toList();
        return notificaciones.stream().map(new NotificacionService()::toDomain).toList();
    }

    private NotificacionDomain toDomain(NotificacionEntity entity){
        var autor = new PersonaDomain(entity.getAutor().getIdentificador(), entity.getAutor().getPrimerNombre(), entity.getAutor().getSegundoNombre(), entity.getAutor().getPrimerApellido(), entity.getAutor().getSegundoApellido(), entity.getAutor().getCorreoElectronico());
        return new NotificacionDomain(entity.getIdentificador(), autor, entity.getTitulo(), entity.getContenido(), entity.getFechaCreacion(), entity.getEstado(), entity.getFechaProgramada(), entity.getTipoEntrega(), entity.getDestinatario().stream().map(new NotificacionService()::personaToDomain).toList());
    }

    private PersonaDomain personaToDomain(PersonaEntity entity){
        return new PersonaDomain(entity.getIdentificador(), entity.getPrimerNombre(), entity.getSegundoNombre(), entity.getPrimerApellido(), entity.getSegundoApellido(), entity.getCorreoElectronico());
    }

    private PersonaEntity personaToEntity(PersonaDomain domain){
        return new PersonaEntity(domain.getIdentificador(), domain.getPrimerNombre(), domain.getSegundoNombre(), domain.getPrimerApellido(), domain.getSegundoApellido(), domain.getCorreoElectronico());
    }

    public NotificacionDomain findById(UUID identificador){
        var entity = notificacionRepository.findById(identificador).orElse(null);
        assert entity != null;
        return toDomain(entity);
    }

    public void saveNotificacion(NotificacionDomain notificacion){
        messageSenderNotificacion.execute(notificacion,"");
    }

    public void deleteNotificacion(UUID identificador){
        notificacionRepository.deleteById(identificador);
    }

}
