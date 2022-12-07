package com.testing.demo.Numeric;

public enum EProfesion {
    jardinero("jardinero"), piscinero("piscinero"), podador_palmeras("podador palmeras");

    private final String nombre;

    EProfesion(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public EProfesion getRandom() {
        return values()[(int) (Math.random() * values().length)];
    }

}
