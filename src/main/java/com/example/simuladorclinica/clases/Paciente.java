package com.example.simuladorclinica.clases;

public class Paciente {

    private static int idPacientes = 0;

    private int edad;
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private Estado estado;

    private Double tiempoEspera = 0d;

    private TipoAtencion tipoAtencion;


    public Paciente(TipoAtencion tipoAtencion) {
        this.tipoAtencion = tipoAtencion;
        idPacientes = idPacientes + 1;
        this.id = idPacientes;
    }

    public String getEstado(){
        return estado.getNombre();
    }

    public void setEstado(Estado e){
      estado = e;
    }

    public void setTipoAtencion(TipoAtencion atencion){
      tipoAtencion.setNombre(atencion.getNombre());
    }

    public void aumentarTiempoEspera(Double tiempo){
       this.tiempoEspera += tiempo;
    }

    public TipoAtencion getTipoAtencion() {
        return tipoAtencion;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public Double getTiempoEspera (){
        return tiempoEspera;
    }
}
