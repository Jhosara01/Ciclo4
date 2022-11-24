package com.Sprint2.Sprint2_SmartSoftTeam.servicios;
import com.Sprint2.Sprint2_SmartSoftTeam.modelos.Empresa;
import com.Sprint2.Sprint2_SmartSoftTeam.repositorio.EmpresaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmpresaServicio {
    @Autowired //Se conecta con el repositorio empresa
    EmpresaRepositorio empresaRepositorio; //Se crea un objeto tipo empresa repositorio para poder usar los metodos

    //Metodo para ver todas las empresas (lista con todas las empresas) usando metodos heredados de jpaRepository
    public List<Empresa> getAllEmpresas() {
        return empresaRepositorio.findAll();
    }

        //Para llenar la lista con las empresas
        //https://docs.spring.io/spring-data/jpa/docs/current/api/
        //El "findAll" regresa un iterable por lo tanto se usa un ciclo for

        //empresaRepositorio.findAll(); //Se recorre el iterable que regresa el metodo findAll del JPA y se guarda en la lista
        //return efielectrica;
    //}

    // Metodo que trae un objeto de tipo Empresa cuando cuento con el id de la misma
    public Empresa getEmpresaById(Integer id){
        return empresaRepositorio.findById(id).get();
    }

    //Metodo para guardar o actualizar objetos de tipo empresa
    //public boolean SaveOrUpdateEmpresa(Empresa efielectrica) {

    public boolean saveOrUpdateEmpresa(Empresa efielectrica){
        Empresa emp=empresaRepositorio.save(efielectrica);
        if (empresaRepositorio.findById(emp.getId())!=null){
            return true;
        }
        return false;
    }

    //Metodo para eliminar empresas registradas teniendo el id
    public boolean deleteEmpresa(Integer id){
        empresaRepositorio.deleteById(id);
        if(getEmpresaById(id)!=null){

            return false;
        }
        return true;
    }
}