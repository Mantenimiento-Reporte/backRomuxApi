package com.notificationapi.notificationapi.service;

import com.notificationapi.notificationapi.domain.PersonaDomain;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class PersonaService {

    private List<PersonaDomain> baseDatosQuemada;


    public PersonaService(){
        this.baseDatosQuemada = new ArrayList<>();
    }

    public List<PersonaDomain> consult(String correoElectronico){
        List<PersonaDomain> messageDialog = new ArrayList<>();
        if(!correoElectronico.equals("")){
            messageDialog.add(new PersonaDomain().setPrimerNombre("Error, correo electronico no encontrado"));
            return messageDialog;
        }
        for (PersonaDomain puntero : baseDatosQuemada){
            if(puntero.getIdentificador().equals(correoElectronico)) {
                messageDialog.add(puntero);
            }
        }
        if(messageDialog.isEmpty()){
            messageDialog.add(new PersonaDomain().setPrimerNombre("Registro no encontrado"));
            return messageDialog;
        }
        return messageDialog;
    }

    public String create(PersonaDomain persona){
        String messageDialog;
        if(persona.getPrimerNombre().equals("") || persona.getPrimerApellido().equals("")){
            return messageDialog = "Error debe ingresar al menos un nombre y apellido";
        }
        if(persona.getCorreoElectronico().equals("")){
            return messageDialog = "Error, debe ingresar un correo electronico";
        }
        if(persona.getContraseña().equals("")){
            return messageDialog = "Error, debe ingresar una contraseña";
        }
        persona.setIdentificador(UUID.randomUUID());
        baseDatosQuemada.add(persona);
        return messageDialog = "Usuario registrado con exito";
    }
}
