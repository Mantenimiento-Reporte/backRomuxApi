package domain;

import java.util.List;
import java.util.UUID;

public class BuzonNotificacionDomain {
    private UUID identificador;
    private UsuarioDomain propietario;
    private String nombre;

    private List<NotificacionDomain> notificaciones;

    public BuzonNotificacionDomain() {
    }

    public UUID getIdentificador() {
        return identificador;
    }

    public void setIdentificador(UUID identificador) {
        this.identificador = identificador;
    }

    public UsuarioDomain getPropietario() {
        return propietario;
    }

    public void setPropietario(UsuarioDomain propietario) {
        this.propietario = propietario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<NotificacionDomain> getNotificaciones() {
        return notificaciones;
    }

    public void setNotificaciones(List<NotificacionDomain> notificaciones) {
        this.notificaciones = notificaciones;
    }
}
