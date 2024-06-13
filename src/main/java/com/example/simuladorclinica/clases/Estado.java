package com.example.simuladorclinica.clases;

public enum Estado {

  LIBRE("Libre"),
  OCUPADO("Ocupado"),
  ESPERANDO_ATENCION ("Esperando Atencion"),

  SIENDO_ATENDIDO ("Siendo Atendido"),

  ESPERANDO_RECEPCION ("Esperando Recepcion"),

  SIENDO_ATENDIDO_RECEPCION ("Siendo Atendido Recepcion");


  private String nombre;

  Estado(String nombre) {
    this.nombre = nombre;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }


}
