package com.notificationapi.notificationapi.controller;


import com.notificationapi.notificationapi.crossCutting.exception.NotificationException;
import com.notificationapi.notificationapi.domain.UsuarioDomain;
import com.notificationapi.notificationapi.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1")
public class UsuarioController {

    private UsuarioService usuarioService = new UsuarioService();
    @GetMapping("/dummy_usuario")
    public UsuarioDomain dummy(){
        return new UsuarioDomain();
    }


    @GetMapping("/usuario")
    public List<UsuarioDomain> getUsuario(@RequestParam(required = true) String correoElectronico){
        return usuarioService.consult(correoElectronico);
    }

    @PostMapping("/usuario")
    public ResponseEntity<String> createUsuario(@RequestBody(required = true) UsuarioDomain usuarioDomain){
        try {
            usuarioService.save(usuarioDomain);
            return new ResponseEntity<>("Usuario Registrado con exito!!", HttpStatus.OK);
        } catch (NotificationException e) {
            return new ResponseEntity<>("Error, el correo electronico o la contraseña deben ser validos", HttpStatus.BAD_REQUEST);
        }
    }


    @PutMapping("/usuario")
    public String update(@RequestParam(required = true) String correoElectronico,@Validated @RequestBody String contraseña){
        return usuarioService.update(correoElectronico,contraseña);
    }

    @DeleteMapping("/usuario")
    public String delete(@RequestParam(required = true) UUID identificador){
        return usuarioService.delete(identificador);
    }

}
