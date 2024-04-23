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

    @GetMapping("/usuario_all")
    public ResponseEntity<List<UsuarioDomain>> findAll(){
        return new ResponseEntity<>(usuarioService.findAll(),HttpStatus.OK);
    }

    @GetMapping("/usuario")
    public ResponseEntity<UsuarioDomain> getUsuario(@RequestParam(required = true) String correoElectronico){
        return new ResponseEntity<>(usuarioService.consult(correoElectronico),HttpStatus.OK);
    }

    @PostMapping("/usuario")
    public ResponseEntity<String> createUsuario(@RequestBody(required = true) UsuarioDomain usuarioDomain){
        try {
            usuarioService.save(usuarioDomain);
            return new ResponseEntity<>("Usuario Registrado con exito!!", HttpStatus.OK);
        } catch (NotificationException e) {
            return new ResponseEntity<>("Error, el correo electronico o la contraseña deben ser validos", HttpStatus.BAD_REQUEST);
        } catch (Exception e){
            return new ResponseEntity<>("Error, el correo electronico ya esta registrado previamente", HttpStatus.BAD_REQUEST);
        }
    }


    @PutMapping("/usuario")
    public ResponseEntity<String> update(@RequestParam(required = true) String correoElectronico,@RequestParam String contraseña){
        try {
            usuarioService.update(correoElectronico,contraseña);
            return new ResponseEntity<>("Contraseña actualizada con Exito!!",HttpStatus.BAD_REQUEST);
        }catch (NotificationException n){
            return new ResponseEntity<>("Error la contraseña debe tener un formato valido",HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return new ResponseEntity<>("Error la contraseña es demasiado larga",HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/usuario")
    public ResponseEntity<String> delete(@RequestParam(required = true) UUID identificador){
        try{
            usuarioService.delete(identificador);
            return new ResponseEntity<>("Usuario eliminado con Exito!!",HttpStatus.OK);
        }catch (NotificationException n){
            return new ResponseEntity<>("Error identificador no valido",HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return new ResponseEntity<>("Error Identificador no encontrado",HttpStatus.BAD_REQUEST);
        }
    }

}
