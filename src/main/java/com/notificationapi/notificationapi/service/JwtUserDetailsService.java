package com.notificationapi.notificationapi.service;

import com.notificationapi.notificationapi.crossCutting.utils.UtilDefaultObject;
import com.notificationapi.notificationapi.crossCutting.utils.UtilEmail;
import com.notificationapi.notificationapi.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

public class JwtUserDetailsService implements UserDetailsService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder bcrypt;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var usuario = usuarioRepository.findByCorreoElectronico(username);

        if(usuario.equals(null)){
            throw new UsernameNotFoundException("No se encontró ningun usuario con el siguiente nombre: "+ username);
        }else {
            return new org.springframework.security.core.userdetails.User(usuario.getCorreoElectronico(), usuario.getContraseña(),
                    new ArrayList<>());
        }

    }
}
