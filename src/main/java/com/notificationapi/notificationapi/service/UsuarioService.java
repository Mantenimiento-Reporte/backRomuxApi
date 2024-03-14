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

import java.util.List;
import java.util.UUID;

@Repository
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;


    public List<UsuarioDomain> findAll(){
        return usuarioRepository.findAll().stream().map(new UsuarioService()::toDomain).toList();
    }
    private UsuarioDomain toDomain(UsuarioEntity entity){
        return new UsuarioDomain(entity.getIdentificador(),entity.getCorreoElectronico(),entity.getContraseña());

    }
    private UsuarioEntity toEntity(UsuarioDomain domain){
        return new UsuarioEntity(domain.getIdentificador(),domain.getCorreoElectronico(),domain.getContraseña());
    }

    public UsuarioDomain consult(String correoElectronico){
        return toDomain(usuarioRepository.findByCorreoElectronico(correoElectronico));
    }

    public void save(UsuarioDomain usuario) throws NotificationException {
        if(!datosSonValidos(usuario)){
            throw new NotificationException();
        }
        try{
            usuarioRepository.save(toEntity(usuario));
        }catch (Exception e){
            System.out.println("Entra aqui?");
            throw e;
        }

    }

    public void update(String correoElectronico, String contraseña) throws NotificationException {
        System.out.println(contraseña+"service");
        if(contraseña.equals(UtilText.getDefaultTextValue())){
            throw new NotificationException();
        }
        try {
            usuarioRepository.updateByCorreoElectronico(contraseña, correoElectronico);
        }catch (Exception e){
            throw e;
        }
    }

    public void delete(UUID identificador) throws NotificationException {
        if(identificador.equals(UtilUUID.getUuidDefaultValue())){
            throw new NotificationException();
        }
        try{
            usuarioRepository.deleteById(identificador);
        }catch (Exception e){
            throw e;
        }
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
