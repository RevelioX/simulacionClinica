package com.example.simuladorclinica.logic;

import com.example.simuladorclinica.clases.Evento;
import com.example.simuladorclinica.clases.Servidor;
import com.example.simuladorclinica.clases.TipoAtencion;
import com.example.simuladorclinica.clases.TipoEvento;
import com.example.simuladorclinica.generators.Generador;
import com.example.simuladorclinica.generators.GeneradorNumerosExponencial;
import com.example.simuladorclinica.generators.GeneradorNumerosNormales;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class Controller {
    private List<String> vectorSimulacion = new ArrayList<>();
    private PriorityQueue<Evento> eventos;
    private double reloj;
    private double tiempoDesdeAnteriorEvento;
    List<Servidor> servidores;
    private Generador generadorLlegadasGeneral;
    private Generador generadorLlegadasEmergencia;
    private Generador generadorLlegadasEspecialista;
    private Generador generadorLlegadaTerapia;
    private Generador generadorFinAtencionGeneral;
    private Generador generadorFinAtencionEmergencia;
    private Generador generadorFinAtencionEspecialista;
    private Generador generadorFinAtencionTerapia;
    private int lineasSimular;

    private int desdeDondeMostrar;

    boolean seDebeMostrar;

    public prepararSimulacion(int lineasSimular, int desdeDondeMostrar, double mediaLlegadaGeneral, double mediaLlegadaEmergencia, double mediaLlegadaEspecialista, double mediaLlegadaTerapia, double mediaAtencionGeneral, double mediaAtencionEmergencia, double mediaAtencionEspecialidad, double mediaAtencionTerapia){
        this.desdeDondeMostrar = desdeDondeMostrar;
        this.lineasSimular = lineasSimular;


        instanciarGeneradoresLlegadas(mediaLlegadaGeneral, mediaLlegadaEmergencia,mediaLlegadaEspecialista, mediaLlegadaTerapia);
        instanciarGeneradoresAtencion(mediaAtencionGeneral, mediaAtencionEmergencia,mediaAtencionEspecialidad,mediaAtencionTerapia);
        instanciarServidores();
        generarPrimerosEventos();
    }


    private void prepararColaEventos(){
        Comparator<Evento> comparator = Comparator.comparing(Evento::getTiempo);
        eventos = new PriorityQueue<Evento>(comparator);
    }

    private void instanciarGeneradoresLlegadas( double mediaLlegadaGeneral, double mediaLlegadaEmergencia, double mediaLlegadaEspecialista, double mediaLlegadaTerapia){
        generadorLlegadasGeneral = new GeneradorNumerosExponencial(mediaLlegadaGeneral);
        generadorLlegadasGeneral.generarValor(1000);

        generadorLlegadasEmergencia = new GeneradorNumerosExponencial(mediaLlegadaEmergencia);
        generadorLlegadasEmergencia.generarValor(1000);

        generadorLlegadasEspecialista = new GeneradorNumerosExponencial(mediaLlegadaEspecialista);
        generadorLlegadasEspecialista.generarValor(1000);

        generadorLlegadaTerapia = new GeneradorNumerosExponencial(mediaLlegadaTerapia);
        generadorLlegadaTerapia.generarValor(1000);
    }

    private void instanciarGeneradoresAtencion(double mediaAtencionGeneral, double mediaAtencionEmergencia, double mediaAtencionEspecialidad, double mediaAtencionTerapia){
        generadorFinAtencionGeneral = new GeneradorNumerosExponencial(mediaAtencionGeneral);
        generadorFinAtencionGeneral.generarValor(1000);

        generadorFinAtencionEmergencia = new GeneradorNumerosExponencial(mediaAtencionEmergencia);
        generadorFinAtencionEmergencia.generarValor(1000);

        generadorFinAtencionEspecialista = new GeneradorNumerosExponencial(mediaAtencionEspecialidad);
        generadorFinAtencionEspecialista.generarValor(1000);

        generadorFinAtencionTerapia = new GeneradorNumerosExponencial(mediaAtencionTerapia);
        generadorFinAtencionTerapia.generarValor(1000);

    }

    private void instanciarServidores(){
        Servidor general1 = new Servidor(TipoAtencion.General);
        Servidor general2 = new Servidor(TipoAtencion.General);
        Servidor general3 = new Servidor(TipoAtencion.General);

        servidores.add(general1);
        servidores.add(general2);
        servidores.add(general3);

        Servidor emergencias1 = new Servidor(TipoAtencion.Emergencia);
        Servidor emergencias2 = new Servidor(TipoAtencion.Emergencia);

        servidores.add(emergencias1);
        servidores.add(emergencias2);

        Servidor especialista1 = new Servidor(TipoAtencion.Especialista);
        Servidor especialista2 = new Servidor(TipoAtencion.Especialista);
        Servidor especialista3 = new Servidor(TipoAtencion.Especialista);
        Servidor especialista4 = new Servidor(TipoAtencion.Especialista);

        servidores.add(especialista1);
        servidores.add(especialista2);
        servidores.add(especialista3);
        servidores.add(especialista4);

        Servidor terapia1 = new Servidor(TipoAtencion.Terapia);
        Servidor terapia2 = new Servidor(TipoAtencion.Terapia);

        servidores.add(terapia1);
        servidores.add(terapia2);


    }
    private void generarPrimerosEventos(){
        double tiempo;
        tiempo = generadorLlegadasGeneral.getValor();
        Evento eventoLlegadaGeneral = new Evento(tiempo, TipoEvento.LLEGADA_PACIENTE_GENERAL);

        tiempo = generadorLlegadasEmergencia.getValor();
        Evento eventoLlegadaEmergencia = new Evento(tiempo, TipoEvento.LLEGADA_PACIENTE_EMERGENCIA);

        tiempo = generadorLlegadasEspecialista.getValor();
        Evento eventoLlegadaEspecialista = new Evento(tiempo, TipoEvento.LLEGADA_PACIENTE_ESPECIALIDAD);

        tiempo = generadorLlegadaTerapia.getValor();
        Evento eventoLlegadaTerapia = new Evento(tiempo, TipoEvento.LLEGADA_PACIENTE_TERAPIA);

        eventos.add(eventoLlegadaGeneral);
        eventos.add(eventoLlegadaEspecialista);
        eventos.add(eventoLlegadaEmergencia);
        eventos.add(eventoLlegadaTerapia);
    }
    public simular(){
        for(int i = 0; i < lineasSimular; i++){
            if (i > desdeDondeMostrar && i < desdeDondeMostrar + 300){
                seDebeMostrar = true;
            }else{
                seDebeMostrar = false;
            }
            Evento eventoAResolver = eventos.remove();
            reloj = eventoAResolver.getTiempo();
            resolverEvento(eventoAResolver);
            //todo - Falta por añadir logica

        }

    }

    private resolverEvento(Evento evento){
        TipoEvento tipoEvento = evento.getTipoEvento();
        if(tipoEvento.esDeLlegada()){
            resolverEventoLlegada(evento);
        } else if(tipoEvento.esDeFinAtenciónNormal()){
            resolverFinAtencion(evento);
        }else if(tipoEvento.esDeRecepcion()){
            resolverFinAtencionRecepcion(evento);
        }
    }

    private resolverEventoLlegada(Evento evento){
        if(seDebeMostrar)
    }
}
