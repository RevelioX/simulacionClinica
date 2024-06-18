package com.example.simuladorclinica.clases;

import java.util.ArrayList;
import java.util.List;

public class Servidor {

  private int id;

  private List<Paciente> cola;
  private Estado estado;
  private TipoAtencion tipoAtencion;

  public Servidor( TipoAtencion tipoAtencion, int id) {
    this.id = id;
    this.cola = new ArrayList<>();
    this.estado = Estado.LIBRE;
    this.tipoAtencion = tipoAtencion;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public List<Paciente> getCola() {
    return cola;
  }

  public Paciente finalizarAtencion(Boolean continuaAtencion) {
    if (cola.isEmpty()) {
      estado = Estado.LIBRE;
      return null;
    }

    Paciente atendido = cola.remove(0);
    if (continuaAtencion) {
     // atendido.setTipoAtencion(TipoAtencion.Recepcion);
     // atendido.setEstado(Estado.ESPERANDO_RECEPCION);

      if (!cola.isEmpty()) {
        Paciente siguiente = cola.get(0);
        siguiente.setEstado(Estado.SIENDO_ATENDIDO);
      } else {
        estado = Estado.LIBRE;
      }

      return atendido;
    } else {
      if(cola.isEmpty()){
        estado = Estado.LIBRE;
      }
      return atendido;
    }
  }

  public Estado getEstado(){
    return estado;
  }

  public void añadirCola(Paciente paciente){
    estado = Estado.OCUPADO;

    if (cola.isEmpty()){ //todo hay q añadir el manejo de los casos q este servidor sea "Recepcion"
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

  @Override
  public String toString() {
    return "Servidor{" +
            "estado=" + estado +
            ", tipoAtencion=" + tipoAtencion.getNombre() +
            '}';
  }
}
