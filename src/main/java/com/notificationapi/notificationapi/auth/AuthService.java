package com.notificationapi.notificationapi.auth;

import com.notificationapi.notificationapi.domain.Rol;
import com.notificationapi.notificationapi.entity.PersonaEntity;
import com.notificationapi.notificationapi.entity.UsuarioEntity;
import com.notificationapi.notificationapi.jwt.JwtService;
import com.notificationapi.notificationapi.repository.PersonaRepository;
import com.notificationapi.notificationapi.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private PersonaRepository personaRepository;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private AuthenticationManager authenticationManager;


    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.username,request.password));
        UserDetails  user = usuarioRepository.findByCorreoElectronico(request.getUsername()).orElseThrow();
        String token = jwtService.getToken(user);
        return new AuthResponse(token);


    }

    public AuthResponse register(RegisterRequest request) {
        UsuarioEntity usuario = UsuarioEntity.builder().identificador(request.getIdentificador()).correoElectronico(request.getCorreoElectronico()).contraseña(request.getPassword()).rol(Rol.USER).build();

        usuarioRepository.save(usuario);
        return  new AuthResponse(jwtService.getToken(usuario));
    }
}
