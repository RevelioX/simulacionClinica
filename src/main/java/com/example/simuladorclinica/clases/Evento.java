package com.example.simuladorclinica.clases;

public class Evento {

  private double tiempo;

  private TipoEvento tipoEvento;

  public Evento(double tiempo, TipoEvento tipoEvento) {
    this.tiempo = tiempo;
    this.tipoEvento = tipoEvento;
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
