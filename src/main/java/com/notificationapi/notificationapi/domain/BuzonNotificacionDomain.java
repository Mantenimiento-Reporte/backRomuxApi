package com.notificationapi.notificationapi.domain;

import com.notificationapi.notificationapi.crossCutting.utils.UtilDefaultObject;
import com.notificationapi.notificationapi.crossCutting.utils.UtilText;
import com.notificationapi.notificationapi.crossCutting.utils.UtilUUID;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class BuzonNotificacionDomain {

    private UUID identificador;
    private PersonaDomain propietario;
    private String nombre;
    private List<NotificacionDomain> notificaciones;

    public BuzonNotificacionDomain() {
        setIdentificador(UtilUUID.getUuidDefaultValue());
        setPropietario(new PersonaDomain());
        setNombre(UtilText.getDefaultTextValue());
        setNotificaciones(new ArrayList<>());
    }

    public BuzonNotificacionDomain(UUID identificador, PersonaDomain propietario, String nombre, List<NotificacionDomain> notificaciones) {
        this.identificador = identificador;
        this.propietario = propietario;
        this.nombre = nombre;
        this.notificaciones = notificaciones;
    }

    public UUID getIdentificador() {
        return identificador;
    }

    public void setIdentificador(UUID identificador) {

        this.identificador = (UUID) UtilDefaultObject.defaultValue(identificador, UtilUUID.getUuidDefaultValue());
    }

    public PersonaDomain getPropietario() {
        return propietario;
    }

    public void setPropietario(PersonaDomain propietario) {
        this.propietario = (PersonaDomain) UtilDefaultObject.defaultValue(propietario,new UsuarioDomain());
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {

        this.nombre = (String) UtilDefaultObject.defaultValue(nombre,UtilText.getDefaultTextValue());
    }

    public List<NotificacionDomain> getNotificaciones() {
        return notificaciones;
    }

    public void setNotificaciones(List<NotificacionDomain> notificaciones) {
        this.notificaciones = notificaciones;
    }
}
