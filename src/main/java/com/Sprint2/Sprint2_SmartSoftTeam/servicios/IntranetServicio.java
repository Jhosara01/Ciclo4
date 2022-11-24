package com.Sprint2.Sprint2_SmartSoftTeam.servicios;
import com.Sprint2.Sprint2_SmartSoftTeam.repositorio.EmpresaRepositorio;
import com.Sprint2.Sprint2_SmartSoftTeam.repositorio.IntranetRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IntranetServicio {

    @Autowired //Se conecta con el repositorio
    IntranetRepositorio intranetRepositorio; //Se crea un objeto tipo intranet repositorio para poder usar los metodos

}
