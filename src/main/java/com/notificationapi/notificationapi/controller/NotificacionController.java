package com.notificationapi.notificationapi.controller;

import com.notificationapi.notificationapi.domain.NotificacionDomain;
import com.notificationapi.notificationapi.service.NotificacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/v1")
public class    NotificacionController {

    @Autowired
    private NotificacionService notificacionService;

    @GetMapping("/dummy_notificacion")
    public NotificacionDomain dummy(){
        return new NotificacionDomain();
    }

    @GetMapping("/notificacion")
    public List<NotificacionDomain> get(){
        return null;
    }

    @GetMapping("/notificacion/autor")
    public ResponseEntity<List<NotificacionDomain>> getNotificacion(@RequestParam(required = true) String correo){
        return null;
    }

    @PostMapping("/notificacion")
    public ResponseEntity<String> create(@Validated @RequestBody NotificacionDomain notificacion){
        notificacionService.saveNotificacion(notificacion);
        return new ResponseEntity<>("", HttpStatus.OK);
        
    }

    @GetMapping("/notificacion/respuesta")
    public ResponseEntity<String> getRespuesta(){
        return new ResponseEntity<>(notificacionService.getMensajeExcepcion(),HttpStatus.OK);
    }

}
