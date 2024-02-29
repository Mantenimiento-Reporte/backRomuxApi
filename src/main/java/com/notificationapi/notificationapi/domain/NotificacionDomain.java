package com.notificationapi.notificationapi.domain;

import com.notificationapi.notificationapi.crossCutting.UtilDate;
import com.notificationapi.notificationapi.crossCutting.UtilDefaultValue;
import com.notificationapi.notificationapi.crossCutting.UtilUUID;

import java.util.Date;
import java.util.UUID;

public class NotificacionDomain {
    private UUID identificador;
    private PersonaDomain autor;
    private String titulo;
    private String contenido;
    private Date fechaCreacion;
    private String estado;
    private Date fechaProgramada;
    private String tipoEntrega;


    public NotificacionDomain() {

    }

    public NotificacionDomain(UUID identificador, PersonaDomain autor, String titulo, String contenido, Date fechaCreacion, String estado, Date fechaProgramada, String tipoEntrega) {
        this.identificador = identificador;
        this.autor = autor;
        this.titulo = titulo;
        this.contenido = contenido;
        this.fechaCreacion = fechaCreacion;
        this.estado = estado;
        this.fechaProgramada = fechaProgramada;
        this.tipoEntrega = tipoEntrega;
    }

    public UUID getIdentificador() {
        return identificador;
    }

    public void setIdentificador(UUID identificador) {
        this.identificador = (UUID) UtilDefaultValue.defaultValue(identificador,UtilUUID.getUuidDefaultValue());
    }

    public PersonaDomain getAutor() {
        return autor;
    }

    public void setAutor(UsuarioDomain autor) {
        this.autor = (PersonaDomain) UtilDefaultValue.defaultValue(autor,new UsuarioDomain());
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {

        this.titulo = (String) UtilDefaultValue.defaultValue(titulo,"");
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = (String) UtilDefaultValue.defaultValue(contenido,"");
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = (Date) UtilDefaultValue.defaultValue(fechaCreacion,UtilDate.getDefaultValueDate());
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = (String) UtilDefaultValue.defaultValue(estado,"");
    }

    public Date getFechaProgramada() {
        return fechaProgramada;
    }

    public void setFechaProgramada(Date fechaProgramada) {
        this.fechaProgramada = (Date) UtilDefaultValue.defaultValue(fechaProgramada, UtilDate.getDefaultValueDate());
    }

    public String getTipoEntrega() {
        return tipoEntrega;
    }

    public void setTipoEntrega(String tipoEntrega) {

        this.tipoEntrega = (String) UtilDefaultValue.defaultValue(tipoEntrega,"");
    }
}
