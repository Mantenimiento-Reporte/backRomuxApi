package com.notificationapi.notificationapi.auth;

import com.notificationapi.notificationapi.MessengerService.buzonNotificacion.MessageSenderBuzonNotificacion;
import com.notificationapi.notificationapi.crossCutting.exception.NotificationException;
import com.notificationapi.notificationapi.crossCutting.utils.UtilUUID;
import com.notificationapi.notificationapi.domain.PersonaDomain;
import com.notificationapi.notificationapi.domain.Rol;
import com.notificationapi.notificationapi.entity.PersonaEntity;
import com.notificationapi.notificationapi.entity.UsuarioEntity;
import com.notificationapi.notificationapi.jwt.JwtService;
import com.notificationapi.notificationapi.repository.PersonaRepository;
import com.notificationapi.notificationapi.repository.UsuarioRepository;
import com.notificationapi.notificationapi.service.BuzonNotificacionService;
import com.notificationapi.notificationapi.service.PersonaService;
import com.notificationapi.notificationapi.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private PersonaService personaService;

    @Autowired
    private UsuarioService usuarioService;

    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.correoElectronico,request.password));
        UserDetails  user = usuarioRepository.findByCorreoElectronico(request.getCorreoElectronico()).orElseThrow();
        String token = jwtService.getToken(user);
        return  AuthResponse.builder()
                .token(token)
                .build();

    }

    public AuthResponse register(RegisterRequest request) throws NotificationException {
        UsuarioEntity usuario = UsuarioEntity.builder().correoElectronico(request.getCorreoElectronico()).
                password(passwordEncoder.encode((request.getPassword()))).rol(Rol.USER).build();
        PersonaEntity persona = PersonaEntity.builder().identificador(UtilUUID.getUuidDefaultValue()).primerNombre(request.primerNombre).segundoNombre(request.segundoNombre).
                primerApellido(request.primerApellido).segundoApellido(request.segundoApellido).correoElectronico(request.correoElectronico).build();
        personaService.save(personaService.toDomain(persona));
        usuarioService.save(usuarioService.toDomain(usuario));
        return  new AuthResponse(jwtService.getToken(usuario));
    }
}
