package com.example.simuladorclinica.clases;

public class Evento {

  private double tiempo;

  private TipoEvento tipoEvento;

  private Servidor servidor;

  public Servidor getServidor() {
    return servidor;
  }

  public Evento(double tiempo, TipoEvento tipoEvento) {
    this.tiempo = tiempo;
    this.tipoEvento = tipoEvento;
  }

  public Evento(double tiempo, TipoEvento tipoEvento, Servidor servidor){
    this.tiempo = tiempo;
    this.tipoEvento = tipoEvento;
    this.servidor = servidor;
  }

  public double getTiempo() {
    return tiempo;
  }

  public void setTiempo(double tiempo) {
    this.tiempo = tiempo;
  }

  public TipoEvento getTipoEvento() {
    return tipoEvento;
  }

  public void setTipoEvento(TipoEvento tipoEvento) {
    this.tipoEvento = tipoEvento;
  }
}
