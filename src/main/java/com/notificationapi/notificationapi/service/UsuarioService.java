package com.notificationapi.notificationapi.service;

import com.notificationapi.notificationapi.crossCutting.UtilEmail;
import com.notificationapi.notificationapi.crossCutting.UtilText;
import com.notificationapi.notificationapi.crossCutting.UtilUUID;
import com.notificationapi.notificationapi.domain.PersonaDomain;
import com.notificationapi.notificationapi.domain.UsuarioDomain;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class UsuarioService {


    public UsuarioService() {
    }

    public List<UsuarioDomain> consult(String correoElectronico){
        List<UsuarioDomain> messageDialog = new ArrayList<>();
        List<UsuarioDomain> registrosEncontrado = new ArrayList<>();
        UsuarioDomain registroPrueba_1 = new UsuarioDomain();
        registroPrueba_1.setCorreoElectronico("alejandrodev117@gmail.com");
        messageDialog.add(registroPrueba_1);
        if(!correoElectronico.equals(UtilText.getDefaultTextValue())){
            messageDialog.add(new UsuarioDomain().setCorreoElectronico("Error, correo electronico no ingresado"));
            return messageDialog;
        }
        if(correoElectronico.equals(UtilEmail.getDefaultValueMail())){
            messageDialog.add(new UsuarioDomain().setCorreoElectronico("Error, correo electronico no valido"));
            return messageDialog;
        }
        for (UsuarioDomain puntero : messageDialog){
            if(puntero.getIdentificador().equals(correoElectronico)) {
                registrosEncontrado.add(puntero);
            }
        }
        if(messageDialog.isEmpty()){
            messageDialog.add(new UsuarioDomain().setCorreoElectronico("Registro no encontrado"));
            return registrosEncontrado;
        }
        return registrosEncontrado;
    }

    public String create(UsuarioDomain usuario){
        String messageDialog;
        if(usuario.getCorreoElectronico().equals(UtilText.getDefaultTextValue()) || usuario.getCorreoElectronico().equals(UtilEmail.getDefaultValueMail())){
            return messageDialog = "Error, debe ingresar un correo electronico válido";
        }
        usuario.setIdentificador(UUID.randomUUID());
        return messageDialog = "Usuario registrado con exito";
    }

    public String update(String correoElectronico, String contraseña){
        if(correoElectronico.equals(UtilText.getDefaultTextValue()) || correoElectronico.equals(UtilEmail.getDefaultValueMail())){
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
