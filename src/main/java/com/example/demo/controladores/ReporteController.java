package com.example.demo.controladores;

import com.example.demo.servicios.XMLGeneratorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador REST para generar reportes de proyectos.
 */
@RestController
@RequestMapping("/api/reportes")
@CrossOrigin(origins = "*")
public class ReporteController {
    
    private static final Logger logger = LoggerFactory.getLogger(ReporteController.class);
    
    @Autowired
    private XMLGeneratorService xmlGeneratorService;
    
    /**
     * Genera un reporte XML completo de un proyecto.
     * @param id Identificador del proyecto
     * @return ResponseEntity con el XML del reporte o error
     */
    @GetMapping("/proyecto/{id}/xml")
    public ResponseEntity<String> generarReporteXML(@PathVariable int id) {
        try {
            String xml = xmlGeneratorService.generarReporteProyecto(id);
            
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_XML);
            headers.setContentDispositionFormData("attachment", "reporte_proyecto_" + id + ".xml");
            
            return ResponseEntity.ok()
                    .headers(headers)
                    .body(xml);
        } catch (Exception e) {
            logger.error("Error al generar reporte XML para proyecto {}: {}", id, e.getMessage(), e);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_XML);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .headers(headers)
                    .body("<?xml version=\"1.0\"?><error><mensaje>Error al generar reporte</mensaje><detalle>" + 
                          e.getMessage() + "</detalle></error>");
        }
    }
}
