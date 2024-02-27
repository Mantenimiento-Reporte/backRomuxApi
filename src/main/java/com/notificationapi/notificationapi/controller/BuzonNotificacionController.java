package com.notificationapi.notificationapi.controller;


import com.notificationapi.notificationapi.domain.BuzonNotificacionDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("api/v1")

public class BuzonNotificacionController {

    @Autowired
    private BuzonNotificacionController buzonNotificacionController;

    @GetMapping("/BuzonNotificacion")
    public List<BuzonNotificacionDomain> get(@RequestParam(required = true) String nombre){return null;}

    @PostMapping("/BuzonNotificacion")
    public ResponseEntity<BuzonNotificacionDomain> create(@Validated @RequestBody BuzonNotificacionDomain propietario){return null;}

    @PutMapping("/BuzonNotificacion")
    public List<ResponseEntity<BuzonNotificacionDomain>> update(@RequestParam(required = true) String persona,@Validated @RequestBody BuzonNotificacionDomain nombre){
        return null;
    }

    @DeleteMapping("/BuzonNotificacion")
    public List<ResponseEntity<BuzonNotificacionDomain>> delete(@RequestParam(required = true) UUID identificador){
        return null;
    }

}
