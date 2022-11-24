package com.Sprint2.Sprint2_SmartSoftTeam.controlador;

import com.Sprint2.Sprint2_SmartSoftTeam.modelos.Empleado;
import com.Sprint2.Sprint2_SmartSoftTeam.modelos.Empresa;
import com.Sprint2.Sprint2_SmartSoftTeam.modelos.MovimientoDinero;
import com.Sprint2.Sprint2_SmartSoftTeam.repositorio.EmpresaRepositorio;
import com.Sprint2.Sprint2_SmartSoftTeam.repositorio.IntranetRepositorio;
import com.Sprint2.Sprint2_SmartSoftTeam.repositorio.MovimientoRepositorio;
import com.Sprint2.Sprint2_SmartSoftTeam.servicios.EmpleadoServicio;
import com.Sprint2.Sprint2_SmartSoftTeam.servicios.EmpresaServicio;
import com.Sprint2.Sprint2_SmartSoftTeam.servicios.IntranetServicio;
import com.Sprint2.Sprint2_SmartSoftTeam.servicios.MovimientoServicio;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
public class Intranet_Controlador {

    @Autowired
    IntranetServicio intranetServicio;
    @Autowired
    EmpresaServicio empresaServicio;
    @Autowired
    EmpleadoServicio empleadoServicio;

    @Autowired
    MovimientoServicio movimientoServicio;

    @Autowired
    MovimientoRepositorio movimientoRepositorio;

    @GetMapping("/intranet")
    public String intranet(Model modelo) {
        return "intranet";
    }

    //EMPRESAS
    @GetMapping("/VerEmpresas")
    public String verEmpresas(Model modelo, @ModelAttribute("mensaje") String mensaje) {
        List<Empresa> listaEmpresas = empresaServicio.getAllEmpresas();
        modelo.addAttribute("emplista", listaEmpresas);
        modelo.addAttribute("mensaje", mensaje);

        return "verEmpresas";
    }

    @GetMapping("/AgregarEmpresa")
    public String agregarEmpresa( Model modelo) {
        modelo.addAttribute("empresa", new Empresa());
        //modelo.addAttribute("mensaje", mensaje);
        return "agregarEmpresa";
    }

    @PostMapping("/AddEnterprise")
    public String guardarEmpresa(@ModelAttribute Empresa empresa, Model modelo) {
        empresaServicio.saveOrUpdateEmpresa(empresa);

        /*if (empresaServicio.saveOrUpdateEmpresa(empresa) == true) {
            redirectAttributes.addFlashAttribute("mensaje", "saveOK");
            return "redirect:/VerEmpresas";
        }
        redirectAttributes.addFlashAttribute("mensaje", "saveError");*/
        return "redirect:/VerEmpresas";

    }

    /*@PostMapping("/GuardarEmpresa")
    public String guardarEmpresa(Empresa emp, RedirectAttributes redirectAtributes){
     Empresa empresultante= new Empresa();
    empresultante=empresaServicio.saveOrUpdateEmpresa(emp);

    if(empresultante.getId()!=0){
     return "redirect:/VerEmpresas";
    }
    return "redirect:/AgregarEmpresa";
    }*/

    @GetMapping("/EditarEmpresa/{id}")
    public String editarEmpresa(Model modelo, @PathVariable Integer id, @ModelAttribute("mensaje") String mensaje) {
        Empresa emp = empresaServicio.getEmpresaById(id);
        modelo.addAttribute("mensaje", mensaje);
        modelo.addAttribute("emp", emp);

        return "editarEmpresa";
    }

    @PostMapping("/ActualizarEmpresa")
    public String actualizarEmpresa(Empresa emp, RedirectAttributes redirectAttributes) {

        if (empresaServicio.saveOrUpdateEmpresa(emp) == true) {
            redirectAttributes.addFlashAttribute("mensaje", "updateOK");
            return "redirect:/VerEmpresas";
        }
        redirectAttributes.addFlashAttribute("mensaje", "updateError");
        return "redirect:/ActualizarEmpresa";

    }

    @GetMapping("/EliminarEmpresa/{id}")
    public String eliminarEmpresa(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        try {
            empresaServicio.deleteEmpresa(id);

        } catch (Exception a) {
            redirectAttributes.addFlashAttribute("mensaje", "deleteError");
            return "redirect:/VerEmpresas";
        }

        redirectAttributes.addFlashAttribute("mensaje", "deleteOK");
        return "redirect:/VerEmpresas";

    }

    //USUARIO O EMPLEADO
    @GetMapping("/VerEmpleados")
    public String viewEmpleados(Model model, @ModelAttribute("mensaje") String mensaje) {
        List<Empleado> listaEmpleados = empleadoServicio.getAllEmpleado();
        model.addAttribute("emplelist", listaEmpleados);
        model.addAttribute("mensaje", mensaje);
        return "verEmpleados";//Llamamos HTML

    }
    @GetMapping("/AgregarEmpleado")
    public String agregarEmpleado(@ModelAttribute Empleado empleado, Model modelo) {
        Empleado empl = new Empleado();
        modelo.addAttribute("empleado", empl);
        //modelo.addAttribute("mensaje", mensaje);
        return "agregarEmpleado";
    }

    @PostMapping("/AgregarEmpleado")
    public String guardarEmpleado(Empleado empl, RedirectAttributes redirectAttributes) {

        if (empleadoServicio.saveOrUpdateEmpleado(empl) == true) {
            redirectAttributes.addFlashAttribute("mensaje", "updateOK");
            return "redirect:/VerEmpleados";
        }
        redirectAttributes.addFlashAttribute("mensaje", "updateError");
        return "redirect:/ActualizarEmpleado";

    }

    @GetMapping("/EditarEmpleado/{id}")
    public String editarEmpleado(Model model, @PathVariable Integer id, @ModelAttribute("mensaje") String mensaje) {
        Empleado empl = empleadoServicio.getEmpleadoById(id).get();
        //Creamos un atributo para el modelo, que se llame igualmente empl y es el que ira al html para llenar o alimentar campos
        model.addAttribute("empl", empl);
        model.addAttribute("mensaje", mensaje);
        List<Empresa> listaEmpresas = empresaServicio.getAllEmpresas();
        model.addAttribute("emprelist", listaEmpresas);
        return "editarEmpleado";
    }

    @PostMapping("/ActualizarEmpleado")
    public String updateEmpleado(@ModelAttribute("empl") Empleado empl, RedirectAttributes redirectAttributes) {

        if (empleadoServicio.saveOrUpdateEmpleado(empl)) {
            redirectAttributes.addFlashAttribute("mensaje", "updateOK");
            return "redirect:/VerEmpleados";
        }
        redirectAttributes.addFlashAttribute("mensaje", "updateError");
        return "redirect:/EditarEmpleado/" + empl.getId();

    }

    @GetMapping("/EliminarEmpleado/{id}")
    public String eliminarEmpleado(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        if (empleadoServicio.deleteEmpleado(id)) {
            redirectAttributes.addFlashAttribute("mensaje", "deleteOK");
            return "redirect:/VerEmpleados";
        }
        redirectAttributes.addFlashAttribute("mensaje", "deleteError");
        return "redirect:/VerEmpleados";
    }

    //MOVIMIENTOS
    @GetMapping("/VerMovimientos")
    public String verMovimientos(Model modelo, @ModelAttribute("mensaje") String mensaje){
        List<MovimientoDinero> ListaMovimientos=movimientoServicio.getAllMovimientos();
        modelo.addAttribute("movLista",ListaMovimientos);
        modelo.addAttribute("mensaje",mensaje);

        return "verMovimientos";
    }

    @GetMapping("/AgregarMovimiento")
    public String nuevoMovimiento(Model modelo, @ModelAttribute("mensaje") String mensaje){
        MovimientoDinero movimiento= new MovimientoDinero();
        modelo.addAttribute("mov", movimiento);
        modelo.addAttribute("mensaje",mensaje);
        List<Empleado> ListaEmpleados=empleadoServicio.getAllEmpleado();
        modelo.addAttribute("empleList",ListaEmpleados);
        return "agregarMovimiento";
    }

    @PostMapping("/AgregarMovimiento")
    public String guardarMovimiento(MovimientoDinero mov, RedirectAttributes redirectAttributes){
        if(movimientoServicio.saveOrUpdateMovimiento(mov)){
            redirectAttributes.addAttribute("mensaje","SaveOK");
            return "redirect:/VerMovimientos";
        }
        return "redirect:/AgregarMovimiento";
    }

    @GetMapping("/EditarMovimiento/{id}")
    public String editarMovimento(Model model, @PathVariable Integer id, @ModelAttribute("mensaje") String mensaje){
        MovimientoDinero mov=movimientoServicio.getMovimientoById(id);
        //Creamos un atributo para el modelo, que se llame igualmente empl y es el que ira al html para llenar o alimentar campos
        model.addAttribute("mov",mov);
        model.addAttribute("mensaje", mensaje);
        List<Empleado> listaEmpleados= empleadoServicio.getAllEmpleado();
        model.addAttribute("emplelist",listaEmpleados);
        return "editarMovimiento";
    }

    @GetMapping("/EliminarMovimiento/{id}")
    public String eliminarMovimiento(@PathVariable Integer id, RedirectAttributes redirectAttributes){
        if(movimientoServicio.deleteMovimiento(id)){
            redirectAttributes.addAttribute("mensaje","deleteOK");
            return "redirect:/VerMovimientos";
        }
        redirectAttributes.addAttribute("mensaje","deleteError");
        return "redirect:/VerMovimientos";
    }


}

