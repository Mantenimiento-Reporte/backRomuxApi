package com.notificationapi.notificationapi.controller;


import com.notificationapi.notificationapi.domain.BuzonNotificacionDomain;
import com.notificationapi.notificationapi.service.BuzonNotificacionService;
import org.springframework.amqp.AmqpException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/v1")

public class BuzonNotificacionController {

    private BuzonNotificacionService buzonNotificacionService;

    @Autowired
    public BuzonNotificacionController(BuzonNotificacionService buzonNotificacionService) {
        this.buzonNotificacionService = buzonNotificacionService;
    }

    @GetMapping("/dummy_buzonnotificacion")
    BuzonNotificacionDomain dummy(){
        return new BuzonNotificacionDomain();
    }

    @GetMapping("/buzonnotificacion/propietario")
    public void getPorPropietario(@RequestParam(required = true) String correo){
        buzonNotificacionService.getBuzonNotificacionesPorPropietario(correo);
    }

    @GetMapping("/buzonnotificacion/all")
    public void getAll(){
        buzonNotificacionService.findAll();
    }



    @PostMapping("/buzonnotificacion")
    public ResponseEntity<String> create(@Validated @RequestBody BuzonNotificacionDomain buzonNotificacion){
        buzonNotificacionService.saveBuzonNotificacion(buzonNotificacion);
        return new ResponseEntity<>("", HttpStatus.OK);
    }

    @DeleteMapping("/buzonnotificacion")
    public ResponseEntity<String> delete(@Validated @RequestBody BuzonNotificacionDomain buzonNotificacion){
        try {
            buzonNotificacionService.eliminar(buzonNotificacion);
        }catch (AmqpException ex){
            return new ResponseEntity<>("Ha ocurrido un error inesperado",HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>("Formulario enviado con exito",HttpStatus.OK);
    }

    @GetMapping("/buzonnotificacion/lista")
    public ResponseEntity<List<BuzonNotificacionDomain>> getLista(){
        return new ResponseEntity<>(buzonNotificacionService.getRespuesta(),HttpStatus.OK);

    }
    @GetMapping("/buzonnotificacion/respuesta")
    public ResponseEntity<String> getRespuesta(){
        return new ResponseEntity<>(buzonNotificacionService.getMensajeExcepcion(),HttpStatus.OK);
    }


}
