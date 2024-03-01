package com.notificationapi.notificationapi.controller;

import com.notificationapi.notificationapi.domain.NotificacionDomain;
import com.notificationapi.notificationapi.domain.PersonaDomain;
import com.notificationapi.notificationapi.domain.UsuarioDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.notificationapi.notificationapi.service.NotificacionService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("v1")
public class NotificacionController {

    @Autowired
    private NotificacionService notificacionService;

    @GetMapping("/notificacion")
    public NotificacionDomain get(@RequestParam(required = true) String correoElectronico){
        var persona = new UsuarioDomain();
        return new NotificacionDomain(UUID.randomUUID(), persona, "Despido", "Usted no hace nada", new Date(), "enviado",new Date(), "Programada" );
    }

    @PostMapping("/notificacion")
    public ResponseEntity<NotificacionDomain> create(@Validated @RequestBody NotificacionDomain notificacion){
        return null;
    }

    @DeleteMapping("/notificacion")
    public List<ResponseEntity<NotificacionDomain>> delete(@RequestParam(required = true) UUID identificador){
        return new ArrayList<>();
    }


}
