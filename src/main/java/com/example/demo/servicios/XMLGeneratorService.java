package com.example.demo.servicios;

import com.example.demo.entidades.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.StringWriter;
import java.util.Optional;

@Service
public class XMLGeneratorService {
    
    @Autowired
    private ProyectoService proyectoService;
    
    public String generarReporteProyecto(int idProyecto) throws Exception {
        Optional<Proyecto> proyectoOpt = proyectoService.buscarPorId(idProyecto);
        
        if (proyectoOpt.isEmpty()) {
            throw new Exception("Proyecto no encontrado");
        }
        
        Proyecto proyecto = proyectoOpt.get();
        
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        docFactory.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
        docFactory.setFeature("http://xml.org/sax/features/external-general-entities", false);
        docFactory.setFeature("http://xml.org/sax/features/external-parameter-entities", false);
        docFactory.setXIncludeAware(false);
        docFactory.setExpandEntityReferences(false);
        DocumentBuilder builder = docFactory.newDocumentBuilder();
        Document doc = builder.newDocument();
        
        Element root = doc.createElement("ReporteProyecto");
        doc.appendChild(root);
        
        // Información del Proyecto
        Element proyectoElement = doc.createElement("Proyecto");
        root.appendChild(proyectoElement);
        
        agregarElemento(doc, proyectoElement, "ID", String.valueOf(proyecto.getIdProyecto()));
        agregarElemento(doc, proyectoElement, "Nombre", proyecto.getNombreProyecto());
        agregarElemento(doc, proyectoElement, "Descripcion", proyecto.getDescripcionProyecto());
        agregarElemento(doc, proyectoElement, "Complejidad", proyecto.getComplejidadProyecto());
        agregarElemento(doc, proyectoElement, "FechaInicio", proyecto.getFechaInicioProyecto() != null ? proyecto.getFechaInicioProyecto().toString() : "");
        agregarElemento(doc, proyectoElement, "FechaFin", proyecto.getFechaFinProyecto() != null ? proyecto.getFechaFinProyecto().toString() : "");
        agregarElemento(doc, proyectoElement, "Estado", proyecto.getEstadoProyecto());
        
        // Información del Cliente
        if (proyecto.getCliente() != null) {
            Element clienteElement = doc.createElement("Cliente");
            proyectoElement.appendChild(clienteElement);
            
            agregarElemento(doc, clienteElement, "ID", String.valueOf(proyecto.getCliente().getIdCliente()));
            agregarElemento(doc, clienteElement, "Empresa", proyecto.getCliente().getEmpresaCliente());
            agregarElemento(doc, clienteElement, "RFC", proyecto.getCliente().getRfcCliente());
            agregarElemento(doc, clienteElement, "Email", proyecto.getCliente().getEmailCliente());
            agregarElemento(doc, clienteElement, "Telefono", proyecto.getCliente().getTelefonoCliente());
        }
        
        // Requisitos
        if (proyecto.getRequisitos() != null && !proyecto.getRequisitos().isEmpty()) {
            Element requisitosElement = doc.createElement("Requisitos");
            root.appendChild(requisitosElement);
            
            for (Requisito req : proyecto.getRequisitos()) {
                Element requisitoElement = doc.createElement("Requisito");
                requisitosElement.appendChild(requisitoElement);
                
                agregarElemento(doc, requisitoElement, "ID", String.valueOf(req.getIdRequisitos()));
                agregarElemento(doc, requisitoElement, "Descripcion", req.getDescripcionRequisitos());
                agregarElemento(doc, requisitoElement, "Prioridad", req.getPrioridadRequisitos());
            }
        }
        
        // Sprints
        if (proyecto.getSprints() != null && !proyecto.getSprints().isEmpty()) {
            Element sprintsElement = doc.createElement("Sprints");
            root.appendChild(sprintsElement);
            
            for (Sprint sprint : proyecto.getSprints()) {
                Element sprintElement = doc.createElement("Sprint");
                sprintsElement.appendChild(sprintElement);
                
                agregarElemento(doc, sprintElement, "ID", String.valueOf(sprint.getIdSprint()));
                agregarElemento(doc, sprintElement, "Nombre", sprint.getNombreSprint());
                agregarElemento(doc, sprintElement, "Estado", sprint.getEstadoSprint());
                agregarElemento(doc, sprintElement, "FechaInicio", sprint.getFechaInicioSprint() != null ? sprint.getFechaInicioSprint().toString() : "");
                agregarElemento(doc, sprintElement, "FechaFin", sprint.getFechaFinSprint() != null ? sprint.getFechaFinSprint().toString() : "");
            }
        }
        
        // Colaboradores
        if (proyecto.getColaboradores() != null && !proyecto.getColaboradores().isEmpty()) {
            Element colaboradoresElement = doc.createElement("Colaboradores");
            root.appendChild(colaboradoresElement);
            
            for (Colaborador col : proyecto.getColaboradores()) {
                Element colaboradorElement = doc.createElement("Colaborador");
                colaboradoresElement.appendChild(colaboradorElement);
                
                agregarElemento(doc, colaboradorElement, "ID", String.valueOf(col.getIdColaborador()));
                agregarElemento(doc, colaboradorElement, "Nombre", col.getNombreColaborador());
                agregarElemento(doc, colaboradorElement, "Email", col.getEmailColaborador());
                agregarElemento(doc, colaboradorElement, "Rol", col.getRolColaborador());
                agregarElemento(doc, colaboradorElement, "TarifaHora", col.getTarifaHoraProgramador() != null ? col.getTarifaHoraProgramador().toString() : "");
            }
        }
        
        // Presupuestos
        if (proyecto.getPresupuestos() != null && !proyecto.getPresupuestos().isEmpty()) {
            Element presupuestosElement = doc.createElement("Presupuestos");
            root.appendChild(presupuestosElement);
            
            for (Presupuesto pre : proyecto.getPresupuestos()) {
                Element presupuestoElement = doc.createElement("Presupuesto");
                presupuestosElement.appendChild(presupuestoElement);
                
                agregarElemento(doc, presupuestoElement, "ID", String.valueOf(pre.getIdPresupuesto()));
                agregarElemento(doc, presupuestoElement, "Subtotal", pre.getSubtotal() != null ? pre.getSubtotal().toString() : "");
                agregarElemento(doc, presupuestoElement, "IVA", pre.getIva() != null ? pre.getIva().toString() : "");
                agregarElemento(doc, presupuestoElement, "Total", pre.getTotal() != null ? pre.getTotal().toString() : "");
                agregarElemento(doc, presupuestoElement, "FechaElaboracion", pre.getFechaElaboracionPresupuesto() != null ? pre.getFechaElaboracionPresupuesto().toString() : "");
                agregarElemento(doc, presupuestoElement, "Estado", pre.getEstadoPresupuesto());
                agregarElemento(doc, presupuestoElement, "MetodoPago", pre.getMetodoPagoPresupuesto());
                agregarElemento(doc, presupuestoElement, "Descripcion", pre.getDescripcionPresupuesto());
            }
        }
        
        // Convertir a String
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        transformerFactory.setAttribute("http://javax.xml.XMLConstants/property/accessExternalDTD", "");
        transformerFactory.setAttribute("http://javax.xml.XMLConstants/property/accessExternalStylesheet", "");
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
        
        StringWriter writer = new StringWriter();
        transformer.transform(new DOMSource(doc), new StreamResult(writer));
        
        return writer.toString();
    }
    
    private void agregarElemento(Document doc, Element parent, String nombre, String valor) {
        Element element = doc.createElement(nombre);
        element.setTextContent(valor != null ? valor : "");
        parent.appendChild(element);
    }
}
