package com.notificationapi.notificationapi.service;

import com.notificationapi.notificationapi.domain.NotificacionDomain;
import com.notificationapi.notificationapi.domain.UsuarioDomain;
import com.notificationapi.notificationapi.entity.NotificacionEntity;
import com.notificationapi.notificationapi.entity.UsuarioEntity;
import com.notificationapi.notificationapi.repository.NotificacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class NotificacionService {

    @Autowired
    private NotificacionRepository notificacionRepository;

    public List<NotificacionDomain> findAll(){
        return notificacionRepository.findAll().stream().map(new NotificacionService()::getDomain).toList();
    }

    public NotificacionDomain getDomain(NotificacionEntity entity){
        var autor = new UsuarioDomain(entity.getAutor().getIdentificador(), entity.getAutor().getCorreoElectronico(), entity.getAutor().getContraseña());
        return new NotificacionDomain(entity.getIdentificador(), autor, entity.getTitulo(), entity.getContenido(), entity.getFechaCreacion(), entity.getEstado(), entity.getFechaProgramada(), entity.getTipoEntrega());
    }

    public NotificacionDomain findById(UUID identificador){
        var entity = notificacionRepository.findById(identificador).orElse(null);
        assert entity != null;
        return getDomain(entity);
    }

    public UUID saveNotificacion(NotificacionDomain notificacion){
        var autor = new UsuarioEntity(notificacion.getAutor().getIdentificador(), notificacion.getAutor().getCorreoElectronico(), notificacion.getAutor().getContraseña());
        var entity = new NotificacionEntity(notificacion.getIdentificador(), autor, notificacion.getTitulo(), notificacion.getContenido(), notificacion.getFechaCreacion(), notificacion.getEstado(), notificacion.getFechaProgramada(), notificacion.getTipoEntrega());
        return notificacionRepository.save(entity).getIdentificador();
    }

    public void deleteNotificacion(UUID identificador){
        notificacionRepository.deleteById(identificador);
    }

}
