package com.notificationapi.notificationapi.domain;

import com.notificationapi.notificationapi.crossCutting.UtilDate;
import com.notificationapi.notificationapi.crossCutting.UtilDefaultValue;
import com.notificationapi.notificationapi.crossCutting.UtilText;
import com.notificationapi.notificationapi.crossCutting.UtilUUID;

import java.util.Date;
import java.util.UUID;

public class NotificacionDomain {
    private UUID identificador;
    private UsuarioDomain autor;
    private String titulo;
    private String contenido;
    private Date fechaCreacion;
    private String estado;
    private Date fechaProgramada;
    private String tipoEntrega;


    public NotificacionDomain() {
        setIdentificador(UtilUUID.getUuidDefaultValue());
        setAutor(new UsuarioDomain());
        setTitulo(UtilText.getDefaultTextValue());
        setContenido(UtilText.getDefaultTextValue());
        setFechaCreacion(UtilDate.getDefaultValueDate());
        setEstado(UtilText.getDefaultTextValue());
        setFechaProgramada(UtilDate.getDefaultValueDate());
        setTipoEntrega(UtilText.getDefaultTextValue());
    }

    public UUID getIdentificador() {
        return identificador;
    }

    public void setIdentificador(UUID identificador) {
        this.identificador = (UUID) UtilDefaultValue.defaultValue(identificador,UtilUUID.getUuidDefaultValue());
    }

    public UsuarioDomain getAutor() {
        return autor;
    }

    public void setAutor(UsuarioDomain autor) {
        this.autor = (UsuarioDomain) UtilDefaultValue.defaultValue(autor,new UsuarioDomain());
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
