package com.notificationapi.notificationapi.entity;

import jakarta.persistence.*;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.UUID;

@Entity
@Table(name = "usuario")
public class UsuarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "identificador")
    private UUID identificador;


    @NotNull
    @Column(name = "correoElectronico",length = 50)
    private String correoElectronico;

    @NotNull
    @Column(name = "contrasena", length = 30)
    private String contraseña;

    public UsuarioEntity(UUID identificador, String correoElectronico, String contraseña) {
        this.identificador = identificador;
        this.correoElectronico = correoElectronico;
        this.contraseña = contraseña;
    }

    public UsuarioEntity() {
    }

    public UUID getIdentificador() {
        return identificador;
    }

    public void setIdentificador(UUID identificador) {
        this.identificador = identificador;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }
}
