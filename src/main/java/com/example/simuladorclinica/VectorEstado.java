package com.example.simuladorclinica;

import com.example.simuladorclinica.clases.Paciente;

import java.util.List;

public class VectorEstado {
    String evento;
    String reloj;
    String LlegadaGeneral_ProximaLLegada;
    String LlegadaEmergencia_ProximaLlegada;
    String LlegadaEspecialista_ProximaLlegada;
    String LlegadaTerapia_ProximaLlegada;
    List<String> Estado_Espera_Paciente; // Estado - Espera - TipoAtencion En ese orden
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
    //--------------------------------------------//
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





    //todo - Aca faltaría lo relacionado a los eventos fin_atención pero hay q definirlo con Colo.


}
