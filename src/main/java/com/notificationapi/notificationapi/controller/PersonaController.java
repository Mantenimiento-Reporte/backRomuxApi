package com.notificationapi.notificationapi.controller;

import com.notificationapi.notificationapi.domain.PersonaDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.notificationapi.notificationapi.service.PersonaService;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("persona/v1")

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
    public String create(@Validated @RequestBody PersonaDomain persona){
        return personaService.create(persona);
    }
    @PutMapping("/persona")
    public List<ResponseEntity<PersonaDomain>> update(@RequestParam(required = true) String correoElectronico,@Validated @RequestBody PersonaDomain persona){
        return null;
    }

    @DeleteMapping("/persona")
    public List<ResponseEntity<PersonaDomain>> delete(@RequestParam(required = true) UUID identificador){
        return null;
    }
}
