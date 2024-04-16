package com.notificationapi.notificationapi.entity;

import com.notificationapi.notificationapi.domain.Rol;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

import static net.sf.jsqlparser.util.validation.metadata.NamedObject.role;

@Entity
@Builder
@RequiredArgsConstructor
@Table(name = "usuario")
public class UsuarioEntity implements UserDetails {

    @Id
    @Column(name = "identificador")
    private UUID identificador;

    @Column(name = "correoElectronico",length = 50)
    private String correoElectronico;

    @Column(name = "contrasena", length = 30)
    private String contraseña;

    @Enumerated(EnumType.STRING)
    private Rol rol;


    public UsuarioEntity(UUID identificador, String correoElectronico, String contraseña, Rol rol) {
        this.identificador = identificador;
        this.correoElectronico = correoElectronico;
        this.contraseña = contraseña;
    }

    public UsuarioEntity(String correoElectronico, String contraseña, Rol rol) {
        this.correoElectronico = correoElectronico;
        this.contraseña = contraseña;
        this.rol = rol;
    }

    public UUID getIdentificador() {
        return identificador;
    }

    public void setIdentificador(UUID identificador) {
        this.identificador = identificador;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority((role.name())));
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
