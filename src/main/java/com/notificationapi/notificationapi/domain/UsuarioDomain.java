package com.notificationapi.notificationapi.domain;

import com.notificationapi.notificationapi.crossCutting.utils.UtilDefaultObject;
import com.notificationapi.notificationapi.crossCutting.utils.UtilEmail;
import com.notificationapi.notificationapi.crossCutting.utils.UtilText;
import com.notificationapi.notificationapi.crossCutting.utils.UtilUUID;

import java.util.UUID;

public class UsuarioDomain {

    private UUID identificador;
    private String correoElectronico;
    private String contraseña;
    private Rol rol;

    public UsuarioDomain(UUID identificador, String correoElectronico, String contraseña, Rol rol) {
        this.identificador = identificador;
        this.correoElectronico = correoElectronico;
        this.contraseña = contraseña;
        this.rol = rol;
    }

    public UsuarioDomain() {
        setIdentificador(UtilUUID.getUuidDefaultValue());
        setCorreoElectronico(UtilEmail.getDefaultValueMail());
        setContraseña(UtilText.getDefaultTextValue());
    }

    public UUID getIdentificador() {
        return identificador;
    }

    public void setIdentificador(UUID identificador) {
        this.identificador = (UUID) UtilDefaultObject.defaultValue(identificador, UtilUUID.getUuidDefaultValue());
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public UsuarioDomain setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = (String) UtilDefaultObject.defaultValue(correoElectronico, UtilEmail.getDefaultValueMail());
        return null;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = (String) UtilDefaultObject.defaultValue(contraseña, UtilText.getDefaultTextValue());
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }
}
