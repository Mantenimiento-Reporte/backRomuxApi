package com.notificationapi.notificationapi.domain;

import com.notificationapi.notificationapi.crossCutting.UtilDefaultValue;
import com.notificationapi.notificationapi.crossCutting.UtilText;
import com.notificationapi.notificationapi.crossCutting.UtilUUID;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class BuzonNotificacionDomain {

    private UUID identificador;
    private UsuarioDomain propietario;
    private String nombre;
    private List<NotificacionDomain> notificaciones;

    public BuzonNotificacionDomain() {
        setIdentificador(UtilUUID.getUuidDefaultValue());
        setPropietario(new UsuarioDomain());
        setNombre(UtilText.getDefaultTextValue());
        setNotificaciones(new ArrayList<>());
    }

    public BuzonNotificacionDomain(UUID identificador, UsuarioDomain propietario, String nombre, List<NotificacionDomain> notificaciones) {
        this.identificador = identificador;
        this.propietario = propietario;
        this.nombre = nombre;
        this.notificaciones = notificaciones;
    }

    public UUID getIdentificador() {
        return identificador;
    }

    public void setIdentificador(UUID identificador) {

        this.identificador = (UUID) UtilDefaultValue.defaultValue(identificador, UtilUUID.getUuidDefaultValue());
    }

    public UsuarioDomain getPropietario() {
        return propietario;
    }

    public void setPropietario(UsuarioDomain propietario) {
        this.propietario = (UsuarioDomain) UtilDefaultValue.defaultValue(propietario,new UsuarioDomain());
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {

        this.nombre = (String) UtilDefaultValue.defaultValue(nombre,"");
    }

    public List<NotificacionDomain> getNotificaciones() {
        return notificaciones;
    }

    public void setNotificaciones(List<NotificacionDomain> notificaciones) {
        this.notificaciones = notificaciones;
    }
}
