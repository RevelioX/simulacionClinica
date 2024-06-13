package com.example.simuladorclinica.clases;

import java.util.ArrayList;
import java.util.List;

public class Servidor {

  private List<Paciente> cola;
  private Estado estado;

  private TipoAtencion tipoAtencion;

  public Servidor( TipoAtencion tipoAtencion) {
    this.cola = new ArrayList<>();
    this.estado = Estado.LIBRE; //todo hay que hacer que estados sea un enum. El servidor se crea en estado libre.
    this.tipoAtencion = tipoAtencion;
  }

  public Paciente finalizarAtencion (Boolean b){
    if (b) {
      if (!cola.isEmpty()) {
        Paciente ultimo = cola.get(0);
        ultimo.setTipoAtencion(TipoAtencion.Recepcion);
        ultimo.setEstado(Estado.SIENDO_ATENDIDO_RECEPCION);
        cola.remove(0);

        if (!cola.isEmpty()) {
          Paciente siguiente = cola.get(0);
          siguiente.setEstado(Estado.SIENDO_ATENDIDO_RECEPCION);
        }
        return ultimo;
      } else {
        return null;
      }
    } else {
      if (!cola.isEmpty()) {
        Paciente eliminado = cola.remove(0);
        eliminado = null;
      }
      return null;
    }
  }

  public String getEstado(){
    return estado.getNombre();
  }

  public void añadirCola(Paciente paciente){
    if (cola.isEmpty()){
      paciente.setEstado(Estado.SIENDO_ATENDIDO);
    }else cola.add(paciente);
  }

  public int getLongitud(){
    return cola.size() - 1;
  }

  public void actualizarTiempoEsperaPacientes (Double d){

  }

  public TipoAtencion getTipoAtencion(){
    return this.tipoAtencion;
  }

  public String esTipoAtencion(TipoAtencion t){
    return t.getNombre();
  }









}
