package com.notificationapi.notificationapi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
public class NotificacionApiController {

    @PostMapping(value = "notificacion_api")
    public String welcome(){
        return "Welcome from secure endpoint";
    }
}