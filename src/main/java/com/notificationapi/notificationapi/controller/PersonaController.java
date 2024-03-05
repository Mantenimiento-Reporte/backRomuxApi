package com.notificationapi.notificationapi.controller;

import com.notificationapi.notificationapi.domain.PersonaDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
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
    private List<String> messageDialog;

    @GetMapping("/dummy_persona")
    public PersonaDomain getDummy(){
        return new PersonaDomain();
    }
    @GetMapping("/persona")
    public List<PersonaDomain> get(@RequestParam(required = true) String correoElectronico){
        return personaService.consult(correoElectronico);
    }

    @PostMapping("/persona")
    public ResponseEntity<String> save(@Validated @RequestBody PersonaDomain persona) {
        try {
            personaService.save(persona);
            var response = new ResponseEntity<>("Usuario Registrado con exito", HttpStatus.OK);
            return response;
        }catch (Exception e){
            var response = new ResponseEntity<>("Error, correo electrónico ya existente", HttpStatus.BAD_REQUEST);
            return response;
        }

    }
    @PutMapping("/persona")
    public String update(@RequestParam(required = true) String correoElectronico,@Validated @RequestBody PersonaDomain persona){
        persona.setCorreoElectronico(correoElectronico);
        return personaService.update(persona);
    }

    @DeleteMapping("/persona")
    public String delete(@RequestParam(required = true) UUID identificador){
        return personaService.delete(identificador);
    }
}
