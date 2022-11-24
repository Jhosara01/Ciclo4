package com.Sprint2.Sprint2_SmartSoftTeam.servicios;
import com.Sprint2.Sprint2_SmartSoftTeam.modelos.Empleado;
import com.Sprint2.Sprint2_SmartSoftTeam.repositorio.EmpleadoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmpleadoServicio {

    @Autowired
    EmpleadoRepositorio empleadoRepositorio;
    //Metodo para ver todos los empleados registrados
    public List<Empleado> getAllEmpleado(){
        List<Empleado> empleadoList = new ArrayList<>();
        empleadoRepositorio.findAll().forEach(empleado -> empleadoList.add(empleado));
        return empleadoList;
    }
    // Metodo para buscar Empleado por ID
    public Optional<Empleado> getEmpleadoById(Integer Id) {
        return empleadoRepositorio.findById(Id);
    }

    //Metodo para buscar empleados por empresa
    public ArrayList<Empleado> obtenerPorEmpresa(Integer id){
        return empleadoRepositorio.findByEmpresa(id);
    }

    //Metodo para guardar o actualizar registros en Empleados
    public boolean saveOrUpdateEmpleado(Empleado empl){
        Empleado emp=empleadoRepositorio.save(empl);
        if (empleadoRepositorio.findById(emp.getId())!=null){
            return true;
        }
        return false;
    }

    //Metodo para eliminar un registro de Empleado por Id
    public boolean deleteEmpleado(Integer id){
        empleadoRepositorio.deleteById(id);
        if(this.empleadoRepositorio.findById(id).isPresent()){
            return false;
        }
        return true;
    }
}
