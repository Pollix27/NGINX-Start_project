package com.example.demo.servicios;

import com.example.demo.entidades.*;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Service
public class PDFGeneratorService {
    
    @Autowired
    private ProyectoService proyectoService;
    
    public byte[] generarTicketProyecto(int idProyecto) throws Exception {
        Optional<Proyecto> proyectoOpt = proyectoService.buscarPorId(idProyecto);
        
        if (proyectoOpt.isEmpty()) {
            throw new Exception("Proyecto no encontrado");
        }
        
        Proyecto proyecto = proyectoOpt.get();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        
        PdfWriter writer = new PdfWriter(baos);
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf);
        
        // Color corporativo
        DeviceRgb colorPrimario = new DeviceRgb(102, 126, 234);
        
        // Encabezado
        Paragraph titulo = new Paragraph("TICKET DE PROYECTO")
                .setFontSize(24)
                .setBold()
                .setFontColor(colorPrimario)
                .setTextAlignment(TextAlignment.CENTER);
        document.add(titulo);
        
        Paragraph subtitulo = new Paragraph("Sistema de Gestión Empresarial")
                .setFontSize(12)
                .setTextAlignment(TextAlignment.CENTER)
                .setMarginBottom(20);
        document.add(subtitulo);
        
        // Fecha de generación
        String fechaActual = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
        Paragraph fecha = new Paragraph("Fecha de generación: " + fechaActual)
                .setFontSize(10)
                .setTextAlignment(TextAlignment.RIGHT)
                .setMarginBottom(20);
        document.add(fecha);
        
        // Información del Cliente
        agregarSeccion(document, "INFORMACIÓN DEL CLIENTE", colorPrimario);
        if (proyecto.getCliente() != null) {
            agregarCampo(document, "Empresa", proyecto.getCliente().getEmpresaCliente());
            agregarCampo(document, "RFC", proyecto.getCliente().getRfcCliente());
            agregarCampo(document, "Email", proyecto.getCliente().getEmailCliente());
            agregarCampo(document, "Teléfono", proyecto.getCliente().getTelefonoCliente());
        }
        
        // Información del Proyecto
        agregarSeccion(document, "INFORMACIÓN DEL PROYECTO", colorPrimario);
        agregarCampo(document, "Nombre", proyecto.getNombreProyecto());
        agregarCampo(document, "Descripción", proyecto.getDescripcionProyecto());
        agregarCampo(document, "Complejidad", proyecto.getComplejidadProyecto());
        agregarCampo(document, "Estado", proyecto.getEstadoProyecto());
        agregarCampo(document, "Fecha Inicio", proyecto.getFechaInicioProyecto() != null ? proyecto.getFechaInicioProyecto().toString() : "N/A");
        agregarCampo(document, "Fecha Fin", proyecto.getFechaFinProyecto() != null ? proyecto.getFechaFinProyecto().toString() : "N/A");
        
        // Requisitos
        if (proyecto.getRequisitos() != null && !proyecto.getRequisitos().isEmpty()) {
            agregarSeccion(document, "REQUISITOS", colorPrimario);
            Table tablaRequisitos = new Table(UnitValue.createPercentArray(new float[]{1, 4, 2}));
            tablaRequisitos.setWidth(UnitValue.createPercentValue(100));
            
            tablaRequisitos.addHeaderCell(crearCeldaEncabezado("ID"));
            tablaRequisitos.addHeaderCell(crearCeldaEncabezado("Descripción"));
            tablaRequisitos.addHeaderCell(crearCeldaEncabezado("Prioridad"));
            
            for (Requisito req : proyecto.getRequisitos()) {
                tablaRequisitos.addCell(String.valueOf(req.getIdRequisitos()));
                tablaRequisitos.addCell(req.getDescripcionRequisitos() != null ? req.getDescripcionRequisitos() : "");
                tablaRequisitos.addCell(req.getPrioridadRequisitos() != null ? req.getPrioridadRequisitos() : "");
            }
            document.add(tablaRequisitos);
            document.add(new Paragraph("\n"));
        }
        
        // Sprints
        if (proyecto.getSprints() != null && !proyecto.getSprints().isEmpty()) {
            agregarSeccion(document, "SPRINTS", colorPrimario);
            Table tablaSprints = new Table(UnitValue.createPercentArray(new float[]{1, 3, 2, 2, 2}));
            tablaSprints.setWidth(UnitValue.createPercentValue(100));
            
            tablaSprints.addHeaderCell(crearCeldaEncabezado("ID"));
            tablaSprints.addHeaderCell(crearCeldaEncabezado("Nombre"));
            tablaSprints.addHeaderCell(crearCeldaEncabezado("Estado"));
            tablaSprints.addHeaderCell(crearCeldaEncabezado("Inicio"));
            tablaSprints.addHeaderCell(crearCeldaEncabezado("Fin"));
            
            for (Sprint sprint : proyecto.getSprints()) {
                tablaSprints.addCell(String.valueOf(sprint.getIdSprint()));
                tablaSprints.addCell(sprint.getNombreSprint() != null ? sprint.getNombreSprint() : "");
                tablaSprints.addCell(sprint.getEstadoSprint() != null ? sprint.getEstadoSprint() : "");
                tablaSprints.addCell(sprint.getFechaInicioSprint() != null ? sprint.getFechaInicioSprint().toString() : "");
                tablaSprints.addCell(sprint.getFechaFinSprint() != null ? sprint.getFechaFinSprint().toString() : "");
            }
            document.add(tablaSprints);
            document.add(new Paragraph("\n"));
        }
        
        // Colaboradores
        if (proyecto.getColaboradores() != null && !proyecto.getColaboradores().isEmpty()) {
            agregarSeccion(document, "COLABORADORES", colorPrimario);
            Table tablaColaboradores = new Table(UnitValue.createPercentArray(new float[]{1, 3, 3, 2, 2}));
            tablaColaboradores.setWidth(UnitValue.createPercentValue(100));
            
            tablaColaboradores.addHeaderCell(crearCeldaEncabezado("ID"));
            tablaColaboradores.addHeaderCell(crearCeldaEncabezado("Nombre"));
            tablaColaboradores.addHeaderCell(crearCeldaEncabezado("Email"));
            tablaColaboradores.addHeaderCell(crearCeldaEncabezado("Rol"));
            tablaColaboradores.addHeaderCell(crearCeldaEncabezado("Tarifa/Hora"));
            
            for (Colaborador col : proyecto.getColaboradores()) {
                tablaColaboradores.addCell(String.valueOf(col.getIdColaborador()));
                tablaColaboradores.addCell(col.getNombreColaborador() != null ? col.getNombreColaborador() : "");
                tablaColaboradores.addCell(col.getEmailColaborador() != null ? col.getEmailColaborador() : "");
                tablaColaboradores.addCell(col.getRolColaborador() != null ? col.getRolColaborador() : "");
                tablaColaboradores.addCell(col.getTarifaHoraProgramador() != null ? "$" + col.getTarifaHoraProgramador() : "");
            }
            document.add(tablaColaboradores);
            document.add(new Paragraph("\n"));
        }
        
        // Presupuestos
        if (proyecto.getPresupuestos() != null && !proyecto.getPresupuestos().isEmpty()) {
            agregarSeccion(document, "PRESUPUESTOS", colorPrimario);
            Table tablaPresupuestos = new Table(UnitValue.createPercentArray(new float[]{1, 2, 2, 2, 2, 2}));
            tablaPresupuestos.setWidth(UnitValue.createPercentValue(100));
            
            tablaPresupuestos.addHeaderCell(crearCeldaEncabezado("ID"));
            tablaPresupuestos.addHeaderCell(crearCeldaEncabezado("Subtotal"));
            tablaPresupuestos.addHeaderCell(crearCeldaEncabezado("IVA"));
            tablaPresupuestos.addHeaderCell(crearCeldaEncabezado("Total"));
            tablaPresupuestos.addHeaderCell(crearCeldaEncabezado("Estado"));
            tablaPresupuestos.addHeaderCell(crearCeldaEncabezado("Método Pago"));
            
            for (Presupuesto pre : proyecto.getPresupuestos()) {
                tablaPresupuestos.addCell(String.valueOf(pre.getIdPresupuesto()));
                tablaPresupuestos.addCell(pre.getSubtotal() != null ? "$" + pre.getSubtotal() : "");
                tablaPresupuestos.addCell(pre.getIva() != null ? "$" + pre.getIva() : "");
                tablaPresupuestos.addCell(pre.getTotal() != null ? "$" + pre.getTotal() : "");
                tablaPresupuestos.addCell(pre.getEstadoPresupuesto() != null ? pre.getEstadoPresupuesto() : "");
                tablaPresupuestos.addCell(pre.getMetodoPagoPresupuesto() != null ? pre.getMetodoPagoPresupuesto() : "");
            }
            document.add(tablaPresupuestos);
        }
        
        // Pie de página
        document.add(new Paragraph("\n\n"));
        Paragraph pie = new Paragraph("Documento generado automáticamente por el Sistema de Gestión Empresarial")
                .setFontSize(8)
                .setTextAlignment(TextAlignment.CENTER)
                .setFontColor(ColorConstants.GRAY);
        document.add(pie);
        
        document.close();
        return baos.toByteArray();
    }
    
    private void agregarSeccion(Document document, String titulo, DeviceRgb color) {
        Paragraph seccion = new Paragraph(titulo)
                .setFontSize(14)
                .setBold()
                .setFontColor(color)
                .setMarginTop(15)
                .setMarginBottom(10);
        document.add(seccion);
    }
    
    private void agregarCampo(Document document, String etiqueta, String valor) {
        Paragraph campo = new Paragraph()
                .add(new Paragraph(etiqueta + ": ").setBold().setFontSize(10))
                .add(new Paragraph(valor != null ? valor : "N/A").setFontSize(10))
                .setMarginBottom(5);
        document.add(campo);
    }
    
    private Cell crearCeldaEncabezado(String texto) {
        return new Cell()
                .add(new Paragraph(texto).setBold())
                .setBackgroundColor(new DeviceRgb(102, 126, 234))
                .setFontColor(ColorConstants.WHITE)
                .setTextAlignment(TextAlignment.CENTER);
    }
}
