package com.notificationapi.notificationapi.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {


    String primerNombre;
    String segundoNombre;
    String primerApellido;
    String segundoApellido;
    String correoElectronico;
    String password;
}
