package com.example.simuladorclinica.clases;

public class TipoAtencion {
    // Atributo de la clase
    private String nombre;

    // Constructor
    public TipoAtencion(String nombre) {
        this.nombre = nombre;
    }

    // Método getter para el atributo 'nombre'
    public String getNombre() {
        return nombre;
    }

    // Método setter para el atributo 'nombre'
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // Método toString para representar el objeto como una cadena
    @Override
    public String toString() {
        return "TipoAtencion{" +
                "nombre='" + nombre + '\'' +
                '}';
    }

}
