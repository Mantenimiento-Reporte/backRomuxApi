package com.notificationapi.notificationapi.controller;

import com.notificationapi.notificationapi.service.ReporteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/v1/reporte")
public class ReporteController {

    private final ReporteService reporteService;

    public ReporteController(ReporteService reporteService) {
        this.reporteService = reporteService;
    }

    @GetMapping("/gen")
    public ResponseEntity<?> generarReporte() {
        if (reporteService.generarReporte("C:\\Users\\User\\Desktop\\reportePersonas.xlsx")){
            return ResponseEntity.ok("Reporte generado exitosamente");
        }else {
            return ResponseEntity.ok("Reporte NO generado exitosamente");
        }

    }
}
