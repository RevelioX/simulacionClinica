package com.example.simuladorclinica.clases;

public enum TipoEvento {
  LLEGADA_PACIENTE_GENERAL("Llegada Paciente General", TipoAtencion.General),
  LLEGADA_PACIENTE_EMERGENCIA("Llegada Paciente Emergencia", TipoAtencion.Emergencia),
  LLEGADA_PACIENTE_ESPECIALIDAD("Llegada Paciente Especialidad", TipoAtencion.Especialista),
  LLEGADA_PACIENTE_TERAPIA("Llegada Paciente Terapia", TipoAtencion.Terapia),
  FIN_ATENCION_GENERAL("Fin atención general", TipoAtencion.General),
  FIN_ATENCION_EMERGENCIA("Fin atención emergencia", TipoAtencion.Emergencia),
  FIN_ATENCION_ESPECIALIDAD("Fin atención especialidad", TipoAtencion.Especialista),
  FIN_ATENCION_TERAPIA("Fin atención terapia", TipoAtencion.Terapia),
  FIN_ATENCION_RECEPCION("Fin atención recepción", TipoAtencion.Recepcion);

  private String nombre;

  private TipoAtencion tipoAtencion;


  TipoEvento(String nombre, TipoAtencion tipoAtencion)
  {
    this.nombre = nombre;
    this.tipoAtencion = tipoAtencion;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public TipoAtencion getTipoAtencion(){
    return this.tipoAtencion;
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

  public boolean esDeFinAtencion(){
    TipoEvento tipoEvento = this;
    return (tipoEvento == FIN_ATENCION_EMERGENCIA ||
            tipoEvento == FIN_ATENCION_ESPECIALIDAD ||
            tipoEvento == FIN_ATENCION_GENERAL ||
            tipoEvento == FIN_ATENCION_TERAPIA ||
            tipoEvento == FIN_ATENCION_RECEPCION);
  }

  public boolean esDeRecepcion(){
    TipoEvento tipoEvento = this;
    return tipoEvento == FIN_ATENCION_RECEPCION;
  }

}
