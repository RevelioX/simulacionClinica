package com.example.simuladorclinica.clases;

import com.example.simuladorclinica.generators.Generador;
import com.example.simuladorclinica.generators.GeneradorNumerosExponencial;
import com.example.simuladorclinica.generators.GeneradorNumerosNormales;
import com.example.simuladorclinica.generators.GeneradorNumerosUniformes;

public class Paciente {

    private static int idPacientes = 0;

    private static Generador generadorEdadPacienteGeneralTerapia = new GeneradorNumerosUniformes(10,70);

    private static Generador generadorEdadPacientesEspecialista = new GeneradorNumerosNormales(40,5);

    private static Generador generadorEdadPacientesEmergencia = new GeneradorNumerosNormales(80, 10);

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

        if(tipoAtencion == TipoAtencion.Emergencia){
            this.edad = (int) generadorEdadPacientesEmergencia.getValor();
        }else if(tipoAtencion == TipoAtencion.Especialista){
            this.edad = (int) generadorEdadPacientesEspecialista.getValor();
        }else {
            this.edad = (int) generadorEdadPacienteGeneralTerapia.getValor();
        }
    }

    public int getEdad() {
        return edad;
    }

    public void setTiempoEspera(Double tiempoEspera) {
        this.tiempoEspera = tiempoEspera;
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
