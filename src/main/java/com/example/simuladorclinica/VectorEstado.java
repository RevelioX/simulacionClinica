package com.example.simuladorclinica;

import com.example.simuladorclinica.clases.Paciente;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class VectorEstado {
    String evento;
    String reloj;
    String LlegadaGeneral_ProximaLLegada;
    String LlegadaEmergencia_ProximaLlegada;
    String LlegadaEspecialista_ProximaLlegada;
    String LlegadaTerapia_ProximaLlegada;
    List<String> Estado_Espera_Paciente = new ArrayList<>(); // Estado - Espera - TipoAtencion En ese orden
    //-----------------------------------------------//
    String Fin_Atencion_General_1_TiempoFin;
    String Fin_Atencion_General_2_TiempoFin;
    String Fin_Atencion_General_3_TiempoFin;
    //--------------------------------------------//
    String Fin_Atencion_Emergencia_1_TiempoFin;
    String Fin_Atencion_Emergencia_2_TiempoFin;
    //--------------------------------------------//
    String Fin_Atencion_Especialista_1_TiempoFin;
    String Fin_Atencion_Especialista_2_TiempoFin;
    String Fin_Atencion_Especialista_3_TiempoFin;
    String Fin_Atencion_Especialista_4_TiempoFin;
    //--------------------------------------------//
    String Fin_Atencion_Terapia_Fisica_1_TiempoFin;
    String Fin_Atencion_Terapia_Fisica_2_TiempoFin;

    public String getFin_Atencion_Recepcion_1_TiempoFin() {
        return Fin_Atencion_Recepcion_1_TiempoFinString;
    }

    public void setFin_Atencion_Recepcion_1_TiempoFin(String fin_Atencion_Recepcion_1_TiempoFinString) {
        Fin_Atencion_Recepcion_1_TiempoFinString = fin_Atencion_Recepcion_1_TiempoFinString;
    }

    //--------------------------------------------//
    String Fin_Atencion_Recepcion_1_TiempoFinString;
    String Resultado_Recepcion;
    //--------------------------------------------//
    String Estado_Medico_General_1;
    String Cola_Medico_General_1;
    String Estado_Medico_General_2;
    String Cola_Medico_General_2;
    String Estado_Medico_General_3;
    String Cola_Medico_General_3;
    //--------------------------------------------//
    String Estado_Medico_Emergencia_1;
    String Cola_Medico_Emergencia_1;
    String Estado_Medico_Emergencia_2;
    String Cola_Medico_Emergencia_2;
    //--------------------------------------------//
    String Estado_Medico_Especialista_1;
    String Cola_Medico_Especialista_1;
    String Estado_Medico_Especialista_2;
    String Cola_Medico_Especialista_2;
    String Estado_Medico_Especialista_3;
    String Cola_Medico_Especialista_3;
    String Estado_Medico_Especialista_4;
    String Cola_Medico_Especialista_4;
    //--------------------------------------------//
    String Estado_Medico_Fisico_1;
    String Cola_Medico_Fisico_1;
    String Estado_Medico_Fisico_2;
    String Cola_Medico_Fisico_2;

    //--------------------------------------------//
    String Estado_Recepcion;
    String Cola_Recepcion;

    public String getEstado_Recepcion() {
        return Estado_Recepcion;
    }

    public void setEstado_Recepcion(String estado_Recepcion) {
        Estado_Recepcion = estado_Recepcion;
    }

    public String getCola_Recepcion() {
        return Cola_Recepcion;
    }

    public void setCola_Recepcion(String cola_Recepcion) {
        Cola_Recepcion = cola_Recepcion;
    }

    public VectorEstado(){

    }

    public String getEvento() {
        return evento;
    }

    public void setEvento(String evento) {
        this.evento = evento;
    }

    public String getReloj() {
        return reloj;
    }

    public void setReloj(String reloj) {
        this.reloj = reloj;
    }

    public String getLlegadaGeneral_ProximaLLegada() {
        return LlegadaGeneral_ProximaLLegada;
    }

    public void setLlegadaGeneral_ProximaLLegada(String llegadaGeneral_ProximaLLegada) {
        LlegadaGeneral_ProximaLLegada = llegadaGeneral_ProximaLLegada;
    }

    public String getLlegadaEmergencia_ProximaLlegada() {
        return LlegadaEmergencia_ProximaLlegada;
    }

    public void setLlegadaEmergencia_ProximaLlegada(String llegadaEmergencia_ProximaLlegada) {
        LlegadaEmergencia_ProximaLlegada = llegadaEmergencia_ProximaLlegada;
    }

    public String getLlegadaEspecialista_ProximaLlegada() {
        return LlegadaEspecialista_ProximaLlegada;
    }

    public void setLlegadaEspecialista_ProximaLlegada(String llegadaEspecialista_ProximaLlegada) {
        LlegadaEspecialista_ProximaLlegada = llegadaEspecialista_ProximaLlegada;
    }

    public String getLlegadaTerapia_ProximaLlegada() {
        return LlegadaTerapia_ProximaLlegada;
    }

    public void setLlegadaTerapia_ProximaLlegada(String llegadaTerapia_ProximaLlegada) {
        LlegadaTerapia_ProximaLlegada = llegadaTerapia_ProximaLlegada;
    }

    public List<String> getEstado_Espera_Paciente() {
        return Estado_Espera_Paciente;
    }

    public void setEstado_Espera_Paciente(List<String> estado_Espera_Paciente) {
        Estado_Espera_Paciente = estado_Espera_Paciente;
    }

    public void addEstado_Espera_Paciente(String estado, String espera, String paciente){
        Estado_Espera_Paciente.add(estado);
        Estado_Espera_Paciente.add(espera);
        Estado_Espera_Paciente.add(paciente);
    }

    public String getFin_Atencion_General_1_TiempoFin() {
        return Fin_Atencion_General_1_TiempoFin;
    }

    public void setFin_Atencion_General_1_TiempoFin(String fin_Atencion_General_1_TiempoFin) {
        Fin_Atencion_General_1_TiempoFin = fin_Atencion_General_1_TiempoFin;
    }

    public String getFin_Atencion_General_2_TiempoFin() {
        return Fin_Atencion_General_2_TiempoFin;
    }

    public void setFin_Atencion_General_2_TiempoFin(String fin_Atencion_General_2_TiempoFin) {
        Fin_Atencion_General_2_TiempoFin = fin_Atencion_General_2_TiempoFin;
    }

    public String getFin_Atencion_General_3_TiempoFin() {
        return Fin_Atencion_General_3_TiempoFin;
    }

    public void setFin_Atencion_General_3_TiempoFin(String fin_Atencion_General_3_TiempoFin) {
        Fin_Atencion_General_3_TiempoFin = fin_Atencion_General_3_TiempoFin;
    }

    public String getFin_Atencion_Emergencia_1_TiempoFin() {
        return Fin_Atencion_Emergencia_1_TiempoFin;
    }

    public void setFin_Atencion_Emergencia_1_TiempoFin(String fin_Atencion_Emergencia_1_TiempoFin) {
        Fin_Atencion_Emergencia_1_TiempoFin = fin_Atencion_Emergencia_1_TiempoFin;
    }

    public String getFin_Atencion_Emergencia_2_TiempoFin() {
        return Fin_Atencion_Emergencia_2_TiempoFin;
    }

    public void setFin_Atencion_Emergencia_2_TiempoFin(String fin_Atencion_Emergencia_2_TiempoFin) {
        Fin_Atencion_Emergencia_2_TiempoFin = fin_Atencion_Emergencia_2_TiempoFin;
    }

    public String getFin_Atencion_Especialista_1_TiempoFin() {
        return Fin_Atencion_Especialista_1_TiempoFin;
    }

    public void setFin_Atencion_Especialista_1_TiempoFin(String fin_Atencion_Especialista_1_TiempoFin) {
        Fin_Atencion_Especialista_1_TiempoFin = fin_Atencion_Especialista_1_TiempoFin;
    }

    public String getFin_Atencion_Especialista_2_TiempoFin() {
        return Fin_Atencion_Especialista_2_TiempoFin;
    }

    public void setFin_Atencion_Especialista_2_TiempoFin(String fin_Atencion_Especialista_2_TiempoFin) {
        Fin_Atencion_Especialista_2_TiempoFin = fin_Atencion_Especialista_2_TiempoFin;
    }

    public String getFin_Atencion_Especialista_3_TiempoFin() {
        return Fin_Atencion_Especialista_3_TiempoFin;
    }

    public void setFin_Atencion_Especialista_3_TiempoFin(String fin_Atencion_Especialista_3_TiempoFin) {
        Fin_Atencion_Especialista_3_TiempoFin = fin_Atencion_Especialista_3_TiempoFin;
    }

    public String getFin_Atencion_Especialista_4_TiempoFin() {
        return Fin_Atencion_Especialista_4_TiempoFin;
    }

    public void setFin_Atencion_Especialista_4_TiempoFin(String fin_Atencion_Especialista_4_TiempoFin) {
        Fin_Atencion_Especialista_4_TiempoFin = fin_Atencion_Especialista_4_TiempoFin;
    }

    public String getFin_Atencion_Terapia_Fisica_1_TiempoFin() {
        return Fin_Atencion_Terapia_Fisica_1_TiempoFin;
    }

    public void setFin_Atencion_Terapia_Fisica_1_TiempoFin(String fin_Atencion_Terapia_Fisica_1_TiempoFin) {
        Fin_Atencion_Terapia_Fisica_1_TiempoFin = fin_Atencion_Terapia_Fisica_1_TiempoFin;
    }

    public String getFin_Atencion_Terapia_Fisica_2_TiempoFin() {
        return Fin_Atencion_Terapia_Fisica_2_TiempoFin;
    }

    public void setFin_Atencion_Terapia_Fisica_2_TiempoFin(String fin_Atencion_Terapia_Fisica_2_TiempoFin) {
        Fin_Atencion_Terapia_Fisica_2_TiempoFin = fin_Atencion_Terapia_Fisica_2_TiempoFin;
    }

    public String getResultado_Recepcion() {
        return Resultado_Recepcion;
    }

    public void setResultado_Recepcion(String resultado_Recepcion) {
        Resultado_Recepcion = resultado_Recepcion;
    }

    public String getEstado_Medico_General_1() {
        return Estado_Medico_General_1;
    }

    public void setEstado_Medico_General_1(String estado_Medico_General_1) {
        Estado_Medico_General_1 = estado_Medico_General_1;
    }

    public String getCola_Medico_General_1() {
        return Cola_Medico_General_1;
    }

    public void setCola_Medico_General_1(String cola_Medico_General_1) {
        Cola_Medico_General_1 = cola_Medico_General_1;
    }

    public String getEstado_Medico_General_2() {
        return Estado_Medico_General_2;
    }

    public void setEstado_Medico_General_2(String estado_Medico_General_2) {
        Estado_Medico_General_2 = estado_Medico_General_2;
    }

    public String getCola_Medico_General_2() {
        return Cola_Medico_General_2;
    }

    public void setCola_Medico_General_2(String cola_Medico_General_2) {
        Cola_Medico_General_2 = cola_Medico_General_2;
    }

    public String getEstado_Medico_General_3() {
        return Estado_Medico_General_3;
    }

    public void setEstado_Medico_General_3(String estado_Medico_General_3) {
        Estado_Medico_General_3 = estado_Medico_General_3;
    }

    public String getCola_Medico_General_3() {
        return Cola_Medico_General_3;
    }

    public void setCola_Medico_General_3(String cola_Medico_General_3) {
        Cola_Medico_General_3 = cola_Medico_General_3;
    }

    public String getEstado_Medico_Emergencia_1() {
        return Estado_Medico_Emergencia_1;
    }

    public void setEstado_Medico_Emergencia_1(String estado_Medico_Emergencia_1) {
        Estado_Medico_Emergencia_1 = estado_Medico_Emergencia_1;
    }

    public String getCola_Medico_Emergencia_1() {
        return Cola_Medico_Emergencia_1;
    }

    public void setCola_Medico_Emergencia_1(String cola_Medico_Emergencia_1) {
        Cola_Medico_Emergencia_1 = cola_Medico_Emergencia_1;
    }

    public String getEstado_Medico_Emergencia_2() {
        return Estado_Medico_Emergencia_2;
    }

    public void setEstado_Medico_Emergencia_2(String estado_Medico_Emergencia_2) {
        Estado_Medico_Emergencia_2 = estado_Medico_Emergencia_2;
    }

    public String getCola_Medico_Emergencia_2() {
        return Cola_Medico_Emergencia_2;
    }

    public void setCola_Medico_Emergencia_2(String cola_Medico_Emergencia_2) {
        Cola_Medico_Emergencia_2 = cola_Medico_Emergencia_2;
    }

    public String getEstado_Medico_Especialista_1() {
        return Estado_Medico_Especialista_1;
    }

    public void setEstado_Medico_Especialista_1(String estado_Medico_Especialista_1) {
        Estado_Medico_Especialista_1 = estado_Medico_Especialista_1;
    }

    public String getCola_Medico_Especialista_1() {
        return Cola_Medico_Especialista_1;
    }

    public void setCola_Medico_Especialista_1(String cola_Medico_Especialista_1) {
        Cola_Medico_Especialista_1 = cola_Medico_Especialista_1;
    }

    public String getEstado_Medico_Especialista_2() {
        return Estado_Medico_Especialista_2;
    }

    public void setEstado_Medico_Especialista_2(String estado_Medico_Especialista_2) {
        Estado_Medico_Especialista_2 = estado_Medico_Especialista_2;
    }

    public String getCola_Medico_Especialista_2() {
        return Cola_Medico_Especialista_2;
    }

    public void setCola_Medico_Especialista_2(String cola_Medico_Especialista_2) {
        Cola_Medico_Especialista_2 = cola_Medico_Especialista_2;
    }

    public String getEstado_Medico_Especialista_3() {
        return Estado_Medico_Especialista_3;
    }

    public void setEstado_Medico_Especialista_3(String estado_Medico_Especialista_3) {
        Estado_Medico_Especialista_3 = estado_Medico_Especialista_3;
    }

    public String getCola_Medico_Especialista_3() {
        return Cola_Medico_Especialista_3;
    }

    public void setCola_Medico_Especialista_3(String cola_Medico_Especialista_3) {
        Cola_Medico_Especialista_3 = cola_Medico_Especialista_3;
    }

    public String getEstado_Medico_Especialista_4() {
        return Estado_Medico_Especialista_4;
    }

    public void setEstado_Medico_Especialista_4(String estado_Medico_Especialista_4) {
        Estado_Medico_Especialista_4 = estado_Medico_Especialista_4;
    }

    public String getCola_Medico_Especialista_4() {
        return Cola_Medico_Especialista_4;
    }

    public void setCola_Medico_Especialista_4(String cola_Medico_Especialista_4) {
        Cola_Medico_Especialista_4 = cola_Medico_Especialista_4;
    }

    public String getEstado_Medico_Fisico_1() {
        return Estado_Medico_Fisico_1;
    }

    public void setEstado_Medico_Fisico_1(String estado_Medico_Fisico_1) {
        Estado_Medico_Fisico_1 = estado_Medico_Fisico_1;
    }

    public String getCola_Medico_Fisico_1() {
        return Cola_Medico_Fisico_1;
    }

    public void setCola_Medico_Fisico_1(String cola_Medico_Fisico_1) {
        Cola_Medico_Fisico_1 = cola_Medico_Fisico_1;
    }

    public String getEstado_Medico_Fisico_2() {
        return Estado_Medico_Fisico_2;
    }

    public void setEstado_Medico_Fisico_2(String estado_Medico_Fisico_2) {
        Estado_Medico_Fisico_2 = estado_Medico_Fisico_2;
    }

    @Override
    public String toString() {
        return "VectorEstado{" +
                "evento='" + evento + '\'' +
                ", reloj='" + reloj + '\'' +
                ", LlegadaGeneral_ProximaLLegada='" + LlegadaGeneral_ProximaLLegada + '\'' +
                ", LlegadaEmergencia_ProximaLlegada='" + LlegadaEmergencia_ProximaLlegada + '\'' +
                ", LlegadaEspecialista_ProximaLlegada='" + LlegadaEspecialista_ProximaLlegada + '\'' +
                ", LlegadaTerapia_ProximaLlegada='" + LlegadaTerapia_ProximaLlegada + '\'' +
                ", Estado_Espera_Paciente=" + Estado_Espera_Paciente +
                ", Fin_Atencion_General_1_TiempoFin='" + Fin_Atencion_General_1_TiempoFin + '\'' +
                ", Fin_Atencion_General_2_TiempoFin='" + Fin_Atencion_General_2_TiempoFin + '\'' +
                ", Fin_Atencion_General_3_TiempoFin='" + Fin_Atencion_General_3_TiempoFin + '\'' +
                ", Fin_Atencion_Emergencia_1_TiempoFin='" + Fin_Atencion_Emergencia_1_TiempoFin + '\'' +
                ", Fin_Atencion_Emergencia_2_TiempoFin='" + Fin_Atencion_Emergencia_2_TiempoFin + '\'' +
                ", Fin_Atencion_Especialista_1_TiempoFin='" + Fin_Atencion_Especialista_1_TiempoFin + '\'' +
                ", Fin_Atencion_Especialista_2_TiempoFin='" + Fin_Atencion_Especialista_2_TiempoFin + '\'' +
                ", Fin_Atencion_Especialista_3_TiempoFin='" + Fin_Atencion_Especialista_3_TiempoFin + '\'' +
                ", Fin_Atencion_Especialista_4_TiempoFin='" + Fin_Atencion_Especialista_4_TiempoFin + '\'' +
                ", Fin_Atencion_Terapia_Fisica_1_TiempoFin='" + Fin_Atencion_Terapia_Fisica_1_TiempoFin + '\'' +
                ", Fin_Atencion_Terapia_Fisica_2_TiempoFin='" + Fin_Atencion_Terapia_Fisica_2_TiempoFin + '\'' +
                ", Resultado_Recepcion='" + Resultado_Recepcion + '\'' +
                ", Estado_Medico_General_1='" + Estado_Medico_General_1 + '\'' +
                ", Cola_Medico_General_1='" + Cola_Medico_General_1 + '\'' +
                ", Estado_Medico_General_2='" + Estado_Medico_General_2 + '\'' +
                ", Cola_Medico_General_2='" + Cola_Medico_General_2 + '\'' +
                ", Estado_Medico_General_3='" + Estado_Medico_General_3 + '\'' +
                ", Cola_Medico_General_3='" + Cola_Medico_General_3 + '\'' +
                ", Estado_Medico_Emergencia_1='" + Estado_Medico_Emergencia_1 + '\'' +
                ", Cola_Medico_Emergencia_1='" + Cola_Medico_Emergencia_1 + '\'' +
                ", Estado_Medico_Emergencia_2='" + Estado_Medico_Emergencia_2 + '\'' +
                ", Cola_Medico_Emergencia_2='" + Cola_Medico_Emergencia_2 + '\'' +
                ", Estado_Medico_Especialista_1='" + Estado_Medico_Especialista_1 + '\'' +
                ", Cola_Medico_Especialista_1='" + Cola_Medico_Especialista_1 + '\'' +
                ", Estado_Medico_Especialista_2='" + Estado_Medico_Especialista_2 + '\'' +
                ", Cola_Medico_Especialista_2='" + Cola_Medico_Especialista_2 + '\'' +
                ", Estado_Medico_Especialista_3='" + Estado_Medico_Especialista_3 + '\'' +
                ", Cola_Medico_Especialista_3='" + Cola_Medico_Especialista_3 + '\'' +
                ", Estado_Medico_Especialista_4='" + Estado_Medico_Especialista_4 + '\'' +
                ", Cola_Medico_Especialista_4='" + Cola_Medico_Especialista_4 + '\'' +
                ", Estado_Medico_Fisico_1='" + Estado_Medico_Fisico_1 + '\'' +
                ", Cola_Medico_Fisico_1='" + Cola_Medico_Fisico_1 + '\'' +
                ", Estado_Medico_Fisico_2='" + Estado_Medico_Fisico_2 + '\'' +
                ", Cola_Medico_Fisico_2='" + Cola_Medico_Fisico_2 + '\'' +
                ", Estado_Recepcion='" + Estado_Recepcion + '\'' +
                ", Cola_Recepcion='" + Cola_Recepcion + '\'' +
                '}';
    }

    public String getCola_Medico_Fisico_2() {
        return Cola_Medico_Fisico_2;
    }

    public void setCola_Medico_Fisico_2(String cola_Medico_Fisico_2) {
        Cola_Medico_Fisico_2 = cola_Medico_Fisico_2;
    }


    //todo - Aca faltaría lo relacionado a los eventos fin_atención pero hay q definirlo con Colo.


}
