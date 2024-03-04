package com.notificationapi.notificationapi.service;

import com.notificationapi.notificationapi.domain.BuzonNotificacionDomain;
import com.notificationapi.notificationapi.domain.NotificacionDomain;
import com.notificationapi.notificationapi.domain.UsuarioDomain;
import com.notificationapi.notificationapi.entity.BuzonNotificacionEntity;
import com.notificationapi.notificationapi.entity.NotificacionEntity;
import com.notificationapi.notificationapi.entity.UsuarioEntity;
import com.notificationapi.notificationapi.repository.BuzonNotificacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class BuzonNotificacionService {

    @Autowired
    private BuzonNotificacionRepository buzonNotificacionRepository;

    public BuzonNotificacionDomain getDomain(BuzonNotificacionEntity entity){
        var propietario = new UsuarioDomain(entity.getPropietario().getIdentificador(), entity.getPropietario().getCorreoElectronico(), entity.getPropietario().getContraseña());
        return new BuzonNotificacionDomain(entity.getIdentificador(), propietario, entity.getNombre(), getNotificaciones(entity.getNotificaciones()));
    }

    private List<NotificacionDomain> getNotificaciones(List<NotificacionEntity> notificaciones){
        return notificaciones.stream().map(new BuzonNotificacionService()::getNotificacion).toList();
    }

    private NotificacionDomain getNotificacion(NotificacionEntity notificacion){
        var usuario = new UsuarioDomain(notificacion.getAutor().getIdentificador(), notificacion.getAutor().getCorreoElectronico(), notificacion.getAutor().getContraseña());
        return new NotificacionDomain(notificacion.getIdentificador(), usuario, notificacion.getTitulo(), notificacion.getContenido(), notificacion.getFechaCreacion(), notificacion.getEstado(), notificacion.getFechaProgramada(), notificacion.getTipoEntrega());
    }

    public List<BuzonNotificacionDomain> findAll(){

        return buzonNotificacionRepository.findAll().stream().map(new BuzonNotificacionService()::getDomain).toList();
    }

    public BuzonNotificacionDomain findById(UUID identificador){
        var entity = buzonNotificacionRepository.findById(identificador).orElse(null);
        var propietario = new UsuarioDomain(entity.getPropietario().getIdentificador(), entity.getPropietario().getCorreoElectronico(), entity.getPropietario().getContraseña());
        assert entity != null;
        return new BuzonNotificacionDomain(entity.getIdentificador(), propietario, entity.getNombre(), getNotificaciones(entity.getNotificaciones()));
    }

    public UUID saveBuzonNotificacion(BuzonNotificacionDomain buzonNotificacion){
        var propietario = new UsuarioEntity(buzonNotificacion.getPropietario().getIdentificador(), buzonNotificacion.getPropietario().getCorreoElectronico(), buzonNotificacion.getPropietario().getContraseña());
        var entity = new BuzonNotificacionEntity(buzonNotificacion.getIdentificador(), propietario, buzonNotificacion.getNombre(), getNotificacionesEntity(buzonNotificacion.getNotificaciones()));
        return buzonNotificacionRepository.save(entity).getIdentificador();
    }

    private List<NotificacionEntity> getNotificacionesEntity(List<NotificacionDomain> notificaciones){
        return notificaciones.stream().map(new BuzonNotificacionService()::getNotificacionEntity).toList();
    }

    private NotificacionEntity getNotificacionEntity(NotificacionDomain notificacion){
        var usuario = new UsuarioEntity(notificacion.getAutor().getIdentificador(), notificacion.getAutor().getCorreoElectronico(), notificacion.getAutor().getContraseña());
        return new NotificacionEntity(notificacion.getIdentificador(), usuario, notificacion.getTitulo(), notificacion.getContenido(), notificacion.getFechaCreacion(), notificacion.getEstado(), notificacion.getFechaProgramada(), notificacion.getTipoEntrega());
    }


}
