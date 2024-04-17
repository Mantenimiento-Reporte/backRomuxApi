package com.notificationapi.notificationapi.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

import static net.sf.jsqlparser.util.validation.metadata.NamedObject.role;

@Entity
@Data
@Builder
@RequiredArgsConstructor
@Table(name = "persona")
public class PersonaEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "identificador", columnDefinition = "uuid", updatable = false, nullable = false)
    private UUID identificador;

    @Column(name = "primerNombre", length = 30)
    private String primerNombre;

    @Column(name = "segundoNombre", length = 30)
    private String segundoNombre;

    @Column(name = "primerApellido", length = 30)
    private String primerApellido;

    @Column(name = "segundoApellido", length = 30)
    private String segundoApellido;

    @Column(name = "correoElectronico", length = 50, unique = true)
    private String correoElectronico;

    public PersonaEntity(UUID identificador, String primerNombre, String segundoNombre, String primerApellido, String segundoApellido, String correoElectronico) {
        this.identificador = identificador;
        this.primerNombre = primerNombre;
        this.segundoNombre = segundoNombre;
        this.primerApellido = primerApellido;
        this.segundoApellido = segundoApellido;
        this.correoElectronico = correoElectronico;
    }

    public PersonaEntity(String primerNombre, String segundoNombre, String primerApellido, String segundoApellido, String correoElectronico) {
        this.primerNombre = primerNombre;
        this.segundoNombre = segundoNombre;
        this.primerApellido = primerApellido;
        this.segundoApellido = segundoApellido;
        this.correoElectronico = correoElectronico;
    }


    public UUID getIdentificador() {
        return identificador;
    }

    public void setIdentificador(UUID identificador) {
        this.identificador = identificador;
    }

    public String getPrimerNombre() {
        return primerNombre;
    }

    public void setPrimerNombre(String primerNombre) {
        this.primerNombre = primerNombre;
    }

    public String getSegundoNombre() {
        return segundoNombre;
    }

    public void setSegundoNombre(String segundoNombre) {
        this.segundoNombre = segundoNombre;
    }

    public String getPrimerApellido() {
        return primerApellido;
    }

    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }

    public String getSegundoApellido() {
        return segundoApellido;
    }

    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
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
