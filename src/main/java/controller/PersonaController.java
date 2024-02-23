package controller;

import domain.PersonaDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import service.PersonaService;

import java.util.List;
import java.util.UUID;

import static com.sun.beans.introspect.PropertyInfo.Name.required;

@RestController
@RequestMapping("api/v1")

public class PersonaController {

    @Autowired
    private PersonaService personaService;

    @GetMapping("/persona")
    public List<PersonaDomain> get(@RequestParam(required = true) String correoElectronico){
        return null;
    }

    @PostMapping("/persona")
    public ResponseEntity<PersonaDomain> create(@Validated @RequestBody PersonaDomain persona){
        return null;
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
