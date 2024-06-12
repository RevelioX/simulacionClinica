package com.example.simuladorclinica.clases;

public enum TipoEvento {
  LLEGADA_PACIENTE_GENERAL("Llegada Paciente General"),
  LLEGADA_PACIENTE_EMERGENCIA("Llegada Paciente Emergencia"),
  LLEGADA_PACIENTE_ESPECIALIDAD("Llegada Paciente Especialidad"),
  LLEGADA_PACIENTE_TERAPIA("Llegada Paciente Terapia"),
  FIN_ATENCION_GENERAL("Fin atención general"),
  FIN_ATENCION_EMERGENCIA("Fin atención emergencia"),
  FIN_ATENCION_ESPECIALIDAD("Fin atención especialidad"),
  FIN_ATENCION_TERAPIA("Fin atención terapia"),
  FIN_ATENCION_RECEPCION("Fin atención recepción");

  private String nombre;


  TipoEvento(String nombre) {
    this.nombre = nombre;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public boolean esDeLlegada(){
    TipoEvento tipoEvento = this;
    return (tipoEvento == LLEGADA_PACIENTE_EMERGENCIA ||
            tipoEvento == LLEGADA_PACIENTE_ESPECIALIDAD ||
            tipoEvento == LLEGADA_PACIENTE_GENERAL ||
            tipoEvento == LLEGADA_PACIENTE_TERAPIA);
  }

  public boolean esDeFinAtenciónNormal(){
    TipoEvento tipoEvento = this;
    return (tipoEvento == FIN_ATENCION_EMERGENCIA ||
            tipoEvento == FIN_ATENCION_ESPECIALIDAD ||
            tipoEvento == FIN_ATENCION_GENERAL ||
            tipoEvento == FIN_ATENCION_TERAPIA);
  }

  public boolean esDeRecepcion(){
    TipoEvento tipoEvento = this;
    return tipoEvento == FIN_ATENCION_RECEPCION;
  }

}
