package com.notificationapi.notificationapi.controller;


import com.notificationapi.notificationapi.crossCutting.exception.NotificationException;
import com.notificationapi.notificationapi.domain.UsuarioDomain;
import com.notificationapi.notificationapi.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService = new UsuarioService();
    @GetMapping("/dummy_usuario")
    public UsuarioDomain dummy(){
        return new UsuarioDomain();
    }

    @GetMapping("/usuario/all")
    public ResponseEntity<List<UsuarioDomain>> findAll(){
        return new ResponseEntity<>(usuarioService.findAll(),HttpStatus.OK);
    }

    @GetMapping("/usuario")
    public ResponseEntity<UsuarioDomain> getUsuario(@RequestParam(required = true) String correoElectronico){
        return new ResponseEntity<>(usuarioService.consult(correoElectronico),HttpStatus.OK);
    }



}
