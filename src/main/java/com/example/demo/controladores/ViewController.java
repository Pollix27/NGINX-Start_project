package com.example.demo.controladores;

import com.example.demo.entidades.*;
import com.example.demo.servicios.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ViewController {
    
    @Autowired
    private ClienteService clienteService;
    
    @Autowired
    private ProyectoService proyectoService;
    
    @Autowired
    private SprintService sprintService;
    
    @Autowired
    private RequisitoService requisitoService;
    
    @Autowired
    private ColaboradorService colaboradorService;
    
    @Autowired
    private PresupuestoService presupuestoService;
    
    @GetMapping("/")
    public String root() {
        return "redirect:/index";
    }
    
    @GetMapping("/index")
    public String index() {
        return "index";
    }
    
    @GetMapping("/clientes")
    public String clientes(Model model) {
        model.addAttribute("clientes", clienteService.listarTodos());
        return "clientes";
    }
    
    @PostMapping("/clientes/guardar")
    public String guardarCliente(Cliente cliente, RedirectAttributes redirectAttributes) {
        try {
            clienteService.guardar(cliente);
            redirectAttributes.addFlashAttribute("mensaje", "Cliente guardado exitosamente");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al guardar el cliente: " + e.getMessage());
        }
        return "redirect:/clientes";
    }
    
    @GetMapping("/clientes/editar/{id}")
    public String editarCliente(@PathVariable int id, Model model) {
        clienteService.buscarPorId(id).ifPresent(cliente -> model.addAttribute("cliente", cliente));
        model.addAttribute("clientes", clienteService.listarTodos());
        return "clientes";
    }
    
    @PostMapping("/clientes/eliminar/{id}")
    public String eliminarCliente(@PathVariable int id, RedirectAttributes redirectAttributes) {
        try {
            clienteService.eliminar(id);
            redirectAttributes.addFlashAttribute("mensaje", "Cliente eliminado exitosamente");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al eliminar el cliente: " + e.getMessage());
        }
        return "redirect:/clientes";
    }
    
    @GetMapping("/proyectos")
    public String proyectos(Model model) {
        model.addAttribute("proyectos", proyectoService.listarTodos());
        model.addAttribute("clientes", clienteService.listarTodos());
        return "proyectos";
    }
    
    @PostMapping("/proyectos/guardar")
    public String guardarProyecto(Proyecto proyecto, RedirectAttributes redirectAttributes) {
        try {
            proyectoService.guardar(proyecto);
            redirectAttributes.addFlashAttribute("mensaje", "Proyecto guardado exitosamente");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al guardar el proyecto: " + e.getMessage());
        }
        return "redirect:/proyectos";
    }
    
    @GetMapping("/proyectos/editar/{id}")
    public String editarProyecto(@PathVariable int id, Model model) {
        proyectoService.buscarPorId(id).ifPresent(proyecto -> model.addAttribute("proyecto", proyecto));
        model.addAttribute("proyectos", proyectoService.listarTodos());
        model.addAttribute("clientes", clienteService.listarTodos());
        return "proyectos";
    }
    
    @PostMapping("/proyectos/eliminar/{id}")
    public String eliminarProyecto(@PathVariable int id, RedirectAttributes redirectAttributes) {
        try {
            proyectoService.eliminar(id);
            redirectAttributes.addFlashAttribute("mensaje", "Proyecto eliminado exitosamente");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al eliminar el proyecto: " + e.getMessage());
        }
        return "redirect:/proyectos";
    }
    
    @GetMapping("/requisitos")
    public String requisitos(Model model) {
        model.addAttribute("requisitos", requisitoService.listarTodos());
        model.addAttribute("proyectos", proyectoService.listarTodos());
        model.addAttribute("sprints", sprintService.listarTodos());
        return "requisitos";
    }
    
    @PostMapping("/requisitos/guardar")
    public String guardarRequisito(Requisito requisito, RedirectAttributes redirectAttributes) {
        try {
            requisitoService.guardar(requisito);
            redirectAttributes.addFlashAttribute("mensaje", "Requisito guardado exitosamente");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al guardar el requisito: " + e.getMessage());
        }
        return "redirect:/requisitos";
    }
    
    @GetMapping("/requisitos/editar/{id}")
    public String editarRequisito(@PathVariable int id, Model model) {
        requisitoService.buscarPorId(id).ifPresent(requisito -> model.addAttribute("requisito", requisito));
        model.addAttribute("requisitos", requisitoService.listarTodos());
        model.addAttribute("proyectos", proyectoService.listarTodos());
        model.addAttribute("sprints", sprintService.listarTodos());
        return "requisitos";
    }
    
    @PostMapping("/requisitos/eliminar/{id}")
    public String eliminarRequisito(@PathVariable int id, RedirectAttributes redirectAttributes) {
        try {
            requisitoService.eliminar(id);
            redirectAttributes.addFlashAttribute("mensaje", "Requisito eliminado exitosamente");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al eliminar el requisito: " + e.getMessage());
        }
        return "redirect:/requisitos";
    }
    
    @GetMapping("/sprints")
    public String sprints(Model model) {
        model.addAttribute("sprints", sprintService.listarTodos());
        model.addAttribute("proyectos", proyectoService.listarTodos());
        return "sprints";
    }
    
    @PostMapping("/sprints/guardar")
    public String guardarSprint(Sprint sprint, RedirectAttributes redirectAttributes) {
        try {
            sprintService.guardar(sprint);
            redirectAttributes.addFlashAttribute("mensaje", "Sprint guardado exitosamente");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al guardar el sprint: " + e.getMessage());
        }
        return "redirect:/sprints";
    }
    
    @GetMapping("/sprints/editar/{id}")
    public String editarSprint(@PathVariable int id, Model model) {
        sprintService.buscarPorId(id).ifPresent(sprint -> model.addAttribute("sprint", sprint));
        model.addAttribute("sprints", sprintService.listarTodos());
        model.addAttribute("proyectos", proyectoService.listarTodos());
        return "sprints";
    }
    
    @PostMapping("/sprints/eliminar/{id}")
    public String eliminarSprint(@PathVariable int id, RedirectAttributes redirectAttributes) {
        try {
            sprintService.eliminar(id);
            redirectAttributes.addFlashAttribute("mensaje", "Sprint eliminado exitosamente");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al eliminar el sprint: " + e.getMessage());
        }
        return "redirect:/sprints";
    }
    
    @GetMapping("/colaboradores")
    public String colaboradores(Model model) {
        model.addAttribute("colaboradores", colaboradorService.listarTodos());
        model.addAttribute("proyectos", proyectoService.listarTodos());
        model.addAttribute("sprints", sprintService.listarTodos());
        return "colaboradores";
    }
    
    @PostMapping("/colaboradores/guardar")
    public String guardarColaborador(Colaborador colaborador, RedirectAttributes redirectAttributes) {
        try {
            colaboradorService.guardar(colaborador);
            redirectAttributes.addFlashAttribute("mensaje", "Colaborador guardado exitosamente");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al guardar el colaborador: " + e.getMessage());
        }
        return "redirect:/colaboradores";
    }
    
    @GetMapping("/colaboradores/editar/{id}")
    public String editarColaborador(@PathVariable int id, Model model) {
        colaboradorService.buscarPorId(id).ifPresent(colaborador -> model.addAttribute("colaborador", colaborador));
        model.addAttribute("colaboradores", colaboradorService.listarTodos());
        model.addAttribute("proyectos", proyectoService.listarTodos());
        model.addAttribute("sprints", sprintService.listarTodos());
        return "colaboradores";
    }
    
    @PostMapping("/colaboradores/eliminar/{id}")
    public String eliminarColaborador(@PathVariable int id, RedirectAttributes redirectAttributes) {
        try {
            colaboradorService.eliminar(id);
            redirectAttributes.addFlashAttribute("mensaje", "Colaborador eliminado exitosamente");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al eliminar el colaborador: " + e.getMessage());
        }
        return "redirect:/colaboradores";
    }
    
    @GetMapping("/presupuestos")
    public String presupuestos(Model model) {
        model.addAttribute("presupuestos", presupuestoService.listarTodos());
        model.addAttribute("proyectos", proyectoService.listarTodos());
        model.addAttribute("requisitos", requisitoService.listarTodos());
        model.addAttribute("colaboradores", colaboradorService.listarTodos());
        return "presupuestos";
    }
    
    @PostMapping("/presupuestos/guardar")
    public String guardarPresupuesto(Presupuesto presupuesto, RedirectAttributes redirectAttributes) {
        try {
            presupuestoService.guardar(presupuesto);
            redirectAttributes.addFlashAttribute("mensaje", "Presupuesto guardado exitosamente");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al guardar el presupuesto: " + e.getMessage());
        }
        return "redirect:/presupuestos";
    }
    
    @GetMapping("/presupuestos/editar/{id}")
    public String editarPresupuesto(@PathVariable int id, Model model) {
        presupuestoService.buscarPorId(id).ifPresent(presupuesto -> model.addAttribute("presupuesto", presupuesto));
        model.addAttribute("presupuestos", presupuestoService.listarTodos());
        model.addAttribute("proyectos", proyectoService.listarTodos());
        model.addAttribute("requisitos", requisitoService.listarTodos());
        model.addAttribute("colaboradores", colaboradorService.listarTodos());
        return "presupuestos";
    }
    
    @PostMapping("/presupuestos/eliminar/{id}")
    public String eliminarPresupuesto(@PathVariable int id, RedirectAttributes redirectAttributes) {
        try {
            presupuestoService.eliminar(id);
            redirectAttributes.addFlashAttribute("mensaje", "Presupuesto eliminado exitosamente");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al eliminar el presupuesto: " + e.getMessage());
        }
        return "redirect:/presupuestos";
    }
    
    @GetMapping("/reporte")
    public String reporte(Model model) {
        model.addAttribute("clientes", clienteService.listarTodos());
        return "reporte";
    }
}
