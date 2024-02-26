package com.notificationapi.notificationapi.domain;

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
        this.identificador = identificador;
    }

    public PersonaDomain getAutor() {
        return autor;
    }

    public void setAutor(PersonaDomain autor) {
        this.autor = autor;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Date getFechaProgramada() {
        return fechaProgramada;
    }

    public void setFechaProgramada(Date fechaProgramada) {
        this.fechaProgramada = fechaProgramada;
    }

    public String getTipoEntrega() {
        return tipoEntrega;
    }

    public void setTipoEntrega(String tipoEntrega) {
        this.tipoEntrega = tipoEntrega;
    }
}
