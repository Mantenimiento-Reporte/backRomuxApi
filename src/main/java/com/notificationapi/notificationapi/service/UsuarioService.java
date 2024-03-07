package com.notificationapi.notificationapi.service;

import com.notificationapi.notificationapi.crossCutting.exception.NotificationException;
import com.notificationapi.notificationapi.crossCutting.utils.UtilEmail;
import com.notificationapi.notificationapi.crossCutting.utils.UtilText;
import com.notificationapi.notificationapi.crossCutting.utils.UtilUUID;
import com.notificationapi.notificationapi.domain.UsuarioDomain;
import com.notificationapi.notificationapi.entity.UsuarioEntity;
import com.notificationapi.notificationapi.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

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

    public void save(UsuarioDomain usuario) throws NotificationException {
        if(!datosSonValidos(usuario)){
            throw new NotificationException();
        }
        var usuarioEntity = new UsuarioEntity(usuario.getIdentificador(),usuario.getCorreoElectronico(),usuario.getContraseña());
            usuarioRepository.save(usuarioEntity);

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

    private boolean datosSonValidos(UsuarioDomain usuario){
        if(usuario.getCorreoElectronico().equals(UtilEmail.getDefaultValueMail()) || usuario.getCorreoElectronico().equals(UtilEmail.getDefaultValueMail())){
            return false;
        }
        if(usuario.getContraseña().equals(UtilText.getDefaultTextValue())){
            return false;
        }
        return true;
    }
}
