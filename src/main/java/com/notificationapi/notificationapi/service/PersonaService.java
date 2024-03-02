package com.notificationapi.notificationapi.service;

import com.notificationapi.notificationapi.crossCutting.UtilEmail;
import com.notificationapi.notificationapi.crossCutting.UtilText;
import com.notificationapi.notificationapi.crossCutting.UtilUUID;
import com.notificationapi.notificationapi.domain.PersonaDomain;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class PersonaService {



    public PersonaService(){

    }

    public List<PersonaDomain> consult(String correoElectronico){
        List<PersonaDomain> messageDialog = new ArrayList<>();
        List<PersonaDomain> registrosEncontrado = new ArrayList<>();
        PersonaDomain registroPrueba_1 = new PersonaDomain();
        registroPrueba_1.setCorreoElectronico("alejandrodev117@gmail.com");
        messageDialog.add(registroPrueba_1);
        if(!correoElectronico.equals(UtilText.getDefaultTextValue())){
            messageDialog.add(new PersonaDomain().setPrimerNombre("Error, correo electronico no encontrado"));
            return messageDialog;
        }
        if(correoElectronico.equals(UtilEmail.getDefaultValueMail())){
            messageDialog.add(new PersonaDomain().setPrimerNombre("Error, correo electronico no valido"));
            return messageDialog;
        }
        for (PersonaDomain puntero : messageDialog){
            if(puntero.getIdentificador().equals(correoElectronico)) {
                registrosEncontrado.add(puntero);
            }
        }
        if(messageDialog.isEmpty()){
            messageDialog.add(new PersonaDomain().setPrimerNombre("Registro no encontrado"));
            return registrosEncontrado;
        }
        return registrosEncontrado;
    }

    public String create(PersonaDomain persona){
        String messageDialog;
        if(persona.getPrimerNombre().equals(UtilText.getDefaultTextValue()) || persona.getPrimerApellido().equals(UtilText.getDefaultTextValue()))
        {
            return messageDialog = "Error debe ingresar al menos un nombre y apellido";
        }
        if(persona.getCorreoElectronico().equals(UtilText.getDefaultTextValue()) || persona.getCorreoElectronico().equals(UtilEmail.getDefaultValueMail())){
            return messageDialog = "Error, debe ingresar un correo electronico válido";
        }
        persona.setIdentificador(UUID.randomUUID());
        return messageDialog = "Usuario registrado con exito";
    }

    public String update(PersonaDomain persona){
        if(persona.getCorreoElectronico().equals(UtilText.getDefaultTextValue()) || persona.getCorreoElectronico().equals(UtilEmail.getDefaultValueMail())){
            return "Error, correo electronico no válido";
        }
        return "se conectó bien";
    }

    public String delete(UUID identificador){
        if(identificador.equals(UtilUUID.getUuidDefaultValue())){
            return "identificador no valido";
        }
        return "se conecta bien";
    }
}
