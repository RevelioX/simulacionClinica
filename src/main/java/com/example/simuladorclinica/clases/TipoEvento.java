package com.example.simuladorclinica.clases;

public class TipoEvento {

  private String nombre;

  private String descripcion;

  public TipoEvento(String nombre, String descripcion) {
    this.nombre = nombre;
    this.descripcion = descripcion;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public String getDescripcion() {
    return descripcion;
  }

  public void setDescripcio(String descripcion) {
    this.descripcion = descripcion;
  }
}
