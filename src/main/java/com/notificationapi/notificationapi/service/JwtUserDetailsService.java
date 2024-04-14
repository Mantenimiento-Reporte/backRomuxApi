package com.notificationapi.notificationapi.service;

import com.notificationapi.notificationapi.domain.UsuarioDomain;
import com.notificationapi.notificationapi.repository.IUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import reactor.core.publisher.Mono;

import java.util.ArrayList;

public class JwtUserDetailsService implements UserDetailsService {
    @Autowired
    private IUsuarioRepository iUsuarioRepository;

    @Autowired
    private PasswordEncoder bcryptEnconder;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UsuarioDomain usuario = iUsuarioRepository.findByCorreoElectronico(username).block();

        if(usuario.equals(null)){
            throw new UsernameNotFoundException("No se encontró ningun usuario con el siguiente nombre: "+ username);
        }else {
            return new org.springframework.security.core.userdetails.User(usuario.getCorreoElectronico(), usuario.getContraseña(),
                    new ArrayList<>());
        }
    }

    public Mono<UsuarioDomain> save(UsuarioDomain UsuarioDomain){
        UsuarioDomain.setContraseña(bcryptEnconder.encode(UsuarioDomain.getContraseña()));
        return iUsuarioRepository.save(UsuarioDomain);
    }
}