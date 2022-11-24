package com.Sprint2.Sprint2_SmartSoftTeam.repositorio;
import com.Sprint2.Sprint2_SmartSoftTeam.modelos.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository //Anotacion que dice a Spring que esta clase es un repositorio
//Se crea repositorio y se mira con que entidad va a trabajar
public interface EmpresaRepositorio extends JpaRepository <Empresa,Integer>{
}
