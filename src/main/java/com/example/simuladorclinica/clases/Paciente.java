package com.example.simuladorclinica.clases;

public class Paciente {

    private int edad;
    private Estado estado;

    private Double tiempoEspera = 0d;

    private TipoAtencion tipoAtencion;


    public Paciente(TipoAtencion tipoAtencion) {
        this.tipoAtencion = tipoAtencion;
    }

    public String getEstado(){
        return estado.getNombre();
    }

    public void setEstado(Estado e){
      estado.setNombre(e.getNombre());
    }

    public void setTipoAtencion(TipoAtencion atencion){
      tipoAtencion.setNombre(atencion.getNombre());
    }

    public void aumentarTiempoEspera(Double tiempo){
       this.tiempoEspera += tiempo;
    }

    public Double getTiempoEspera (){
        return tiempoEspera;
    }


    public void setEdad(int edad) {
        this.edad = edad;
    }

    public Double getTiempoEspera (){
        return tiempoEspera;
    }
}
