package com.notificationapi.notificationapi.domain;

import com.notificationapi.notificationapi.crossCutting.UtilDefaultValue;
import com.notificationapi.notificationapi.crossCutting.UtilUUID;

import java.util.UUID;

public class UsuarioDomain {
    private UUID identificador;
    private String correoElectronico;
    private String contraseña;


    public UsuarioDomain() {
    }

    public UUID getIdentificador() {
        return identificador;
    }

    public void setIdentificador(UUID identificador) {
        this.identificador = (UUID) UtilDefaultValue.defaultValue(identificador, UtilUUID.getUuidDefaultValue());
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = (String) UtilDefaultValue.defaultValue(correoElectronico,"");
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = (String) UtilDefaultValue.defaultValue(contraseña,"");
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
