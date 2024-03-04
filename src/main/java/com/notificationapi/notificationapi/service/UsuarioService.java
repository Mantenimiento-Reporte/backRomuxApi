package com.notificationapi.notificationapi.service;

import com.notificationapi.notificationapi.domain.UsuarioDomain;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UsuarioService {

    private List<UsuarioDomain> baseDatosQuemada;


    public UsuarioService() {
        baseDatosQuemada = new ArrayList<>();
    }

}
