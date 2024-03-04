package com.notificationapi.notificationapi.domain;

import com.notificationapi.notificationapi.crossCutting.UtilDefaultValue;
import com.notificationapi.notificationapi.crossCutting.UtilUUID;
import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;


public class PersonaDomain {

    private UUID identificador;

    private String primerNombre;

    private String segundoNombre;

    private String primerApellido;

    private String segundoApellido;

    private String correoElectronico;


    public PersonaDomain(UUID identificador, String primerNombre, String segundoNombre, String primerApellido, String segundoApellido, String correoElectronico) {
        this.identificador = identificador;
        this.primerNombre = primerNombre;
        this.segundoNombre = segundoNombre;
        this.primerApellido = primerApellido;
        this.segundoApellido = segundoApellido;
        this.correoElectronico = correoElectronico;
    }

    public PersonaDomain(){
        setIdentificador(UtilUUID.getUuidDefaultValue());
        setPrimerNombre("");
        setSegundoNombre("");
        setPrimerApellido("");
        setSegundoApellido("");
        setCorreoElectronico("");

    };
    public UUID getIdentificador() {
        return identificador;
    }

    public void setIdentificador(UUID identificador) {
        this.identificador = (UUID) UtilDefaultValue.defaultValue(identificador,UtilUUID.getUuidDefaultValue());
    }

    public String getSegundoNombre() {
        return segundoNombre;
    }

    public void setSegundoNombre(String segundoNombre) {
        this.segundoNombre = (String) UtilDefaultValue.defaultValue(segundoNombre,"");
    }

    public String getPrimerApellido() {
        return primerApellido;
    }

    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = (String) UtilDefaultValue.defaultValue(primerApellido,"");
    }

    public String getSegundoApellido() {
        return segundoApellido;
    }

    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = (String) UtilDefaultValue.defaultValue(segundoApellido,"");
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = (String) UtilDefaultValue.defaultValue(correoElectronico,"");
    }

    public String getPrimerNombre() {
        return primerNombre;
    }

    public PersonaDomain setPrimerNombre(String primerNombre) {
        this.primerNombre = (String) UtilDefaultValue.defaultValue(primerNombre,"");
        return null;
    }

    @Override
    public String toString() {
        return "PersonaDomain{" +
                "identificador=" + identificador +
                ", primerNombre='" + primerNombre + '\'' +
                ", segundoNombre='" + segundoNombre + '\'' +
                ", primerApellido='" + primerApellido + '\'' +
                ", segundoApellido='" + segundoApellido + '\'' +
                ", correoElectronico='" + correoElectronico + '\''+
                '}';
    }
}
