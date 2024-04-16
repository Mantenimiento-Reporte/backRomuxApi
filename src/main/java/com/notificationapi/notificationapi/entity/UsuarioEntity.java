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
@Table(name = "usuario")
public class UsuarioEntity implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "identificador", columnDefinition = "uuid", updatable = false, nullable = false)
    private UUID identificador;

    @Column(name = "username",length = 50)
    private String username;

    @Column(name = "password", length = 30)
    private String password;

    @Enumerated(EnumType.STRING)
    private Rol rol;


    public UsuarioEntity(UUID identificador, String username, String password, Rol rol) {
        this.identificador = identificador;
        this.username = username;
        this.password = password;
    }

    public UsuarioEntity(String username, String password, Rol rol) {
        this.username = username;
        this.password = password;
        this.rol = rol;
    }


    public UUID getIdentificador() {
        return identificador;
    }

    public void setIdentificador(UUID identificador) {
        this.identificador = identificador;
    }


    public void setCorreoElectronico(String correoElectronico) {
        this.username = correoElectronico;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
    public String getUsername() {
        return username;
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
