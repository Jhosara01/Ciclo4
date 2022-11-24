package com.Sprint2.Sprint2_SmartSoftTeam.controlador;

import com.Sprint2.Sprint2_SmartSoftTeam.servicios.EmpresaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class Inicio_Controlador {
    @Autowired
    EmpresaServicio empresaServicio;

    @GetMapping("/inicio")
    public String inicio(Model modelo ){
        return "inicio"; //El "inicio" es un archivo html donde puedo ver todas informacion general de la empresa
    }
}
