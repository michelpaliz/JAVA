package com.testing.demo.Models;

import java.time.LocalDate;
import java.time.Period;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.testing.demo.Models.interfaces.IFechaNacimiento;

import lombok.Data;

// @Table(name = "empleados")
@Data
@MappedSuperclass
public abstract class Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "numero_registro")
    private long numero_registro;
    @NotNull
    @Size(min = 9, max = 9, message = "El dni no es correcto")
    @Column(name = "dni")
    private String dni;
    @Column(name = "nombre")
    @NotNull
    @Size(min = 1, max = 30, message = "El nombre no es correcto")
    private String nombre;
    @Column(name = "email")
    @NotNull
    @Email(message = "El email debe ser valido")
    @Size(min = 1, message = "El email es requerido")
    private String email;
    @Column(name = "fechaNacimiento")
    @NotNull(message = "La fecha de nacimiento es requerida")
    @Past
    // @DateTimeFormat(pattern = "dd-MM-yyyy")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaNacimiento;
    @Column(name = "edad")
    private Integer edad;

    public Persona() {
    }

    // En este constructo no utlizo el id; lo va a generar mi base de datos
    public Persona(String dni, String nombre, String email, LocalDate fechaNacimiento) {
        this.dni = dni;
        this.nombre = nombre;
        this.email = email;
        this.fechaNacimiento = fechaNacimiento;
        this.edad = calcularEdad();
    }

    public void setNumeroRegistro(Long numeroRegistro) {
        this.numero_registro = numeroRegistro;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    /**
     * @return
     */
    public Integer calcularEdad() {
        if (this.fechaNacimiento == null) {
            return 0;
        }
        return Period.between(this.fechaNacimiento, LocalDate.now()).getYears();
    }

    @Override
    public String toString() {
        return "Persona{" +
                "dni='" + dni + '\'' +
                ", nombre='" + nombre + '\'' +
                ", email='" + email + '\'' +
                ", fechaNacimiento=" + fechaNacimiento +
                ", edad=" + edad +
                '}';
    }
}
