package com.notificationapi.notificationapi.controller;

import com.notificationapi.notificationapi.crossCutting.exception.NotificationException;
import com.notificationapi.notificationapi.domain.PersonaDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.notificationapi.notificationapi.service.PersonaService;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/v1")

public class PersonaController {

    @Autowired
    private PersonaService personaService = new PersonaService();

    @GetMapping("/dummy_persona")
    public PersonaDomain getDummy(){
        return new PersonaDomain();
    }

    @GetMapping("/persona/all")
    public ResponseEntity<List<PersonaDomain>> findAll(){
        return new ResponseEntity<>(personaService.findAll(),HttpStatus.OK);
    }
    @GetMapping("/persona")
    public ResponseEntity<PersonaDomain> get(@RequestParam(required = true) String correoElectronico){
        return new ResponseEntity<>(personaService.consult(correoElectronico),HttpStatus.OK);
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

}
