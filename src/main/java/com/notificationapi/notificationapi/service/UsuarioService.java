package com.notificationapi.notificationapi.service;

import com.notificationapi.notificationapi.crossCutting.exception.NotificationException;
import com.notificationapi.notificationapi.crossCutting.utils.UtilEmail;
import com.notificationapi.notificationapi.crossCutting.utils.UtilText;
import com.notificationapi.notificationapi.crossCutting.utils.UtilUUID;
import com.notificationapi.notificationapi.domain.UsuarioDomain;
import com.notificationapi.notificationapi.entity.UsuarioEntity;
import com.notificationapi.notificationapi.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    public UsuarioDomain toDomain(UsuarioEntity entity){
        return new UsuarioDomain(entity.getIdentificador(),entity.getUsername(),entity.getPassword(),entity.getRol());
    }
    public UsuarioEntity toEntity(UsuarioDomain domain) {
        return new UsuarioEntity(domain.getIdentificador(), domain.getCorreoElectronico(), domain.getContraseña(), domain.getRol());
    }


    public UsuarioDomain consult(String correoElectronico){
        return toDomain(usuarioRepository.findByCorreo(correoElectronico));
    }

    public void save(UsuarioDomain usuario) throws NotificationException {
        if(!datosSonValidos(usuario)){
            throw new NotificationException();
        }
        try{
            usuarioRepository.save(toEntity(usuario));
        }catch (Exception e){
            throw e;
        }

    }

    public void update(String correoElectronico, String contraseña) throws NotificationException {
        if(contraseña.equals(UtilText.getDefaultTextValue())){
            throw new NotificationException();
        }
        try {
            PasswordEncoder passwordEncoder = null;
            String encriptadaContraseña = passwordEncoder.encode(contraseña);
            usuarioRepository.updateByCorreoElectronico(encriptadaContraseña, correoElectronico);
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
