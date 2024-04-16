package com.notificationapi.notificationapi.auth;

import com.notificationapi.notificationapi.domain.Rol;
import com.notificationapi.notificationapi.entity.UsuarioEntity;
import com.notificationapi.notificationapi.jwt.JwtService;
import com.notificationapi.notificationapi.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UsuarioRepository usuarioRepository;
    private final JwtService jwtService;
    public AuthResponse login(LoginRequest request) {
        return null;
    }

    public AuthResponse register(RegisterRequest request) {
        UsuarioEntity usuario = UsuarioEntity.builder().correoElectronico(request.getCorreoElectronico()).contraseña(request.getPassword()).rol(Rol.USER).build();

        usuarioRepository.save(usuario);
        return  AuthResponse.builder().token(jwtService.getToken(usuario)).build();
    }
}
