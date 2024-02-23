package controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.PersonaService;
@RestController
@RequestMapping("v1")

public class PersonaController {

    private PersonaService personaService;
}
