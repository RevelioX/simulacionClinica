package com.example.simuladorclinica;

import com.example.simuladorclinica.clases.Paciente;
import com.example.simuladorclinica.clases.PacienteDTO;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class VectorEstado {
    public String getNroIteracion() {
        return nroIteracion;
    }

    public void setNroIteracion(String nroIteracion) {
        this.nroIteracion = nroIteracion;
    }

    String nroIteracion;
    String evento;
    String reloj;
    String LlegadaGeneral_ProximaLLegada;
    String LlegadaEmergencia_ProximaLlegada;
    String LlegadaEspecialista_ProximaLlegada;
    String LlegadaTerapia_ProximaLlegada;
    List<PacienteDTO> Estado_Espera_Paciente = new ArrayList<>(); // Estado - Espera - TipoAtencion En ese orden
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

    // COSAS ESTADISTICAS //



    String cantidadPacientesAtendidos;
    String acumuladorTiempoEspera;

    String acumuladorTiempoEsperaPacientesEmergencia;

    String acumuladorTiempoOcupadoServidores;

    String tiempoEsperaPromedio;


    String tiempoOcupacionRecepcion;

    String porcentajeOcupacionRecepcion;

    String tiempoOcupacionGeneral;

    String porcentajeOcupacionGeneral;

    String tiempoOcupacionEspecialidad;

    String porcentajeOcupacionEspecialidad;

    String tiempoOcupacionTerapia;

    String porcentajeOcupacionTerapia;

    String tiempoOcupacionEmergencia;

    String porcentajeOcupacionEmergencia;

    String cantidadPacientesAtendidosPorHora;

    String probabilidadLlegadaConServicioLleno;

    public String getProbabilidadLlegadaConServicioLleno() {
        return probabilidadLlegadaConServicioLleno;
    }

    public void setProbabilidadLlegadaConServicioLleno(String probabilidadLlegadaConServicioLleno) {
        this.probabilidadLlegadaConServicioLleno = probabilidadLlegadaConServicioLleno;
    }

    public String getCantidadPacientesAtendidosPorHora() {
        return cantidadPacientesAtendidosPorHora;
    }

    public void setCantidadPacientesAtendidosPorHora(String cantidadPacientesAtendidosPorHora) {
        this.cantidadPacientesAtendidosPorHora = cantidadPacientesAtendidosPorHora;
    }

    public String getAcumuladorEdadesPacientesAtendidos() {
        return acumuladorEdadesPacientesAtendidos;
    }

    public void setAcumuladorEdadesPacientesAtendidos(String acumuladorEdadesPacientesAtendidos) {
        this.acumuladorEdadesPacientesAtendidos = acumuladorEdadesPacientesAtendidos;
    }

    public String getPromedioEdadesPacientesAtendidos() {
        return promedioEdadesPacientesAtendidos;
    }

    public void setPromedioEdadesPacientesAtendidos(String promedioEdadesPacientesAtendidos) {
        this.promedioEdadesPacientesAtendidos = promedioEdadesPacientesAtendidos;
    }

    String acumuladorEdadesPacientesAtendidos;

    String promedioEdadesPacientesAtendidos;

    public String getTiempoEsperaPacientesEmergenciaPromedio() {
        return tiempoEsperaPacientesEmergenciaPromedio;
    }

    public void setTiempoEsperaPacientesEmergenciaPromedio(String tiempoEsperaPacientesEmergenciaPromedio) {
        this.tiempoEsperaPacientesEmergenciaPromedio = tiempoEsperaPacientesEmergenciaPromedio;
    }

    String tiempoEsperaPacientesEmergenciaPromedio;

    //METODOS

    public String getTiempoOcupacionGeneral() {
        return tiempoOcupacionGeneral;
    }

    public void setTiempoOcupacionGeneral(String tiempoOcupacionGeneral) {
        this.tiempoOcupacionGeneral = tiempoOcupacionGeneral;
    }

    public String getPorcentajeOcupacionGeneral() {
        return porcentajeOcupacionGeneral;
    }

    public void setPorcentajeOcupacionGeneral(String porcentajeOcupacionGeneral) {
        this.porcentajeOcupacionGeneral = porcentajeOcupacionGeneral;
    }

    public String getTiempoOcupacionEspecialidad() {
        return tiempoOcupacionEspecialidad;
    }

    public void setTiempoOcupacionEspecialidad(String tiempoOcupacionEspecialidad) {
        this.tiempoOcupacionEspecialidad = tiempoOcupacionEspecialidad;
    }

    public String getPorcentajeOcupacionEspecialidad() {
        return porcentajeOcupacionEspecialidad;
    }

    public void setPorcentajeOcupacionEspecialidad(String porcentajeOcupacionEspecialidad) {
        this.porcentajeOcupacionEspecialidad = porcentajeOcupacionEspecialidad;
    }

    public String getTiempoOcupacionTerapia() {
        return tiempoOcupacionTerapia;
    }

    public void setTiempoOcupacionTerapia(String tiempoOcupacionTerapia) {
        this.tiempoOcupacionTerapia = tiempoOcupacionTerapia;
    }

    public String getPorcentajeOcupacionTerapia() {
        return porcentajeOcupacionTerapia;
    }

    public void setPorcentajeOcupacionTerapia(String porcentajeOcupacionTerapia) {
        this.porcentajeOcupacionTerapia = porcentajeOcupacionTerapia;
    }

    public String getTiempoOcupacionEmergencia() {
        return tiempoOcupacionEmergencia;
    }

    public void setTiempoOcupacionEmergencia(String tiempoOcupacionEmergencia) {
        this.tiempoOcupacionEmergencia = tiempoOcupacionEmergencia;
    }

    public String getPorcentajeOcupacionEmergencia() {
        return porcentajeOcupacionEmergencia;
    }

    public void setPorcentajeOcupacionEmergencia(String porcentajeOcupacionEmergencia) {
        this.porcentajeOcupacionEmergencia = porcentajeOcupacionEmergencia;
    }

    public String getFin_Atencion_Recepcion_1_TiempoFinString() {
        return Fin_Atencion_Recepcion_1_TiempoFinString;
    }

    public void setFin_Atencion_Recepcion_1_TiempoFinString(String fin_Atencion_Recepcion_1_TiempoFinString) {
        Fin_Atencion_Recepcion_1_TiempoFinString = fin_Atencion_Recepcion_1_TiempoFinString;
    }

    public String getCantidadPacientesAtendidos() {
        return cantidadPacientesAtendidos;
    }
    public String getTiempoEsperaPromedio() {
        return tiempoEsperaPromedio;
    }

    public String getTiempoOcupacionRecepcion() {
        return tiempoOcupacionRecepcion;
    }

    public String getPorcentajeOcupacionRecepcion() {
        return porcentajeOcupacionRecepcion;
    }


    public void setTiempoEsperaPromedio(String tiempoEsperaPromedio) {
        this.tiempoEsperaPromedio = tiempoEsperaPromedio;
    }

    public void setTiempoOcupacionRecepcion(String tiempoOcupacionRecepcion) {
        this.tiempoOcupacionRecepcion = tiempoOcupacionRecepcion;
    }

    public void setPorcentajeOcupacionRecepcion(String porcentajeOcupacionRecepcion) {
        this.porcentajeOcupacionRecepcion = porcentajeOcupacionRecepcion;
    }

    public void setCantidadPacientesAtendidos(String cantidadPacientesAtendidos) {
        this.cantidadPacientesAtendidos = cantidadPacientesAtendidos;
    }

    public String getAcumuladorTiempoEspera() {
        return acumuladorTiempoEspera;
    }

    public void setAcumuladorTiempoEspera(String acumuladorTiempoEspera) {
        this.acumuladorTiempoEspera = acumuladorTiempoEspera;
    }
    public String getAcumuladorTiempoOcupadoServidores() {
        return acumuladorTiempoOcupadoServidores;
    }

    public void setAcumuladorTiempoOcupadoServidores(String acumuladorTiempoOcupadoServidores) {
        this.acumuladorTiempoOcupadoServidores = acumuladorTiempoOcupadoServidores;
    }

    public String getAcumuladorTiempoEsperaPacientesEmergencia() {
        return acumuladorTiempoEsperaPacientesEmergencia;
    }

    public void setAcumuladorTiempoEsperaPacientesEmergencia(String acumuladorTiempoEsperaPacientesEmergencia) {
        this.acumuladorTiempoEsperaPacientesEmergencia = acumuladorTiempoEsperaPacientesEmergencia;
    }


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

    public List<PacienteDTO> getEstado_Espera_Paciente() {
        return Estado_Espera_Paciente;
    }

    public void setEstado_Espera_Paciente(List<PacienteDTO> estado_Espera_Paciente) {
        Estado_Espera_Paciente = estado_Espera_Paciente;
    }

    public void addEstado_Espera_Paciente(Paciente paciente){
        Estado_Espera_Paciente.add(new PacienteDTO(String.valueOf(paciente.getId()), paciente.getTipoAtencion().getNombre(), String.valueOf(paciente.getTiempoEspera()), paciente.getEstado()));
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
