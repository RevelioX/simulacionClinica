package com.example.simuladorclinica.clases;

public class Evento {

  private double tiempo;

  private TipoEvento tipoEvento;

  private Servidor servidor;

  private double tiempoEnfriamentoLlave;

  private double tiempoCreacion;

  public double getTiempoEnfriamentoLlave() {
    return tiempoEnfriamentoLlave;
  }

  public void setTiempoEnfriamentoLlave(double tiempoEnfriamentoLlave) {
    this.tiempoEnfriamentoLlave = tiempoEnfriamentoLlave;
  }

  public Servidor getServidor() {
    return servidor;
  }

  @Override
  public String toString() {
    return "Evento{" +
            "tiempo=" + tiempo +
            ", tipoEvento=" + tipoEvento +
            ", servidor=" + servidor +
            '}';
  }

  public Evento(double tiempo, TipoEvento tipoEvento, double tiempoCreacion) {
    this.tiempo = tiempo;
    this.tipoEvento = tipoEvento;
    this.tiempoCreacion = tiempoCreacion;
  }

  public double getTiempoCreacion() {
    return tiempoCreacion;
  }

  public void setTiempoCreacion(double tiempoCreacion) {
    this.tiempoCreacion = tiempoCreacion;
  }

  public Evento(double tiempo, TipoEvento tipoEvento, Servidor servidor, double tiempoCreacion){
    this.tiempo = tiempo;
    this.tipoEvento = tipoEvento;
    this.servidor = servidor;
    this.tiempoCreacion = tiempoCreacion;
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
