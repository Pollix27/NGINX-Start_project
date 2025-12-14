package com.example.demo.controladores;

import com.example.demo.entidades.Proyecto;
import com.example.demo.servicios.PDFGeneratorService;
import com.example.demo.servicios.ProyectoService;
import com.example.demo.servicios.XMLGeneratorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/reportes")
@CrossOrigin(origins = "*")
public class ReporteController {
    
    private static final Logger logger = LoggerFactory.getLogger(ReporteController.class);
    
    @Autowired
    private XMLGeneratorService xmlGeneratorService;
    
    @Autowired
    private PDFGeneratorService pdfGeneratorService;
    
    @Autowired
    private ProyectoService proyectoService;
    
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
    
    @GetMapping("/proyecto/{id}/pdf")
    public ResponseEntity<byte[]> generarTicketPDF(@PathVariable int id) {
        try {
            Optional<Proyecto> proyectoOpt = proyectoService.buscarPorId(id);
            if (proyectoOpt.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            
            Proyecto proyecto = proyectoOpt.get();
            byte[] pdfBytes = pdfGeneratorService.generarTicketProyecto(id);
            
            String clienteNombre = proyecto.getCliente() != null ? 
                    proyecto.getCliente().getEmpresaCliente().replaceAll("[^a-zA-Z0-9]", "") : "Cliente";
            String proyectoNombre = proyecto.getNombreProyecto().replaceAll("[^a-zA-Z0-9]", "");
            String nombreArchivo = "ticket-" + clienteNombre + "-" + proyectoNombre + ".pdf";
            
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("attachment", nombreArchivo);
            headers.setContentLength(pdfBytes.length);
            
            return ResponseEntity.ok()
                    .headers(headers)
                    .body(pdfBytes);
        } catch (Exception e) {
            logger.error("Error al generar ticket PDF para proyecto {}: {}", id, e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
