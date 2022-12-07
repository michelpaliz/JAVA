package com.testing.demo.Models.interfaces.Implementacion;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import com.testing.demo.App;
import com.testing.demo.Models.Empleado;
import com.testing.demo.Models.interfaces.Repository.EmpleadoRepositorio;
import com.testing.demo.Models.interfaces.Servicio.EmpleadoServicio;

@Service
public class EmpleadoServicioImp implements EmpleadoServicio {

    @Autowired
    private EmpleadoRepositorio empleadoRepositorio;

    public void setEmpleadoRepositorio(EmpleadoRepositorio empleadoRepositorio) {
        this.empleadoRepositorio = empleadoRepositorio;
    }

    public EmpleadoRepositorio getEmpleadoRepositorio() {
        return empleadoRepositorio;
    }

    public void eleminarTodo() {
        empleadoRepositorio.deleteAll();
    }

    @Override
    public List<Empleado> listarEmpleado() {
        return empleadoRepositorio.findAll();
    }

    @Override
    public Empleado guardarEmpleado(Empleado nuevoEmpleado) {
        return empleadoRepositorio.save(nuevoEmpleado);
    }

    @Override
    public Empleado obtenerEmpleado(Long id) {
        return empleadoRepositorio.findById(id).get();
    }

    @Override
    public Empleado actualizarEmpleado(Empleado empleado) {
        return empleadoRepositorio.save(empleado);
    }

    @Override
    public void eliminarEmpleado(Long id) {
        empleadoRepositorio.deleteById(id);
    }

    // public Iterable<Empleado> empleadoServicio.listarEmpleado() {
    // return empleadoRepositorio.findAll();
    // }

    // @ModelAttribute
    // public Iterable<Empleado> clasificarTipo(EProfesion profesion) {
    // return empleados.stream().filter(x ->
    // x.getProfesion().equals(profesion)).collect(Collectors.toList());
    // }

}
