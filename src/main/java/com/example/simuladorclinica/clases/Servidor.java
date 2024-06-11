package com.example.simuladorclinica.clases;

import java.util.List;

public class Servidor {

  private List<Paciente> cola;
  private Estado estado;

  private TipoAtencion tipoAtencion;

  public String getEstado(){
    return estado.getNombre();
  }



}
