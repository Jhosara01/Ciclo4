package com.Sprint2.Sprint2_SmartSoftTeam.modelos;
import javax.persistence.*;

//enum TipoEmpleado { ADMINISTRADOR, OPERATIVO }

@Entity
@Table(name = "Empleado")

public class Empleado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nombre;
    private String correo;
    @ManyToOne
    @JoinColumn(name = "efielectrica_id")
    private Empresa Efielectrica;
    private Boolean estado;

    public Empleado() {
    }
    public Empleado(int id, String nombre, String correo, Empresa efielectrica, Boolean estado) {
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
        this.Efielectrica = efielectrica;
        this.estado = estado;
    }
//public TipoEmpleado rol; // Admin = true

    /*public Empleado(String nombre, String correo, Empresa efielectrica, TipoEmpleado rol) {
        this.nombre = nombre;
        this.correo = correo;
        Efielectrica = efielectrica;
        this.rol = TipoEmpleado.OPERATIVO;
    }*/

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Empresa getEfielectrica() {
        return Efielectrica;
    }

    public void setEfielectrica(Empresa efielectrica) {
        Efielectrica = efielectrica;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }
}
