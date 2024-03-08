package com.notificationapi.notificationapi.controller;

import com.notificationapi.notificationapi.crossCutting.exception.NotificationException;
import com.notificationapi.notificationapi.domain.PersonaDomain;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.notificationapi.notificationapi.service.PersonaService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/v1")

public class PersonaController {

    @Autowired
    private PersonaService personaService = new PersonaService();
    private List<String> messageDialog;

    @GetMapping("/dummy_persona")
    public PersonaDomain getDummy(){
        return new PersonaDomain();
    }

    @GetMapping("/persona_all")
    public ResponseEntity<List<PersonaDomain>> findAll(){
        return new ResponseEntity<>(personaService.findAll(),HttpStatus.OK);
    }
    @GetMapping("/persona")
    public ResponseEntity<PersonaDomain> get(@RequestParam(required = true) String correoElectronico){
        return new ResponseEntity<>(personaService.consult(correoElectronico),HttpStatus.OK);
    }

    @PostMapping("/persona")
    public ResponseEntity<String> save(@Validated @RequestBody PersonaDomain persona) {
        try {
            personaService.save(persona);
            var response = new ResponseEntity<>("Usuario Registrado con exito", HttpStatus.OK);
            return response;
        }catch (NotificationException n){
            var response = new ResponseEntity<>("Error, debe ingresar primer nombre y primer apellido obligatoriamente", HttpStatus.BAD_REQUEST);
            return response;
        }
        catch (Exception e){
            var response = new ResponseEntity<>("Error, correo electrónico ya existente", HttpStatus.BAD_REQUEST);
            return response;
        }

    }
    @PutMapping("/persona")
    public ResponseEntity<String> update(@RequestParam(required = true) String correoElectronico,@Validated @RequestBody PersonaDomain persona){
        try {
            persona.setCorreoElectronico(correoElectronico);
            personaService.update(persona);
            return new ResponseEntity<>("Datos actualizados con Exito!!",HttpStatus.BAD_REQUEST);
        }catch (NotificationException n){
            return new ResponseEntity<>("Error, el correo electronico no es valido",HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return new ResponseEntity<>("Error correo electronico no encontrado",HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/persona")
    public ResponseEntity<String> delete(@RequestParam(required = true) UUID identificador){
        try {
            personaService.delete(identificador);
            return new ResponseEntity<>("Usuario Eliminado con Exito!!",HttpStatus.BAD_REQUEST);
        }catch (NotificationException n){
            return new ResponseEntity<>("Error,el identificador no es valido",HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return new ResponseEntity<>("Error identificador no encontrado",HttpStatus.BAD_REQUEST);
        }
    }
}
