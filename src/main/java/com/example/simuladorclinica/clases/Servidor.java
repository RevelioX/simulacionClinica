package com.example.simuladorclinica.clases;

import java.util.List;

public class Servidor {

  private List<Paciente> cola;
  private Estado estado;

  private TipoAtencion tipoAtencion;

  public Servidor(List<Paciente> cola, Estado estado, TipoAtencion tipoAtencion) {
    this.cola = cola;
    this.estado = estado;
    this.tipoAtencion = tipoAtencion;
  }

  public Paciente finalizarAtencion (Boolean b){
    if (b) {
      Paciente ultimo = cola.get(0);
      ultimo.setTipoAtencion(TipoAtencion.Recepcion);

      return ultimo;
    } else {
      Paciente elimando = cola.remove(0);
      elimando = null;
      return null;
    }
  }

  public String getEstado(){
    return estado.getNombre();
  }

  public void añadirCola(Paciente paciente){
    cola.add(paciente);
  }

  public int getLongitud(){
    return cola.size() - 1;
  }

  public void actualizarTiempoEsperaPacientes (Double d){

  }

  public String esTipoAtencion(TipoAtencion t){
    return t.getNombre();
  }









}
