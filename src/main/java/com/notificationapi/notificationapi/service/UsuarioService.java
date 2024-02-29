package com.notificationapi.notificationapi.service;

import com.notificationapi.notificationapi.domain.UsuarioDomain;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioService {

    private List<UsuarioDomain> baseDatosQuemada;


    public UsuarioService() {
        baseDatosQuemada = new ArrayList<>();
    }

}
