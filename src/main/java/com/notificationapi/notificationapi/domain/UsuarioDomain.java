package com.notificationapi.notificationapi.domain;

import com.notificationapi.notificationapi.crossCutting.UtilDefaultObject;
import com.notificationapi.notificationapi.crossCutting.UtilEmail;
import com.notificationapi.notificationapi.crossCutting.UtilText;
import com.notificationapi.notificationapi.crossCutting.UtilUUID;

import java.util.UUID;

public class UsuarioDomain {
    private UUID identificador;
    private String correoElectronico;
    private String contraseña;


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
        this.correoElectronico = (String) UtilDefaultObject.defaultValue(correoElectronico,UtilEmail.getDefaultValueMail());
        return null;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = (String) UtilDefaultObject.defaultValue(contraseña,UtilText.getDefaultTextValue());
    }

    @Override
    public String toString() {
        return "UsuarioDomain{" +
                "identificador=" + identificador +
                ", correoElectronico='" + correoElectronico + '\'' +
                ", contraseña='" + contraseña + '\'' +
                '}';
    }
}
