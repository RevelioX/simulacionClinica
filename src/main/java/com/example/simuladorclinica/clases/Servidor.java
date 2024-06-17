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

  public Paciente finalizarAtencion(Boolean continuaAtencion) {
    System.out.println(tipoAtencion.toString() + " - " + cola.size());
    if (cola.isEmpty()) {
      estado = Estado.LIBRE;
      return null;
    }

    Paciente atendido = cola.remove(0);
    if (continuaAtencion) {
      atendido.setTipoAtencion(TipoAtencion.Recepcion);
      atendido.setEstado(Estado.SIENDO_ATENDIDO_RECEPCION);

      if (!cola.isEmpty()) {
        Paciente siguiente = cola.get(0);
        siguiente.setEstado(Estado.SIENDO_ATENDIDO);
      } else {
        estado = Estado.LIBRE;
      }

      return atendido;
    } else {
      return atendido;
    }
  }

  public Estado getEstado(){
    return estado;
  }

  public void añadirCola(Paciente paciente){
    estado = Estado.OCUPADO;

    if (cola.isEmpty()){
      paciente.setEstado(Estado.SIENDO_ATENDIDO);
    }else{
      paciente.setEstado(Estado.ESPERANDO_ATENCION);
    }
    cola.add(paciente);
  }

  public int getLongitud(){
    return cola.size() - 1;
  }

  public void actualizarTiempoEsperaPacientes (Double tiempo){
    if (cola == null || cola.isEmpty()) {
      return; // No hay pacientes en la cola
    }

    for (int i = 1; i < cola.size(); i++) {
      Paciente paciente = cola.get(i);
      paciente.aumentarTiempoEspera(tiempo);
    }
  }

  public TipoAtencion getTipoAtencion(){
    return this.tipoAtencion;
  }

  public Boolean esTipoAtencion(TipoAtencion t)
  {
    if (t == null) {
      return false;
    }
    return this.tipoAtencion.equals(t);
  }









}
