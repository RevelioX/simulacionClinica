package com.example.simuladorclinica.clases;

public class PacienteDTO {
    private String tipoAtencion;

    private String estado;

    private String id;

    public String getTipoAtencion() {
        return tipoAtencion;
    }

    public void setTipoAtencion(String tipoAtencion) {
        this.tipoAtencion = tipoAtencion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTiempoEspera() {
        return tiempoEspera;
    }

    public void setTiempoEspera(String tiempoEspera) {
        this.tiempoEspera = tiempoEspera;
    }

    private String tiempoEspera;

    public PacienteDTO(String id, String tipoAtencion, String tiempoEspera, String estado){
        this.id = id;
        this.tipoAtencion = tipoAtencion;
        this.tiempoEspera = tiempoEspera;
        this.estado = estado;
    }
}
