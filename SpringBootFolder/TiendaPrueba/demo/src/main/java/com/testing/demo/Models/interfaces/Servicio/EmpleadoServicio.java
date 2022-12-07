package com.testing.demo.Models.interfaces.Servicio;

import java.util.List;

import com.testing.demo.Models.Empleado;
import com.testing.demo.Models.interfaces.Repository.EmpleadoRepositorio;

public interface EmpleadoServicio {

    public List<Empleado> listarEmpleado();

    public Empleado guardarEmpleado(Empleado nuevoEmpleado);

    // Para obtner el empleado;
    // 1~ solicitamos el dni del empleado al usuario
    // 2~ validamos ese dni con el numero de registro.
    public Empleado obtenerEmpleado(Long id);

    public Empleado actualizarEmpleado(Empleado empleado);

    // para poder eliminar a un empleado;
    // 1~ solicitamos el dni del empleado al usuario que quiere eliminar
    // 2~ obtenemos el dni y obtenemos el numero del registro del empleado para
    // eliminarlo.
    public void eliminarEmpleado(Long id);

}
