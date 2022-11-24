package com.Sprint2.Sprint2_SmartSoftTeam.servicios;

import com.Sprint2.Sprint2_SmartSoftTeam.modelos.MovimientoDinero;
import com.Sprint2.Sprint2_SmartSoftTeam.repositorio.MovimientoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MovimientoServicio {

    @Autowired
    MovimientoRepositorio movimientoRepositorio;

    public List<MovimientoDinero> getAllMovimientos(){
        List<MovimientoDinero> movimientoLista= new ArrayList<>();
        movimientoRepositorio.findAll().forEach(movimiento -> movimientoLista.add(movimiento));
        return movimientoLista;
    }
    public MovimientoDinero getMovimientoById(Integer id){ //Ver movimientos por id
        return movimientoRepositorio.findById(id).get();
    }
    public boolean saveOrUpdateMovimiento(MovimientoDinero movimientoDinero) { //Guardar o actualizar elementos
        MovimientoDinero mov = movimientoRepositorio.save(movimientoDinero);
        if (movimientoRepositorio.findById(mov.getId()) != null) {
            return true;
        }
        return false;
    }

    public boolean deleteMovimiento(Integer id){ //Eliminar movimiento por id
        movimientoRepositorio.deleteById(id); //Eliminar usando el metodo que nos ofrece el repositorio
        if(this.movimientoRepositorio.findById(id).isPresent()){ //Si al buscar el movimiento lo encontramos, no se eliminó (false)
            return false;
        }
        return true; //Si al buscar el movimiento no lo encontramos, si lo eliminò (true)
    }

    public ArrayList<MovimientoDinero> obtenerPorEmpresa(Integer id) { //Obtener movimientos teniendo en cuenta el id de la empresa a la que pertencen los empleados que la registraron
        return movimientoRepositorio.findByEmpresa(id);
    }

    //Servicio para ver la suma de todos los montos
    public Long obtenerSumaMontos(){
        return movimientoRepositorio.SumarMonto();
    }

    //Servicio para ver la suma de los montos por empleado
    public Long MontosPorEmpleado(Integer id){
        return movimientoRepositorio.MontosPorEmpleado(id);
    }

    //Servicio para ver la suma de los montos por empresa
    public Long MontosPorEmpresa(Integer id){
        return movimientoRepositorio.MontosPorEmpresa(id);
    }

    //servicio que nos deja conseguir el id de un empleado si tenemos su correo
    public Integer IdPorCorreo(String Correo){
        return movimientoRepositorio.IdPorCorreo(Correo);
    }

}